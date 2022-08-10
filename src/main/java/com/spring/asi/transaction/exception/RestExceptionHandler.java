package com.spring.asi.transaction.exception;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.asi.transaction.dto.ResponseJson;
import com.spring.asi.transaction.utils.ErrorConstant;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private Environment environment;

	public RestExceptionHandler(Environment environment) {
		this.environment = environment;
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseJson errorInfo = new ResponseJson(environment, ErrorConstant.E1000_ERROR_CODE,
				ErrorConstant.E1000_ERROR_DESCRIPTION);
		return handleExceptionInternal(ex, errorInfo, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseJson errorInfo = new ResponseJson(environment, ErrorConstant.E1000_ERROR_CODE,
				ErrorConstant.E1000_ERROR_DESCRIPTION);
		return handleExceptionInternal(ex, errorInfo, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseJson errorInfo = new ResponseJson(environment, ErrorConstant.E1000_ERROR_CODE,
				ErrorConstant.E1000_ERROR_DESCRIPTION);
		return handleExceptionInternal(ex, errorInfo, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseJson errorInfo = new ResponseJson(environment, ErrorConstant.E1000_ERROR_CODE,
				ErrorConstant.E1000_ERROR_DESCRIPTION);
		return handleExceptionInternal(ex, errorInfo, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseJson errorInfo = new ResponseJson(environment, ErrorConstant.E1000_ERROR_CODE,
				ErrorConstant.E1000_ERROR_DESCRIPTION);
		return handleExceptionInternal(ex, errorInfo, headers, status, request);
	}

	@ExceptionHandler({ ServiceException.class })
	protected ResponseEntity<Object> handleServiceException(RuntimeException e, WebRequest request) {
		ServiceException exception = (ServiceException) e;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(exception, exception.getErrorInfo(), headers, exception.getHttpStatus(),
				request);
	}

//	@ExceptionHandler({ AccessDeniedException.class })
//	protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		ResponseJson errorInfo = new ResponseJson(environment, ErrorConstant.E1000_ERROR_CODE,
//				ErrorConstant.E1000_ERROR_DESCRIPTION);
//		return handleExceptionInternal(ex, errorInfo, headers, HttpStatus.FORBIDDEN, request);
//	}
//
//	@ExceptionHandler(BadCredentialsException.class)
//	protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		ResponseJson errorInfo = new ResponseJson(environment, ErrorConstant.E1000_ERROR_CODE,
//				ErrorConstant.E1000_ERROR_DESCRIPTION);
//		return handleExceptionInternal(ex, errorInfo, headers, HttpStatus.FORBIDDEN, request);
//	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGenericException(RuntimeException e, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpStatus httpStatus = HttpStatus.OK;
		ResponseJson errorInfo = new ResponseJson(environment, ErrorConstant.E1000_ERROR_CODE,
				ErrorConstant.E1000_ERROR_DESCRIPTION);
		return handleExceptionInternal(e, errorInfo, headers, httpStatus, request);
	}
}
