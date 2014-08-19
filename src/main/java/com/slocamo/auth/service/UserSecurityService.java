package com.slocamo.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.slocamo.auth.bean.entity.Session;
import com.slocamo.auth.exception.UserNotFoundException;
import com.slocamo.entity.analytics.LoggingRequest;

/**
 * @author vvr@slocamo.com
 * 
 */
public interface UserSecurityService {

	/**
	 * @param partnerId
	 * @return user by partnerId
	 */
	UserDetails getUserByPartnerId(String partnerId)
			throws UserNotFoundException;

	/**
	 * @param sessionKey
	 * @return Session details by session key
	 */
	UserDetails getUserBySessionKey(String sessionKey)
			throws UserNotFoundException;

	/**
	 * @param sessionKey
	 * @param sessionSecret
	 */
	Session addSessionDetails(LoggingRequest loggingRequest, Session session);
}
