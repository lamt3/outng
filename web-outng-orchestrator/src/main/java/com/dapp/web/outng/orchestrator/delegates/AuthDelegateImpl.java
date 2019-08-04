package com.dapp.web.outng.orchestrator.delegates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dapp.outng.common.models.user.OutngUser;
import com.dapp.outng.partner.models.ValidUserResponse;
import com.dapp.outng.profile.services.UserAccountService;
import com.dapp.web.outng.orchestrator.factories.UserValidatorFactory;
import com.dapp.web.outng.orchestrator.models.request.UserAuthRequest;
import com.dapp.web.outng.orchestrator.models.responses.UserAuthResponse;
import com.dapp.web.outng.orchestrator.services.validator.UserValidator;

@Component
public class AuthDelegateImpl implements AuthDelegate {
	
	// @Autowired
	// private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserAccountService userAccountService;

	public UserAuthResponse authorizeUserAndGenerateJWT(UserAuthRequest userAuthRequest) {
		
		UserValidator userValidator = UserValidatorFactory.getUserValidator(userAuthRequest.getPartnerType());
		ValidUserResponse validUser = userValidator.validateUserAuthToken(userAuthRequest.getUserAccessToken());
		UserAuthResponse userAuthResponse = new UserAuthResponse();
		
		if (validUser != null && validUser.isValidUser()) {
			
			OutngUser outngUser = userAccountService.getUserByUserPartnerId(validUser.getClientUserId());
			
			if (outngUser == null) {
				outngUser = userAccountService.createNewUser(validUser.getClientUserId(), validUser.getUserType());
				userAuthResponse.setNewUser(true);
			}
			userAuthResponse.setOutngUser(outngUser);
			
			//getJWT Token
			
			
		}else {
			
		}

		return userAuthResponse;
	}
	
}
