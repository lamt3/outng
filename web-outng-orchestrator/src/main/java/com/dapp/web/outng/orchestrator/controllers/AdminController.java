package com.dapp.web.outng.orchestrator.controllers;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.dapp.outng.common.db.OutngDynamoClient;
import com.dapp.outng.profile.models.Profile;



@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {

	@Autowired
	private OutngDynamoClient dynamoClient;

	protected AmazonDynamoDB client;
	
	@PostConstruct
	public void initialize() {
		client = dynamoClient.getClientV1();
	}

	@RequestMapping(value = "user/table", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String createTable(HttpServletRequest httpRequest, HttpServletResponse response, @RequestBody Profile profile) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		CreateTableRequest req = mapper.generateCreateTableRequest(Profile.class);
		req.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
		System.out.println("Creating Table");
		CreateTableResult r = client.createTable(req);
		mapper.save(r);
		return "Success";
	}
	
	@RequestMapping(value = "user/table1", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String createTable2(HttpServletRequest httpRequest, HttpServletResponse response, @RequestBody Profile profile) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		CreateTableRequest req = mapper.generateCreateTableRequest(Profile.class);
		req.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
		req.getGlobalSecondaryIndexes().get(0).setProvisionedThroughput(new ProvisionedThroughput(5l, 5l));
		System.out.println("Creating Table");
		CreateTableResult r = client.createTable(req);
		mapper.save(r);
		return "Success";
	}
	
	
}
