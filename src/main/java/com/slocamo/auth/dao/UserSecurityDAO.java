package com.slocamo.auth.dao;

import org.springframework.security.core.userdetails.UserDetails;

import com.slocamo.auth.bean.entity.Session;

/**
 * @author vvr@slocamo.com
 *
 */
public interface UserSecurityDAO {

	/**
	 * @param partnerId
	 * @return credentials for a partnerId, , if exists. Otherwise returns null.
	 */
	UserDetails getUserByPartnerId(String partnerId);
	
    /**
     * @param sessionKey
     * @return session credentials for a sessionKey, if exists. Otherwise returns null 
     */
    UserDetails getSessionByKey(String sessionKey);
    
	/**
	 * Adds session key and value pair. This pair serves as a token for the user to communicate.
	 * @param sessionKey
	 * @param sessionSecret
	 */
	Session addSessionDetails(Session session);
}
