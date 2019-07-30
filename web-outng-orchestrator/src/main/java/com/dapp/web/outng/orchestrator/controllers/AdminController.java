package com.dapp.web.outng.orchestrator.controllers;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProjectionType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.dapp.outng.common.db.OutngDynamoClient;
import com.dapp.outng.common.models.user.OutngUser;
import com.dapp.outng.common.utils.DateUtils;
import com.dapp.outng.messaging.configs.OutngKafkaConfigs;
import com.dapp.outng.profile.services.UserAccountService;
import com.dapp.outng.recommendations.services.SearchCreationService;



@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {

	@Autowired
	private OutngDynamoClient dynamoClient;
	@Autowired
	private SearchCreationService searchCreationService;
	@Autowired 
	private UserAccountService userService;
	

	protected AmazonDynamoDB client;
	
	
	@PostConstruct
	public void initialize() {
		client = dynamoClient.getClientV1();
	}
	
	@RequestMapping(value = "user", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public ResponseEntity<OutngUser> addUser(HttpServletRequest httpRequest, HttpServletResponse response, @RequestBody OutngUser user) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		mapper.save(user);
		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(value = "user/{userId}", method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public ResponseEntity<OutngUser> getUser(HttpServletRequest httpRequest, HttpServletResponse response, @PathVariable String userId) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		OutngUser user = mapper.load(OutngUser.class, userId);
		user.setAge(DateUtils.getAge(user.getBirthDate()));
		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(value = "user/table", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String createTable2(HttpServletRequest httpRequest, HttpServletResponse response, @RequestBody OutngUser user) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		CreateTableRequest req = mapper.generateCreateTableRequest(OutngUser.class);
		req.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
		req.getGlobalSecondaryIndexes().get(0).setProjection(new Projection().withProjectionType(ProjectionType.ALL));
		req.getGlobalSecondaryIndexes().get(0).setProvisionedThroughput(new ProvisionedThroughput(5l, 5l));
		client.createTable(req);
		System.out.println("Creating Table");
		return "Success";
	}
	
	@RequestMapping(value = "user/search_doc", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String insert(HttpServletRequest httpRequest, HttpServletResponse response, @RequestBody String payload) {
		searchCreationService.indexOutngUser(payload, "7619");
		return "Success";
	}
	
	@RequestMapping(value = "user/by_clientId/{clientId}", method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public ResponseEntity <OutngUser> findUserByClientId(HttpServletRequest httpRequest, HttpServletResponse response, @PathVariable String clientId) {
		OutngUser user = userService.getUserByUserPartnerId(clientId);
		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(value = "user/{userId}", method = { RequestMethod.PUT }, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public ResponseEntity <OutngUser> updateUser(HttpServletRequest httpRequest, HttpServletResponse response, @RequestBody OutngUser user) {
		user = userService.updateUserInfo(user);
		return ResponseEntity.ok().body(user);
	}
	
	
	
}
