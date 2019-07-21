package com.dapp.web.outng.orchestrator.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dapp.outng.common.db.OutngDynamoClient;

@RestController
@RequestMapping("/api/v1/profile/")
public class ProfileController {

	@Autowired
	private OutngDynamoClient c;
	
	@RequestMapping(value = "/user", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String authorizeUser(HttpServletRequest httpRequest, HttpServletResponse response) {
		
		return "Success";
		
	}

}
