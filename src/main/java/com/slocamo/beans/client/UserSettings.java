package com.slocamo.beans.client;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
@JsonAutoDetect
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class UserSettings {
	private List<Object> userSettings;

	public List<Object> getUserSettings() {
		return userSettings;
	}

	public void setUserSettings(List<Object> userSettings) {
		this.userSettings = userSettings;
	}
}
