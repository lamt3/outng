package com.dapp.web.outng.orchestrator.auth.delegates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dapp.outng.common.models.user.OutngUser;
import com.dapp.outng.common.security.JwtTokenProvider;
import com.dapp.outng.partner.models.ValidUserResponse;
import com.dapp.outng.profile.services.UserAccountService;
import com.dapp.web.outng.orchestrator.auth.factories.UserValidatorFactory;
import com.dapp.web.outng.orchestrator.auth.models.request.UserAuthRequest;
import com.dapp.web.outng.orchestrator.auth.models.responses.UserAuthResponse;
import com.dapp.web.outng.orchestrator.auth.services.validator.UserValidator;

@Component
public class AuthDelegateImpl implements AuthDelegate {
	
	 @Autowired
	 private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserAccountService userAccountService;

	public UserAuthResponse authorizeUserAndGenerateJWT(UserAuthRequest userAuthRequest) {
		
		UserValidator userValidator = UserValidatorFactory.getUserValidator(userAuthRequest.getPartnerType());
		ValidUserResponse validUser = userValidator.validateUserAuthToken(userAuthRequest.getUserAccessToken());
		UserAuthResponse userAuthResponse = new UserAuthResponse();
		
		if (validUser != null && validUser.isValidUser()) {
			
			OutngUser outngUser = userAccountService.getUserByUserPartnerId(validUser.getClientUserId());
			
			if (outngUser == null) {
				userAuthResponse.setNewUser(true);
			}
			
	
			String jwt = tokenProvider.generateToken(outngUser.getUserId());
			userAuthResponse.setOutngUser(outngUser);
			userAuthResponse.setJwtToken(jwt);
			
		}else {
			userAuthResponse.setError(true);
			userAuthResponse.setErrorMessage("Not Valid User for User Access Token: " + userAuthRequest.getUserAccessToken());
		}

		return userAuthResponse;
	}
	
}
