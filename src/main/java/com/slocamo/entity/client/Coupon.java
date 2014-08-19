package com.slocamo.entity.client;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.slocamo.common.GlobalVars;

@Entity
@Table(name = "coupons")
@NamedQueries({ @NamedQuery(name = "getValidCoupons",
	query = "select NEW com.slocamo.entity.client.Coupon(cs.idcoupons, c.coupon_image_url,c.can_forward, c.end_date, c.multiple_redeems," +
			"(select count(cr) from CouponRedeems cr where cr.idcoupons=c.idcoupons and cr.uid=:uid) as count) from " +
			"Coupon c,CouponStores cs where c.idcoupons=cs.idcoupons and cs.idstore=:storeId and c.start_date < :currentTime and c.end_date > :currentTime"),
			@NamedQuery(name="getCouponsInRange", query="select c.idcoupons,c.name from Coupon c,CouponStores cs where c.idcoupons=cs.idcoupons and " +
					"cs.idstore=:storeId and STR_TO_DATE(:from,  '%d-%m-%Y')<= FROM_UNIXTIME(c.end_date) and STR_TO_DATE(:to,  '%d-%m-%Y') >= FROM_UNIXTIME(c.start_date)")})
@XmlRootElement
@JsonAutoDetect
public class Coupon {
	public static final String GET_VALID_COUPONS = "getValidCoupons";
	public static final String GET_COUPONS_IN_RANGE = "getCouponsInRange";
	public Coupon() {

	}

	public Coupon(Long idcoupons, String coupon_image_url, Integer can_forward,
			Long end_date, Long multiple_redeems, Long redeemcount_foruser) {
		this.idcoupons = idcoupons;
		this.setCoupon_image_url(coupon_image_url);
		this.can_forward = can_forward;
		this.end_date = end_date;
		this.multiple_redeems = multiple_redeems;
		this.redeemcount_foruser = redeemcount_foruser;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idcoupons;
	private String name;
	private String coupon_image_url;
	private Integer can_forward;
	private Long end_date;
	private Long start_date;
	private Long multiple_redeems;
	@Transient
	private Long redeemcount_foruser;


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
	 * @return the coupon_image_url
	 */
	public String getCoupon_image_url() {
		return coupon_image_url;
	}

	/**
	 * @param coupon_image_url
	 *            the coupon_image_url to set
	 */
	public void setCoupon_image_url(String coupon_image_url) {
		this.coupon_image_url = GlobalVars.getImageUrl()+coupon_image_url;
	}

	/**
	 * @return the can_forward
	 */
	public Integer getCan_forward() {
		return can_forward;
	}

	/**
	 * @param can_forward
	 *            the can_forward to set
	 */
	public void setCan_forward(Integer can_forward) {
		this.can_forward = can_forward;
	}

	/**
	 * @return the end_date
	 */
	public Long getEnd_date() {
		return end_date;
	}

	/**
	 * @param end_date
	 *            the end_date to set
	 */
	public void setEnd_date(Long end_date) {
		this.end_date = end_date;
	}

	/**
	 * @return the multiple_redeems
	 */
	public Long getMultiple_redeems() {
		return multiple_redeems;
	}

	/**
	 * @param multiple_redeems
	 *            the multiple_redeems to set
	 */
	public void setMultiple_redeems(Long multiple_redeems) {
		this.multiple_redeems = multiple_redeems;
	}
	/**
	 * @return the start_date
	 */
	public Long getStart_date() {
		return start_date;
	}

	/**
	 * @param start_date the start_date to set
	 */
	public void setStart_date(Long start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return the redeemcount_foruser
	 */
	public Long getRedeemcount_foruser() {
		return redeemcount_foruser;
	}

	/**
	 * @param redeemcount_foruser the redeemcount_foruser to set
	 */
	public void setRedeemcount_foruser(Long redeemcount_foruser) {
		this.redeemcount_foruser = redeemcount_foruser;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
