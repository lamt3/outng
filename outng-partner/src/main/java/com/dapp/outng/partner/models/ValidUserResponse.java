package com.dapp.outng.partner.models;

public class ValidUserResponse {
	
	private boolean isValidUser;
	private String userType;
	private String clientUserId;
	
	public ValidUserResponse(boolean isValidUser, String userType, String userId) {
		super();
		this.isValidUser = isValidUser;
		this.userType = userType;
		this.clientUserId = userId;
	}
	public boolean isValidUser() {
		return isValidUser;
	}
	public void setValidUser(boolean isValidUser) {
		this.isValidUser = isValidUser;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getClientUserId() {
		return clientUserId;
	}
	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}
	
}
