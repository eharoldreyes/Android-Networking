/*
 * 
 * Copyright (C) 2014 CodeMagnus. All Rights Reserved.
 * Created by Edgar Harold C. Reyes
 * eharoldreyes@gmail.com
 * 
 */
package com.haroldreyes.androidnetworking.models;

import org.apache.http.HttpResponse;
/*
 * 
 * Written by: Edgar Harold Reyes
 * eharoldreyes@gmail.com
 * 
 */
public class Response {
	private HttpResponse httpResponse;
	private Exception exception;
	private int responseCode;
	private String responseMesssage;

	public HttpResponse getHttpResponse() {
		return httpResponse;
	}

	public void setHttpResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public boolean hasError() {
		return this.exception != null;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMesssage() {
		return responseMesssage;
	}

	public void setResponseMesssage(String responseMesssage) {
		this.responseMesssage = responseMesssage;
	}
}
