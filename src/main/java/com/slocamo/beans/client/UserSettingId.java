package com.slocamo.beans.client;

import java.io.Serializable;

public class UserSettingId implements Serializable {

	public UserSettingId() {

	}

	public UserSettingId(Long uid, String setting_name) {
		this.uid = uid;
		this.setting_name = setting_name;
	}

	private static final long serialVersionUID = 1L;
	private Long uid;
	private String setting_name;

	public String getSetting_name() {
		return setting_name;
	}

	public void setSetting_name(String setting_name) {
		this.setting_name = setting_name;
	}

	/**
	 * @return the uid
	 */
	public Long getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}

}
