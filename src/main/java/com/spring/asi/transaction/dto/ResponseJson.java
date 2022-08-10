package com.spring.asi.transaction.dto;

import org.springframework.core.env.Environment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.spring.asi.transaction.utils.CommonConstant;

/**
 * 
 * Class for common response format
 *
 */
@JsonInclude(Include.NON_NULL)
public class ResponseJson {

	private Environment successProperty;

	private String responseCode;

	private String responseDescription;

	private Object response;

	public ResponseJson(Environment successProperty) {
		this.successProperty = successProperty;
	}
	
	public ResponseJson(Environment successProperty, String responseCode, String responseDescription) {
		this.successProperty = successProperty;
		this.responseCode = successProperty.getProperty(responseCode);
		this.responseDescription = successProperty.getProperty(responseDescription);
	}

	public String getResponseCode() {
		if (this.responseCode == null) {
			responseCode = successProperty.getProperty(CommonConstant.S0001_SUCCESS_CODE);
		}
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = successProperty.getProperty(responseCode);
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = successProperty.getProperty(responseDescription);
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseJson [successProperty=");
		builder.append(successProperty);
		builder.append(", responseCode=");
		builder.append(responseCode);
		builder.append(", responseDescription=");
		builder.append(responseDescription);
		builder.append(", response=");
		builder.append(response);
		builder.append("]");
		return builder.toString();
	}

}
