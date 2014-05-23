Android-Networking
==================

Library for asynchronous execution of HttpGet, HttpPost, HttpPut, HttpDelete, Multipart-Put, Multipart-Post and HttpUrlConnection.

//Creating a new instance of request
HttpGET httpGet = new HttpGET();

//Setting the URL 
httpGet.setUrl("http://gdata.youtube.com/feeds/api/playlists/56D792A831D0C362/?v=2&alt=json&feature=plcp");

//Setting the Callback
httpGet.setCallback(callBack);

//Executing the request
httpGet.execute();

//Getting the response from callback
private WebserviceRequest.Callback callBack = new WebserviceRequest.Callback() {

		@Override
		public void onResult(int responseCode, String responseMessage, Exception exception) {
			if(responseCode == 200 && exception == null){					
			    Log.d("callback", "responseMessage: " + responseMessage);
			}			
		}	
	};
