package com.dapp.web.outng.orchestrator.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthRequest {
	private String userAccessToken;
	private String partnerType;
	
}
