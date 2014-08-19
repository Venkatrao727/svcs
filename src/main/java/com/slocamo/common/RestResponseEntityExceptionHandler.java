package com.slocamo.common;

import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

/**
 * @author vvr@slocamo.com
 * 
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends
		ResponseEntityExceptionHandler {
	public static final String INTERNAL_ERROR = "Internal error";

	public RestResponseEntityExceptionHandler() {
		super();
	}

	// 400
	@ExceptionHandler({ ConstraintViolationException.class,
			DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleBadRequest(final RuntimeException ex,
			final WebRequest request) {
		return handleExceptionInternal(ex, INTERNAL_ERROR, new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}

	// 403, 404

	@ExceptionHandler(value = { EntityNotFoundException.class })
	protected ResponseEntity<Object> handleBadRequest(
			final EntityNotFoundException ex, final WebRequest request) {
		final String bodyOfResponse = "Entity no longer exists";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
				HttpStatus.NOT_FOUND, request);
	}

	// 409
	@ExceptionHandler({ InvalidDataAccessApiUsageException.class,
			DataAccessException.class })
	protected ResponseEntity<Object> handleConflict(final RuntimeException ex,
			final WebRequest request) {
		final String bodyOfResponse = "Invalid Accessing of API";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
				HttpStatus.CONFLICT, request);
	}

	// 500
	@ExceptionHandler({ NullPointerException.class,
			IllegalArgumentException.class, IllegalStateException.class })
	public ResponseEntity<Object> handleInternal(final RuntimeException ex,
			final WebRequest request) {
		String bodyOfResponse = null;
		if (ex instanceof IllegalArgumentException) {
			bodyOfResponse = "Invalid data";
		} else {
			bodyOfResponse = INTERNAL_ERROR;
		}
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	

	// 400
	@ExceptionHandler({ ParseException.class})
	public ResponseEntity<Object> handleParseException(final RuntimeException ex,
			final WebRequest request) {
		String bodyOfResponse = "Unable to parse data";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}

	// Ovveriden methods:
	@Override
	protected ResponseEntity<Object> handleNoSuchRequestHandlingMethod(
			NoSuchRequestHandlingMethodException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		pageNotFoundLogger.warn(ex.getMessage());

		return handleExceptionInternal(ex,
				"No handler available for the request", headers, status,
				request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			final HttpMessageNotReadableException ex,
			final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		final String bodyOfResponse = "Message cannot be readable";
		// ex.getCause() instanceof JsonMappingException, JsonParseException //
		// for additional information later on
		return handleExceptionInternal(ex, bodyOfResponse, headers,
				HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		final String bodyOfResponse = "Value for " + ex.getParameter()
				+ " is not valid.";
		return handleExceptionInternal(ex, bodyOfResponse, headers,
				HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, "Request method not supported :",
				headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
			HttpMediaTypeNotSupportedException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, "Media type not supported", headers,
				status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
			HttpMediaTypeNotAcceptableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex,
				"Service does not support the sent media type", headers,
				status, request);
	}

	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex,
				"Paramter is missing, " + ex.getParameterName(), headers,
				status, request);
	}

	protected ResponseEntity<Object> handleServletRequestBindingException(
			ServletRequestBindingException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, INTERNAL_ERROR, headers, status,
				request);
	}

	protected ResponseEntity<Object> handleConversionNotSupported(
			ConversionNotSupportedException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex,
				"Type mismatch for  " + ex.getValue(), headers, status, request);
	}

	protected ResponseEntity<Object> handleTypeMismatch(
			TypeMismatchException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		return handleExceptionInternal(ex,
				"Type mismatch for " + ex.getValue(), headers, status, request);
	}

	protected ResponseEntity<Object> handleHttpMessageNotWritable(
			HttpMessageNotWritableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, null, headers, status, request);
	}

	protected ResponseEntity<Object> handleMissingServletRequestPart(
			MissingServletRequestPartException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, "Param " + ex.getRequestPartName()
				+ " is missing/invalid", headers, status, request);
	}

	protected ResponseEntity<Object> handleBindException(BindException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, "Invalid data passed", headers,
				status, request);
	}

	@ExceptionHandler(value = Exception.class)
	protected ResponseEntity<Object> handleGeneralException(final Exception ex,
			final WebRequest webRequest) {
		return handleExceptionInternal(ex, INTERNAL_ERROR, new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
			Object body, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		ErrorMessage error = new ErrorMessage();
		error.setError(status.value());
		error.setMessage((String) body);
		logger.error("Exception is " + ex.getMessage());
		return new ResponseEntity<Object>(error, headers, status);
	}

}
