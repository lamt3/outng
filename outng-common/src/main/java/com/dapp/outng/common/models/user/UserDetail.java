package com.dapp.outng.common.models.user;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@DynamoDBDocument
public class UserDetail {
	
	@DynamoDBAttribute(attributeName = "gender")
	private String gender;
	@DynamoDBAttribute(attributeName = "situation")
	private List<String> situation; 
	@DynamoDBAttribute(attributeName = "interests")
	private List<String> interests;
	@DynamoDBAttribute(attributeName = "height")
	private int height;
	@DynamoDBAttribute(attributeName = "weight")
	private int weight;
	@DynamoDBAttribute(attributeName = "occupation")
	private String occupation;
	@DynamoDBAttribute(attributeName = "school")
	private String school;
	@DynamoDBAttribute(attributeName = "religion")
	private String religion;
	@DynamoDBAttribute(attributeName = "quirks")
	private String quirks;
	@DynamoDBAttribute(attributeName = "smoking")
	private String smoking;
	@DynamoDBAttribute(attributeName = "drinking")
	private String drinking;
	@DynamoDBAttribute(attributeName = "ethnicity")
	private String ethnicity;
	@DynamoDBAttribute(attributeName = "bio")
	private String bio;
	
}
