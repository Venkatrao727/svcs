package com.slocamo.client.dao;

import java.util.List;

import com.slocamo.entity.client.Store;

/**
 * @author vvr@slocamo.com
 * 
 */
public interface StoreDAO {

	/**
	 * @param searchParam
	 * @param limit
	 * @param offset
	 * @return all store results that matched the search param.
	 */
	List<Store> getSearchStoreResults(String searchParam, Integer limit,
			Integer offset);

	/**
	 * @param searchString
	 * @return count of stores for a search string
	 */
	Long getSearchStoreCount(String searchString);

	/**
	 * @param lat
	 * @param lng
	 * @param limit
	 * @param offset
	 * @return a list of stores, support pagination. Used when location is
	 *         known.
	 */
	List<Store> getStoresList(Double lat, Double lng, Integer limit,
			Integer offset);

	/**
	 * @param limit
	 * @param offset
	 * @return a list of stores, support pagination. Used to return all stores.
	 *         Generally called when lat and lng are not available.
	 */
	List<Store> getStoresList(Integer limit, Integer offset);

	/**
	 * @param lat
	 * @param lng
	 * @param rangeInMeters
	 * @return all the stores with a perimeter. rangeInMeters is the radius that determines the perimeter.
	 */
	List<Store> getStoresMap(Double lat, Double lng, Double rangeInMeters);

	/**
	 * @param startLat
	 * @param startLng
	 * @param endLat
	 * @param endLng
	 * @return all the stores between two locations.
	 */
	List<Store> getStoresMap(Double startLat, Double startLng, Double endLat,
			Double endLng);

	/**
	 * @return total count of stores.
	 */
	Long getStoreCount();

	/**
	 * @param uid
	 * @return
	 */
	List<Store> getFavouriteStores(Long uid);

	/**
	 * @param uid
	 * @param storeId
	 */
	void deleteFavStore(Long uid, Long storeId);

	/**
	 * @param uid
	 * @param storeId
	 */
	void postFavStore(Long uid, Long storeId);

	/**
	 * @param createdAfterTime
	 * @return
	 */
	List<Store> getStoresCreatedAfterTime(Long createdAfterTime);

	List<Store> getStoresCreatedAfterTime(Long createdAfterTime, Double lat,
			Double lng);

}
