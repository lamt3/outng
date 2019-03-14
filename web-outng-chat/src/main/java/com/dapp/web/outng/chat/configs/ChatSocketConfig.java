package com.dapp.web.outng.chat.configs;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

public class ChatSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/secured/user/queue/specific-user");
		config.setApplicationDestinationPrefixes("/spring-security-mvc-socket");
		config.setUserDestinationPrefix("/secured/user");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		 registry.addEndpoint("/secured/room").setHandshakeHandler(new DefaultHandshakeHandler() {
		    @SuppressWarnings("unused")
			public boolean beforeHandshake(
		        ServerHttpRequest request, 
		        ServerHttpResponse response, 
		        WebSocketHandler wsHandler, Map<String,String> attributes) throws Exception {
		            if (request instanceof ServletServerHttpRequest) {
		                ServletServerHttpRequest servletRequest
		                 = (ServletServerHttpRequest) request;
		                HttpSession session = servletRequest
		                  .getServletRequest().getSession();
		                attributes.put("sessionId", session.getId());
		            }
		                return true;
		        }}).withSockJS();
		 
	}
}
