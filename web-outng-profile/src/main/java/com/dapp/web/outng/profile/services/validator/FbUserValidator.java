package com.dapp.web.outng.profile.services.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dapp.outng.partner.fb.client.FBClient;
import com.dapp.outng.partner.fb.models.FBAppAccessToken;
import com.dapp.outng.partner.models.ValidUserResponse;

@Component("FbUserValidator")
public class FbUserValidator implements UserValidator {

	@Autowired
	private FBClient fbClient;

	public ValidUserResponse validateUserAccessToken(String userAccessToken) {

		FBAppAccessToken appAccessToken = fbClient.getAppAccessToken();
		ValidUserResponse validUser = null;
		if (appAccessToken != null && appAccessToken.getAccess_token() != null) {
			validUser = fbClient.verifyFBUser(appAccessToken.getAccess_token(), userAccessToken);
		}

		return validUser;
	}

}
