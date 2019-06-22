package com.dapp.web.outng.profile.delegates;

import com.dapp.web.outng.profile.models.request.UserAuthRequest;

public interface AuthDelegate {
	
	public String authorizeUserAndGenerateJWT(UserAuthRequest userAuthRequest);
	

}
