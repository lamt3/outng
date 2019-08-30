package com.dapp.web.outng.orchestrator.profile.controllers;

import java.security.Principal;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dapp.outng.common.models.actions.ActionTypes;
import com.dapp.outng.common.models.actions.OutngAction;
import com.dapp.outng.common.models.user.OutngUser;
import com.dapp.outng.profile.services.UserAccountService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api/v1/services/user")
public class ProfileController {

	@Autowired
	private UserAccountService userAccountService;
//	@Autowired
//	private MessageProducer messageProducer;
	@Autowired
	@Qualifier("serviceRestTemplate")
	private RestTemplate restTemplate;

	@PutMapping("/signup")
	public String signUpUser(HttpServletRequest httpRequest, HttpServletResponse response,
			@RequestBody OutngUser user) {

		userAccountService.updateUserInfo(user);

//		CompletableFuture.runAsync(() -> messageProducer.sendMessage("outng-async-topic", user.getUserId(), payload));

		return "Success";

	}

	@GetMapping("/profile")
	public String getUser(HttpServletRequest httpRequest, HttpServletResponse response, Principal principal) {
		String userId = principal.getName();
		userAccountService.getUser(userId);

		return "Success";

	}
	
	@GetMapping("/test")
	public String testUser(HttpServletRequest httpRequest, HttpServletResponse response, Principal principal) {
		CompletableFuture.runAsync(() -> {
			System.out.println("Executing rest client...");
			String url = "http://localhost:8088/api/v1/admin/hi";
			restTemplate.getForEntity(url, String.class);
		});

		return "Success";

	}
	

	@PutMapping("/profile")
	public String editUser(HttpServletRequest httpRequest, HttpServletResponse response, Principal principal,
			@RequestBody OutngUser user) {
		String userId = "abc";

		userAccountService.updateUserInfo(user);

		Gson gson = new Gson();
		OutngAction action = new OutngAction();
		action.setActionType(ActionTypes.CREATE_USER_ACION);
		action.setOutngUser(user);
		String payload = gson.toJson(action);

//		messageProducer.sendMessage("outng-async-topic", user.getUserId(), payload);

		return "Success";

	}

}
