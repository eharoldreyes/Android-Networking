![alt text](https://raw.githubusercontent.com/eharoldreyes/Android-Networking/master/ic_launcher-web.png "Android-Networking")

Android Networking
==================

Library for asynchronous execution of HttpGet, HttpPost, HttpPut, HttpDelete, Multipart-Put, Multipart-Post and HttpUrlConnection with callback. Simplifies the amount of code needed for network communications. Compatible with Android Versions 2.2 and above.

Features
---
- Compatible with **Android 2.2 Froyo** (API level 7) and later
- Execute **asynchronous** HTTP requests
- Supports **GET**, **POST**, **PUT**, **DELETE** and **Multipart/form-data**
- Supports **GZip**
- Supports **Request Headers**
- Supports **Basic Authentication**
- Requests happens in **a background thread**
- Results are in **Callback**

TODO
---
- HEAD request
- OPTION request
- HTTPS Support

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
	httpGet.setUrl("http://ip.jsontest.com/");

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

Adding permissions to Manifest

````html
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
```` 

**For more details, send me a message [eharoldreyes@gmail.com](mailto:eharoldreyes@gmail.com)

Author
------
Harold Reyes | eharoldreyes@gmail.com


License
------
Apache License, Version 2.0
http://www.apache.org/licenses/LICENSE-2.0
