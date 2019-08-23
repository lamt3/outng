package com.dapp.web.outng.orchestrator.recommendations.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dapp.outng.recommendations.models.userrec.UserRecQuery;
import com.dapp.outng.recommendations.models.userrec.UserRecResponse;
import com.dapp.web.outng.orchestrator.auth.models.responses.UserAuthResponse;
import com.dapp.web.outng.orchestrator.recommendations.delegates.RecommendationDelegateImpl;

@RestController
@RequestMapping("/api/v1/services/rec")
public class RecommendationController {

	@Autowired
	private RecommendationDelegateImpl recDelegateImpl;
	
	@RequestMapping(value = "/users", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public ResponseEntity<UserRecResponse> getUserRecommendations(HttpServletRequest httpRequest, HttpServletResponse response, @RequestBody UserRecQuery userRecQuery) {
		//get seen users and input into getUserRecs
		String userId = "abc";
		UserRecResponse userRecResponse = recDelegateImpl.getUserRecs(userId, userRecQuery);
		if(userRecResponse != null && userRecResponse.getRecommendedUsers() != null) {
			userRecResponse.setSeenIds(null);
			return ResponseEntity.ok().body(userRecResponse);
		}
		
		
		return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(userRecResponse);
	}
	
}
