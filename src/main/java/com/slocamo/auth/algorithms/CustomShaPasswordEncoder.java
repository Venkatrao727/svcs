package com.slocamo.auth.algorithms;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.util.StringUtils;

/**
 * @author vvr@slocamo.com
 * 
 *         A sha1 encoder
 */
public class CustomShaPasswordEncoder extends ShaPasswordEncoder {
	private static final String ENCODING_FOR_ENCRYPTION = "UTF-8";
	private static Logger logger = Logger
			.getLogger(CustomShaPasswordEncoder.class);

	public CustomShaPasswordEncoder(int strength) {
		super(strength);
	}

	/**
	 * Checking if the encoded pass is equal to sha1(rawPass+salt)
	 */
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		if (!StringUtils.hasText(encPass) || !StringUtils.hasText(rawPass)) {
			return false;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Encoded pass: " + encPass + " , raw Pass: " + rawPass
					+ " salt: " + salt);
		}
		String pass1 = "" + encPass;
		String pass2 = super.encodePassword(rawPass + salt, null);
		return equals(pass1, pass2);
	}

	/**
	 * Checking if the two encoded passes are equal
	 */
	private static boolean equals(String expected, String actual) {
		byte[] expectedBytes = null;
		byte[] actualBytes = null;
		try {
			expectedBytes = expected.getBytes(ENCODING_FOR_ENCRYPTION);
			actualBytes = actual.getBytes(ENCODING_FOR_ENCRYPTION);
		} catch (UnsupportedEncodingException e) {
			logger.error("Error occured while encoding " + e);
			throw new RuntimeException(
					"Unsupported Encoding while encrypting.", e);
		}

		int expectedLength = expectedBytes == null ? -1 : expectedBytes.length;
		int actualLength = actualBytes == null ? -1 : actualBytes.length;
		if (expectedLength != actualLength) {
			return false;
		}

		int result = 0;
		for (int i = 0; i < expectedLength; i++) {
			result |= expectedBytes[i] ^ actualBytes[i];
		}
		return (result == 0);
	}
}
