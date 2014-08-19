package com.slocamo.auth.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slocamo.auth.bean.entity.Session;
import com.slocamo.auth.dao.UserSecurityDAO;
import com.slocamo.auth.exception.UserNotFoundException;
import com.slocamo.entity.analytics.LoggingRequest;

@Service(value = "userSecurityService")
public class UserSecurityServiceImpl implements UserSecurityService {

	private UserSecurityDAO userSecurityDAO;
	private Logger logger = Logger.getLogger(this.getClass());

	@Transactional
	public UserDetails getUserByPartnerId(String PartnerId)
			throws UserNotFoundException {
		UserDetails userDetails = userSecurityDAO.getUserByPartnerId(PartnerId);

		if (userDetails == null) {
			String errorMsg = "User could not be found with the supplied partnerID :"
					+ PartnerId;
			logger.error(errorMsg);
			throw new UserNotFoundException(errorMsg);
		}

		return userDetails;
	}

	@Transactional
	public UserDetails getUserBySessionKey(String sessionKey)
			throws UserNotFoundException {
		UserDetails userDetails = userSecurityDAO.getSessionByKey(sessionKey);
		if (userDetails == null) {
			String errorMsg = "User could not be found with the supplied session key : "
					+ sessionKey;
			logger.error(errorMsg);
			throw new UserNotFoundException(errorMsg);
		}

		return userDetails;
	}

	@Transactional
	public Session addSessionDetails(LoggingRequest loggingRequest, Session session) {
		if (logger.isDebugEnabled()) {
			logger.debug("Persisting (sessionKey, sessionSecret) : ("
					+ session.getSession_key() + " , " + session.getSession_secret() + ")");
		}
		return userSecurityDAO.addSessionDetails(session);
	}

	/**
	 * @param userSecurityDAO
	 *            the userSecurityDAO to set
	 */
	@Autowired
	public void setUserSecurityDAO(UserSecurityDAO userSecurityDAO) {
		this.userSecurityDAO = userSecurityDAO;
	}

}
