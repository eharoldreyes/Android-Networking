Android-Networking
==================

Library for asynchronous execution of HttpGet, HttpPost, HttpPut, HttpDelete, Multipart-Put, Multipart-Post and HttpUrlConnection.

USAGE
---

Starting a new request

````html
@Override
public void onStart() {
	super.onStart();

	//Creating a new instance of the request.
	WebserviceRequest.HttpGET httpGet = new WebserviceRequest.HttpGET(); 

	//Setting the URL 
	httpGet.setUrl("http://gdata.youtube.com/feeds/api/playlists/56D792A831D0C362/?v=2&alt=json&feature=plcp");

	//Setting the Callback
	httpGet.setCallback(callBack);

	//Executing the request
	httpGet.execute();
}

````

Declaring a callback

````html
private WebserviceRequest.Callback callBack = new WebserviceRequest.Callback() {
	@Override
	public void onResult(int responseCode, String responseMessage, Exception exception) {
		if(responseCode == 200 && exception == null){					
			//Getting the response from callback
			Log.d("callback", "responseMessage: " + responseMessage);   
		}				
	}				
};
````

Add permissions to Manifest

````html
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
```` 

**For more details, send me a message [eharoldreyes@gmail.com](mailto:eharoldreyes@gmail.com)

Author
------
Harold Reyes | eharoldreyes@gmail.com
