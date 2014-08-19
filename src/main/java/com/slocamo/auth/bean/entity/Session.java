package com.slocamo.auth.bean.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * @author vvr@slocamo.com
 * 
 */

@XmlRootElement
@JsonAutoDetect
@Entity
@Table(name = "S_session")
public class Session {
	@Id
	private String session_key;

	private String session_secret;
	
	private Long uid;

	/**
	 * @return session_key
	 */
	public String getSession_key() {
		return session_key;
	}

	/**
	 * @param session_key
	 */
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	/**
	 * @return the session_secret
	 */
	public String getSession_secret() {
		return session_secret;
	}

	/**
	 * @param session_secret
	 *            the session_secret to set
	 */
	public void setSession_secret(String session_secret) {
		this.session_secret = session_secret;
	}

	@Override
	public String toString() {
		return "session key: " + session_key + " session secret: "
				+ session_secret;
	}

	/**
	 * @return the uid
	 */
	public Long getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}
}
