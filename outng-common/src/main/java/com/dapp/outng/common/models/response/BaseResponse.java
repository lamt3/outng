package com.dapp.outng.common.models.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
	/*
	 * Hugo - replace with your error objet
	 */
	private boolean error;
	private String errorMessage;
	
	public BaseResponse() {
		this.error = false;
		this.errorMessage = "No Error";
	}

}
