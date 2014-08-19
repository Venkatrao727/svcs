package com.slocamo.entity.analytics;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author vvr@slocamo.com
 * 
 */
@Entity
@Table(name = "A_loggingRequest")
@NamedQueries({
		@NamedQuery(name = "getTotalVisits", query = "select count(distinct l.uid) from LoggingRequest l, CampaignRequest c where "
				+ "l.requestId=c.requestId and c.businessVendor=:storeId and l.timestamp >= STR_TO_DATE(:from,  '%d-%m-%Y') "
				+ "and l.timestamp <= STR_TO_DATE(:to, '%d-%m-%Y')"),
		@NamedQuery(name = "hourlyVisits", query = "select hour(l.timestamp),count(distinct l.uid) from LoggingRequest l, CampaignRequest c "
				+ "where l.requestId=c.requestId and c.businessVendor=:storeId and l.timestamp >= STR_TO_DATE(:from,  '%d-%m-%Y') and "
				+ "l.timestamp <= STR_TO_DATE(:to,  '%d-%m-%Y') group by hour(l.timestamp)"),

		@NamedQuery(name = "visitsByGender", query = "select l.gender,count(distinct l.uid) from LoggingRequest l,CampaignRequest c "
				+ "where l.requestId=c.requestId and c.businessVendor=:storeId and l.timestamp  >= STR_TO_DATE(:from,  '%d-%m-%Y') "
				+ "and l.timestamp <= STR_TO_DATE(:to,  '%d-%m-%Y') group by gender"),
		@NamedQuery(name = "getCampaign", query = "select recordID, count(recordID) from Notification where recordID in(:couponIds) and status=0 and "
				+ "timestamp  >= STR_TO_DATE(:from,  '%d-%m-%Y') and timestamp <= STR_TO_DATE(:to,  '%d-%m-%Y') and notificationtype=:notificationType "
				+ "group by recordID"),
		@NamedQuery(name = "visitsByAge", query = "select l.age,count(distinct l.uid) from LoggingRequest l,CampaignRequest c "
				+ "where l.requestId=c.requestId and c.businessVendor=:storeId and l.timestamp  >= STR_TO_DATE(:from,  '%d-%m-%Y') "
				+ "and l.timestamp <= STR_TO_DATE(:to,  '%d-%m-%Y') group by l.age"), })
public class LoggingRequest {
	public static final String GET_TOTAL_VISITS = "getTotalVisits";
	public static final String GET_HOURLY_VISITS = "hourlyVisits";
	public static final String GET_VISITS_BY_GENDER = "visitsByGender";
	public static final String GET_CAMPAIGN = "getCampaign";
	public static final String GET_VISITS_BY_AGE = "visitsByAge";
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String requestId;
	private Timestamp timestamp;
	private String userName;
	private String siteId;
	private String userNameType;
	private String lat;
	private String lng;
	private String ipAddress;
	private String uid;
	private String uidType;
	private String dnt;
	private String userAgent;
	private String device;
	private String age;
	private String gender;
	private String income;
	private String education;
	private String carrier;
	private String os;
	private String osVersion;
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
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return the userNameType
	 */
	public String getUserNameType() {
		return userNameType;
	}

	/**
	 * @param userNameType
	 *            the userNameType to set
	 */
	public void setUserNameType(String userNameType) {
		this.userNameType = userNameType;
	}

	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * @return the lng
	 */
	public String getLng() {
		return lng;
	}

	/**
	 * @param lng
	 *            the lng to set
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the uidType
	 */
	public String getUidType() {
		return uidType;
	}

	/**
	 * @param uidType
	 *            the uidType to set
	 */
	public void setUidType(String uidType) {
		this.uidType = uidType;
	}

	/**
	 * @return the dnt
	 */
	public String getDnt() {
		return dnt;
	}

	/**
	 * @param dnt
	 *            the dnt to set
	 */
	public void setDnt(String dnt) {
		this.dnt = dnt;
	}

	/**
	 * @return the userAgent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * @param userAgent
	 *            the userAgent to set
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * @return the device
	 */
	public String getDevice() {
		return device;
	}

	/**
	 * @param device
	 *            the device to set
	 */
	public void setDevice(String device) {
		this.device = device;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the income
	 */
	public String getIncome() {
		return income;
	}

	/**
	 * @param income
	 *            the income to set
	 */
	public void setIncome(String income) {
		this.income = income;
	}

	/**
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * @param education
	 *            the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * @return the carrier
	 */
	public String getCarrier() {
		return carrier;
	}

	/**
	 * @param carrier
	 *            the carrier to set
	 */
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os
	 *            the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return the osVersion
	 */
	public String getOsVersion() {
		return osVersion;
	}

	/**
	 * @param osVersion
	 *            the osVersion to set
	 */
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
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
