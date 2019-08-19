package com.dapp.web.outng.chat.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
	private String sendToUserId;
	private String fromUserId;
	private String message;
}
