package com.slocamo.entity.client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@Entity
@Table(name = "stores")
@NamedQueries({
	@NamedQuery(name = "searchStore", query = "SELECT distinct s FROM Store s where LOWER(store_title) "
			+ "like LOWER(:searchString) OR LOWER(store_address) like LOWER(:searchString) OR LOWER(store_city) "
			+ "like LOWER(:searchString) OR LOWER(store_state) like LOWER(:searchString) OR LOWER(store_country) "
			+ "like LOWER(:searchString)"),

			@NamedQuery(name = "searchStoreCount", query = "SELECT count(*) FROM Store s where LOWER(store_title) "
					+ "like LOWER(:searchString) OR LOWER(store_address) like LOWER(:searchString) OR LOWER(store_city) "
					+ "like LOWER(:searchString) OR LOWER(store_state) like LOWER(:searchString) OR LOWER(store_country) "
					+ "like LOWER(:searchString)"),
					// -------Search

					@NamedQuery(name = "getStoresMapIfLocKnown", query = "SELECT NEW com.slocamo.entity.client.Store(s, (((acos(sin((:lat*pi()/180))*sin((s.location_latitude*pi()/180))"
							+ "+cos((:lat*pi()/180))*cos((s.location_latitude*pi()/180)) "
							+ "* cos(((:lng - s.location_longitude)*pi()/180))))*180/pi())*60*1.1515)"
							+ " as distance) FROM Store s where (((acos(sin((:lat*pi()/180))*sin((s.location_latitude*pi()/180))"
							+ "+cos((:lat*pi()/180))*cos((s.location_latitude*pi()/180)) "
							+ "* cos(((:lng - s.location_longitude)*pi()/180))))*180/pi())*60*1.1515) < (:rangeInMeters * 0.00062137119) order by distance ASC"),

							@NamedQuery(name = "getStoresIfLocKnown", query = "SELECT NEW com.slocamo.entity.client.Store(s, (((acos(sin((:lat*pi()/180))"
									+ " *sin((s.location_latitude*pi()/180))+cos((:lat*pi()/180))*"
									+ "cos((s.location_latitude*pi()/180))*cos(((:lng-s.location_longitude)*pi()/180))))"
									+ "*180/pi())*60*1.1515) as distance) FROM Store s order by distance ASC"),

									@NamedQuery(name = "getStoresCountIfLocNotKnown", query = "select s from Store s order by s.store_title"),

									@NamedQuery(name = "getStoreCount", query = "select count(*) from Store s"), 
									@NamedQuery(name = "getFavStores", query = "select s from Store s where s.idstore in :storeIds"),
									@NamedQuery(name="getStoresAfterTime", query="select s from Store s where s.last_update_date > :createdAfterTime"),
									@NamedQuery(name = "getStoresAfterTimeLocKnown", query = "SELECT NEW com.slocamo.entity.client.Store(s, (((acos(sin((:lat*pi()/180))"
											+ " *sin((s.location_latitude*pi()/180))+cos((:lat*pi()/180))*"
											+ "cos((s.location_latitude*pi()/180))*cos(((:lng-s.location_longitude)*pi()/180))))"
											+ "*180/pi())*60*1.1515) as distance) FROM Store s where s.last_update_date > :createdAfterTime order by distance ASC"),
									@NamedQuery(name = "getStoresIfZoomed", query="SELECT s FROM Store s WHERE s.location_latitude BETWEEN trim(:endLat) AND trim(:startLat)" +
											" AND s.location_longitude BETWEEN trim(:endLng) AND trim(:startLng)")
})
@JsonSerialize(include=Inclusion.NON_DEFAULT)
public class Store {
	public static final String SEARCH_STORE = "searchStore";
	public static final String SEARCH_STORE_COUNT = "searchStoreCount";
	public static final String GET_STORES_MAP_IF_LOC_KNOWN = "getStoresMapIfLocKnown";
	public static final String GET_STORES_IF_LOC_KNOWN = "getStoresIfLocKnown";
	public static final String GET_STORES_IF_LOC_NOT_KNOWN = "getStoresIfLocNotKnown";
	public static final String GET_STORE_COUNT = "getStoreCount";
	public static final String GET_FAV_STORES = "getFavStores";
	public static final String GET_STORES_AFTER_TIME = "getStoresAfterTime";
	public static final String GET_STORES_AFTER_TIME_LOC_KNOWN = "getStoresAfterTimeLocKnown";
	public static final String GET_STORES_IF_ZOOMED = "getStoresIfZoomed";
	public Store() {

	}

	public Store(Store s, Double distance) {
		this.setDistance(distance);
		this.idstore = s.idstore;
		this.is_active = s.is_active;
		this.location_latitude = s.location_latitude;
		this.location_longitude = s.location_longitude;
		this.postal_code = s.postal_code;
		this.store_address = s.store_address;
		this.store_address2 = s.store_address2;
		this.store_city = s.store_city;
		this.store_contact = s.store_contact;
		this.store_country = s.store_country;
		this.store_county = s.store_county;
		this.store_state = s.store_state;
		this.store_title = s.store_title;
		this.uid = s.uid;
		this.last_update_date = s.last_update_date;
		this.store_image_url = s.store_image_url;
	}

	@Id
	private Long idstore;
	@Column
	private String store_title;
	@Column
	private String store_address;
	@Column
	private String store_address2;
	private String store_city;
	private String store_state;
	private String store_country;
	private Double location_latitude;
	private Double location_longitude;
	private Long uid;
	// As it is not required to be displayed
	// private Long idcredit_card;
	private String store_contact;
	private String postal_code;
	private String store_county;
	private Boolean is_active;
	private Long last_update_date;
	private String store_image_url;
	@Transient
	private Double distance;

	/**
	 * @return the idstore
	 */
	public Long getIdstore() {
		return idstore;
	}

	/**
	 * @param idstore
	 *            the idstore to set
	 */
	public void setIdstore(Long idstore) {
		this.idstore = idstore;
	}

	/**
	 * @return the store_title
	 */
	public String getStore_title() {
		return store_title;
	}

	/**
	 * @param store_title
	 *            the store_title to set
	 */
	public void setStore_title(String store_title) {
		this.store_title = store_title;
	}

	/**
	 * @return the store_address
	 */
	public String getStore_address() {
		return store_address;
	}

	/**
	 * @param store_address
	 *            the store_address to set
	 */
	public void setStore_address(String store_address) {
		this.store_address = store_address;
	}

	/**
	 * @return the store_address2
	 */
	public String getStore_address2() {
		return store_address2;
	}

	/**
	 * @param store_address2
	 *            the store_address2 to set
	 */
	public void setStore_address2(String store_address2) {
		this.store_address2 = store_address2;
	}

	/**
	 * @return the store_city
	 */
	public String getStore_city() {
		return store_city;
	}

	/**
	 * @param store_city
	 *            the store_city to set
	 */
	public void setStore_city(String store_city) {
		this.store_city = store_city;
	}

	/**
	 * @return the store_state
	 */
	public String getStore_state() {
		return store_state;
	}

	/**
	 * @param store_state
	 *            the store_state to set
	 */
	public void setStore_state(String store_state) {
		this.store_state = store_state;
	}

	/**
	 * @return the store_country
	 */
	public String getStore_country() {
		return store_country;
	}

	/**
	 * @param store_country
	 *            the store_country to set
	 */
	public void setStore_country(String store_country) {
		this.store_country = store_country;
	}

	/**
	 * @return the location_latitude
	 */
	public Double getLocation_latitude() {
		return location_latitude;
	}

	/**
	 * @param location_latitude
	 *            the location_latitude to set
	 */
	public void setLocation_latitude(Double location_latitude) {
		this.location_latitude = location_latitude;
	}

	/**
	 * @return the location_longitude
	 */
	public Double getLocation_longitude() {
		return location_longitude;
	}

	/**
	 * @param location_longitude
	 *            the location_longitude to set
	 */
	public void setLocation_longitude(Double location_longitude) {
		this.location_longitude = location_longitude;
	}

	/**
	 * @return the uid
	 */
	public Long getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}

	/**
	 * @return the store_contact
	 */
	public String getStore_contact() {
		return store_contact;
	}

	/**
	 * @param store_contact
	 *            the store_contact to set
	 */
	public void setStore_contact(String store_contact) {
		this.store_contact = store_contact;
	}

	/**
	 * @return the postal_code
	 */
	public String getPostal_code() {
		return postal_code;
	}

	/**
	 * @param postal_code
	 *            the postal_code to set
	 */
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	/**
	 * @return the store_county
	 */
	public String getStore_county() {
		return store_county;
	}

	/**
	 * @param store_county
	 *            the store_county to set
	 */
	public void setStore_county(String store_county) {
		this.store_county = store_county;
	}

	/**
	 * @return the is_active
	 */
	public Boolean getIs_active() {
		return is_active;
	}

	/**
	 * @param is_active
	 *            the is_active to set
	 */
	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	/**
	 * @return the distance
	 */
	public Double getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(Double distance) {
		this.distance = distance;
	}

	/**
	 * @return the last_update_date
	 */
	public Long getLast_update_date() {
		return last_update_date;
	}

	/**
	 * @param last_update_date the last_update_date to set
	 */
	public void setLast_update_date(Long last_update_date) {
		this.last_update_date = last_update_date;
	}

	/**
	 * @return the store_image_url
	 */
	public String getStore_image_url() {
		return store_image_url;
	}

	/**
	 * @param store_image_url the store_image_url to set
	 */
	public void setStore_image_url(String store_image_url) {
		this.store_image_url = store_image_url;
	}
}
