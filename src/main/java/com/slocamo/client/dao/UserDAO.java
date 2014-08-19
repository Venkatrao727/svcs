package com.slocamo.client.dao;

import java.util.List;

import com.slocamo.entity.client.SlocamoFeedback;
import com.slocamo.entity.client.UloginIdentity;
import com.slocamo.entity.client.User;
import com.slocamo.entity.client.UserFeedback;
import com.slocamo.entity.client.UserSetting;

public interface UserDAO {
	/**
	 * @param userId
	 * @return user settings for a user, like push update settings e.t.c
	 */
	List<Object> getUserSettings(Long userId);

	/**
	 * @param storeId
	 * @return store feedback from customers. This data is used to plot a bar graph.
	 */
	List<Object> getUserFeedback(Long storeId);

	/**
	 * @param uloginIdentity
	 * @param fields
	 * @return returns the details of user associated to social network
	 */
	Object getUserNetwork(String uloginIdentity, String fields);

	/**
	 * @param uloginIdentity
	 */
	void postUserNetwork(UloginIdentity uloginIdentity);

	/**
	 * @param user
	 */
	void putUser(User user);

	/**
	 * @param user
	 * @return uid of the user.
	 */
	Long postUser(User user);

	/**
	 * @param uid
	 * @return User details based on uid
	 */
	User getUser(Long uid);

	/**
	 * @param userSetting
	 */
	void postUserSettings(UserSetting userSetting);

	/**
	 * @param userSetting
	 */
	void putUserSettings(UserSetting userSetting);

	/**
	 * @param slocamoFeedback
	 */
	void postSlocamoFeedback(SlocamoFeedback slocamoFeedback);

	/**
	 * @param userFeedback
	 */
	void postUserFeedback(UserFeedback userFeedback);


	Object getUserFeedbackWithDateRange(Long storeId, String from, String to);

	Object getUserCommentsWithDateRange(Long storeId, String from, String to);

}
