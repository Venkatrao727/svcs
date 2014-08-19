package com.slocamo.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.slocamo.client.service.UserService;
import com.slocamo.entity.client.SlocamoFeedback;
import com.slocamo.entity.client.UloginIdentity;
import com.slocamo.entity.client.User;
import com.slocamo.entity.client.UserFeedback;
import com.slocamo.entity.client.UserSetting;

/**
 * @author vvr@slocamo.com
 * 
 */
@Controller
public class UserController {
	private UserService userService;

	/**
	 * @param uloginIdentity
	 * @param fields
	 * @return few details of the user like uid and social network details.
	 */
	@RequestMapping(value = "/user-network", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Object getUserNetwork(
			@RequestParam(value = "ulogin-identity") String uloginIdentity,
			@RequestParam(defaultValue = "*:*", value = "fields") String fields) {
		return userService.getUserNetwork(uloginIdentity, fields);
	}

	/**
	 * @param uid
	 * @param network
	 * @param uloginIdentity
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/user-network", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public void postUserNetwork(
			@ModelAttribute(value = "uIdentity") UloginIdentity uloginIdentity) {
		userService.postUserNetwork(uloginIdentity);
	}

	/**
	 * @param uid
	 * @return the user details by uid
	 */
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/user/{uid}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public User getUser(@PathVariable(value = "uid") Long uid) {
		return userService.getUser(uid);
	}

	/**
	 * @param name
	 * @param init
	 * @param mail
	 * @param pass
	 * @param status
	 * @return uid of the user posted.
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public User postUser(@ModelAttribute(value = "user") User user) {
		return userService.postUSer(user);
	}

	/**
	 * @param bdate
	 * @param sex
	 * @param userid
	 * @return
	 */
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/user/{uid}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public void putUser(@ModelAttribute(value = "user") User user,
			@PathVariable(value = "uid") Long uid) {
		user.setUid(uid);
		userService.putUSer(user);
	}

	/**
	 * @param uid
	 * @return user settings of user, like push to social network settings e.t.c
	 */
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/user-settings", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Object getUserSettings(@RequestParam(value = "uid") Long uid) {
		return userService.getUserSettings(uid);
	}

	/**
	 * @param userSetting
	 */
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/user-settings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public void postUserSettings(
			@ModelAttribute(value = "userSetting") UserSetting userSetting,
			@Value("#{request.getAttribute('uid')}") Long uid) {
		userSetting.setUid(uid);
		userService.postUserSettings(userSetting);
	}

	/**
	 * @param uid
	 * @param userSetting
	 */
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/user-settings/{uid}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public void putUserSettings(@PathVariable(value = "uid") Long uid,
			@ModelAttribute(value = "userSetting") UserSetting userSetting) {
		userSetting.setUid(uid);
		userService.putUserSettings(userSetting);
	}

	/**
	 * @param storeId
	 * @return Aggregate feedback ofd store. Essentially used to plot the bar
	 *         chart.
	 */
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, value = "/user-feedback", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Object getUserFeedback(@RequestParam(value = "store-id") Long storeId) {
		return userService.getUserFeedback(storeId);
	}

	/**
	 * @param userFeedback
	 */
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, value = "/user-feedback", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void postUserFeedback(
			@ModelAttribute(value = "userFeedback") UserFeedback userFeedback,
			@Value("#{request.getAttribute('uid')}") Long uid) {
		userFeedback.setUid(uid);
		userService.postUserFeedback(userFeedback);
	}

	/**
	 * @param slocamoFeedback
	 */
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, value = "/slocamo-feedback", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void postSlocamoFeedback(
			@ModelAttribute(value = "slocamoFeedback") SlocamoFeedback slocamoFeedback,
			@Value("#{request.getAttribute('uid')}") Long uid) {
		slocamoFeedback.setUid(uid);
		userService.postSlocamoFeedback(slocamoFeedback);
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
