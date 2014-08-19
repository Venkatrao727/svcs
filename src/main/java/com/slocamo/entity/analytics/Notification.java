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
@Table(name = "A_notification")
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Timestamp timeStamp;
	private String requestId;
	private String recordID;
	private String siteId;
	private String billingParameter;
	private String notificationType;
	private String businessVendor;
	private String status;
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
	 * @return the siteId
	 */
	public String getSiteId() {
		return siteId;
	}

	/**
	 * @param siteId
	 *            the siteId to set
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	/**
	 * @return the billingParameter
	 */
	public String getBillingParameter() {
		return billingParameter;
	}

	/**
	 * @param billingParameter
	 *            the billingParameter to set
	 */
	public void setBillingParameter(String billingParameter) {
		this.billingParameter = billingParameter;
	}

	/**
	 * @return the notificationType
	 */
	public String getNotificationType() {
		return notificationType;
	}

	/**
	 * @param notificationType
	 *            the notificationType to set
	 */
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	/**
	 * @return the businessVendor
	 */
	public String getBusinessVendor() {
		return businessVendor;
	}

	/**
	 * @param businessVendor
	 *            the businessVendor to set
	 */
	public void setBusinessVendor(String businessVendor) {
		this.businessVendor = businessVendor;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
