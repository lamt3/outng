package com.dapp.web.outng.chat.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.dapp.web.outng.chat.interceptors.AuthHandshakeHandler;
import com.dapp.web.outng.chat.interceptors.OutngChannelInterceptor;

import lombok.RequiredArgsConstructor;

//
@Configuration
//@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@EnableWebSocketMessageBroker
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketConfig.class);

	@Autowired
	private SimpUserRegistry userRegistry;
	
	@NonNull
	private AuthHandshakeHandler authShakeHandler;
	
	@NonNull
	private OutngChannelInterceptor outngChannelInterceptor;


	

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
		config.setUserDestinationPrefix("/user");

	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		registry
				.addEndpoint("/router")
				.setHandshakeHandler(authShakeHandler).setAllowedOrigins("*");

	}

	@EventListener
	public void onSocketConnected(SessionConnectedEvent event) {
		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
		LOGGER.info("WebSocket User Connected: {}", sha.getUser().getName());
		LOGGER.info("WebSocket Session Connected: {}", event.getMessage());
		LOGGER.info("Connect event [sessionId: {} ]", sha.getSessionId());
	}

	@EventListener
	public void onSocketDisconnected(SessionDisconnectEvent event) {
		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
		LOGGER.info("WebSocket Session Disconnected: {}", event.getMessage());
		LOGGER.info("DisConnect event [sessionId: {}]", sha.getSessionId());
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(outngChannelInterceptor);
	}


}
