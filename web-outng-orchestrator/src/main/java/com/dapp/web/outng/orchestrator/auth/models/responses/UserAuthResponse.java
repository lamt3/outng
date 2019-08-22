package com.dapp.web.outng.orchestrator.auth.models.responses;

import com.dapp.outng.common.models.response.BaseResponse;
import com.dapp.outng.common.models.user.OutngUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAuthResponse extends BaseResponse{
	
	private String jwtToken; 
	private OutngUser outngUser;
	private boolean isNewUser;
	
	
	
	

}
