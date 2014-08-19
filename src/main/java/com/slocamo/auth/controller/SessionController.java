package com.slocamo.auth.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.slocamo.auth.bean.entity.Session;
import com.slocamo.auth.service.UserSecurityService;
import com.slocamo.client.exception.GenericException;
import com.slocamo.common.util.RestUtils;
import com.slocamo.common.wrapper.HttpClientWrapper;
import com.slocamo.entity.analytics.LoggingRequest;
import com.slocamo.entity.client.UserDetail;
import com.slocamo.social.UserAnalyticsService;

/**
 * @author vvr@slocamo.com
 * 
 */
@Controller
@RequestMapping(value = "/session")
public class SessionController {
	private UserSecurityService userSecurityService;
	public static final int STRING_SIZE = 32;
	private Logger logger = Logger.getLogger(this.getClass());
	private HttpClientWrapper httpClientWrapper;
	private UserAnalyticsService userAnalyticsService;

	/**
	 * This method takes care of creating a session information to the newly
	 * requested user. This session info serves as a auth token for every
	 * request that is made by a client.
	 * 
	 * @throws ParseException
	 * @throws IOException
	 * @throws HttpException
	 */
	@RequestMapping(method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Object createSession(
			HttpServletRequest request,
			@ModelAttribute(value = "loggingRequest") LoggingRequest loggingRequest,
			@RequestParam(value = "type") String type,
			@RequestParam(value = "access-token", required = false) String accessToken,
			@RequestParam(value = "access-token-secret", required = false) String accessTokenSecret,
			@RequestParam(value = "network-unique-id") String networkUniqueId)
			throws ParseException, HttpException, IOException {

		RestUtils.addDetailsForLoggingRequest(request, loggingRequest);
		String sessionKey = RandomStringUtils.random(STRING_SIZE, true, true);
		String sessionSecret = RandomStringUtils
				.random(STRING_SIZE, true, true);
		JSONObject userRespone = createUser(type, networkUniqueId, sessionKey,
				sessionSecret);
		JSONObject user = (JSONObject) userRespone.get("user");
		Long uid = Long.valueOf((String) user.get("uid"));
		Session session = new Session();
		session.setSession_key(sessionKey);
		session.setSession_secret(sessionSecret);
		session.setUid(uid);
		userSecurityService.addSessionDetails(loggingRequest, session);

		if (user.get("updated") == null
				|| Integer.valueOf((String) user.get(("updated"))) != 1) {
			try {
				updateUserWithSocialData(userRespone, type, uid, accessToken,
						accessTokenSecret, networkUniqueId);
			} catch (Exception ex) {
				logger.error("Unable to update user details from social network data");
			}

		}

		if (logger.isDebugEnabled()) {
			logger.debug("Session created with " + session);
		}
		return session;
	}

	/**
	 * @param userRespone
	 * @param type
	 * @param uid
	 * @param accessToken
	 * @param accessTokenSecret
	 * @param networkUniqueId
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void updateUserWithSocialData(JSONObject userRespone, String type,
			Long uid, String accessToken, String accessTokenSecret,
			String networkUniqueId) throws Exception {
		UserDetail userDetail = null;
		// TODO: add a column with updated status to 1
		JSONObject user = (JSONObject) userRespone.get("user");
		userDetail = userAnalyticsService.getUserData(type, uid, accessToken,
				accessTokenSecret, networkUniqueId);
		if(userDetail == null) {
			//TODO: Add a logger or do some action
			return;
		}
		user.put("name", userDetail.getFirstName());
		if (userDetail.getEmail() != null) {
			user.put("mail", userDetail.getEmail());
		}
		if (userDetail.getGender() != null && !userDetail.getGender().isEmpty()) {
			if ("male".equalsIgnoreCase(userDetail.getGender())) {
				user.put("sex", 0);
			} else if ("female".equalsIgnoreCase(userDetail.getGender())) {
				user.put("sex", 1);
			}
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;
		try {
			date = simpleDateFormat.parse(userDetail.getBirthday());
			user.put("bdate", new SimpleDateFormat("yyyy-MM-dd").format(date));
		} catch (java.text.ParseException e) {
		}
		updateUser(userRespone);
	}

	/**
	 * @param type
	 * @param networkUniqueId
	 * @param sessionKey
	 * @param sessionSecret
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private JSONObject createUser(String type, String networkUniqueId,
			String sessionKey, String sessionSecret) throws HttpException,
			IOException {
		JSONObject user = new JSONObject();
		JSONObject ulogin = new JSONObject();
		JSONObject userInfoWrapper = new JSONObject();
		user.put("name", networkUniqueId + "dummyName");
		user.put("mail", networkUniqueId + "@dummyMail.com");
		user.put("status", 1);

		ulogin.put("ulogin_uid", networkUniqueId);
		ulogin.put("network", type);
		userInfoWrapper.put("user", user);
		userInfoWrapper.put("ulogin", ulogin);
		String result = httpClientWrapper.post("user-ulogin",
				userInfoWrapper.toString(), true);
		if (result == null) {
			logger.error("Unable to create user");
			throw new GenericException("Unable to create user");
		}
		JSONObject jsonResponse = null;
		try {
			jsonResponse = (JSONObject) new JSONParser().parse(result);
		} catch (ParseException e) {
			logger.error("Unable to create user" + e.getMessage());
			throw new GenericException("Unable to create user" + e);
		}
		return jsonResponse;
	}

	/**
	 * @param userInfoWrapper
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	private JSONObject updateUser(JSONObject userInfoWrapper)
			throws HttpException, IOException {

		String result = httpClientWrapper.post("user-ulogin",
				userInfoWrapper.toString(), true);
		if (result == null) {
			logger.error("Unable to create user");
			throw new GenericException("Unable to create user");
		}
		JSONObject jsonResponse = null;
		try {
			jsonResponse = (JSONObject) new JSONParser().parse(result);
		} catch (ParseException e) {
			logger.error("Unable to create user" + e.getMessage());
			throw new GenericException("Unable to create user" + e);
		}
		return jsonResponse;
	}

	/**
	 * @param userSecurityService
	 *            the userSecurityService to set
	 */
	@Autowired
	public void setUserSecurityService(UserSecurityService userSecurityService) {
		this.userSecurityService = userSecurityService;
	}

	/**
	 * @return the httpClientWrapper
	 */
	public HttpClientWrapper getHttpClientWrapper() {
		return httpClientWrapper;
	}

	/**
	 * @param httpClientWrapper
	 *            the httpClientWrapper to set
	 */
	@Autowired
	public void setHttpClientWrapper(HttpClientWrapper httpClientWrapper) {
		this.httpClientWrapper = httpClientWrapper;
	}

	/**
	 * @param userAnalyticsService
	 *            the userAnalyticsService to set
	 */
	@Autowired
	public void setUserAnalyticsService(
			UserAnalyticsService userAnalyticsService) {
		this.userAnalyticsService = userAnalyticsService;
	}

}
