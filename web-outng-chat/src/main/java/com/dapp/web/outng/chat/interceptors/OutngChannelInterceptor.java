package com.dapp.web.outng.chat.interceptors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.dapp.outng.common.security.JwtTokenProvider;
import com.dapp.web.outng.chat.services.RedisService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OutngChannelInterceptor implements ChannelInterceptor {

	@NonNull
	private JwtTokenProvider provider; 
	@NonNull
	private RedisService redisService;
	

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		System.out.println("Channel Interceptor");
		MessageHeaders headers = message.getHeaders();
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		if(StompCommand.CONNECT.equals(accessor.getCommand())) {

			MultiValueMap<String, String> multiValueMap = headers.get(StompHeaderAccessor.NATIVE_HEADERS, MultiValueMap.class);	
			
			List<String> jwtList = multiValueMap.get("jwt");
//			String jwtUser;
//			if(jwtList.size() > 0 && StringUtils.isNotBlank(jwtList.get(0))) {
//				String jwt = jwtList.get(0);
//				jwtUser = provider.getUserIdFromJwt(jwt);
//				MyPrincipal principalUser = (MyPrincipal) headers.get("simpUser");
//				
//				if(StringUtils.isNotBlank(jwtUser) && principalUser != null && StringUtils.isNotBlank(principalUser.getName())) {
//					
//					redisService.addToSet("outng:socket-connection:user-" + jwtUser, principalUser.getName());
//					
//				}
//				
//				
//			}
			
			return MessageBuilder.createMessage(message.getPayload(), accessor.getMessageHeaders());
		
		}
	
		
		return message; 
	}

	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preReceive(MessageChannel channel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Message<?> postReceive(Message<?> message, MessageChannel channel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
		// TODO Auto-generated method stub

	}

}
