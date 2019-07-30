package com.dapp.web.outng.orchestrator.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dapp.outng.common.db.OutngDynamoClient;
import com.dapp.outng.recommendations.services.SearchCreationService;

import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

@RestController
@RequestMapping("/api/v1/services/user")
public class ProfileController {

	@Autowired
	private OutngDynamoClient c;
	@Autowired
	private SearchCreationService a;
	
	
	
	
	@RequestMapping(value = "/signup", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String signUpUser(HttpServletRequest httpRequest, HttpServletResponse response) {
		
		return "Success";
		
	}
	
	
}
