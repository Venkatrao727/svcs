package com.slocamo.auth.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.slocamo.auth.bean.RESTCredentials;
import com.slocamo.auth.bean.tokens.RESTAuthenticationToken;

/**
 * @author vvr@slocamo.com
 * 
 */
public class AnalyticsAuthenticationFilter extends
		AbstractAuthenticationProcessingFilter {

	private static final String ACCESS_TOKEN = "access-token";

	private Logger mLogger = Logger.getLogger(this.getClass());

	/**
	 * @param defaultFilterProcessesUrl
	 */
	protected AnalyticsAuthenticationFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {
		String accessToken = obtainValuesFromRequest(request, ACCESS_TOKEN);
		AbstractAuthenticationToken authRequest = null;
		// If accessToken is not empty create an authentication
		if (!isEmpty(accessToken)) {
			authRequest = createAuthenticationToken(accessToken, null);
			if (mLogger.isDebugEnabled()) {
				mLogger.debug("Got accessToken: " + accessToken);
			}
		}

		// If authRequest is null implies that no sufficient credentials are
		// passed, hence throwing an error.
		if (authRequest == null) {
			mLogger.error("invalid accessToken is passed");
			throw new AuthenticationServiceException("Insufficient credentials");
		}

		// to set the "details" property
		setDetails(request, authRequest);

		// Passing the credentials for authentication.
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	private String obtainValuesFromRequest(HttpServletRequest request,
			String requestParam) throws UnsupportedEncodingException {
		return decodeParameterValue(request, requestParam);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		// Need to write logic based on request Accept header. For now adding
		// only for json.
		JSONObject obj = new JSONObject();
		if ("Internal server Error".equals(failed.getMessage())) {
			obj.put("error", 500);
			obj.put("message", failed.getMessage());
		} else {
			obj.put("error", HttpStatus.UNAUTHORIZED.value());
			obj.put("message",
					"Authentication credentials are missing/not vaild");
		}
		response.setContentType("application/json");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().print(obj.toJSONString());
		// response.sendError(HttpStatus.UNAUTHORIZED.value());
	}

	/**
	 * @param request
	 * @param requestParameterName
	 * @return Decoded param value. This is basically to avoid the weird RFC
	 *         spec when it comes to spaces in the URL and how they are encoded
	 * @throws UnsupportedEncodingException
	 */
	private String decodeParameterValue(HttpServletRequest request,
			String requestParameterName) throws UnsupportedEncodingException {
		return URLDecoder.decode(
				getParameterValue(request, requestParameterName),
				request.getCharacterEncoding()).replaceAll(" ", "+");
	}

	/**
	 * @param request
	 * @param requestParameterName
	 * @return parameter value.
	 */
	private String getParameterValue(HttpServletRequest request,
			String requestParameterName) {
		return (request.getHeader(requestParameterName) != null) ? request
				.getHeader(requestParameterName) : "";
	}

	/**
	 * @param request
	 * @param authRequest
	 */
	protected void setDetails(HttpServletRequest request,
			AbstractAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource
				.buildDetails(request));
	}

	/**
	 * @param key
	 * @param restCredentials
	 * @return
	 */
	private AbstractAuthenticationToken createAuthenticationToken(String key,
			RESTCredentials restCredentials) {
		return new RESTAuthenticationToken(key, restCredentials);
	}

	@Override
	/**
	 * Because we require the API client to send credentials with every request, we must authenticate on every request
	 */
	protected boolean requiresAuthentication(HttpServletRequest request,
			HttpServletResponse response) {
		return true;
	}

	/**
	 * @param str
	 * @return
	 */
	private boolean isEmpty(String str) {
		return (str == null || str.trim().equals(""));
	}
}
