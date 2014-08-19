package com.slocamo.entity.client;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupon_stores")
public class CouponStores {
	@Id
	private Long idcoupon_stores;
	private Long idcoupons;
	private Long idstore;
	/**
	 * @return the idcoupon_stores
	 */
	public Long getIdcoupon_stores() {
		return idcoupon_stores;
	}
	/**
	 * @param idcoupon_stores the idcoupon_stores to set
	 */
	public void setIdcoupon_stores(Long idcoupon_stores) {
		this.idcoupon_stores = idcoupon_stores;
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
	
}
