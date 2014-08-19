package com.slocamo.analytics.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.slocamo.beans.analytics.AnalyticsRequest;
import com.slocamo.common.BaseDAO;
import com.slocamo.entity.analytics.LoggingRequest;

public class AnalyticsDAOImpl extends BaseDAO implements AnalyticsDAO {

	@Override
	public Object getTotalVisitStats(AnalyticsRequest requestParams) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeId", requestParams.getStoreId());
		params.put("from", requestParams.getFrom());
		params.put("to", requestParams.getTo());
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) executeToList(
				LoggingRequest.GET_TOTAL_VISITS, null, null, params);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getHourlyVisitStats(AnalyticsRequest requestParams) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeId", requestParams.getStoreId());
		params.put("from", requestParams.getFrom());
		params.put("to", requestParams.getTo());
		return (List<Object>) executeToList(
				LoggingRequest.GET_HOURLY_VISITS, null, null, params);
	}
	@Override
	public Object getVisitsByGenderStats(AnalyticsRequest requestParams) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeId", requestParams.getStoreId());
		params.put("from", requestParams.getFrom());
		params.put("to", requestParams.getTo());
		return executeToList(
				LoggingRequest.GET_VISITS_BY_GENDER, null, null, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getCampaignStats(AnalyticsRequest requestParams, List<Object> coupons) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("from", requestParams.getFrom());
		params.put("to", requestParams.getTo());
		
		Map<String, String> couponData = new HashMap<String, String>();
		params.put("couponIds", translateToString(coupons, couponData));
		params.put("notificationType", "ACTION_REDEEM");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(
				"redeem",
				formatCampaigns(
						(List<Object>) executeToList(
								LoggingRequest.GET_CAMPAIGN, null, null, params),
						couponData));
		params.put("notificationType", "ACTION_SHARE");
		result.put(
				"share",
				formatCampaigns(
						(List<Object>) executeToList(
								LoggingRequest.GET_CAMPAIGN, null, null, params),
						couponData));
		return result;

	}

	private Object formatCampaigns(List<Object> list,
			Map<String, String> couponData) {
		Map<String, Object> finalList = new HashMap<String, Object>();
		for (Object obj : list) {
			Object[] objArr = (Object[]) obj;
			finalList.put(couponData.get((String) objArr[0]), objArr[1]);
		}
		return finalList;
	}

	@Override
	public Object getVisitsByAgeStats(AnalyticsRequest requestParams) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeId", requestParams.getStoreId());
		params.put("from", requestParams.getFrom());
		params.put("to", requestParams.getTo());
		return  executeToList(
				LoggingRequest.GET_VISITS_BY_AGE, null, null, params);
	}

	private List<String> translateToString(List<Object> coupons,
			Map<String, String> couponData) {
		List<String> result = new ArrayList<String>();
		for (Object coupon : coupons) {
			Object[] couponArr = (Object[]) coupon;
			String couponIdStr = String.valueOf((Long) couponArr[0]);
			result.add(couponIdStr);
			couponData.put(couponIdStr, (String) couponArr[1]);
		}
		return result;
	}

}
