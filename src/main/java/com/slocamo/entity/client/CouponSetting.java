package com.slocamo.entity.client;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@IdClass(value = com.slocamo.beans.client.CouponSettingId.class)
@Table(name = "coupon_settings")
@NamedQueries({ @NamedQuery(name = "getCouponSettings", query = "select cs FROM CouponSetting cs where cs.idcoupons =:idcoupons") })
public class CouponSetting {
	public static final String GET_COUPON_SETTINGS = "getCouponSettings";
	@Id
	private Long idcoupons;
	@Id
	private String setting_name;
	private String setting_value;

	/**
	 * @return the idcoupons
	 */
	public Long getIdcoupons() {
		return idcoupons;
	}

	/**
	 * @param idcoupons
	 *            the idcoupons to set
	 */
	public void setIdcoupons(Long idcoupons) {
		this.idcoupons = idcoupons;
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

}
