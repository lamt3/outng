package com.dapp.web.outng.chat.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {
	
	@RequestMapping(value = "hi", method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String heartBeat(HttpServletRequest httpRequest, HttpServletResponse response) {
		return "hi";
	}
	
}
