package com.dapp.outng.profile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.dapp.outng.common.models.user.OutngUser;




@Component
public class UserAccountService {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	

	
	public OutngUser getUserByUserPartnerId(String partnerUserId) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("partnerUserId").is(partnerUserId));
		OutngUser user = null;
		user = mongoTemplate.findOne(query, OutngUser.class, "OutngUserMale");
		if(user == null) {
			user = mongoTemplate.findOne(query, OutngUser.class, "OutngUserFemale");
		}
		
		return user;
	}

	public OutngUser getUser(String userId) {
		return null;
	}
	
	public OutngUser createNewUser(OutngUser newUser) {
		
		return null;	
	}
	
	public OutngUser updateUserInfo(OutngUser updateUser) {
		return null;
	}

	
	

}


