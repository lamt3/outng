package com.dapp.web.outng.orchestrator.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dapp.web.outng.orchestrator.delegates.AuthDelegate;
import com.dapp.web.outng.orchestrator.models.request.UserAuthRequest;

@RestController
@RequestMapping("/api/v1/profile/auth")
public class AuthController {
	
	@Autowired
	AuthDelegate authDelegate; 
	
	@RequestMapping(value = "/user", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String authorizeUser(HttpServletRequest httpRequest, HttpServletResponse response, @RequestBody UserAuthRequest userAuthRequest) {
		// if fb send token request to fb
		//// use app access token from below + user access token from authroize user endpoint to valid
		// if verified it is user --> store newly created user into db 
		// return jwt token to client ot store 
		// https://stackoverflow.com/questions/5406859/facebook-access-token-server-side-validation-for-iphone-app
			
		authDelegate.authorizeUserAndGenerateJWT(userAuthRequest);
		
		
		return "Success";
		
	}
	
	
}
