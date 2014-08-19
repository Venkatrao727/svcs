package com.slocamo.beans.client;

import java.io.Serializable;

public class UserFeedbackId implements Serializable{

	private static final long serialVersionUID = 1L;
	public UserFeedbackId() {

	}

	public UserFeedbackId(Long uid, Long idstore) {
		this.uid = uid;
		this.idstore = idstore;
	}
	private Long uid;
	private Long idstore;
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
	/**
	 * @return the idstore
	 */
	public Long getIdstore() {
		return idstore;
	}
	/**
	 * @param idstore the idstore to set
	 */
	public void setIdstore(Long idstore) {
		this.idstore = idstore;
	}
}
