package com.dapp.web.outng.chat.receiver;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dapp.web.outng.chat.models.ChatMessage;
import com.google.gson.Gson;


public class RedisReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisReceiver.class);

 

    // Invoked when message is publish to "chat" channel
    public void receiveMessage(String message) throws IOException {

    	Gson gson = new Gson();
    	ChatMessage c = gson.fromJson(message, ChatMessage.class);
        LOGGER.info("Notification Message Received: " + message);  
        

    }


    
}