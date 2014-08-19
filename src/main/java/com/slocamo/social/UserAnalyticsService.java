package com.slocamo.social;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfb.Connection;
import com.slocamo.entity.client.UserDetail;

/**
 * Top level class to expose services related to Analytics
 * 
 * @author Krishna
 */
@Service
public class UserAnalyticsService {

	private GoogleHandler googleHandler;
	private FacebookHandler facebookHandler;
	private TwitterHandler twitterHandler;
	public UserDetail getUserData(String type, Long userId, String accessToken,
			String accessTokenSecret, String handle) {
		if (type.equalsIgnoreCase("FACEBOOK")) {
			return getFacebookUserData(userId, accessToken);
		} else if (type.equalsIgnoreCase("TWITTER")) {
			return getTwitterUserData(userId, handle, accessToken,
					accessTokenSecret);
		} else if (type.equalsIgnoreCase("GOOGLE")) {
			return getGoogleUserData(userId, accessToken);
		}
		return null;
	}

	private UserDetail getGoogleUserData(Long userId, String accessToken) {
		return googleHandler.getUser(accessToken);
	}

	private UserDetail getFacebookUserData(Long userId, String accessToken) {

		com.restfb.types.User user = facebookHandler.getUser(accessToken);
		if (user != null) {
			UserDetail userDetail = new UserDetail();
			userDetail.setId(userId);
			userDetail.setSocialNetworkId(user.getId());
			userDetail.setBirthday(user.getBirthday());
			userDetail.setEmail(user.getEmail());
			userDetail.setFirstName(user.getFirstName());
			userDetail.setMiddleName(user.getMiddleName());
			userDetail.setLastName(user.getLastName());
			userDetail.setLocation(user.getLocation().getName());
			userDetail.setGender(user.getGender());
			Connection<com.restfb.types.User> userFriendsConnection = facebookHandler
					.getUserConnections(accessToken);
			if (userFriendsConnection != null) {
				List<com.restfb.types.User> friendsList = userFriendsConnection
						.getData();
				if (friendsList != null) {
					userDetail.setFriendsCount(friendsList.size());
				}
			}

			Connection<com.restfb.types.User> userLikesConection = facebookHandler
					.getUserLikes(accessToken);
			if (userLikesConection != null) {
				List<com.restfb.types.User> userLikes = userLikesConection
						.getData();
				if (userLikes != null) {
					userDetail.setLikeCount(userLikes.size());
				}
			}
			return userDetail;
		}

		return null;
	}

	private UserDetail getTwitterUserData(Long userId, String handle,
			String accessToken, String accessTokenSecret) {
		twitter4j.User user = twitterHandler.getUser(handle, accessToken,
				accessTokenSecret);
		if (user != null) {
			UserDetail userDetail = new UserDetail();
			userDetail.setSocialNetworkId(String.valueOf(user.getId()));
			userDetail.setId(userId);
			userDetail.setFriendsCount(user.getFriendsCount());
			userDetail.setFirstName(user.getName());
			userDetail.setFollowersCount(user.getFollowersCount());
			userDetail.setLocation(user.getLocation());
			userDetail.setDescription(user.getDescription());
			userDetail.setScreenName(user.getScreenName());
			userDetail.setStatus(user.getStatus().getText());
			userDetail.setUrl(user.getURL());
			userDetail.setTimezone(user.getTimeZone());
			return userDetail;
		}
		return null;
	}

	/**
	 * @param googleHandler the googleHandler to set
	 */
	@Autowired
	public void setGoogleHandler(GoogleHandler googleHandler) {
		this.googleHandler = googleHandler;
	}

	/**
	 * @param facebookHandler the facebookHandler to set
	 */
	@Autowired
	public void setFacebookHandler(FacebookHandler facebookHandler) {
		this.facebookHandler = facebookHandler;
	}
	
	/**
	 * @param twitterHandler the twitterHandler to set
	 */
	@Autowired
	public void setTwitterHandler(TwitterHandler twitterHandler) {
		this.twitterHandler = twitterHandler;
	}

}
