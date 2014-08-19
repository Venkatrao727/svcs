package com.slocamo.auth.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.slocamo.auth.bean.RESTCredentials;
import com.slocamo.auth.bean.SessionUser;
import com.slocamo.auth.bean.tokens.RESTAuthenticationToken;
import com.slocamo.auth.service.UserSecurityService;

/**
 * @author vvr@slocamo.com
 * 
 */
public class RESTDaoAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {

	private UserSecurityService userSecurityService;
	private PasswordEncoder passwordEncoder;
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * This method, based on the apikey/session key retrieves the details. If no
	 * record is found, we immediately throw an exception and exit the
	 * autorization process
	 */

	@Override
	protected UserDetails retrieveUser(String key,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		if (authentication == null
				|| (RESTCredentials) authentication.getCredentials() == null) {
			logger.error("No credentials provided for key :" + key);
			throw new AuthenticationServiceException("No credentials provided");
		}

		RESTCredentials restCredentials = (RESTCredentials) authentication
				.getCredentials();
		UserDetails loadedUser = null;
		try {
			if (restCredentials.isFirstAuthentication()) {
				loadedUser = userSecurityService.getUserByPartnerId(key);
			} else {
				loadedUser = userSecurityService.getUserBySessionKey(key);
			}
		} catch (Exception repositoryProblem) {
			logger.error("Error retrieving user details, " + repositoryProblem);
			throw new AuthenticationServiceException(
					"Internal server Error", repositoryProblem);
		}

		if (loadedUser == null) {
			logger.error("No user found for key :" + key);
			throw new AuthenticationServiceException("No user found for key :"
					+ key);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Loaded user from db :" + " partnerid/sessionkey : "
					+ loadedUser.getUsername() + "apikey/sessionsecret: "
					+ loadedUser.getPassword());
		}
		return loadedUser;
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
		RESTAuthenticationToken token = (RESTAuthenticationToken) authentication;
		if (token != null) {
			if (authentication.getCredentials() == null) {
				logger.error("Authentication failed: no credentials provided");
				throw new BadCredentialsException(
						messages.getMessage(
								"AbstractUserDetailsAuthenticationProvider.badCredentials",
								"Bad credentials"));
			}

			RESTCredentials restCredentials = (RESTCredentials) authentication
					.getCredentials();

			if (restCredentials.isFirstAuthentication()) {
				// check if the api key sent is correct
				if (userDetails.getPassword() != null
						&& passwordEncoder.isPasswordValid(
								restCredentials.getApiKey(),
								userDetails.getPassword(),
								restCredentials.getSalt())) {
					if (logger.isDebugEnabled()) {
						logger.debug("Authentication is successful: api key and partnerId passed are correct");
					}
					return;
				}
			} else {
				if (passwordEncoder.isPasswordValid(
						restCredentials.getSignature(),
						userDetails.getPassword(), restCredentials.getSalt())) {
					if (logger.isDebugEnabled()) {
						logger.debug("Authentication is successful: signature does not match stored value");
					}
					authentication.setDetails(((SessionUser)(userDetails)).getUid());
					return;
				}

			}
		}

		throw new BadCredentialsException(messages.getMessage(
				"AbstractUserDetailsAuthenticationProvider.badCredentials",
				"Bad credentials"));
	}

	@Override
	protected void doAfterPropertiesSet() throws Exception {
		Assert.notNull(this.userSecurityService,
				"A UserSecurityServiceImpl must be set");
		Assert.notNull(this.passwordEncoder, "A PasswordEncoder must be set");
	}

	@Autowired
	public void setUserSecurityService(UserSecurityService userSecurityService) {
		this.userSecurityService = userSecurityService;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
