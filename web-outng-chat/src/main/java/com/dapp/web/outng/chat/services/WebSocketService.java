package com.dapp.web.outng.chat.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.dapp.web.outng.chat.models.ChatMessage;

@Component
public class WebSocketService {

	private SimpMessagingTemplate template;

	
	public void sendChatMessage(ChatMessage message) {
//		template.convertAndSendToUser(user, destination, message);
	}

}
