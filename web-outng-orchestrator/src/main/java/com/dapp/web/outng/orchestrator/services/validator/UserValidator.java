package com.dapp.web.outng.orchestrator.services.validator;

import com.dapp.outng.partner.models.ValidUserResponse;


public interface UserValidator {
	
	public ValidUserResponse validateUserAccessToken(String userAccessToken);

}
