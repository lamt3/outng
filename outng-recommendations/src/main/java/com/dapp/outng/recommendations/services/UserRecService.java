package com.dapp.outng.recommendations.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.dapp.outng.common.models.user.OutngUser;
import com.dapp.outng.recommendations.models.userrec.UserRecQuery;
import com.dapp.outng.recommendations.models.userrec.UserRecResponse;

@Component
public class UserRecService {

	private static final Logger LOG = LoggerFactory.getLogger(UserRecService.class);
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public UserRecResponse getUserRecs(UserRecQuery userRecQuery) {
		Query query = new Query();
		query.addCriteria(Criteria.where("lookingFor.gender").is("m"));

		query.addCriteria(Criteria.where("location").near(new Point(37, 120)).maxDistance(100));
		query.addCriteria(Criteria.where("userDetail.age").gte(20).lte(30));
		List<String> seenUsers = new ArrayList<String>();
		query.addCriteria(Criteria.where("_id").nin(seenUsers));
		List<String> situation = new ArrayList<>();
		query.addCriteria(Criteria.where("userDetail.situation").in(situation));
		
		mongoTemplate.find(query, OutngUser.class, "maleOutngUser");
		return null;
		
	
	}

}
