package com.dapp.web.outng.orchestrator.profile.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dapp.outng.common.models.actions.ActionTypes;
import com.dapp.outng.common.models.actions.OutngAction;
import com.dapp.outng.common.models.user.OutngUser;
import com.dapp.outng.messaging.producers.MessageProducer;
import com.dapp.outng.profile.services.UserAccountService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api/v1/services/user")
public class ProfileController {

	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private MessageProducer messageProducer;
	
	
	
//	@RequestMapping(value = "/signup", method = {RequestMethod.PUT}, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	@PutMapping("/signup")
	public String signUpUser(HttpServletRequest httpRequest, HttpServletResponse response, @RequestBody OutngUser user) {
		
		userAccountService.updateUserInfo(user);
		
		Gson gson = new Gson();
		OutngAction action = new OutngAction(ActionTypes.CREATE_USER_ACION, user);
		String payload = gson.toJson(action);
		
		messageProducer.sendMessage("outng-async-topic", user.getUserId(), payload);
		
		return "Success";
		
	}
	
	
}
