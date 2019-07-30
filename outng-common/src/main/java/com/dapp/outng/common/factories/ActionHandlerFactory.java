package com.dapp.outng.common.factories;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.dapp.outng.common.handlers.ActionHandler;

public class ActionHandlerFactory {
	
	private static ActionHandlerFactory INSTANCE;

	public static ActionHandler getActionHandler(String actionType) {
		return INSTANCE.doGetUserValidator(actionType);
	}

	@Autowired
	private Map<String, ActionHandler> actionHandlerProvider;

	public ActionHandlerFactory() {
		if (INSTANCE == null) {
			INSTANCE = this;
		}
	}

	private ActionHandler doGetUserValidator(String actionType) {
		ActionHandler actionHandler = actionHandlerProvider.get(actionType);
		if (actionHandler == null) {
			throw new IllegalStateException("Could not find auth Type: " + actionType);
		}
		return actionHandler;
	}

}
