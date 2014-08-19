package com.slocamo.beans.analytics;


public enum RequestType {
	VISITOR_TYPES(1, "visitortype"),

	VISITS_BY_GENDER(2, "visitsbygender"),

	TOTAL_VISITS(3, "totalvisits"),

	SATISFACTION(4, "satisfaction"),

	CAMPAIGN(5, "campaign"),

	HOURLY_VISITS(6, "hourlyvisits"),

	VISITS_BY_AGE(7, "visitsbyage"),

	COMMENTS(8, "comments");

	RequestType(int id, String type) {
		this.id = id;
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public static RequestType getByType(String type) {
		for (RequestType requestType : values()) {
			if (requestType.getType().equalsIgnoreCase(type)) {
				return requestType;
			}
		}
		return null;
	}

	public int getID() {
		return this.id;
	}

	private int id;
	private String type;
}
