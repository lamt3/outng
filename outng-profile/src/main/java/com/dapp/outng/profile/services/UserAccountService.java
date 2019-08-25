package com.dapp.outng.profile.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.dapp.outng.common.db.OutngDynamoClient;
import com.dapp.outng.common.models.user.OutngUser;
import com.dapp.outng.common.utils.DateUtils;

@Component
public class UserAccountService {

	@Autowired
	private OutngDynamoClient outngDynamoClient;

	protected AmazonDynamoDB client;
	protected DynamoDBMapper mapper ;

	@PostConstruct
	public void initialize() {
		this.client = outngDynamoClient.getClientV1();
		mapper = new DynamoDBMapper(client);

	}

	/*
	 * @Param String clientId 
	 * clientId can be FB Id or Phone Id (Phone Login)
	 */
	public OutngUser getUserByUserPartnerId(String userPartnerId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(userPartnerId));
		
		Map<String, String> ean = new HashMap<String, String>();
		ean.put("#name",  "name");
		ean.put("#location", "location");
		
		DynamoDBQueryExpression<OutngUser> queryExpression = new DynamoDBQueryExpression<OutngUser>()
				.withIndexName("partnerUserId").withConsistentRead(false)
				.withProjectionExpression("userId, userDetail, #location, #name")
				.withExpressionAttributeNames(ean)
				.withKeyConditionExpression("partnerUserId = :v1")
				.withExpressionAttributeValues(eav);
		
		PaginatedQueryList<OutngUser> user = mapper.query(OutngUser.class, queryExpression);
		return CollectionUtils.isEmpty(user) ? null : user.get(0);
	}
	
	public OutngUser getUser(String userId) {
		OutngUser user = mapper.load(OutngUser.class, userId);
		if (user == null) {
			return null;
		}
		if(user.getBirthDate() != null) {
			user.setAge(DateUtils.getAge(user.getBirthDate()));
		}
		
		return user;
	}
	
	public OutngUser updateUserInfo(OutngUser userUpdate) {
		DynamoDBMapperConfig dynamoDBMapperConfig = new DynamoDBMapperConfig.Builder()
				  .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
				  .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES)
				  .build();
		mapper.save(userUpdate, dynamoDBMapperConfig);
		return userUpdate;

	}
	
	public OutngUser saveUser(OutngUser outngUser) {
		mapper.save(outngUser);
		return outngUser;
	}
	
	public OutngUser createNewUser(String partnerUserId, String partnerType) {
		OutngUser newUser = new OutngUser();
		newUser.setPartnerUserId(partnerUserId);
		newUser.setPartnerUserType(partnerType);
		mapper.save(newUser);
		return newUser;
	}
	
	

}
