package com.dapp.web.outng.orchestrator.auth.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthRequest {
	private String userAccessToken;
	private String partnerType;
	
}
