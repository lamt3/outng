package com.dapp.outng.partner.fb.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FBError {

	private String message;
	private String type;
	private int code; 
	private int error_subcode;
	private String error_user_title;
	private String error_user_msg;
	private String fbtrace_id;
	
}
