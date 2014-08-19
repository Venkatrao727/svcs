package com.slocamo.common;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@XmlRootElement
@JsonAutoDetect
public class ErrorMessage {
	private int error;
	private String message;

	/**
	 * @return the error
	 */
	public int getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(int error) {
		this.error = error;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
