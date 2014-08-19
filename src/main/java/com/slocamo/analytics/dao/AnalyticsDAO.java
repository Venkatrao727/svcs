package com.slocamo.analytics.dao;

import java.util.List;

import com.slocamo.beans.analytics.AnalyticsRequest;

public interface AnalyticsDAO {

	Object getTotalVisitStats(AnalyticsRequest requestParams);

	Object getHourlyVisitStats(AnalyticsRequest requestParams);

	Object getVisitsByGenderStats(AnalyticsRequest requestParams);

	Object getCampaignStats(AnalyticsRequest requestParams, List<Object> couponList);

	Object getVisitsByAgeStats(AnalyticsRequest requestParams);

}
