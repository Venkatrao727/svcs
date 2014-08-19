package com.slocamo.entity.client;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coupon_redeems")
public class CouponRedeems {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idcoupon_redeems;
	private Long uid;
	private Long idcoupons;
	private Long idstore;
	private Long redeem_date;
	/**
	 * @return the idcoupon_redeems
	 */
	public Long getIdcoupon_redeems() {
		return idcoupon_redeems;
	}
	/**
	 * @param idcoupon_redeems the idcoupon_redeems to set
	 */
	public void setIdcoupon_redeems(Long idcoupon_redeems) {
		this.idcoupon_redeems = idcoupon_redeems;
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
	/**
	 * @return the idcoupons
	 */
	public Long getIdcoupons() {
		return idcoupons;
	}
	/**
	 * @param idcoupons the idcoupons to set
	 */
	public void setIdcoupons(Long idcoupons) {
		this.idcoupons = idcoupons;
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
	/**
	 * @return the redeem_date
	 */
	public Long getRedeem_date() {
		return redeem_date;
	}
	/**
	 * @param redeem_date the redeem_date to set
	 */
	public void setRedeem_date(Long redeem_date) {
		this.redeem_date = redeem_date;
	}

}
