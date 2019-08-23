package com.dapp.web.outng.orchestrator.recommendations.delegates;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dapp.outng.common.models.actions.OutngAction;
import com.dapp.outng.common.models.actions.OutngActionBuilder;
import com.dapp.outng.common.utils.JsonUtils;
import com.dapp.outng.messaging.producers.MessageProducer;
import com.dapp.outng.profile.services.UserSeenService;
import com.dapp.outng.recommendations.models.userrec.UserElasticDoc;
import com.dapp.outng.recommendations.models.userrec.UserRecQuery;
import com.dapp.outng.recommendations.models.userrec.UserRecResponse;
import com.dapp.outng.recommendations.services.UserRecService;

public class RecommendationDelegateImpl {
	@Autowired
	private UserSeenService userSeenService;
	@Autowired
	private UserRecService userRecService; 
	@Autowired
	private MessageProducer messageProducer;

	public List<UserElasticDoc> getUsers(String userId, UserRecQuery userRecQuery) {

		List<String> seenUserIds = userSeenService.getSeenUsers(userId);
		if(seenUserIds != null) {
			userRecQuery.setSeenIds(seenUserIds);
		}
		UserRecResponse userRecResponse = userRecService.getUserRecs(userRecQuery);
		
		sendMessage(userId, userRecResponse);
		
		return userRecResponse.getRecommendedUsers();
	}
	
	private void sendMessage(String userId, UserRecResponse userRecResponse) {
		if(userRecResponse != null && userRecResponse.getSeenIds() != null && userRecResponse.getSeenIds().size() > 0) {
			OutngAction seenUsersAction = OutngActionBuilder.buildSeenUsersAction(userId, userRecResponse.getSeenIds());
			String payload = JsonUtils.toJson(seenUsersAction);
			messageProducer.sendMessage("outng-async-topic", userId, payload);
		}
		
	}

}
