package com.slocamo.entity.client;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_coupons")
public class UserCoupons {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long iduser_coupons;
	private Long uid;
	private Long idcoupons;
	private String alloc_type;
	private Long alloc_date;
	private Long forward_count;
	private Long forwarded_by;
	private Integer accepted_status;
	/**
	 * @return the iduser_coupons
	 */
	public Long getIduser_coupons() {
		return iduser_coupons;
	}
	/**
	 * @param iduser_coupons the iduser_coupons to set
	 */
	public void setIduser_coupons(Long iduser_coupons) {
		this.iduser_coupons = iduser_coupons;
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
	 * @return the alloc_type
	 */
	public String getAlloc_type() {
		return alloc_type;
	}
	/**
	 * @param alloc_type the alloc_type to set
	 */
	public void setAlloc_type(String alloc_type) {
		this.alloc_type = alloc_type;
	}
	/**
	 * @return the alloc_date
	 */
	public Long getAlloc_date() {
		return alloc_date;
	}
	/**
	 * @param alloc_date the alloc_date to set
	 */
	public void setAlloc_date(Long alloc_date) {
		this.alloc_date = alloc_date;
	}
	/**
	 * @return the forward_count
	 */
	public Long getForward_count() {
		return forward_count;
	}
	/**
	 * @param forward_count the forward_count to set
	 */
	public void setForward_count(Long forward_count) {
		this.forward_count = forward_count;
	}
	/**
	 * @return the forwarded_by
	 */
	public Long getForwarded_by() {
		return forwarded_by;
	}
	/**
	 * @param forwarded_by the forwarded_by to set
	 */
	public void setForwarded_by(Long forwarded_by) {
		this.forwarded_by = forwarded_by;
	}
	/**
	 * @return the accepted_status
	 */
	public Integer getAccepted_status() {
		return accepted_status;
	}
	/**
	 * @param accepted_status the accepted_status to set
	 */
	public void setAccepted_status(Integer accepted_status) {
		this.accepted_status = accepted_status;
	}
	
}
