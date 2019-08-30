package com.dapp.web.outng.orchestrator.admin.controllers;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dapp.outng.common.models.user.OutngUser;
import com.dapp.outng.common.models.user.UserDetail;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminMongoController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AdminMongoController.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@RequestMapping(value = "hi", method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String addUser(HttpServletRequest httpRequest, HttpServletResponse response) {
		return "hi";
	}

	@RequestMapping(value = "user/table", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public String createUserTable(HttpServletRequest httpRequest, HttpServletResponse response) {
		try {
			
			OutngUser user = new OutngUser();
			user.setName("Tom");
			user.setAge("25");
			user.setLocation(new Point(37, 112));
			UserDetail userDetail = new UserDetail();
			userDetail.setInterests(Arrays.asList("japanese", "chinese"));
			user.setUserDetail(userDetail);
			mongoTemplate.insert(user, "user_test");
			
			LOG.info("Creating User Table");
		}catch(Exception e) {
			LOG.error("ERROR:" , e);
		}
		
		return "Success Creating User Table";
	}
	
}
