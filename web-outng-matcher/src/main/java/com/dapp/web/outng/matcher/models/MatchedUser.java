package com.dapp.web.outng.matcher.models;

import java.util.List;

public class MatchedUser {
	private List<UserMatch> matchedUsers;

	public MatchedUser(List<UserMatch> matchedUsers) {
		super();
		this.matchedUsers = matchedUsers;
	}

	public List<UserMatch> getMatchedUsers() {
		return matchedUsers;
	}

	public void setMatchedUsers(List<UserMatch> matchedUsers) {
		this.matchedUsers = matchedUsers;
	}
	
	
	

}
