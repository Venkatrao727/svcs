package com.slocamo.common;

public class GlobalVars {

	private static String imageUrl;
	private static String baseUrl;
	private static String apiKey;
	/**
	 * @return the imageUrl
	 */
	public static String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public static void setImageUrl(String imageUrl) {
		GlobalVars.imageUrl = imageUrl;
	}

	/**
	 * @return the apiKey
	 */
	public static String getApiKey() {
		return apiKey;
	}

	/**
	 * @param apiKey the apiKey to set
	 */
	public static void setApiKey(String apiKey) {
		GlobalVars.apiKey = apiKey;
	}

	/**
	 * @return the baseUrl
	 */
	public static String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @param baseUrl the baseUrl to set
	 */
	public static void setBaseUrl(String baseUrl) {
		GlobalVars.baseUrl = baseUrl;
	}
}
