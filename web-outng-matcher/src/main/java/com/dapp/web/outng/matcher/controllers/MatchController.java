package com.dapp.web.outng.matcher.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dapp.web.outng.matcher.models.MatchedUser;
import com.dapp.web.outng.matcher.models.UserMatch;

@RestController
@RequestMapping("/api/v1/match/")
public class MatchController {
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public ResponseEntity<MatchedUser> getMatches(HttpServletRequest httpRequest, HttpServletResponse response) {
		UserMatch a = new UserMatch("abc", "bob", "https://placeimg.com/640/480/people");
		UserMatch b = new UserMatch("abec", "fred", "https://placeimg.com/640/480/people");
		List<UserMatch> l = Arrays.asList(a, b);
		MatchedUser m = new MatchedUser(l);
		return ResponseEntity.ok().body(m);	
	}
	

}
