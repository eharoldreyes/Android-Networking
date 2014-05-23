![alt text](https://github.com/eharoldreyes/Android-Networking/blob/master/ic_launcher-web.png "Android-Networking")

Android Networking
==================

Library for asynchronous execution of HttpGet, HttpPost, HttpPut, HttpDelete, Multipart-Put, Multipart-Post and HttpUrlConnection with callback. Simplifies the amount of code needed for network communications. Compatible with Android versions 2.1 and above.


Features
---
- Compatible with **Android 1.5 Cupcake** (API level 3) and later
- Make **asynchronous** HTTP requests
- **GET**, **POST**, **PUT**, **DELETE** requests supported
- Requests happens in **a background thread**
- Results are in **Callback**
- Supports **Request Headers**
- Supports **Basic Authentication**
- **Multipart/form-data** support

TODO
---
- HEAD request
- OPTION request
- HTTPS Support
- GZip Decompression

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
