package com.dapp.web.outng.orchestrator.services.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dapp.outng.partner.fb.client.FBClient;
import com.dapp.outng.partner.fb.models.FBAppAccessToken;
import com.dapp.outng.partner.models.ValidUserResponse;

@Component("Fb")
public class FbUserValidator implements UserValidator {

	@Autowired
	private FBClient fbClient;

	public ValidUserResponse validateUserAuthToken(String userAuthToken) {

//		FBAppAccessToken appAccessToken = fbClient.getAppAccessToken();
		ValidUserResponse validUser = null;
		String accessToken = "2765121657046995|cu7rJNo6TNRUE5yivSZFoEtSpak";
		validUser = fbClient.verifyFBUser(accessToken, userAuthToken);
//		if (appAccessToken != null && appAccessToken.getAccess_token() != null) {
//			validUser = fbClient.verifyFBUser(appAccessToken.getAccess_token(), userAuthToken);
//		}

		return validUser;
	}

}
