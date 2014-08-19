package com.slocamo.analytics.endpoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.slocamo.analytics.services.AnalyticsService;
import com.slocamo.beans.analytics.AnalyticsRequest;
import com.slocamo.beans.analytics.RequestType;

@Controller
public class AnalyticsEndPoint {
	private AnalyticsService analyticsService;
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/store/{storeId}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Object getAnalytics(@PathVariable(value = "storeId") String storeId,
			@ModelAttribute(value = "reqParams") AnalyticsRequest requestParams) {
		requestParams.setStoreId(storeId);

		if (storeId == null) {
			throw new IllegalArgumentException("store Id is mandatory");
		}
		if (requestParams.getType() == null
				|| requestParams.getType().isEmpty()) {
			throw new IllegalArgumentException("type param is mandatory");
		}

		if (requestParams.getFrom() == null || requestParams.getTo() == null) {
			throw new IllegalArgumentException(
					"From and to params are mandatory");
		}
		Map<String, Object> resultsMap = new HashMap<String, Object>();
		List<RequestType> reqestTypes = getType(requestParams.getType());

		if (reqestTypes.isEmpty()) {
			throw new IllegalArgumentException("Invalid type param is passed");
		}

		for (RequestType requestType : reqestTypes) {
			resultsMap.put(requestType.getType(),
					getStatsForRequest(requestType, requestParams));
		}
		return resultsMap;
	}

	private Object getStatsForRequest(RequestType requestType,
			AnalyticsRequest requestParams) {
		switch (requestType) {
		case COMMENTS:
			return analyticsService.getComments(requestParams);
		case CAMPAIGN:
			return analyticsService.getCampaignStats(requestParams);
		case HOURLY_VISITS:
			return analyticsService.getHourlyVisitStats(requestParams);
		case SATISFACTION:
			return analyticsService.getSatisfactionStats(requestParams);
		case TOTAL_VISITS:
			return analyticsService.getTotalVisitStats(requestParams);
		case VISITOR_TYPES:
			return analyticsService.getVisitorTypeStats(requestParams);
		case VISITS_BY_AGE:
			return analyticsService.getVisitsByAgeStats(requestParams);
		case VISITS_BY_GENDER:
			return analyticsService.getVisitsByGenderStats(requestParams);
		default:
			return null;
		}
	}

	private List<RequestType> getType(String type) {
		if (type == null) {
			return null;
		}
		List<RequestType> requestTypes = new ArrayList<RequestType>();
		StringTokenizer stringTokenizer = new StringTokenizer(type, ",");
		while (stringTokenizer.hasMoreTokens()) {
			try {
				RequestType requestType = RequestType.getByType(stringTokenizer
						.nextToken());
				if (requestType != null) {
					requestTypes.add(requestType);
				}
			} catch (Exception ex) {
				log.error("Invalid type for getting analytics data is passed, excetion is"
						+ ex);
			}

		}
		return requestTypes;
	}

	/**
	 * @param analyticsService
	 *            the analyticsService to set
	 */
	@Autowired
	public void setAnalyticsService(AnalyticsService analyticsService) {
		this.analyticsService = analyticsService;
	}
}
