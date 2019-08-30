package com.dapp.outng.recommendations.models.userrec;

import java.util.List;

import com.dapp.outng.common.models.user.OutngUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRecResponse {
	
	List<String> seenIds;
	List<OutngUser> recommendedUsers;

}
