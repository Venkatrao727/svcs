package com.slocamo.beans.client;

import java.io.Serializable;

public class CouponSettingId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idcoupons;
	private String setting_name;

	public String getSetting_name() {
		return setting_name;
	}

	public void setSetting_name(String setting_name) {
		this.setting_name = setting_name;
	}

	public long getIdcoupons() {
		return idcoupons;
	}

	public void setIdcoupons(long idcoupons) {
		this.idcoupons = idcoupons;
	}

}
