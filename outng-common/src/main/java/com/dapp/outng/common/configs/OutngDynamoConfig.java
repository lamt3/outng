//package com.dapp.outng.common.configs;
//
//import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
//
//@Configuration
//@EnableDynamoDBRepositories(basePackages = "com.dapp.outng.*")
//public class OutngDynamoConfig {
//
//	@Bean
//	public AmazonDynamoDB amazonDynamoDB() {
//		AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());
//		amazonDynamoDB.setEndpoint("http://localhost:8000");
//		return amazonDynamoDB;
//	}
//	
//	@Bean
//	public AWSCredentials amazonAWSCredentials() {
//		return new BasicAWSCredentials("String", "String");
//	}
//
//}
