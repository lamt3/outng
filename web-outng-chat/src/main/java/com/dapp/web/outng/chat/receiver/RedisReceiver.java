package com.dapp.web.outng.chat.receiver;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RedisReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisReceiver.class);

 

    // Invoked when message is publish to "chat" channel
    public void receiveMessage(String message) throws IOException {



        LOGGER.info("Notification Message Received: " + message);
        

    }


    
}