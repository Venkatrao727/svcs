package com.slocamo.entity.client;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vvr@slocamo.com
 *
 */
@Entity
@Table(name = "S_user_favourite_stores")
public class UserFavouriteStores {
	@Id
	private Long uid;
	private String fav_stores;

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
	 * @return the fav_stores
	 */
	public String getFav_stores() {
		return fav_stores;
	}

	/**
	 * @param fav_stores
	 *            the fav_stores to set
	 */
	public void setFav_stores(String fav_stores) {
		this.fav_stores = fav_stores;
	}
}
