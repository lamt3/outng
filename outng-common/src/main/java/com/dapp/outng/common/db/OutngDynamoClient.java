package com.dapp.outng.common.db;

import java.net.URI;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Component
public class OutngDynamoClient {

	// @Value("${cloud.aws.credentials.accessKey}")
	// private String key;
	//
	// @Value("${cloud.aws.credentials.secretKey}")
	// private String secretKey;

	public DynamoDbAsyncClient dynamoDbClient;
	public AmazonDynamoDB dynamoDbClientV1;

	@PostConstruct
	public void initialize() {
		DynamoDbAsyncClient client = DynamoDbAsyncClient.builder().endpointOverride(URI.create("http://localhost:8000")).build();

		AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new EndpointConfiguration("http://dynamo:8000", "us-west-2")).build();

		this.dynamoDbClient = client;
		this.dynamoDbClientV1 = amazonDynamoDB;

	}

	public DynamoDbAsyncClient getClient() {
		return this.dynamoDbClient;
	}

	public AmazonDynamoDB getClientV1() {
		return this.dynamoDbClientV1;
	}

	@PreDestroy
	public void preDestroy() {
		this.dynamoDbClient.close();
	}

}
