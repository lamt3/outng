package com.dapp.outng.profile.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dapp.outng.common.db.OutngDynamoClient;
import com.dapp.outng.profile.models.AppUser;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Component
public class ProfileService {
	
	@Autowired
	private OutngDynamoClient dynamoClient;
	
	protected DynamoDbClient client;
	
	@PostConstruct
	public void initialize() {
		this.client = dynamoClient.getClient();
		
	}
	
	public void checkForUser() {
		
	}
	
	
	public void saveUser(AppUser appUser){
		
		
		
		
		
	}
	

	

	
}
