package com.dapp.web.outng.orchestrator.auth.services.validator;

import com.dapp.outng.partner.models.ValidUserResponse;


public interface UserValidator {
	
	public ValidUserResponse validateUserAuthToken(String userAccessToken);

}
