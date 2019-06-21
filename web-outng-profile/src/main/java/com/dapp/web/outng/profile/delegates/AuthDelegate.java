package com.dapp.web.outng.profile.delegates;

public interface AuthDelegate {
	
	public String authorizeUserAndGenerateJWT(String userAccessToken);
	

}
