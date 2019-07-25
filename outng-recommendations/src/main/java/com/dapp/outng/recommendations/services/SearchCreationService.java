package com.dapp.outng.recommendations.services;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dapp.outng.common.db.OutngSearchClient;

@Component
public class SearchCreationService {
	@Autowired
	private OutngSearchClient outngSearchClient;
	
	private RestHighLevelClient searchClient;
	
	@PostConstruct
	public void initialize() {
		this.searchClient = outngSearchClient.getSearchClient();
		
	}

	public void createIndex() {
		CreateIndexRequest request = new CreateIndexRequest("user_test2");
		Map<String, Object> message = new HashMap<>();
		message.put("type", "text");
		
		Map<String, Object> properties = new HashMap<>();
		properties.put("field1", message);
		Map<String, Object> mapping = new HashMap<>();
		mapping.put("properties", properties);
		request.mapping(mapping);
		try {
			CreateIndexResponse createIndexResponse = searchClient.indices().create(request, RequestOptions.DEFAULT);
			System.out.println(createIndexResponse.isAcknowledged());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void addUser() {

		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("user", "kimchy");
		jsonMap.put("postDate", new Date());
		jsonMap.put("message", "trying out Elasticsearch");
	
		IndexRequest indexRequest = new IndexRequest("user_test").id("1").source(jsonMap);
		try {
			IndexResponse indexResponse = searchClient.index(indexRequest, RequestOptions.DEFAULT);
			System.out.println(indexResponse.getIndex());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
