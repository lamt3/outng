package com.dapp.outng.profile.models;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class AppUser {
	
	private Long userId;
	private String userType;
	private String firstName;
	private String lastName;
	private String userName; 
	private Date birthDate; 
	private Location location; 
	private String clientUserId;
	private String clientUserType;
	private String gender;
	private Map<String, List<String>> interests;
	
	public AppUser(String clientUserId, String clientUserType) {
		this.clientUserId = clientUserId;
		this.clientUserType= clientUserType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}

	public String getClientUserType() {
		return clientUserType;
	}

	public void setClientUserType(String clientUserType) {
		this.clientUserType = clientUserType;
	}
	
	
	
	
	
	
	
	

	
	
}
