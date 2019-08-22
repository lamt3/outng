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
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.dapp.outng.common.db.OutngDynamoClient;
import com.dapp.outng.common.db.OutngSearchClient;
import com.dapp.outng.common.models.user.OutngUser;
import com.dapp.outng.recommendations.builders.ElasticRequestBuilder;
import com.dapp.outng.recommendations.builders.UserRecBuilder;
import com.dapp.outng.recommendations.models.userrec.UserRecQuery;

@Component
public class UserRecService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserRecService.class);
	
	@Autowired
	private OutngSearchClient outngSearchClient;
	private RestHighLevelClient searchClient;
	
	@PostConstruct
	public void initialize() {
		this.searchClient = outngSearchClient.getSearchClient();
	}
	
	
	public void indexOutngUser(String userPayload, String userId) {
		IndexRequest request = new IndexRequest("user_test3"); 
		request.id(userId); 
		request.source(userPayload, XContentType.JSON);
		try {
			IndexResponse indexResponse = searchClient.index(request, RequestOptions.DEFAULT);
			LOG.info("Successful Insert of OutngUser into ES: {}", indexResponse);
		} catch (IOException e) {
			LOG.error("ES Error handling indexing userId:{}", userId, e.getMessage());
		}
	}
	
	public void indexOutngUserObject(OutngUser outngUser) {
		IndexRequest request = ElasticRequestBuilder.buildUserIndexRequest(outngUser);
		try {
			IndexResponse indexResponse = searchClient.index(request, RequestOptions.DEFAULT);
			LOG.info("Successful Insert of OutngUser into ES: {}", indexResponse);
		} catch (Exception e) {
			LOG.error("ES Error handling indexing userId:{}", outngUser.getUserId(), e.getMessage());
		}
	}
	
	public void updateOutngUser(OutngUser outngUser) {
		UpdateRequest updateRequest = ElasticRequestBuilder.buildUserUpdateRequest(outngUser);
		try {
			searchClient.update(updateRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			LOG.error("ES Error handling updating userId:{}", outngUser.getUserId(), e.getMessage());
		}
	}
	
	public void deleteOutngUser(String userId) {
		DeleteRequest deleteRequest = new DeleteRequest("user_test3", userId);
		try {
			searchClient.delete(deleteRequest, RequestOptions.DEFAULT);
		} catch (Exception e) {
			LOG.error("ES Error handling deleting userId:{}", userId, e.getMessage());
		}
	}
	
	public void getUserRecs(UserRecQuery userRecQuery) {
		List<String> ids = new ArrayList<String>();
		ids.add("123456");
		userRecQuery.setSeenIds(ids);
		SearchRequest searchRequest = new SearchRequest("user_test"); 
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		BoolQueryBuilder qb = UserRecBuilder.buildQuery(userRecQuery);
		
		searchSourceBuilder.query(qb); 
		searchSourceBuilder.size(30);
		searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
		searchRequest.source(searchSourceBuilder); 
		
		
		try {
			SearchResponse searchResponse = searchClient.search(searchRequest, RequestOptions.DEFAULT);
			LOG.info("Response", searchResponse);
		} catch (Exception e) {
			LOG.error("ES Error handling querying user recommendation", e.getMessage());
		}
		
	}
	
}
