package com.slocamo.auth.security;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
/**
 * @author vvr@slocamo.com
 * 
 */
public class AnalyticsDAOAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {

	private Logger logger = Logger.getLogger(this.getClass());
	private String key;
	/**
	 * This method, based on the apikey/session key retrieves the details. If no
	 * record is found, we immediately throw an exception and exit the
	 * autorization process
	 */

	@Override
	protected UserDetails retrieveUser(String key,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		return new User(this.key, "", new ArrayList<GrantedAuthority>());
	}

	/**
	 * This is the method which actually performs the check to see whether the
	 * user is indeed the correct user
	 * 
	 * @param userDetails
	 * @param authentication
	 * @throws AuthenticationException
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		if(!((String)authentication.getPrincipal()).equals(userDetails.getUsername())) {
			throw new AuthenticationServiceException("Invalid token passed");
		}
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
}
