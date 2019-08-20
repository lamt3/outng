package com.dapp.web.outng.orchestrator.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dapp.outng.recommendations.models.userrec.UserRecQuery;
import com.dapp.outng.recommendations.services.UserRecService;

@RestController
@RequestMapping("/api/v1/services/rec")
public class RecommendationController {

	@Autowired
	private UserRecService userRecService; 
	
	@RequestMapping(value = "/users", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String getUserRecommendations(HttpServletRequest httpRequest, HttpServletResponse response, @RequestBody UserRecQuery userRecQuery) {
		userRecService.getUserRecs(userRecQuery);
		
		
		return "Success";
	}
	
}
