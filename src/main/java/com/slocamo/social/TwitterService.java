package com.slocamo.social;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.simple.JSONObject;

import com.slocamo.entity.client.User;

public class TwitterService {
	private HttpClient httpClient;

	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws HttpException, IOException {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod("https://graph.facebook.com/me?access_token=CAACEdEose0cBAIODSSTpqZCwgJi9SZCrKjJm9yN3eGd9z6YkFgaCTnKut2PDTy8rf8bwnZBWZA0QkbypjH5fqflEs7W5jCjTC8YLD3FzmMjzbswAIZAZAz7h3CktYyg6jUOkrNk5lsi4J3Pu7zKW0CmVEReIpiVimRyx3eYsZBZBXeSsXtnxsdMOlL0MZB0q715072kcNarTe8wZDZD");
		httpClient.executeMethod(getMethod);
		User user = new User();
		user.setBdate(1L);
		user.setAccess(1L);
		JSONObject joObject = new JSONObject();
		joObject.put("user", user);
		System.out.println(joObject.toString());
		
		//System.out.println(getMethod.getResponseBodyAsString());
	}
	/**
	 * @return the httpClient
	 */
	public HttpClient getHttpClient() {
		return httpClient;
	}

	/**
	 * @param httpClient the httpClient to set
	 */
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
}
