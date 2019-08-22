package com.dapp.web.outng.orchestrator.auth.delegates;

import com.dapp.web.outng.orchestrator.auth.models.request.UserAuthRequest;
import com.dapp.web.outng.orchestrator.auth.models.responses.UserAuthResponse;

public interface AuthDelegate {
	
	public UserAuthResponse authorizeUserAndGenerateJWT(UserAuthRequest userAuthRequest);
	

}
