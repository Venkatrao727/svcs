package com.slocamo.analytics.services;

import com.slocamo.beans.analytics.AnalyticsRequest;

public interface AnalyticsService {

	Object getCampaignStats(AnalyticsRequest requestParams);

	Object getHourlyVisitStats(AnalyticsRequest requestParams);

	Object getSatisfactionStats(AnalyticsRequest requestParams);

	Object getTotalVisitStats(AnalyticsRequest requestParams);

	Object getVisitorTypeStats(AnalyticsRequest requestParams);

	Object getVisitsByAgeStats(AnalyticsRequest requestParams);

	Object getVisitsByGenderStats(AnalyticsRequest requestParams);

	Object getComments(AnalyticsRequest requestParams);

}
