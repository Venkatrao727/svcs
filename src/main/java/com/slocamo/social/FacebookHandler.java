package com.slocamo.social;

import org.apache.log4j.Logger;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.User;

/**
 * A top level singleton class to access Facebook's Graph API using OAuth 2.0 specification
 * @author Chaitanya
 */
public class FacebookHandler {
	
	//TODO Below variable are Hard coded. Need to change this to a properties file
	private static final String FACEBOOK_APP_ID = "205130369609221";
	private static final String FACEBOOK_APP_SECRET = "d15c8158186c4ecdd5a443d53f561a11";
	
	private static Logger logger = Logger.getLogger(FacebookHandler.class);
	/**
	 * Method to fetch details of a User given an access Token
	 * @param accessToken
	 * @return a User instance or null in case of exception
	 */
	public User getUser(String accessToken) {
		try {
			FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
			if (facebookClient != null) {
				User user = facebookClient.fetchObject("me", User.class);
				if (user != null) {
					logger.info("User successfully retrieved from Facebook Graph API. User ID:" + user.getId());
					return user;
				}
			}
		} catch (Exception e) {
			logger.error("Unable to fetch Facebook user details with the accessToken:" + accessToken, e);
		}
		return null;
	}
	
	/**
	 * Method to fetch User's connections. We use this method only to get the count of friends
	 * @param accessToken
	 * @return List of user's friends (or) null if exception or no friends
	 */
	public Connection<User> getUserConnections(String accessToken) {
		try {
			FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
			if(facebookClient != null) {
				Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
				return myFriends; 
			}
		} catch (Exception e) {
			logger.error("Unable to fetch user connections with the accessToken:"+ accessToken, e);
		}
		return null;
	}
	
	/**
	 * Method to fetch User's connections. We use this method only to get the count of friends
	 * @param accessToken
	 * @return List of user's friends (or) null if exception or no friends
	 */
	public Connection<User> getUserLikes(String accessToken) {
		try {
			FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
			if(facebookClient != null) {
				Connection<User> userLikes = facebookClient.fetchConnection("me/likes", User.class);
				return userLikes; 
			}
		} catch (Exception e) {
			logger.error("Unable to fetch user likes with the accessToken:"+ accessToken, e);
		}
		return null;
	}
	
	/**
	 * Method to get an extended Access token
	 * Tells Facebook to extend the lifetime of access token. Facebook may return the same token or a new one.
	 * @param accessToken
	 * @return an extended access token
	 */
	public String getExtendedAccessToken(String accessToken) {
		try {
			AccessToken extendedAccessToken = new DefaultFacebookClient().obtainExtendedAccessToken(FACEBOOK_APP_ID, FACEBOOK_APP_SECRET, accessToken);
			logger.info("Extended AccessToken:"+ extendedAccessToken.getAccessToken());
			return extendedAccessToken.getAccessToken();
		} catch(FacebookOAuthException e) {
			logger.error("Unable to obtain extended token with the accessToken:"+ accessToken, e);
			return null;
		}
	}
}