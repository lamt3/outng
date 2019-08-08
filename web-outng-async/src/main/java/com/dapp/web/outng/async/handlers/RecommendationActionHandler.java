package com.dapp.web.outng.async.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dapp.outng.common.handlers.ActionHandler;
import com.dapp.outng.common.models.actions.OutngAction;
import com.dapp.outng.common.models.user.OutngUser;
import com.dapp.outng.recommendations.services.UserRecService;

@Component("CREATE_USER_REC")
public class RecommendationActionHandler implements ActionHandler {

	@Autowired
	private UserRecService userRecService;
	
	@Override
	public void processMessage(OutngAction action) {
		OutngUser outngUser = action.getOutngUser();
		userRecService.indexOutngUserObject(outngUser);
	}

}
