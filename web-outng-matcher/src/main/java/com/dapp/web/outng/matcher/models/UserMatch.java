package com.dapp.web.outng.matcher.models;

public class UserMatch {
	String userId;
	String name;
	String avatarImageURI;
	
	public UserMatch(String userId, String name, String avatarImageURI) {
		this.userId=userId;
		this.name=name;
		this.avatarImageURI=avatarImageURI;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatarImageURI() {
		return avatarImageURI;
	}

	public void setAvatarImageURI(String avatarImageURI) {
		this.avatarImageURI = avatarImageURI;
	}
	
	
}
