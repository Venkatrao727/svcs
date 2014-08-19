package com.slocamo.client.service;

import org.springframework.stereotype.Service;

/**
 * @author vvr@slocamo.com
 * 
 */
@Service
public interface StoreService {
	/**
	 * @param searchParam
	 * @param limit
	 * @param offset
	 * @param count
	 * @return the stores that matched the search param. limit and offset
	 *         support the pagination. count if true, returns count of search
	 *         results along with the result.
	 */
	Object getSearchStoreResults(String searchParam, Integer limit,
			Integer offset, boolean count);

	/**
	 * @param lat
	 * @param lng
	 * @param limit
	 * @param offset
	 * @param count
	 * @return stores in the increasing order of distance from
	 *         current location. If location details are not sent then it
	 *         returns all the stores available. Support pagination.
	 */
	Object getStoresList(Double lat, Double lng, Integer limit, Integer offset,
			boolean count);

	/**
	 * @param lat
	 * @param lng
	 * @param rangeInMeters
	 * @return stores that are fall in the perimeter. 'rangeInMeters' determines that.
	 */
	Object getStoresMap(Double lat, Double lng, Double rangeInMeters);

	/**
	 * @param startLat
	 * @param startLng
	 * @param endLat
	 * @param endLng
	 * @return
	 */
	Object getStoresMap(Double startLat, Double startLng, Double endLat,
			Double endLng);

	/**
	 * @param uid
	 * @return
	 */
	Object getFavouriteStores(Long uid);

	/**
	 * @param uid
	 * @param storeId
	 */
	void postFavStore(Long uid, Long storeId);

	/**
	 * @param uid
	 * @param storeId
	 */
	void deleteFavStore(Long uid, Long storeId);

	/**
	 * @param createdAfterTime
	 * @param lat
	 * @param lng
	 * @return
	 */
	Object getStoresCreatedAfterTime(Long createdAfterTime, Double lat,
			Double lng);
}
