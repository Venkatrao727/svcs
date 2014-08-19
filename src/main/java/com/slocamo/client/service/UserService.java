package com.slocamo.client.service;

import org.springframework.stereotype.Service;

import com.slocamo.entity.client.SlocamoFeedback;
import com.slocamo.entity.client.UloginIdentity;
import com.slocamo.entity.client.User;
import com.slocamo.entity.client.UserFeedback;
import com.slocamo.entity.client.UserSetting;

/**
 * @author vvr@slocamo.com
 *
 */
@Service
public interface UserService {

	/**
	 * @param userId
	 * @return the user settings, like fb push e.t.c
	 */
	Object getUserSettings(Long userId);

	/**
	 * @param storeId
	 * @return the aggregate store feedback details. This data is used to plot a graph.
	 */
	Object getUserFeedback(Long storeId);

	/**
	 * @param uloginIdentity
	 * @param fields
	 * @return the requested 'fields' for the user assocaited to a social network.
	 */
	Object getUserNetwork(String uloginIdentity, String fields);

	/**
	 * @param uloginIdentity
	 * post social network details associated to user.
	 */
	void postUserNetwork(UloginIdentity uloginIdentity);

	/**
	 * @param user
	 * @return post user details.
	 */
	User postUSer(User user);

	/**
	 * @param user
	 */
	void putUSer(User user);

	/**
	 * @param uid
	 * @return user details.
	 */
	User getUser(Long uid);

	/**
	 * @param userSetting 
	 * post user settings like social network push e.t.c.
	 */
	void postUserSettings(UserSetting userSetting);

	/**
	 * @param userSetting
	 * update user settings like social network push e.t.c.
	 */
	void putUserSettings(UserSetting userSetting);

	/**
	 * @param slocamoFeedback
	 * to post a feedback to slocamo
	 */
	void postSlocamoFeedback(SlocamoFeedback slocamoFeedback);

	/**
	 * @param userFeedback
	 * to post user feedback on a store.
	 */
	void postUserFeedback(UserFeedback userFeedback);

	/**
	 * @param storeId
	 * @param from
	 * @param to
	 * @return
	 */
	Object getUserFeedbackWithDateRange(Long storeId, String from, String to);
	
	Object getUserCommentsWithDateRange(Long storeId, String from, String to);

}
