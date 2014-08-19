package com.slocamo.client.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slocamo.beans.client.Stores;
import com.slocamo.client.dao.StoreDAO;
import com.slocamo.common.util.RestUtils;

@Service(value = "storeService")
public class StoreServiceImpl implements StoreService {
	private StoreDAO storeDAO;
	Logger logger = Logger.getLogger(this.getClass());

	/**
	 * @param storeDAO
	 */
	@Autowired
	public void setStoreDAO(StoreDAO storeDAO) {
		this.storeDAO = storeDAO;
	}

	/* (non-Javadoc)
	 * @see com.slocamo.rest.service.StoreService#getSearchStoreResults(java.lang.String, java.lang.Integer, java.lang.Integer, boolean)
	 */
	@Transactional
	public Object getSearchStoreResults(String searchParam, Integer limit,
			Integer offset, boolean count) {
		if (RestUtils.isEmpty(searchParam)) {
			throw new IllegalArgumentException("Search param is sent null");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("querying for stores with seach param: " + searchParam
					+ " offset: " + offset + " limit: " + limit+" count: "+count);
		}
		Stores stores = new Stores();
		stores.setStores(storeDAO.getSearchStoreResults(searchParam, limit,
				offset));
		if (count) {
			stores.setCount(storeDAO.getSearchStoreCount(searchParam));
		}
		return stores;
	}

	/* (non-Javadoc)
	 * @see com.slocamo.rest.service.StoreService#getStoresList(java.lang.Double, java.lang.Double, java.lang.Integer, java.lang.Integer, boolean)
	 */
	@Transactional
	public Object getStoresList(Double lat, Double lng, Integer limit,
			Integer offset, boolean count) {
		Stores stores = new Stores();
		if (logger.isDebugEnabled()) {
			logger.debug("querying for stores with lat: " + lat+" lng: "+lng
					+ " offset: " + offset + " limit: " + limit+" count: "+count);
		}
		stores.setStores(storeDAO.getStoresList(lat, lng, limit, offset));
		if (count) {
			stores.setCount(storeDAO.getStoreCount());
		}
		return stores;
	}

	/* (non-Javadoc)
	 * @see com.slocamo.rest.service.StoreService#getStoresMap(java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Transactional
	public Object getStoresMap(Double lat, Double lng, Double rangeInMeters) {
		Stores stores = new Stores();
		if (logger.isDebugEnabled()) {
			logger.debug("querying for stores with lat: " + lat+" lng: "+lng
				+" rangeInMeters: "+rangeInMeters);
		}
		stores.setStores(storeDAO.getStoresMap(lat, lng, rangeInMeters));
		return stores;
	}

	/* (non-Javadoc)
	 * @see com.slocamo.rest.service.StoreService#getStoresMap(java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Transactional
	public Object getStoresMap(Double startLat, Double startLng, Double endLat,
			Double endLng) {
		Stores stores = new Stores();
		stores.setStores(storeDAO.getStoresMap(startLat, startLng, endLat,
				endLng));
		return stores;
	}

	@Transactional
	public Object getFavouriteStores(Long uid) {
		Stores stores = new Stores();
		stores.setStores(storeDAO.getFavouriteStores(uid));
		return stores;
	}

	@Transactional
	public void postFavStore(Long uid, Long storeId) {
		storeDAO. postFavStore(uid,storeId);
		
	}

	@Transactional
	public void deleteFavStore(Long uid, Long storeId) {
		storeDAO.deleteFavStore(uid, storeId);
		
	}

	@Transactional
	public Object getStoresCreatedAfterTime(Long createdAfterTime, Double lat,
			Double lng) {
		Stores stores = new Stores();
		if(lat == null || lng == null) {
			stores.setStores(storeDAO.getStoresCreatedAfterTime(createdAfterTime));
		}
		stores.setStores(storeDAO.getStoresCreatedAfterTime(createdAfterTime, lat, lng));
		return stores;
	}
}
