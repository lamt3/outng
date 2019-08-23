package com.dapp.outng.common.models.user;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@DynamoDBTable(tableName = "Seen_Users")
public class SeenUsers {
	@DynamoDBHashKey(attributeName = "userId")
	private String userId;
	@DynamoDBAttribute(attributeName = "seenUserIds")
	private List<String> seenUserIds;
}
