package com.dapp.outng.common.models.actions;

import java.util.List;

import com.dapp.outng.common.models.user.SeenUsers;

public class OutngActionBuilder {
	
	public static OutngAction buildSeenUsersAction(String userId, List<String> seenIds) {
		OutngAction outngAction = new OutngAction();
		outngAction.setActionType(ActionTypes.SEEN_USERS_ACTION);
		
		SeenUsers nextSeenUsers = new SeenUsers();
		nextSeenUsers.setSeenUserIds(seenIds);
		nextSeenUsers.setUserId(userId);
		
		outngAction.setSeenUsers(nextSeenUsers);
		
		return outngAction;
	}

}
