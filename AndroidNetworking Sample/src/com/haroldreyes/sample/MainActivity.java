package com.haroldreyes.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.haroldreyes.androidnetworking.WebserviceRequest;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Creating a new instance of the request.
	    WebserviceRequest.HttpGET httpGet = new WebserviceRequest.HttpGET(); 
	    
	    //Setting the URL 
	    httpGet.setUrl("http://ip.jsontest.com/");
	    
	    //Setting the Callback
	    httpGet.setCallback(callBack);

	    //Executing the request
	    httpGet.execute();
	}
	
	private WebserviceRequest.Callback callBack = new WebserviceRequest.Callback() {
	    @Override
	    public void onResult(int responseCode, String responseMessage, Exception exception) {
	        if(responseCode == 200 && exception == null){           
	        	//Getting the response from callback
	            Log.d("callback", "responseMessage: " + responseMessage);   
	        }               
	    }               
	};
}