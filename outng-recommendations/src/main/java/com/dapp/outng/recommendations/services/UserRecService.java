package com.dapp.outng.recommendations.services;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dapp.outng.common.db.OutngSearchClient;
import com.dapp.outng.common.models.user.OutngUser;

public class UserRecService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserRecService.class);
	
	@Autowired
	private OutngSearchClient outngSearchClient;
	
	private RestHighLevelClient searchClient;
	
	@PostConstruct
	public void initialize() {
		this.searchClient = outngSearchClient.getSearchClient();
		
	}
	
	public void getUserRecs() {
		
	}
	
	public void indexOutngUser(String userPayload, String userId) {
		IndexRequest request = new IndexRequest("user_test3"); 
		request.id(userId); 
		request.source(userPayload, XContentType.JSON);
		try {
			IndexResponse indexResponse = searchClient.index(request, RequestOptions.DEFAULT);
			System.out.println(indexResponse.getIndex());
		} catch (IOException e) {
			LOG.error("Error handling indexing userId:{}", userId, e.getMessage());
		}
	}
	
//	public void indexOutngUser(OutngUser outngUser) {
//		IndexRequest request = new IndexRequest("user_test3"); 
//		request.id(userId); 
//		request.source(userPayload, XContentType.JSON);
//		try {
//			IndexResponse indexResponse = searchClient.index(request, RequestOptions.DEFAULT);
//			System.out.println(indexResponse.getIndex());
//		} catch (IOException e) {
//			LOG.error("Error handling indexing userId:{}", userId, e.getMessage());
//		}
//	}
	
	public void updateOutngUser(String userPayload, String userId) {
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index("user_test3");
		updateRequest.id(userId);
		updateRequest.doc(XContentType.JSON, userPayload);
		try {
			searchClient.update(updateRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteOutngUser(String userPayload, String userId) {
		DeleteRequest deleteRequest = new DeleteRequest("user_test3", userId);
		try {
			searchClient.delete(deleteRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

}
