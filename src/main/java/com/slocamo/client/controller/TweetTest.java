package com.slocamo.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.slocamo.client.dao.TweetDAO;
import com.slocamo.entity.client.Tweet;

@Controller
public class TweetTest {
	private TweetDAO tweetDAO;

	/**
	 * @return the tweetDAO
	 */
	public TweetDAO getTweetDAO() {
		return tweetDAO;
	}

	/**
	 * @param tweetDAO the tweetDAO to set
	 */
	@Autowired
	public void setTweetDAO(TweetDAO tweetDAO) {
		this.tweetDAO = tweetDAO;
	}

	@RequestMapping(value = { "/tweet" }, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Object getTweets(@RequestParam(value = "lat") Double lat,
			@RequestParam(value = "lng") Double lng,
			@RequestParam(value="distance") Double distance) {
		return tweetDAO.getTweets(lat, lng, distance);
	}

	@RequestMapping(value = "/tweet", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public void postTweet(
			@ModelAttribute(value = "twt")Tweet tweet) {
		tweetDAO.postTweets(tweet);
	}
}
