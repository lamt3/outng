package com.dapp.outng.common.db;

import java.net.URI;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Component
public class OutngDynamoClient {

//	@Value("${cloud.aws.credentials.accessKey}")
//	private String key;
//
//	@Value("${cloud.aws.credentials.secretKey}")
//	private String secretKey;

	public DynamoDbClient dynamoDbClient;

	@PostConstruct
	public void initialize() {
//		AwsBasicCredentials awsCreds = AwsBasicCredentials.create(key, secretKey);
//		DynamoDbClient client = DynamoDbClient.builder().credentialsProvider(StaticCredentialsProvider.create(awsCreds))
//				.region(Region.US_EAST_1).build();
		DynamoDbClient client = DynamoDbClient.builder().endpointOverride(URI.create("http://localhost:8000")).build();
	
		this.dynamoDbClient = client;
		
	}

	@PreDestroy
	public void preDestroy() {
		this.dynamoDbClient.close();
	}

}
