package com.cucumber.automation.webservices;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import cucumber.api.DataTable;

public class RestFactory {
	
	@SuppressWarnings("deprecation")
	HttpClient client = new DefaultHttpClient();
	static HttpResponse httpResponse = null;
	static String responseString = null;
	String getURL = "";
	
	public void getRequest(String url) throws ClientProtocolException, IOException{
		
		RequestConfig requestConfig = RequestConfig.custom().
			    setConnectionRequestTimeout(20000).setConnectTimeout(20000).setSocketTimeout(20000).build();
		HttpClientBuilder builder = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig);
		getURL = url;
		HttpUriRequest request = new HttpGet( url );

		httpResponse = builder.build().execute( request );
		
	
		
		
	}
	
	public void verifyStatusCode(int statusCode) throws ClientProtocolException, IOException{
		 
		assertEquals(statusCode, httpResponse.getStatusLine().getStatusCode());
/*
		int sc = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code = " + sc);
		
		if (statusCode == sc) {
			
		} else {
			System.out.println("Status code did not match for " +  getURL + ". Actual status code = " + sc);
		}*/
	}
	
	public void verifyResponseType(String type){
		String mimeType = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();
		assertTrue( mimeType.contains(type) );
	}
	
	public void verifyResponseData(String responseData) throws ParseException, IOException{
		HttpEntity entity = httpResponse.getEntity();
		responseString = EntityUtils.toString(entity, "UTF-8");
		//System.out.println(responseString);
		
		assertTrue(responseString.contains(responseData));
	}

	public void postRequest(String url, DataTable payloadTable) throws ClientProtocolException, IOException{
		List<List<String>> payload = payloadTable.raw();
		
	    HttpPost post = new HttpPost(url);

	      List<NameValuePair> urlParameters = new ArrayList<NameValuePair>(1);
	      
	      for (int i=1; i<payload.size();i++){
	    	  urlParameters.add(new BasicNameValuePair(payload.get(i).get(0), payload.get(i).get(1)));
	      }
			
	      post.setEntity(new UrlEncodedFormEntity(urlParameters));
	 
	      httpResponse = client.execute(post);
	}
	
	public byte[] returnResponse() {
		String errorMessage = "Rest Test Case failed: "+ responseString;
		return errorMessage.getBytes(Charset.forName("UTF-8"));
	}
	
	public int getResponseCode() {
		return httpResponse.getStatusLine().getStatusCode();
	}
	
	public String getCurrentURL() {
		return getURL;
	}
}
