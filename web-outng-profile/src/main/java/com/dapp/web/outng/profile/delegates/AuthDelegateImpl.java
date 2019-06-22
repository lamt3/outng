package com.dapp.web.outng.profile.delegates;

import org.springframework.stereotype.Component;

import com.dapp.outng.partner.models.ValidUserResponse;
import com.dapp.outng.profile.models.AppUser;
import com.dapp.web.outng.profile.factories.UserValidatorFactory;
import com.dapp.web.outng.profile.models.request.UserAuthRequest;
import com.dapp.web.outng.profile.services.validator.UserValidator;

@Component
public class AuthDelegateImpl implements AuthDelegate {

//	@Autowired
//	private UserValidator userValidator;
	
	// @Autowired
	// private JwtTokenProvider tokenProvider;

	public String authorizeUserAndGenerateJWT(UserAuthRequest userAuthRequest) {
		
		UserValidator userValidator = UserValidatorFactory.getUserValidator(userAuthRequest.getAuthType());
		ValidUserResponse validUser = userValidator.validateUserAccessToken(userAuthRequest.getClientAuthAccessToken());
		AppUser user = null;
		if (validUser != null && validUser.isValidUser()) {
			// check if new user
			// if new user add to db and send new user response back to client to kick
			// him/her into sign up flow
			
		}

		return "hi";
	}

}
