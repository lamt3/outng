package com.dapp.web.outng.orchestrator.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services/v1/rec")
public class RecommendationController {

	@RequestMapping(value = "/location/{locationId}", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String authorizeUser(HttpServletRequest httpRequest, HttpServletResponse response, @PathVariable String locationId) {
		String userId = "abc";
		
		
		return "Success";
	}
	
}
