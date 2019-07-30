package com.dapp.web.outng.orchestrator.delegates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dapp.outng.common.models.actions.ActionTypes;
import com.dapp.outng.common.models.actions.OutngAction;
import com.dapp.outng.common.models.user.OutngUser;
import com.dapp.outng.messaging.producers.MessageProducer;
import com.dapp.outng.partner.models.ValidUserResponse;
import com.dapp.outng.profile.services.UserAccountService;
import com.dapp.web.outng.orchestrator.factories.UserValidatorFactory;
import com.dapp.web.outng.orchestrator.models.request.UserAuthRequest;
import com.dapp.web.outng.orchestrator.services.validator.UserValidator;
import com.google.gson.Gson;

@Component
public class AuthDelegateImpl implements AuthDelegate {

//	@Autowired
//	private UserValidator userValidator;
	
	// @Autowired
	// private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserAccountService userAccountService;

	public String authorizeUserAndGenerateJWT(UserAuthRequest userAuthRequest) {
		
		UserValidator userValidator = UserValidatorFactory.getUserValidator(userAuthRequest.getAuthType());
		ValidUserResponse validUser = userValidator.validateUserAccessToken(userAuthRequest.getClientAuthAccessToken());

		if (validUser != null && validUser.isValidUser()) {
			
			
			// check if new user
			// if new user add to db and send new user response back to client to kick
			// him/her into sign up flow
			
		}

		return "hi";
	}
	
}
