package com.slocamo.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slocamo.beans.client.ChartDetails;
import com.slocamo.beans.client.UserSettings;
import com.slocamo.client.dao.UserDAO;
import com.slocamo.client.exception.GenericException;
import com.slocamo.common.util.RestUtils;
import com.slocamo.common.wrapper.HttpClientWrapper;
import com.slocamo.entity.client.SlocamoFeedback;
import com.slocamo.entity.client.UloginIdentity;
import com.slocamo.entity.client.User;
import com.slocamo.entity.client.UserFeedback;
import com.slocamo.entity.client.UserSetting;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
	private UserDAO userDAO;
	private HttpClientWrapper httpClientWrapper;

	/* (non-Javadoc)
	 * @see com.slocamo.rest.service.UserService#getUserNetwork(java.lang.String, java.lang.String)
	 */
	@Transactional
	public Object getUserNetwork(String uloginIdentity, String fields) {
		return userDAO.getUserNetwork(uloginIdentity, fields);
	}

	/* (non-Javadoc)
	 * @see com.slocamo.rest.service.UserService#postUserNetwork(com.slocamo.rest.entity.UloginIdentity)
	 */
	@Transactional
	public void postUserNetwork(UloginIdentity uloginIdentity) {
		userDAO.postUserNetwork(uloginIdentity);
	}

	/* (non-Javadoc)
	 * @see com.slocamo.rest.service.UserService#postUSer(com.slocamo.rest.entity.User)
	 */
	@Transactional
	public User postUSer(User user) {
		User persistedUser = new User();
		persistedUser.setUid(userDAO.postUser(user));
		return persistedUser;
	}

	/* (non-Javadoc)
	 * @see com.slocamo.rest.service.UserService#putUSer(com.slocamo.rest.entity.User)
	 */
	@Transactional
	public void putUSer(User user) {
		userDAO.putUser(user);
	}

	/* (non-Javadoc)
	 * @see com.slocamo.rest.service.UserService#getUser(java.lang.Long)
	 */
	@Transactional
	public User getUser(Long uid) {
		return userDAO.getUser(uid);
	}

	/* (non-Javadoc)
	 * @see com.slocamo.rest.service.UserService#getUserSettings(java.lang.Long)
	 */
	@Transactional
	public Object getUserSettings(Long userId) {
		UserSettings userSettings = new UserSettings();
		userSettings.setUserSettings(userDAO.getUserSettings(userId));
		return userSettings;
	}

	/* (non-Javadoc)
	 * @see com.slocamo.rest.service.UserService#postUserSettings(com.slocamo.rest.entity.UserSetting)
	 */
	@Transactional
	public void postUserSettings(UserSetting userSetting) {
		String result = httpClientWrapper.post("user-settings", userSetting, false);
		if(result == null) {
			throw new GenericException("Error Posting the user feedback for uid: "+userSetting.getUid());
		}
	}

	/* (non-Javadoc)
	 * @see com.slocamo.rest.service.UserService#putUserSettings(com.slocamo.rest.entity.UserSetting)
	 */
	@Transactional
	public void putUserSettings(UserSetting userSetting) {
		userDAO.putUserSettings(userSetting);

	}

	/* (non-Javadoc)
	 * @see com.slocamo.rest.service.UserService#postUserFeedback(com.slocamo.rest.entity.UserFeedback)
	 */
	@Transactional
	public void postUserFeedback(UserFeedback userFeedback) {
		if (userFeedback == null || userFeedback.getIdstore() == null
				|| userFeedback.getUid() == null
				|| RestUtils.isEmpty(userFeedback.getFeedback())) {
			throw new IllegalArgumentException("Invalid arguments passed for postUserFeedback");
		}
		String result = httpClientWrapper.post("user-feedback", userFeedback, false);
		if(result == null) {
			throw new GenericException("Error Posting the user feedback for uid: "+userFeedback.getUid());
		}
	}

	/* (non-Javadoc)
	 * @see com.slocamo.rest.service.UserService#postSlocamoFeedback(com.slocamo.rest.entity.SlocamoFeedback)
	 */
	@Transactional
	public void postSlocamoFeedback(SlocamoFeedback slocamoFeedback) {
		if (slocamoFeedback == null || slocamoFeedback.getUid() == null
				|| RestUtils.isEmpty(slocamoFeedback.getFeedback_text())) {
			throw new IllegalArgumentException("Invalid data passed for postSlocamoFeedback");
		}
		String result = httpClientWrapper.post("slocamo-feedback", slocamoFeedback, false);
		if(result == null) {
			throw new GenericException("Error Posting the user feedback for uid: "+slocamoFeedback.getUid());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.slocamo.rest.service.UserService#getUserFeedback(java.lang.Long)
	 */
	@Transactional
	public Object getUserFeedback(Long storeId) {
		ChartDetails chartDetails = new ChartDetails();
		chartDetails.setChartDetails(userDAO.getUserFeedback(storeId));
		return chartDetails;
	}
	

	@Transactional
	public Object getUserFeedbackWithDateRange(Long storeId, String from,
			String to) {
		return userDAO.getUserFeedbackWithDateRange(storeId, from, to);
	}
	

	@Transactional
	public Object getUserCommentsWithDateRange(Long storeId, String from,
			String to) {
		return userDAO.getUserCommentsWithDateRange(storeId, from, to);
	}
	
	@Autowired
	public void setuserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * @return the httpClientWrapper
	 */
	public HttpClientWrapper getHttpClientWrapper() {
		return httpClientWrapper;
	}

	/**
	 * @param httpClientWrapper the httpClientWrapper to set
	 */
	@Autowired
	public void setHttpClientWrapper(HttpClientWrapper httpClientWrapper) {
		this.httpClientWrapper = httpClientWrapper;
	}

}
