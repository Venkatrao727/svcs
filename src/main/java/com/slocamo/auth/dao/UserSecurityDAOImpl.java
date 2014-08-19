package com.slocamo.auth.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.slocamo.auth.bean.SessionUser;
import com.slocamo.auth.bean.entity.Auth;
import com.slocamo.auth.bean.entity.Session;
import com.slocamo.common.BaseDAO;

/**
 * @author vvr@slocamo.com
 *
 */
@Repository("userSecurityDAO")
public class UserSecurityDAOImpl extends BaseDAO implements UserSecurityDAO {
	Logger logger = Logger.getLogger(this.getClass());
	/**
	 * @param partnerId
	 * @return credentials for a partnerId, , if exists. Otherwise returns null.
	 */
	public UserDetails getUserByPartnerId(String partnerId) {
		Auth auth = (Auth) find(Auth.class, partnerId);
		if (auth == null) {
			return null;
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("Got partnerId and apiKey :"+auth);
		}
		return new User(auth.getPartner_id(), auth.getApi_key(),
				new ArrayList<GrantedAuthority>());
	}

	/** 
     * @param sessionKey
     * @return session credentials for a sessionKey, if exists. Otherwise returns null 
     */
	public UserDetails getSessionByKey(String sessionKey) {
		Session session = (Session) find(Session.class, sessionKey);
		if (session == null) {
			return null;
		}
		if(logger.isDebugEnabled()) {
			logger.debug("Got session details: "+session);
		}
		return new SessionUser(session.getSession_key(), session.getSession_secret(), session.getUid(),
				new ArrayList<GrantedAuthority>());
	}

	/**
	 * Adds session key and value pair. This pair serves as a token for the user to communicate.
	 * @param sessionKey
	 * @param sessionSecret
	 */
	public Session addSessionDetails(Session session) {
		persist(session);
		return session;
	}

}
