package com.slocamo.analytics.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.slocamo.analytics.dao.AnalyticsDAO;
import com.slocamo.beans.analytics.AnalyticsRequest;
import com.slocamo.client.service.CouponService;
import com.slocamo.client.service.UserService;

@Transactional
public class AnalyticsServiceImpl implements AnalyticsService {
	private AnalyticsDAO analyticsDAO;
	private CouponService couponService;
	private UserService userService;

	@Override
	public Object getCampaignStats(AnalyticsRequest requestParams) {
		@SuppressWarnings("unchecked")
		List<Object> couponList = (List<Object>) couponService
				.getCouponIdsForRange(Long.valueOf(requestParams.getStoreId()),
						requestParams.getFrom(), requestParams.getTo());
		if (couponList.isEmpty()) {
			return null;
		}
		return analyticsDAO.getCampaignStats(requestParams, couponList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getHourlyVisitStats(AnalyticsRequest requestParams) {
		return formatStats((List<Object>) analyticsDAO
				.getHourlyVisitStats(requestParams));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getSatisfactionStats(AnalyticsRequest requestParams) {
		return formatStats((List<Object>) userService
				.getUserFeedbackWithDateRange(
						Long.valueOf(requestParams.getStoreId()),
						requestParams.getFrom(), requestParams.getTo()));
	}

	@Override
	public Object getTotalVisitStats(AnalyticsRequest requestParams) {
		return analyticsDAO.getTotalVisitStats(requestParams);
	}

	@Override
	public Object getVisitorTypeStats(AnalyticsRequest requestParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getVisitsByAgeStats(AnalyticsRequest requestParams) {
		return formatStats((List<Object>) analyticsDAO
				.getVisitsByAgeStats(requestParams));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getVisitsByGenderStats(AnalyticsRequest requestParams) {
		return formatStats((List<Object>) analyticsDAO
				.getVisitsByGenderStats(requestParams));
	}

	@Override
	public Object getComments(AnalyticsRequest requestParams) {
		return userService.getUserCommentsWithDateRange(
				Long.valueOf(requestParams.getStoreId()),
				requestParams.getFrom(), requestParams.getTo());
	}

	@Autowired
	public void setAnalyticsDAO(AnalyticsDAO analyticsDAO) {
		this.analyticsDAO = analyticsDAO;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @param couponService
	 *            the couponService to set
	 */
	@Autowired
	public void setCouponService(CouponService couponService) {
		this.couponService = couponService;
	}

	private Object formatStats(List<Object> unformattedList) {
		Map<Object, Object> stats = new HashMap<Object, Object>();
		for (Object entry : unformattedList) {
			Object[] record = (Object[]) entry;
			if (record[0] == null) {
				stats.put("unknown", record[1]);
			} else {
				stats.put(record[0], record[1]);
			}
		}
		return stats;
	}
}
