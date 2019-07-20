package com.dapp.web.outng.orchestrator.delegates;

import com.dapp.web.outng.orchestrator.models.request.UserAuthRequest;

public interface AuthDelegate {
	
	public String authorizeUserAndGenerateJWT(UserAuthRequest userAuthRequest);
	

}
