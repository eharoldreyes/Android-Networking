/*
 * 
 * Copyright (C) 2014 CodeMagnus. All Rights Reserved.
 * Created by Edgar Harold C. Reyes
 * 
 */
package com.haroldreyes.androidnetworking;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;

import android.net.http.AndroidHttpClient;
import android.util.Log;

import com.haroldreyes.androidnetworking.models.FileValuePair;
import com.haroldreyes.androidnetworking.models.Response;



/*
 * 
 * Written by: Edgar Harold Reyes
 * eharoldreyes@gmail.com
 * 
 */

public class WebService {

	private static final String tag 			= "WebService";	
	
	public static final String MESSAGE 			= "message";
	public static final String STATUS_CODE 		= "status_code";
	public static final String ERROR 			= "error";
	public static final String ERROR_MESSAGE 	= "error_message";
	
	public static final String METHOD_DELETE 	= "DELETE";
	public static final String METHOD_POST 		= "POST";
	public static final String METHOD_PUT 		= "PUT";
	public static final String METHOD_GET 		= "GET";
			
	private static final boolean DEBUG 			= true;
	private static final int TIME_OUT 			= 10000;
	
	public static String getUngzippedResponseMessage(HttpEntity entity) throws IOException {
		StringBuilder builder 	= new StringBuilder();
		InputStream content 	= AndroidHttpClient.getUngzippedContent(entity);
		BufferedReader reader 	= new BufferedReader(new InputStreamReader(content));
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		content.close();
		return builder.toString();	
	}
	
	public static Response GET(String url, List<BasicNameValuePair> requestHeaders, List<BasicNameValuePair> headers) throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);		
		
		
		if(DEBUG) Log.i(tag, "Http GET URL: " + url);	
		
		if(requestHeaders != null){				
			for (BasicNameValuePair requestHeader : requestHeaders) {
				if(DEBUG) Log.i(tag, "HTTP GET REQUESTHEADER: " + requestHeader.toString());	
				httpget.addHeader(requestHeader.getName(), requestHeader.getValue());					
			}
		}
		
		if(headers != null){
			for (NameValuePair header : headers) {
				if(DEBUG) Log.i(tag, "Http GET HEADER: " + header.getName() + ":" + header.getValue());	
				httpget.setHeader(header.getName(), header.getValue());						
			}	
		}
		

	    HttpParams params = httpget.getParams();
	    params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, Integer.valueOf(TIME_OUT));
	    params.setParameter(CoreConnectionPNames.SO_TIMEOUT, Integer.valueOf(TIME_OUT));
	    httpget.setParams(params);
		
		HttpResponse httpResponse = httpclient.execute(httpget);
				
		Response response = new Response();
		response.setHttpResponse(httpResponse);			
		response.setResponseCode(response.getHttpResponse().getStatusLine().getStatusCode());
		response.setResponseMesssage(getUngzippedResponseMessage(httpResponse.getEntity()));
		response.setException(null);		

		httpget.abort();
		httpclient.getConnectionManager().shutdown();
		
		return response;
	}
	
	public static Response POST(String url, List<BasicNameValuePair> requestHeaders, List<BasicNameValuePair> headers, List<BasicNameValuePair> parameters) throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();		
		HttpPost httpPost = new HttpPost(url);

		if(DEBUG) Log.i(tag, "Http POST URL: " + url);	
		
		if(requestHeaders != null){				
			for (BasicNameValuePair requestHeader : requestHeaders) {
				if(DEBUG) Log.i(tag, "HTTP POST REQUESTHEADER: " + requestHeader.toString());	
				httpPost.addHeader(requestHeader.getName(), requestHeader.getValue());					
			}
		}
		
		if(headers != null){
			for (NameValuePair header : headers) {
				if(DEBUG) Log.i(tag, "Http POST HEADER: " + header.getName() + ":" + header.getValue());	
				httpPost.setHeader(header.getName(), header.getValue());						
			}					
		}
		
		if(parameters != null){
			if(DEBUG) Log.i(tag, "Http POST PARAMETER: " + parameters.toString());	
			httpPost.setEntity(new UrlEncodedFormEntity(parameters));					
		}
		


	    HttpParams params = httpPost.getParams();
	    params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, Integer.valueOf(TIME_OUT));
	    params.setParameter(CoreConnectionPNames.SO_TIMEOUT, Integer.valueOf(TIME_OUT));
	    httpPost.setParams(params);
		
		HttpResponse httpResponse = httpclient.execute(httpPost);
				
		Response response = new Response();
		response.setHttpResponse(httpResponse);			
		response.setResponseCode(response.getHttpResponse().getStatusLine().getStatusCode());
		response.setResponseMesssage(getUngzippedResponseMessage(httpResponse.getEntity()));
		response.setException(null);		

		httpPost.abort();
		httpclient.getConnectionManager().shutdown();
		
		return response;
	}
	
	public static Response PUT(String url, List<BasicNameValuePair> requestHeaders, List<BasicNameValuePair> headers, List<BasicNameValuePair> parameters) throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();		
		HttpPut httpPut = new HttpPut(url);
		if(DEBUG) Log.i(tag, "Http PUT URL: " + url);	
		
		if(requestHeaders != null){				
			for (BasicNameValuePair requestHeader : requestHeaders) {
				if(DEBUG) Log.i(tag, "HTTP PUT REQUESTHEADER: " + requestHeader.toString());	
				httpPut.addHeader(requestHeader.getName(), requestHeader.getValue());					
			}
		}
		
		if(headers != null){
			for (NameValuePair header : headers) {
				if(DEBUG) Log.i(tag, "Http PUT HEADER: " + header.getName() + ":" + header.getValue());	
				httpPut.setHeader(header.getName(), header.getValue());						
			}					
		}
		
		if(parameters != null){
			if(DEBUG) Log.i(tag, "Http PUT PARAMETER : " + parameters.toString());	
			httpPut.setEntity(new UrlEncodedFormEntity(parameters));					
		}

		HttpResponse httpResponse = httpclient.execute(httpPut);
				
		Response response = new Response();
		response.setHttpResponse(httpResponse);			
		response.setResponseCode(response.getHttpResponse().getStatusLine().getStatusCode());
		response.setResponseMesssage(getUngzippedResponseMessage(httpResponse.getEntity()));
		response.setException(null);		

		httpPut.abort();
		httpclient.getConnectionManager().shutdown();
		
		return response;
	}
	
	public static Response DELETE(String url, List<BasicNameValuePair> requestHeaders, List<BasicNameValuePair> headers) throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();		
		HttpDelete httpDelete = new HttpDelete(url);
		if(DEBUG) Log.i(tag, "Http DELETE URL: " + url);	
		
		if(requestHeaders != null){				
			for (BasicNameValuePair requestHeader : requestHeaders) {
				if(DEBUG) Log.i(tag, "HTTP DELETE REQUESTHEADER: " + requestHeader.toString());	
				httpDelete.addHeader(requestHeader.getName(), requestHeader.getValue());					
			}
		}
		
		if(headers != null){
			for (NameValuePair header : headers) {
				if(DEBUG) Log.i(tag, "Http DELETE HEADER: " + header.getName() + ":" + header.getValue());	
				httpDelete.setHeader(header.getName(), header.getValue());						
			}					
		}					

		HttpResponse httpResponse = httpclient.execute(httpDelete);
				
		Response response = new Response();
		response.setHttpResponse(httpResponse);			
		response.setResponseCode(response.getHttpResponse().getStatusLine().getStatusCode());
		response.setResponseMesssage(getUngzippedResponseMessage(httpResponse.getEntity()));
		response.setException(null);		

		httpDelete.abort();
		httpclient.getConnectionManager().shutdown();
		
		return response;
	}
	
	
	
	public static Response MULTIPARTPOST(String url, List<BasicNameValuePair> requestHeaders, List<BasicNameValuePair> headers, List<BasicNameValuePair> parameters, List<FileValuePair> files) throws ClientProtocolException, IOException{
		HttpClient client = new DefaultHttpClient();  
		HttpPost post = new HttpPost(url);  	
		if(DEBUG) Log.i(tag, "Http MULTIPARTPOST URL: " + url);	
					
		MultipartEntity reqEntity = new MultipartEntity();  			
		
		if(requestHeaders != null){				
			for (BasicNameValuePair requestHeader : requestHeaders) {
				if(DEBUG) Log.i(tag, "HTTP MULTIPARTPOST REQUESTHEADER: " + requestHeader.toString());	
				post.addHeader(requestHeader.getName(), requestHeader.getValue());					
			}
		}
		
		if(headers != null){
			for (NameValuePair header : headers) {
				if(DEBUG) Log.i(tag, "Http MULTIPARTPOST HEADER: " + header.getName() + ":" + header.getValue());	
				post.setHeader(header.getName(), header.getValue());				
			}					
		}
		
		if(files != null){				
			for (FileValuePair fileValuePair : files) {
				reqEntity.addPart(fileValuePair.getName(), new FileBody(fileValuePair.getFile()));   
				if(DEBUG) Log.i(tag, "Http MULTIPARTPOST name: " + fileValuePair.getName());
				if(DEBUG) Log.i(tag, "Http MULTIPARTPOST filepath: " + fileValuePair.getFile().toString());
				
			}				
		}

		if(parameters != null){
			for (BasicNameValuePair basicNameValuePair : parameters) {
				if(DEBUG) Log.i(tag, "Http MULTIPARTPOST PARAMETER: " + basicNameValuePair.getName() + ":" + basicNameValuePair.getValue());	
				reqEntity.addPart(basicNameValuePair.getName(), new StringBody(basicNameValuePair.getValue()));				
			}				
		}		
		
		post.setEntity(reqEntity);  		
		
		HttpResponse httpResponse = client.execute(post);
				
		Response response = new Response();
		response.setHttpResponse(httpResponse);			
		response.setResponseCode(response.getHttpResponse().getStatusLine().getStatusCode());
		response.setResponseMesssage(getUngzippedResponseMessage(httpResponse.getEntity()));
		response.setException(null);		

		post.abort();
		client.getConnectionManager().shutdown();
		
		return response;
		
	}
	
	public static Response MULTIPARTPUT(String url, List<BasicNameValuePair> requestHeaders, List<BasicNameValuePair> headers, List<BasicNameValuePair> parameters, List<FileValuePair> files) throws ClientProtocolException, IOException{
		HttpClient client = new DefaultHttpClient();  
		HttpPut put = new HttpPut(url);  	
		if(DEBUG) Log.i(tag, "MULTIPART PUT url: " + url);	
					
		MultipartEntity reqEntity = new MultipartEntity();  	
		
		if(requestHeaders != null){				
			for (BasicNameValuePair requestHeader : requestHeaders) {
				if(DEBUG) Log.i(tag, "MULTIPART PUT REQUESTHEADER: " + requestHeader.toString());	
				put.addHeader(requestHeader.getName(), requestHeader.getValue());					
			}
		}
		
		if(headers != null){
			for (NameValuePair header : headers) {
				put.setHeader(header.getName(), header.getValue());				
				if(DEBUG) Log.i(tag, "MULTIPART PUT header: " + header.toString());					
			}					
		}
		
		if(files != null){
			for (FileValuePair fileValuePair : files) {
				reqEntity.addPart(fileValuePair.getName(), new FileBody(fileValuePair.getFile()));   
				if(DEBUG) Log.i(tag, "MULTIPART PUT name: " + fileValuePair.getName());
				if(DEBUG) Log.i(tag, "MULTIPART PUT filepath: " + fileValuePair.getFile().toString());
				
			}				
		}

		if(parameters != null){
			for (BasicNameValuePair basicNameValuePair : parameters) {
				if(DEBUG) Log.i(tag, "MULTIPART PUT PARAMETER: " + basicNameValuePair.getName() + ":" + basicNameValuePair.getValue());	
				reqEntity.addPart(basicNameValuePair.getName(), new StringBody(basicNameValuePair.getValue()));				
			}				
		}
		
		put.setEntity(reqEntity);  			
		
		HttpResponse httpResponse = client.execute(put);
		
		Response response = new Response();
		response.setHttpResponse(httpResponse);			
		response.setResponseCode(response.getHttpResponse().getStatusLine().getStatusCode());
		response.setResponseMesssage(getUngzippedResponseMessage(httpResponse.getEntity()));
		response.setException(null);		

		put.abort();
		client.getConnectionManager().shutdown();
		
		return response;	
	}
	
	
	
	public static Response HttpUrlConnection(String url, String requestMethod, List<BasicNameValuePair> headers, String parameters, WebServiceRequest.OnProgressChangeListener onProgressChangeListener) throws IOException{
		InputStreamReader isr = null;
		BufferedReader reader = null;
		Response webserviceResponse = new Response();
				
		java.net.URL newURL = new URL(url);
		if(DEBUG) Log.i(tag, "HttpUrlConnection url: " + url);	
	    HttpURLConnection connection = (HttpURLConnection) newURL.openConnection();
	    connection.setDoOutput(true);
	    
	    for (BasicNameValuePair header : headers) {
			if(DEBUG) Log.i(tag, "HttpUrlConnection header: " + header.toString());	
		    connection.setRequestProperty(header.getName(), header.getValue());				
		}		    
	    
	    connection.setRequestMethod(requestMethod);   
        
	    OutputStreamWriter request = new OutputStreamWriter(connection.getOutputStream());
	    request.write(parameters);

		if(DEBUG) Log.i(tag, "HttpUrlConnection parameter: " + parameters);	
	    request.flush();
	    request.close();            
	    String line = "";               
	    isr = new InputStreamReader(connection.getInputStream());
	    reader = new BufferedReader(isr);
	    StringBuilder sb = new StringBuilder();
	    
	    int readBytes = 0;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		    readBytes += line.getBytes("ISO-8859-2").length + 2; // CRLF bytes!!
			Log.d("HttpUrlConnection", readBytes + "%");
		    if(onProgressChangeListener != null) onProgressChangeListener.onProgressChanged(readBytes);
		}            
		
	    String response = sb.toString();
	    int status = connection.getResponseCode();
	    
	    if(DEBUG) Log.d("HttpURLConnection", "response " + response);
	    if(DEBUG) Log.i("HttpUrlConnection", "status " + status);
	    
	    webserviceResponse.setHttpResponse(null);			
	    webserviceResponse.setResponseMesssage(response);
	    webserviceResponse.setResponseCode(status);
	    webserviceResponse.setException(null);		
	    	    
	    isr.close();
	    reader.close();
	    
		return webserviceResponse;		
		
	}			
}
