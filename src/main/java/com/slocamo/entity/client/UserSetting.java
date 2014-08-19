package com.slocamo.entity.client;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@Entity
@IdClass(com.slocamo.beans.client.UserSettingId.class)
@Table(name = "user_settings")
@JsonAutoDetect
@XmlRootElement
@NamedQueries({@NamedQuery(name="getUserSettings", query="select u from UserSetting u where uid = :uid")})
public class UserSetting {
	public static final String GET_USER_SETTINGS = "getUserSettings";
	@Id
	private Long uid;
	@Id
	private String setting_name;
	private String setting_value;

	/**
	 * @return the setting_value
	 */
	public String getSetting_value() {
		return setting_value;
	}

	/**
	 * @param setting_value
	 *            the setting_value to set
	 */
	public void setSetting_value(String setting_value) {
		this.setting_value = setting_value;
	}

	/**
	 * @return the setting_name
	 */
	public String getSetting_name() {
		return setting_name;
	}

	/**
	 * @param setting_name
	 *            the setting_name to set
	 */
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
