package com.slocamo.auth.bean;


/**
 * @author vvr@slocamo.com
 *
 */
public final class RESTCredentials {

	private String partnerId;
	private String apiKey;

	private String salt;
	private String signature;
	private String sessionKey;
	private Long uid;
	private boolean isFirstAuthentication;

	@SuppressWarnings("unused")
	private RESTCredentials() {
	}

	public RESTCredentials(String sessionKey, String signature, String salt) {
		this.sessionKey = sessionKey;
		this.signature = signature;
		this.salt = salt;
	}

	public RESTCredentials(String partnerId, String apiKey, String salt,
			boolean isFirstAuthentication) {
		this.partnerId = partnerId;
		this.apiKey = apiKey;
		this.isFirstAuthentication = isFirstAuthentication;
		this.salt = salt;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public boolean isFirstAuthentication() {
		return isFirstAuthentication;
	}

	public void setFirstAuthentication(boolean isFirstAuthentication) {
		this.isFirstAuthentication = isFirstAuthentication;
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
