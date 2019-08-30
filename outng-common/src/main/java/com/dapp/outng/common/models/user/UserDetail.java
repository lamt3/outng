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
public class UserDetail {
	
	private String gender;
	private List<String> situation; 
	private List<String> interests;
	private int height;
	private int weight;
	private String occupation;
	private String school;
	private String religion;
	private String quirks;
	private String smoking;
	private String drinking;
	private String ethnicity;
	private String bio;
	
}
