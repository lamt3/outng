package com.dapp.web.outng.orchestrator.models.request;

public class UserAuthRequest {
	private String clientAuthAccessToken;
	private String authType;
	
	public String getClientAuthAccessToken() {
		return clientAuthAccessToken;
	}
	public void setClientAuthAccessToken(String clientAuthAccessToken) {
		this.clientAuthAccessToken = clientAuthAccessToken;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	
}
