package com.dapp.web.outng.chat.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.dapp.web.outng.chat.models.ChatMessage;

@Controller
public class ChatController {
	private static final Logger log = LoggerFactory.getLogger(ChatController.class);
	 public static final String SECURED_CHAT_SPECIFIC_USER = "/secured/user/queue/specific-user";
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/secured/chat")
	public void sendSpeicifc(@Payload ChatMessage message, @Header("simpSessionId") String sessionId) {
		simpMessagingTemplate.convertAndSendToUser(message.getSendToUserId().toString(),"/secured/user/queue/specific-user", message);
		
		
	}
	
	
	
	   
	   
	   
	   
	
	

}
