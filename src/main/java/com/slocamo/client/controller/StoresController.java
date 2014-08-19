package com.slocamo.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.slocamo.client.service.StoreService;
import com.slocamo.common.util.RestUtils;

/**
 * @author vvr@slocamo.com
 * 
 */
@Controller
public class StoresController {
	private StoreService storeService;

	/**
	 * @param lat
	 * @param lng
	 * @param offset
	 * @param limit
	 * @param filterType
	 * @param rangeInMeters
	 * @param startLat
	 * @param startLng
	 * @param endLat
	 * @param endLng
	 * @param searchParam
	 * @param count
	 * @return stores for various scenarios. Stores are returned for location
	 *         available/not available cases. Support pagination. Supports both
	 *         list view and map view. This method also supports store search.
	 */
	@RequestMapping(value = { "/stores" }, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Object getStores(
			@RequestParam(required = false, value = "lat") Double lat,
			@RequestParam(required = false, value = "lng") Double lng,
			@RequestParam(required = false, value = "offset") Integer offset,
			@RequestParam(required = false, value = "limit") Integer limit,
			@RequestParam(required = false, value = "type") String type,
			@RequestParam(required = false, value = "range-in-meters") Double rangeInMeters,
			@RequestParam(required = false, value = "start-lat") Double startLat,
			@RequestParam(required = false, value = "start-lng") Double startLng,
			@RequestParam(required = false, value = "end-lat") Double endLat,
			@RequestParam(required = false, value = "end-lng") Double endLng,
			@RequestParam(required = false, value = "search-param") String searchParam,
			@RequestParam(defaultValue = "false", value = "count") boolean count,
			@RequestParam(required = false, value = "created-after-time") Long createdAfterTime,
			@Value("#{request.getAttribute('uid')}") Long uid) {

		// If search param is passed
		if (!RestUtils.isEmpty(searchParam)) {
			return storeService.getSearchStoreResults(searchParam, limit,
					offset, count);
		}

		if ("favs".equals(type)) {
			return storeService.getFavouriteStores(uid);
		}

		if (createdAfterTime != null) {
			return storeService.getStoresCreatedAfterTime(createdAfterTime,
					lat, lng);
		}

		// If lat, lng and range in meters are passed, search for map
		if (lat != null && lng != null && rangeInMeters != null) {
			return storeService.getStoresMap(lat, lng, rangeInMeters);
		}		// if lat, lng are passed then search for the stores in the ascending
		// order of distance. Pagination is supported.
		if (lat != null && lng != null) {
			return storeService.getStoresList(lat, lng, limit, offset, count);
		}

		if (startLat != null && endLat != null && startLng != null
				&& endLng != null) {
			return storeService
					.getStoresMap(startLat, startLng, endLat, endLng);
		}

		return storeService.getStoresList(lat, lng, limit, offset, count);
	}

	@RequestMapping(value = "/stores/favs", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public void postFavStore(@RequestParam(value = "uid") Long uid,
			@RequestParam(value = "store-id") Long storeId) {
		storeService.postFavStore(uid, storeId);
	}

	@RequestMapping(value = "/stores/favs", method = RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteFavStore(@RequestParam(value = "uid") Long uid,
			@RequestParam(value = "store-id") Long storeId) {
		storeService.deleteFavStore(uid, storeId);
	}

	// Not sure if the below method is required.
	/**
	 * @param startLat
	 * @param startLng
	 * @param endLat
	 * @param endLng
	 * @return
	 */
	@RequestMapping(value = { "/storesMapZoomIn" }, method = RequestMethod.GET)
	@ResponseBody
	public Object getStoresMapZoomIn(
			@RequestParam(defaultValue = "0", value = "startLat") double startLat,
			@RequestParam(defaultValue = "0", value = "startLng") double startLng,
			@RequestParam(defaultValue = "0", value = "endLat") double endLat,
			@RequestParam(defaultValue = "0", value = "endLng") double endLng) {
		return storeService.getStoresMap(startLat, startLng, endLat, endLng);

	}

	@Autowired
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
}
