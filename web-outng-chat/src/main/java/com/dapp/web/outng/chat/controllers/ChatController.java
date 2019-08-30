package com.dapp.web.outng.chat.controllers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import com.dapp.web.outng.chat.models.ChatMessage;
import com.google.gson.Gson;

@Controller
public class ChatController {
	private static final Logger log = LoggerFactory.getLogger(ChatController.class);
	public static final String SECURED_CHAT_SPECIFIC_USER = "/secured/user/queue/specific-user";

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private SimpUserRegistry userRegistry;
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/chat")
	public void sendSpecificMessage(ChatMessage message, @Header("simpSessionId") String sessionId) {
		
		System.out.println(message.getMessage());
		Gson gson = new Gson();
		String payload = gson.toJson(message);
//		redisTemplate.convertAndSend("chat", payload);
//		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
//		headerAccessor.setSessionId(sessionId);
//		headerAccessor.setLeaveMutable(true);
		
		String sendToUserId = message.getFromUserId();
		if(StringUtils.isNotBlank(sendToUserId)) {
			SimpUser sendToSimpUser = userRegistry.getUser(sendToUserId);
			if(sendToSimpUser != null && StringUtils.isNotBlank(sendToSimpUser.getName())) {
				messagingTemplate.convertAndSendToUser(sendToSimpUser.getName(), "/topic/messages", message);
			}
			
		}
		
		
	}
	
	private void storeUnreadMessage(ChatMessage message) {
		
	}

}
