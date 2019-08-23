package com.dapp.web.outng.async.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dapp.outng.common.handlers.ActionHandler;
import com.dapp.outng.common.models.actions.OutngAction;
import com.dapp.outng.common.models.user.OutngUser;
import com.dapp.outng.profile.services.UserSeenService;
import com.dapp.outng.recommendations.services.UserRecService;

@Component("CREATE_USER_ACTION")
public class CreateUserActionHandler implements ActionHandler {

	@Autowired
	private UserRecService userRecService;
	@Autowired
	private UserSeenService userSeenService;
	
	@Override
	public void processMessage(OutngAction action) {
		OutngUser outngUser = action.getOutngUser();
		userRecService.indexOutngUserObject(outngUser);
		userSeenService.createSeenUsersItem(outngUser.getUserId());
	}

}
