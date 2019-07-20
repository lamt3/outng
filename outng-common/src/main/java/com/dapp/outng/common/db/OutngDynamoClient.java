package com.dapp.outng.common.db;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Component
public class OutngDynamoClient {

	@Value("${cloud.aws.credentials.accessKey}")
	private String key;

	@Value("${cloud.aws.credentials.secretKey}")
	private String secretKey;

	private DynamoDbClient dynamoDbClient;

	@PostConstruct
	public void initialize() {
		AwsBasicCredentials awsCreds = AwsBasicCredentials.create(key, secretKey);
		DynamoDbClient client = DynamoDbClient.builder().credentialsProvider(StaticCredentialsProvider.create(awsCreds))
				.region(Region.US_EAST_1).build();

		this.dynamoDbClient = client;
	}

	@PreDestroy
	public void preDestroy() {
		this.dynamoDbClient.close();
	}

}
