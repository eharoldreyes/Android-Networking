package com.haroldreyes.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.haroldreyes.androidnetworking.WebserviceRequest;
import com.haroldreyes.androidnetworking.WebserviceRequest.HttpGET;

public class MainActivity extends Activity {

	private HttpGET httpGet;
	private HttpGET httpGETUnGzipped;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	    httpGet = new HttpGET(); //Creating a new instance of the request.	    
	    httpGet.setUrl("http://gdata.youtube.com/feeds/api/playlists/56D792A831D0C362/?v=2&alt=json&feature=plcp"); //Setting the URL 
	    httpGet.setCallback(callBack); //Setting the Callback
	    httpGet.execute(); //Executing the request
	    	    
	    httpGETUnGzipped = new HttpGET();
	    httpGETUnGzipped.setUrl("https://api.stackexchange.com/2.2/questions?order=desc&sort=activity&site=stackoverflow");
	    httpGETUnGzipped.setCallback(unGZippedCallback);
	    httpGETUnGzipped.execute();
	    
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(httpGet != null) httpGet.cancel();		
		if(httpGETUnGzipped != null) httpGETUnGzipped.cancel();
	}
	
	private WebserviceRequest.Callback callBack = new WebserviceRequest.Callback() {
	    @Override
	    public void onResult(int responseCode, String responseMessage, Exception exception) {
	        if(responseCode == 200 && exception == null){           
	            Log.d("callback", "responseMessage: " + responseMessage);   
	        }               
	    }               
	};
	
	private WebserviceRequest.Callback unGZippedCallback = new WebserviceRequest.Callback() {
	    @Override
	    public void onResult(int responseCode, String responseMessage, Exception exception) {
	        if(responseCode == 200 && exception == null){           
	            Log.i("unGZippedCallback", "responseMessage: " + responseMessage);   
	        }               
	    }               
	};
}
