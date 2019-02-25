package com.dapp.web.outng.profile.delegates;

import org.springframework.beans.factory.annotation.Autowired;

import com.dapp.outng.common.security.JwtTokenProvider;
import com.dapp.outng.partner.models.ValidUserResponse;
import com.dapp.outng.profile.models.AppUser;
import com.dapp.web.outng.profile.services.NewUserService;
import com.dapp.web.outng.services.validator.UserValidator;

public class AuthDelegateImpl implements AuthDelegate{

	@Autowired
	private UserValidator userValidator;
	@Autowired
	private NewUserService newUserService;
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	public boolean authorizeUser(String userAccessToken) {
		
		ValidUserResponse validUser = userValidator.validateUserAccessToken(userAccessToken);
		AppUser user = null;
		if(validUser!=null && validUser.isValidUser()) {
			//check if new user 
			//if new user add to db and send new user response back to client to kick him/her into sign up flow 
			user = newUserService.checkAndRegisterNewUser(user.getClientUserId());
		}
		
		String jwt = tokenProvider.generateToken(user.getUserId());
		
		
		
		return false;
	}

}
