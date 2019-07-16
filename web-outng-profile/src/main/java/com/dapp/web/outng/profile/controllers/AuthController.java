package com.dapp.web.outng.profile.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dapp.web.outng.profile.delegates.AuthDelegate;
import com.dapp.web.outng.profile.models.request.UserAuthRequest;

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
	
	@RequestMapping(value = "/generate/{provider}/appAccessToken", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String generateAppAcessToken(HttpServletRequest httpRequest, HttpServletResponse response) {
		// generate app access token store it in db
	
		return null;
		
	}
	
	//return JWT Token in Response
	@RequestMapping(value = "/signup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String registerNewUser(HttpServletRequest httpRequest, HttpServletResponse response) {
		return "hi";
	}
	
	
	//return JWT Token in Response
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String login(HttpServletRequest httpRequest, HttpServletResponse response) {
		return "hi";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String logout(HttpServletRequest httpRequest, HttpServletResponse response) {
		return "hi";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String delete(HttpServletRequest httpRequest, HttpServletResponse response) {
		return "hi";
	}

}
