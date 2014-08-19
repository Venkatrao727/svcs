package com.slocamo.entity.analytics;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vvr@slocamo.com
 * 
 */
@Entity
@Table(name="A_campaignRequest")
public class CampaignRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Timestamp timeStamp;
	private String requestId;
	private String recordID;
	private String businessName;
	private String businessCaption;
	private String businessAddress;
	private String businessCity;
	private String businessState;
	private String businessZip;
	private String businessCountry;
	private String businessLtitude;
	private String businessLongitude;
	private String businessPhone;
	private String businessCategory;
	private String businessRating;
	private String businessVendor;
	private String billingParamter;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the timeStamp
	 */
	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp
	 *            the timeStamp to set
	 */
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId
	 *            the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the recordID
	 */
	public String getRecordID() {
		return recordID;
	}

	/**
	 * @param recordID
	 *            the recordID to set
	 */
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}

	/**
	 * @return the businessName
	 */
	public String getBusinessName() {
		return businessName;
	}

	/**
	 * @param businessName the businessName to set
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	/**
	 * @return the businessCaption
	 */
	public String getBusinessCaption() {
		return businessCaption;
	}

	/**
	 * @param businessCaption the businessCaption to set
	 */
	public void setBusinessCaption(String businessCaption) {
		this.businessCaption = businessCaption;
	}

	/**
	 * @return the businessAddress
	 */
	public String getBusinessAddress() {
		return businessAddress;
	}

	/**
	 * @param businessAddress the businessAddress to set
	 */
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	/**
	 * @return the businessCity
	 */
	public String getBusinessCity() {
		return businessCity;
	}

	/**
	 * @param businessCity the businessCity to set
	 */
	public void setBusinessCity(String businessCity) {
		this.businessCity = businessCity;
	}

	/**
	 * @return the businessState
	 */
	public String getBusinessState() {
		return businessState;
	}

	/**
	 * @param businessState the businessState to set
	 */
	public void setBusinessState(String businessState) {
		this.businessState = businessState;
	}

	/**
	 * @return the businessZip
	 */
	public String getBusinessZip() {
		return businessZip;
	}

	/**
	 * @param businessZip the businessZip to set
	 */
	public void setBusinessZip(String businessZip) {
		this.businessZip = businessZip;
	}

	/**
	 * @return the businessCountry
	 */
	public String getBusinessCountry() {
		return businessCountry;
	}

	/**
	 * @param businessCountry the businessCountry to set
	 */
	public void setBusinessCountry(String businessCountry) {
		this.businessCountry = businessCountry;
	}

	/**
	 * @return the businessLtitude
	 */
	public String getBusinessLtitude() {
		return businessLtitude;
	}

	/**
	 * @param businessLtitude the businessLtitude to set
	 */
	public void setBusinessLtitude(String businessLtitude) {
		this.businessLtitude = businessLtitude;
	}

	/**
	 * @return the businessLongitude
	 */
	public String getBusinessLongitude() {
		return businessLongitude;
	}

	/**
	 * @param businessLongitude the businessLongitude to set
	 */
	public void setBusinessLongitude(String businessLongitude) {
		this.businessLongitude = businessLongitude;
	}

	/**
	 * @return the businessPhone
	 */
	public String getBusinessPhone() {
		return businessPhone;
	}

	/**
	 * @param businessPhone the businessPhone to set
	 */
	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	/**
	 * @return the businessCategory
	 */
	public String getBusinessCategory() {
		return businessCategory;
	}

	/**
	 * @param businessCategory the businessCategory to set
	 */
	public void setBusinessCategory(String businessCategory) {
		this.businessCategory = businessCategory;
	}

	/**
	 * @return the businessRating
	 */
	public String getBusinessRating() {
		return businessRating;
	}

	/**
	 * @param businessRating the businessRating to set
	 */
	public void setBusinessRating(String businessRating) {
		this.businessRating = businessRating;
	}

	/**
	 * @return the businessVendor
	 */
	public String getBusinessVendor() {
		return businessVendor;
	}

	/**
	 * @param businessVendor the businessVendor to set
	 */
	public void setBusinessVendor(String businessVendor) {
		this.businessVendor = businessVendor;
	}

	/**
	 * @return the billingParamter
	 */
	public String getBillingParamter() {
		return billingParamter;
	}

	/**
	 * @param billingParamter the billingParamter to set
	 */
	public void setBillingParamter(String billingParamter) {
		this.billingParamter = billingParamter;
	}
}
