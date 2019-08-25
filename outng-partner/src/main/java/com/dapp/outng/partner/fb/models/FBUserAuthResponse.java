package com.dapp.outng.partner.fb.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FBUserAuthResponse {
	
	private FBData data;
	private FBError error;

}
