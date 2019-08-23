package com.dapp.outng.recommendations.models.userrec;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserElasticDoc {
	
	private String userId;
	private String name;
	private String gender;
	private Integer age;
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
	private List<String> photos;
	private SearchLocation location;
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(Include.NON_NULL)
	public static class SearchLocation{
		private double lat; 
		private double lon;
	}

}
