package com.dapp.web.outng.async.handlers;

import org.springframework.stereotype.Component;

import com.dapp.outng.common.handlers.ActionHandler;
import com.dapp.outng.common.models.actions.OutngAction;
import com.dapp.outng.common.models.user.OutngUser;

@Component("CREATE_USER_REC")
public class RecommendationActionHandler implements ActionHandler {

	@Override
	public void processMessage(OutngAction action) {
		OutngUser user = action.getOutngUser();
		
		
		
	}

}
