package com.slocamo.client.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.slocamo.common.BaseDAO;
import com.slocamo.entity.client.Tweet;

public class TweetDAO extends BaseDAO{

	public Object getTweets(Double lat, Double lng, Double distance) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lat", lat);
		params.put("lng", lng);
		params.put("distance", distance);
		return executeToList("getTweets", null, null, params);
	}

	@Transactional
	public void postTweets(Tweet tweet) {
		persist(tweet);
	}

}
