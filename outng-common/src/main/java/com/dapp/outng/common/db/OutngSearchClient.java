package com.dapp.outng.common.db;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

@Component
public class OutngSearchClient {
	
	private RestHighLevelClient client;
	
	@PostConstruct
	public void initialize() {
		this.client = new RestHighLevelClient(RestClient.builder(new HttpHost("es01", 9200, "http")));
	}
	
	public RestHighLevelClient getSearchClient() {
		return client;
	}
	
	@PreDestroy
	public void preDestroy() throws IOException {
		this.client.close();
	}

}
