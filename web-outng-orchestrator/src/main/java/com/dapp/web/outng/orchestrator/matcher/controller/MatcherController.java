package com.dapp.web.outng.orchestrator.matcher.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/services/matcher")
public class MatcherController {
	
	@RequestMapping(value = "/swipe", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String postSwipe(HttpServletRequest httpRequest, HttpServletResponse response) {
		
		
		
		return "Success";
	}

}
