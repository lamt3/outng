package com.dapp.outng.profile.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.dapp.outng.common.models.user.OutngUser;




@Component
public class UserAccountService {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void getRecommendations() {
		Query query = new Query();
		query.addCriteria(Criteria.where("lookingFor.gender").is("m"));

		query.addCriteria(Criteria.where("location").near(new Point(37, 120)).maxDistance(100));
		query.addCriteria(Criteria.where("userDetail.age").gte(20).lte(30));
		List<String> seenUsers = new ArrayList<String>();
		query.addCriteria(Criteria.where("_id").nin(seenUsers));
		List<String> situation = new ArrayList<>();
		query.addCriteria(Criteria.where("userDetail.situation").in(situation));
		
		mongoTemplate.find(query, OutngUser.class, "maleOutngUser");
		
	
	}
	
	public OutngUser getUserByUserPartnerId(String partnerId) {
		
		return null;
		
	}


	
	

}
