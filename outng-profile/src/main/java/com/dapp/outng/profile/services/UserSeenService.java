package com.dapp.outng.profile.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.dapp.outng.common.models.user.SeenUsers;

@Component
public class UserSeenService {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	public List<String> getSeenUsers(String userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		SeenUsers seenUsers = mongoTemplate.findOne(query, SeenUsers.class);
		return seenUsers == null? null : seenUsers.getSeenUserIds();
	}
	
	public void addSeenUsers(String userId, List<String> seenUserIds) {
		
	}
	
	public void createSeenUsersItem(String userId) {
	
	}
	
	
}
