package com.dapp.outng.partner.fb.client;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dapp.outng.common.utils.UrlUtils;
import com.dapp.outng.partner.fb.models.FBAppAccessToken;
import com.dapp.outng.partner.models.ValidUserResponse;

public class FBClient {
	
	private static final Logger LOG = LoggerFactory.getLogger(FBClient.class);
	
	@Autowired
	@Qualifier("fbRestTemplate")
	private RestTemplate fbRestTemplate;
	
	public FBAppAccessToken getAppAccessToken() {
		
		//check db first 
		
		Map<String, String> q = new LinkedHashMap<String, String>();
		q.put("clientId", "2765121657046995");
		q.put("client_secret", "941672fb921d88d815a2641d52c42cf7");
		q.put("grant_type", "client_credentials");
		String fbAppAccessTokenEndPoint  = UrlUtils.buildUrlWithQueryParams("https://graph.facebook.com/oauth/access_token", q);
		ResponseEntity<FBAppAccessToken> response = fbRestTemplate.getForEntity(fbAppAccessTokenEndPoint, FBAppAccessToken.class);
		if(response == null||  response.getBody()==null) {
			return null;
		}
		return response.getBody();

	}
	
	public ValidUserResponse verifyFBUser(String appAccessToken, String userAccessToken) {
		
		ResponseEntity<String> response = fbRestTemplate.getForEntity("https://graph.facebook.com/debug_token?input_token=<>&access_token=<>", String.class);
		String resp = response.getBody();
		JSONObject obj = null;
		String appId = null;
		String userId = null;
		try {
			obj = new JSONObject(resp);
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
