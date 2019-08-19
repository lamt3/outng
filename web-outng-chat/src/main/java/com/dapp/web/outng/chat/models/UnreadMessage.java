package com.dapp.web.outng.chat.models;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnreadMessage {
 	private Map<String, List<ChatMessage>> unreadMessages; 

}
