package com.slocamo.entity.client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@Entity
@Table(name = "users")
@JsonSerialize(include=Inclusion.NON_DEFAULT)
@XmlAccessorType(XmlAccessType.PROPERTY)
public class User {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long uid;
	private Long bdate;
	
	@Column
	private Integer sex;
	
	private String name;
	private String pass;
	private String mail;
	private String theme;
	private String signature;
	private String signature_format;
	private Long created;
	private Long access;
	private Long login;
	private Integer status;
	private String timezone;
	private String language;
	private Long picture;
	private String init;
	private String data;
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
	 * @return the bdate
	 */
	public Long getBdate() {
		return bdate;
	}
	/**
	 * @param bdate the bdate to set
	 */
	public void setBdate(Long bdate) {
		this.bdate = bdate;
	}
	/**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
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
	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}
	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}
	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}
	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
	/**
	 * @return the signature_format
	 */
	public String getSignature_format() {
		return signature_format;
	}
	/**
	 * @param signature_format the signature_format to set
	 */
	public void setSignature_format(String signature_format) {
		this.signature_format = signature_format;
	}
	/**
	 * @return the created
	 */
	public Long getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(Long created) {
		this.created = created;
	}
	/**
	 * @return the access
	 */
	public Long getAccess() {
		return access;
	}
	/**
	 * @param access the access to set
	 */
	public void setAccess(Long access) {
		this.access = access;
	}
	/**
	 * @return the login
	 */
	public Long getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(Long login) {
		this.login = login;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the timezone
	 */
	public String getTimezone() {
		return timezone;
	}
	/**
	 * @param timezone the timezone to set
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the picture
	 */
	public Long getPicture() {
		return picture;
	}
	/**
	 * @param picture the picture to set
	 */
	public void setPicture(Long picture) {
		this.picture = picture;
	}
	/**
	 * @return the init
	 */
	public String getInit() {
		return init;
	}
	/**
	 * @param init the init to set
	 */
	public void setInit(String init) {
		this.init = init;
	}
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	
}
