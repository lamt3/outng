package com.dapp.web.outng.orchestrator.delegates;

import com.dapp.web.outng.orchestrator.models.request.UserAuthRequest;
import com.dapp.web.outng.orchestrator.models.responses.UserAuthResponse;

public interface AuthDelegate {
	
	public UserAuthResponse authorizeUserAndGenerateJWT(UserAuthRequest userAuthRequest);
	

}
