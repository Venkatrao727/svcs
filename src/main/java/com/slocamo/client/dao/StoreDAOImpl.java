package com.slocamo.client.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.slocamo.common.BaseDAO;
import com.slocamo.common.util.RestUtils;
import com.slocamo.entity.client.Store;
import com.slocamo.entity.client.UserFavouriteStores;

/**
 * @author vvr@slocamo.com
 * 
 */
@SuppressWarnings("unchecked")
@Repository(value = "storeDAO")
public class StoreDAOImpl extends BaseDAO implements StoreDAO {

	private Logger mLogger = Logger.getLogger(StoreDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.slocamo.rest.dao.StoreDAO#getSearchStoreResults(java.lang.String,
	 * java.lang.Integer, java.lang.Integer)
	 */
	public List<Store> getSearchStoreResults(String searchParam, Integer limit,
			Integer offset) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchString", "%" + searchParam + "%");
		if (mLogger.isDebugEnabled()) {
			mLogger.debug("search string : " + searchParam);
		}
		return (List<Store>) executeToList(Store.SEARCH_STORE, limit, offset,
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.slocamo.rest.dao.StoreDAO#getSearchStoreCount(java.lang.String)
	 */
	public Long getSearchStoreCount(String searchString) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchString", "%" + searchString + "%");
		return (Long) executeToList(Store.SEARCH_STORE_COUNT, null, null,
				params).get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.slocamo.rest.dao.StoreDAO#getStoresMap(java.lang.Double,
	 * java.lang.Double, java.lang.Double)
	 */
	public List<Store> getStoresMap(Double lat, Double lng, Double rangeInMeters) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lat", lat);
		params.put("lng", lng);
		params.put("rangeInMeters", rangeInMeters);
		return (List<Store>) executeToList(Store.GET_STORES_MAP_IF_LOC_KNOWN,
				null, null, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.slocamo.rest.dao.StoreDAO#getStoresList(java.lang.Double,
	 * java.lang.Double, java.lang.Integer, java.lang.Integer)
	 */
	public List<Store> getStoresList(Double lat, Double lng, Integer limit,
			Integer offset) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lat", lat);
		params.put("lng", lng);
		return (List<Store>) executeToList(Store.GET_STORES_IF_LOC_KNOWN,
				limit, offset, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.slocamo.rest.dao.StoreDAO#getStoresList(java.lang.Integer,
	 * java.lang.Integer)
	 */
	public List<Store> getStoresList(Integer limit, Integer offset) {
		return (List<Store>) executeToList(Store.GET_STORES_IF_LOC_NOT_KNOWN,
				offset, limit, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.slocamo.rest.dao.StoreDAO#getStoreCount()
	 */
	public Long getStoreCount() {
		// We are always sure that count always returns a value
		List<Object> list = (List<Object>) executeToList(Store.GET_STORE_COUNT,
				null, null, null);
		return (Long) list.get(0);
	}

	// ----------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.slocamo.rest.dao.StoreDAO#getStoresMap(java.lang.Double,
	 * java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	public List<Store> getStoresMap(Double startLat, Double startLng,
			Double endLat, Double endLng) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startLat", startLat);
		params.put("startLng", startLng);
		params.put("endLat", endLat);
		params.put("endLng", endLng);
		return (List<Store>) executeToList(Store.GET_STORES_IF_ZOOMED, null, null, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.slocamo.rest.dao.StoreDAO#getFavouriteStores(java.lang.Long)
	 */
	public List<Store> getFavouriteStores(Long uid) {
		UserFavouriteStores userFavouriteStores = (UserFavouriteStores) find(
				UserFavouriteStores.class, uid);
		if (userFavouriteStores == null
				|| RestUtils.isEmpty(userFavouriteStores.getFav_stores())) {
			return null;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		String favStores = userFavouriteStores.getFav_stores();
		String favStrList[] = favStores.split(",");
		List<Long> storeList = new ArrayList<Long>();
		for (String favStr : favStrList) {
			storeList.add(Long.valueOf(favStr));
		}
		params.put("storeIds", storeList);
		return (List<Store>) executeToList(Store.GET_FAV_STORES, null, null,
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.slocamo.rest.dao.StoreDAO#deleteFavStore(java.lang.Long,
	 * java.lang.Long)
	 */
	public void deleteFavStore(Long uid, Long storeId) {
		UserFavouriteStores userFavouriteStores = (UserFavouriteStores) find(
				UserFavouriteStores.class, uid);
		String favStores = userFavouriteStores.getFav_stores();
		favStores = favStores.replaceAll("" + storeId, "")
				.replaceAll(",,", ",");
		// If no stores left, setting the favStores to null
		if (favStores.trim().isEmpty()) {
			userFavouriteStores.setFav_stores(null);
		}

		// If store is there at the start
		if (favStores.startsWith(",")) {
			favStores = favStores.substring(1);
		}

		// If store is there at the end
		if (favStores.endsWith(",")) {
			favStores = favStores.substring(0, favStores.length() - 1);
		}
		userFavouriteStores.setFav_stores(favStores);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.slocamo.rest.dao.StoreDAO#postFavStore(java.lang.Long,
	 * java.lang.Long)
	 */
	public void postFavStore(Long uid, Long storeId) {
		UserFavouriteStores userFavouriteStores = (UserFavouriteStores) find(
				UserFavouriteStores.class, uid);
		if (userFavouriteStores == null) {
			UserFavouriteStores userFavPersist = new UserFavouriteStores();
			userFavPersist.setFav_stores("" + storeId);
			userFavPersist.setUid(uid);
			persist(userFavPersist);
			return;
		}

		String favStores = userFavouriteStores.getFav_stores();
		if (RestUtils.isEmpty(userFavouriteStores.getFav_stores())) {
			favStores = "" + storeId;
		} else {
			favStores = storeId + "," + favStores;
		}
		userFavouriteStores.setFav_stores(favStores);
	}

	/* (non-Javadoc)
	 * @see com.slocamo.rest.dao.StoreDAO#getStoresCreatedAfterTime(java.lang.Long)
	 */
	@Override
	public List<Store> getStoresCreatedAfterTime(Long createdAfterTime) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("createdAfterTime", createdAfterTime);
		return (List<Store>)executeToList(Store.GET_STORES_AFTER_TIME, null, null, params);
	}

	/* (non-Javadoc)
	 * @see com.slocamo.rest.dao.StoreDAO#getStoresCreatedAfterTime(java.lang.Long, java.lang.Double, java.lang.Double)
	 */
	@Override
	public List<Store> getStoresCreatedAfterTime(Long createdAfterTime,
			Double lat, Double lng) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("createdAfterTime", createdAfterTime);
		params.put("lat", lat);
		params.put("lng", lng);
		return (List<Store>)executeToList(Store.GET_STORES_AFTER_TIME_LOC_KNOWN, null, null, params);
	}

}
