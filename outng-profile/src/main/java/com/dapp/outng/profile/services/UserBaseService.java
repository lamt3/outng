package com.dapp.outng.profile.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.dapp.outng.common.db.OutngDynamoClient;

public abstract class UserBaseService {
	
	@Autowired
	private OutngDynamoClient outngDynamoClient;
	protected AmazonDynamoDB client;
	protected DynamoDBMapper mapper ;

	@PostConstruct
	public void initialize() {
		this.client = outngDynamoClient.getClientV1();
		mapper = new DynamoDBMapper(client);

	}

}
