package com.dapp.web.outng.chat.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import com.dapp.web.outng.chat.interceptors.MyPrincipal;

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
	public void sendSpeicifc(@Payload String message,  @Header("simpSessionId") String sessionId, MessageHeaders messageHeaders) {
//		messagingTemplate.convertAndSend("/topic/messages", message);
		String getUserId = "user2";
		MyPrincipal u = (MyPrincipal) messageHeaders.get("simpUser");
		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
			headerAccessor.setUser(u);
			headerAccessor.setSessionId(sessionId);
			headerAccessor.setLeaveMutable(true);
//		SimpUser user = userRegistry.getUser(getUserId);
		messagingTemplate.convertAndSendToUser(sessionId, "/topic/messages", message, headerAccessor.getMessageHeaders());

		System.out.println(message);

	}

}
