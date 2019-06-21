package com.dapp.outng.partner.fb.client;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dapp.outng.partner.fb.models.FBAppAccessToken;
import com.dapp.outng.partner.models.ValidUserResponse;

@Component
public class FBClient {
	
	private static final Logger LOG = LoggerFactory.getLogger(FBClient.class);
	
	@Autowired
	@Qualifier("fbRestTemplate")
	private RestTemplate fbRestTemplate;
	
	public FBAppAccessToken getAppAccessToken() {
		
		//check db first 
		
	
		String fbAppAccessTokenEndpoint = String.format("https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&grant_type=%s", "2765121657046995", "941672fb921d88d815a2641d52c42cf7", "client_credentials");
		
		ResponseEntity<FBAppAccessToken> response = fbRestTemplate.getForEntity(fbAppAccessTokenEndpoint, FBAppAccessToken.class);
		if(response == null||  response.getBody()==null) {
			return null;
		}
		return response.getBody();

	}
	
	public ValidUserResponse verifyFBUser(String appAccessToken, String userAccessToken) {
		String fbRequestURL = String.format("https://graph.facebook.com/debug_token?input_token=%s&access_token=%s", userAccessToken, appAccessToken);
		Object response = fbRestTemplate.getForObject(fbRequestURL, Object.class);
		String r = response.toString();
		JSONObject obj = null;
		String appId = null;
		String userId = null;
		try {
			obj = new JSONObject(r);
			obj = obj.getJSONObject("data");
			appId = obj.getString("app_id");
			userId = obj.getString("user_id");
		} catch (JSONException e) {
			LOG.error("Error verifying user token: {}", e);
		}
		
		if(StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(appId)) {
			return new ValidUserResponse (true, "FB", userId);
		}
		
		return null;
	}
	

}
