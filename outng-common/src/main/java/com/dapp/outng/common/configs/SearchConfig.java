//package com.dapp.outng.common.configs;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SearchConfig {
//	
////	@Value("${elasticsearch.host}")
////	private String elasticsearchHost;
//
//	@Bean(destroyMethod = "close")
//	public RestHighLevelClient client() {
//
//		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost:9200")));
//
//		return client;
//
//	}
//
//}
