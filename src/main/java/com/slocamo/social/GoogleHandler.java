package com.slocamo.social;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import com.restfb.json.JsonObject;
import com.slocamo.common.wrapper.HttpClientWrapper;
import com.slocamo.entity.client.UserDetail;

/**
 * A top level singleton class to access Facebook's Graph API using OAuth 2.0
 * specification
 * 
 * @author Chaitanya
 */
public class GoogleHandler {
	private HttpClientWrapper httpClientWrapper;

	private static Logger logger = Logger.getLogger(GoogleHandler.class);

	private static GoogleHandler handler = null;

	private GoogleHandler() {
		// To ensure Singleton
	}

	public synchronized static GoogleHandler getInstance() {
		if (handler == null) {
			handler = new GoogleHandler();
		}
		return handler;
	}

	/**
	 * Method to fetch details of a User given an access Token
	 * 
	 * @param accessToken
	 * @return a User instance or null in case of exception
	 */
	public UserDetail getUser(String accessToken) {
		String url = "https://www.googleapis.com/plus/v1/people/me?access_token="
				+ accessToken;
		String response = httpClientWrapper.get(url);
		if (response == null) {
			return null;
		}
		JSONObject responseJson = null;
		try {
			responseJson = (JSONObject) new JSONParser().parse(response);
		} catch (ParseException e) {
			logger.error("Unable to parse json :" + response);
			return null;
		}

		if (responseJson.containsKey("error")) {
			logger.error("got the error response from google access token: "
					+ accessToken + " Response is : " + response);
			return null;
		}

		UserDetail userDetail = new UserDetail();
		userDetail.setBirthday((String) responseJson.get("birthday"));
		userDetail.setGender((String) responseJson.get("gender"));
		userDetail
				.setFirstName((String) ((JSONObject) responseJson.get("name"))
						.get("givenName"));
		return userDetail;
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

}