package com.slocamo.entity.client;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@Entity
@Table(name = "ulogin_identity")
@XmlRootElement
@JsonAutoDetect
@JsonSerialize(include=Inclusion.NON_DEFAULT)
@NamedQueries({ @NamedQuery(name = "UloginIdentity.getuid", query = "SELECT NEW com.slocamo.entity.client.UloginIdentity(u.uid) from UloginIdentity u where u.ulogin_uid=:ulogin_uid") })
public class UloginIdentity {
	public UloginIdentity() {

	}

	public UloginIdentity(Long uid) {
		this.uid = uid;
	}
	
	public UloginIdentity(Long uid, String network, String ulogin_uid, String data) {
		this.uid = uid;
		this.network = network;
		this.ulogin_uid = ulogin_uid;
		this.data = data;
	}
	
	public static final String GET_UID = "UloginIdentity.getuid";
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Id
	private Long uid;
	private String network;
	private String ulogin_uid;
	private String data;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the network
	 */
	public String getNetwork() {
		return network;
	}

	/**
	 * @param network the network to set
	 */
	public void setNetwork(String network) {
		this.network = network;
	}

	/**
	 * @return the ulogin_uid
	 */
	public String getUlogin_uid() {
		return ulogin_uid;
	}

	/**
	 * @param ulogin_uid the ulogin_uid to set
	 */
	public void setUlogin_uid(String ulogin_uid) {
		this.ulogin_uid = ulogin_uid;
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
