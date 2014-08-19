package com.slocamo.common.wrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.slocamo.common.GlobalVars;

public class HttpClientWrapper {
	private HttpClient httpClient;
	private Logger logger = Logger.getLogger(HttpClientWrapper.class);

	public String post(String url, Object payLoadObj, boolean isString) {
		ObjectMapper objectMapper = new ObjectMapper();
		String payLoad = null;
		if (!isString) {
			try {
				payLoad = objectMapper.writeValueAsString(payLoadObj);
			} catch (JsonGenerationException e) {
				logger.error("Error generating json: " + e);
				return null;
			} catch (JsonMappingException e) {
				logger.error("Error mapping json: " + e);
				return null;
			} catch (IOException e) {
				logger.error("IO Exception: " + e);
				return null;
			}
		} else {
			payLoad = (String) payLoadObj;
		}
		HttpPost postMethod = new HttpPost(GlobalVars.getBaseUrl() + url
				+ "?api-key=" + GlobalVars.getApiKey());
		postMethod.addHeader("Content-Type", "application/json");
		postMethod.addHeader("Accept", "application/json");
		StringEntity requestEntity = null;
		try {
			requestEntity = new StringEntity(payLoad);
		} catch (UnsupportedEncodingException e) {
			logger.error("Encoding is not supported : " + payLoad
					+ " Exception is " + e);
			return null;
		}
		postMethod.setEntity(requestEntity);
		HttpResponse response = null;
		try {
			response = httpClient.execute(postMethod);
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocolException " + e);
			return null;
		} catch (IOException e) {
			logger.error("IOException: " + payLoad + " Exception is " + e);
			return null;
		}
		String responseString = null;
		int status = response.getStatusLine().getStatusCode();
		try {
			responseString = EntityUtils.toString(response.getEntity());
		} catch (ParseException e) {
			logger.error("ParseException: " + payLoad + " Exception is " + e);
		} catch (IOException e) {
			logger.error("IOException: " + payLoad + " Exception is " + e);
		}
		if (("" + status).contains("2")) {
			return responseString;
		}
		return null;
	}

	public String get(String url) {
		HttpGet getMethod = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getMethod);
		} catch (ParseException e) {
			logger.error("ParseException: while making a GET request, Exception is "
					+ e);
		} catch (IOException e) {
			logger.error("IOException: while making a GET request, Exception is "
					+ e);
		}

		int status = response.getStatusLine().getStatusCode();
		String responseString = null;
		try {
			responseString = EntityUtils.toString(response.getEntity());
		} catch (ParseException e) {
			logger.error("ParseException: while making a GET request, Exception is "
					+ e);
		} catch (IOException e) {
			logger.error("IOException: while making a GET request, Exception is "
					+ e);
		}
		if (("" + status).contains("2")) {
			return responseString;
		}
		return null;
	}

	/**
	 * @return the httpClient
	 */
	public HttpClient getHttpClient() {
		return httpClient;
	}

	/**
	 * @param httpClient
	 *            the httpClient to set
	 */
	@Autowired
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
}
