package com.dapp.web.outng.orchestrator.auth.factories;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.dapp.web.outng.orchestrator.auth.services.validator.UserValidator;

public class UserValidatorFactory {

	private static UserValidatorFactory INSTANCE;

	public static UserValidator getUserValidator(String authType) {
		return INSTANCE.doGetUserValidator(authType);
	}

	@Autowired
	private Map<String, UserValidator> userValidatorProvider;

	public UserValidatorFactory() {
		if (INSTANCE == null) {
			INSTANCE = this;
		}
	}

	private UserValidator doGetUserValidator(String authType) {
		UserValidator userValidator = userValidatorProvider.get(authType);
		if (userValidator == null) {
			throw new IllegalStateException("Could not find auth Type: " + authType);
		}
		return userValidator;
	}

}
