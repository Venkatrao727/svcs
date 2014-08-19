package com.slocamo.social;

import org.apache.log4j.Logger;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterHandler {

	// TODO Below variable are Hard coded. Need to change this to a properties file
	private static final String CONSUMER_KEY = "1BRkCXFDWpqoXeHsRhOHJQ";
	private static final String CONSUMER_SECRET = "12qKUTcb7HWYKhZg32DtgtUxlBcAscNLTVhVryvhaLE";

	private static Logger logger = Logger.getLogger(TwitterHandler.class);

	public User getUser(String twitterHandle, String accessToken, String accessTokenSecret) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(CONSUMER_KEY)
		  .setOAuthConsumerSecret(CONSUMER_SECRET)
		  .setOAuthAccessToken(accessToken)
		  .setOAuthAccessTokenSecret(accessTokenSecret);
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
	    
	    try {
			User user = twitter.showUser(twitterHandle);
			if (user != null) {
				return user;
			}
		} catch (TwitterException e) {
			logger.error("Twitter Exception", e);
		}
		return null;
	}
}
