package com.dapp.web.outng.services.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dapp.outng.partner.fb.client.FBClient;
import com.dapp.outng.partner.fb.models.FBAppAccessToken;
import com.dapp.outng.partner.models.ValidUserResponse;

@Component
public class FbUserValidator implements UserValidator {

	@Autowired
	private FBClient fbClient;
	
	public ValidUserResponse validateUserAccessToken(String userAccessToken) {
		
		//get fb app access token 
		FBAppAccessToken appAccessToken = fbClient.getAppAccessToken();
		ValidUserResponse validUser = null;
		if(appAccessToken != null && appAccessToken.getAccess_token()!=null) {
			// call https://graph.facebook.com/debug_token?input_token=INPUT_TOKEN&access_token=ACCESS_TOKEN
			validUser = fbClient.verifyFBUser(appAccessToken.getAccess_token(), userAccessToken);
		}
		
		return validUser;
	}
	
	
	

}
