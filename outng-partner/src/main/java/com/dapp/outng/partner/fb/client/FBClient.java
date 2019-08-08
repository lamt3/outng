package com.dapp.outng.partner.fb.client;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dapp.outng.partner.configs.PartnerSecretsConfig;
import com.dapp.outng.partner.fb.models.FBAppAccessToken;
import com.dapp.outng.partner.fb.models.FBUserAuthResponse;
import com.dapp.outng.partner.models.ValidUserResponse;
import com.google.gson.Gson;

@Component
public class FBClient {
	
	private static final Logger LOG = LoggerFactory.getLogger(FBClient.class);
	
	@Autowired
	@Qualifier("fbRestTemplate")
	private RestTemplate fbRestTemplate;
	
	@Autowired
	private PartnerSecretsConfig partnerConfig;
	
	public String getAppAccessToken() {

		String fbAppAccessToken = partnerConfig.getFbAppAccessToken();
		if(StringUtils.isBlank(fbAppAccessToken)) {
			String fbAppAccessTokenEndpoint = String.format("https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&grant_type=%s", "2765121657046995", "941672fb921d88d815a2641d52c42cf7", "client_credentials");
			
			ResponseEntity<FBAppAccessToken> response = fbRestTemplate.getForEntity(fbAppAccessTokenEndpoint, FBAppAccessToken.class);
			if(response == null||  response.getBody()==null) {
				return null;
			}
			fbAppAccessToken = response.getBody().getAccess_token();
		}
	
		return fbAppAccessToken;

	}
	
	public ValidUserResponse verifyFBUser(String appAccessToken, String userAccessToken) {
		String fbRequestURL = String.format("https://graph.facebook.com/debug_token?input_token=%s&access_token=%s", userAccessToken, appAccessToken);
		Object response = fbRestTemplate.getForObject(fbRequestURL, Object.class);
		String r = response.toString();
		Gson gson = new Gson();

		ValidUserResponse resp = null;
		try {
			FBUserAuthResponse fbUserAuthResponse = gson.fromJson(r, FBUserAuthResponse.class);
			if(fbUserAuthResponse != null && fbUserAuthResponse.getData() != null && StringUtils.isNotBlank(fbUserAuthResponse.getData().getUser_id())) {
				resp = new ValidUserResponse(true, "FB", fbUserAuthResponse.getData().getUser_id());
			}
		
		} catch (Exception e) {
			LOG.error("Error verifying user token: {}", e);
		}
		
		return resp;
	}
	

}
