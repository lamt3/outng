package com.dapp.outng.recommendations.models.userrec;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRecResponse {
	
	List<String> seenIds;
	List<UserElasticDoc> recommendedUsers;

}
