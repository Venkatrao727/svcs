package com.slocamo.auth.bean.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vvr@slocamo.com
 *
 */
@Entity
@Table(name = "S_auth")
public class Auth {
	@Id
	private String partner_id;
	private String api_key;

	/**
	 * @return api_key
	 */
	public String getApi_key() {
		return api_key;
	}

	/**
	 * @param api_key
	 */
	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	/**
	 * @return
	 */
	public String getPartner_id() {
		return partner_id;
	}

	/**
	 * @param partner_id
	 */
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	@Override
	public String toString() {
		return "partner_id: "+partner_id+" api_key:"+api_key;
	}
}
