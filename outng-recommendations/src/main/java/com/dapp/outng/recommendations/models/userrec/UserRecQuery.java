package com.dapp.outng.recommendations.models.userrec;

import java.util.List;

import com.dapp.outng.common.models.user.Location;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRecQuery {
	private List<Integer> ageRange;
	private List<String> situation;
	private String lookingForGender; 
	private Location location;	
	private String distanceFilter;
	private List<String> seenIds;
}
