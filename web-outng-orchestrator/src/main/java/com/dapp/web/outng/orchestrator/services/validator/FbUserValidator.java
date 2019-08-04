package com.dapp.web.outng.orchestrator.services.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dapp.outng.partner.fb.client.FBClient;
import com.dapp.outng.partner.models.ValidUserResponse;

@Component("Fb")
public class FbUserValidator implements UserValidator {

	@Autowired
	private FBClient fbClient;

	public ValidUserResponse validateUserAuthToken(String userAuthToken) {

		String appAccessToken = fbClient.getAppAccessToken();
		ValidUserResponse validUser = null;

		
		if (StringUtils.isNotBlank(appAccessToken)) {
			validUser = fbClient.verifyFBUser(appAccessToken, userAuthToken);
		}

		return validUser;
	}

}
