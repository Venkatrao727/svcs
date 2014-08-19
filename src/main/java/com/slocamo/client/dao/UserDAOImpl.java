package com.slocamo.client.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Repository;

import com.slocamo.beans.client.UserFeedbackId;
import com.slocamo.beans.client.UserSettingId;
import com.slocamo.common.BaseDAO;
import com.slocamo.entity.client.SlocamoFeedback;
import com.slocamo.entity.client.UloginIdentity;
import com.slocamo.entity.client.User;
import com.slocamo.entity.client.UserFeedback;
import com.slocamo.entity.client.UserSetting;

@SuppressWarnings("unchecked")
@Repository(value = "userDAO")
public class UserDAOImpl extends BaseDAO implements UserDAO {

	public Object getUserNetwork(String uloginIdentity, String fields) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ulogin_uid", uloginIdentity);
		if (fields != null && "uid".equals(fields.trim())) {
			List<Object> list = (List<Object>) executeToList(
					UloginIdentity.GET_UID, null, null, params);
			if (list != null && !list.isEmpty()) {
				return list.get(0);
			}
		}
		return null;
	}

	public void postUserNetwork(UloginIdentity uloginIdentity) {
		persist(uloginIdentity);
	}

	public void putUser(User user) {
		User dbUser = (User) find(User.class, user.getUid());
		if (user.getSex() != null) {
			dbUser.setSex(user.getSex());
		}
		if (user.getBdate() != null) {
			dbUser.setBdate(user.getBdate());
		}
	}

	public Long postUser(User user) {
		persist(user);
		return user.getUid();
	}

	public User getUser(Long uid) {
		return (User) find(User.class, uid);
	}

	public List<Object> getUserSettings(Long uid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", uid);
		return (List<Object>) executeToList(UserSetting.GET_USER_SETTINGS,
				null, null, params);
	}

	public void postUserSettings(UserSetting userSetting) {
		persist(userSetting);

	}

	public void putUserSettings(UserSetting userSetting) {
		UserSetting userSettingRetrieved = (UserSetting) find(
				UserSetting.class, new UserSettingId(userSetting.getUid(),
						userSetting.getSetting_name()));
		if (userSettingRetrieved == null) {
			throw new EntityNotFoundException(
					"Usersetting not found for userID :" + userSetting.getUid());
		}
		userSettingRetrieved.setSetting_value(userSetting.getSetting_value());
	}

	public void postUserFeedback(UserFeedback userFeedback) {
		UserFeedback ufPersisted = (UserFeedback) find(
				UserFeedback.class,
				new UserFeedbackId(userFeedback.getUid(), userFeedback
						.getIdstore()));
		if (ufPersisted == null) {
			persist(userFeedback);
		} else {
			ufPersisted.setFeedback(userFeedback.getFeedback());
		}

	}

	public List<Object> getUserFeedback(Long storeId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeId", storeId);
		return (List<Object>) executeToList(UserFeedback.GET_USER_FEEDBACK,
				null, null, params);
	}

	public void postSlocamoFeedback(SlocamoFeedback slocamoFeedback) {
		persist(slocamoFeedback);
	}

	@Override
	public Object getUserFeedbackWithDateRange(Long storeId, String from,
			String to) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeId", storeId);
		params.put("from", from);
		params.put("to", to);
		return executeToList(UserFeedback.GET_USER_FEEDBACK_WITH_DATE_RANGE,
				null, null, params);
	}

	@Override
	public Object getUserCommentsWithDateRange(Long storeId, String from,
			String to) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeId", storeId);
		params.put("from", from);
		params.put("to", to);
		return executeToList(UserFeedback.GET_USER_COMMENTS_WITH_DATE_RANGE,
				null, null, params);
	}

}
