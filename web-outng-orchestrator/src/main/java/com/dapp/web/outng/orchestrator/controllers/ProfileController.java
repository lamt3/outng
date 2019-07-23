package com.dapp.web.outng.orchestrator.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dapp.outng.common.db.OutngDynamoClient;
import com.dapp.outng.recommendations.services.SearchCreationService;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.CreateTableResponse;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

@RestController
@RequestMapping("/api/v1/profile/")
public class ProfileController {

	@Autowired
	private OutngDynamoClient c;
	@Autowired
	private SearchCreationService a;
	
	@RequestMapping(value = "/user", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String authorizeUser(HttpServletRequest httpRequest, HttpServletResponse response) {
		
		CreateTableRequest request = CreateTableRequest.builder()
				.attributeDefinitions(AttributeDefinition.builder()
						.attributeName("Name")
						.attributeType(ScalarAttributeType.S)
						.build())
				.keySchema(KeySchemaElement.builder()
						.attributeName("Name")
						.keyType(KeyType.HASH)
						.build())
				.provisionedThroughput(ProvisionedThroughput.builder()
						.readCapacityUnits(new Long(10))
						.writeCapacityUnits(new Long(10))
						.build())
				.tableName("User_Test")
				.build();
		
		DynamoDbClient ddb = c.dynamoDbClient;
		CreateTableResponse resp = ddb.createTable(request);
		System.out.println(resp.tableDescription().tableName());
		
		return "Success";
		
	}
	
	@RequestMapping(value = "/add", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String addUser(HttpServletRequest httpRequest, HttpServletResponse response) {
		
		a.addUser();
		
		return "Success";
		
	}
	
	@RequestMapping(value = "/add2", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String addUser2(HttpServletRequest httpRequest, HttpServletResponse response, @RequestBody String payload) {
		
		System.out.println(payload);
		
		return "Success";
		
	}

}
