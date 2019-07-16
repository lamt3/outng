package com.dapp.outng.messaging.producers;

public interface MessageProducer {
	public void sendMessage(String topic, String key, String value);
}
