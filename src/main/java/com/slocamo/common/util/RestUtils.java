package com.slocamo.common.util;

import javax.servlet.http.HttpServletRequest;

import com.slocamo.entity.analytics.LoggingRequest;


/**
 * @author vvr@slocamo.com
 * 
 */
public class RestUtils {
	/**
	 * @param str
	 * @return Checks if the string is empty. Returns true if empty, false
	 *         otherwise.
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().equals(""));
	}

	public static void addDetailsForLoggingRequest(HttpServletRequest request,
			LoggingRequest loggingRequest) {
		loggingRequest.setIpAddress((String) request.getAttribute("ipAddress"));
		loggingRequest.setUid(""+request.getAttribute("uid"));
	}
}
