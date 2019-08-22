package com.dapp.web.outng.orchestrator.auth.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dapp.web.outng.orchestrator.auth.delegates.AuthDelegate;
import com.dapp.web.outng.orchestrator.auth.models.request.UserAuthRequest;
import com.dapp.web.outng.orchestrator.auth.models.responses.UserAuthResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	AuthDelegate authDelegate; 
	
	/*
	 * 	if Partner == 'Fb'  
	 * 		send token request to fb
	 * 		 use App Access Token + user Access Token (sent from client after user logins in via FB)
	 * 			if verified it is user 
	 * 				--> store newly created user into db 
	 * 				--> or retrieve existing user
	 * 			create new jwt token to client to store 
	 * https://stackoverflow.com/questions/5406859/facebook-access-token-server-side-validation-for-iphone-app
	 */
	
	@RequestMapping(value = "/user", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public ResponseEntity<UserAuthResponse> authorizeUser(HttpServletRequest httpRequest, HttpServletResponse response, @RequestBody UserAuthRequest userAuthRequest) {
		UserAuthResponse userAuthResponse = authDelegate.authorizeUserAndGenerateJWT(userAuthRequest);
		return ResponseEntity.ok().body(userAuthResponse);
	}
	
	
}
