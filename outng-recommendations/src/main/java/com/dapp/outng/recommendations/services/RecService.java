package com.dapp.outng.recommendations.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dapp.outng.common.db.OutngDynamoClient;

public class RecService {
	
	@Autowired
	private OutngDynamoClient client; 
	
	public List<String> getSeenIds(String userId){
		return null;
	}
	

}
