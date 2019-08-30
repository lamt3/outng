package com.dapp.outng.recommendations.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.dapp.outng.common.db.OutngSearchClient;
import com.dapp.outng.common.models.user.OutngUser;
import com.dapp.outng.recommendations.builders.ElasticRequestBuilder;
import com.dapp.outng.recommendations.builders.UserRecBuilder;
import com.dapp.outng.recommendations.models.userrec.UserElasticDoc;
import com.dapp.outng.recommendations.models.userrec.UserRecQuery;
import com.dapp.outng.recommendations.models.userrec.UserRecResponse;
import com.google.gson.Gson;

@Component
public class UserRecService {

	private static final Logger LOG = LoggerFactory.getLogger(UserRecService.class);
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

}
