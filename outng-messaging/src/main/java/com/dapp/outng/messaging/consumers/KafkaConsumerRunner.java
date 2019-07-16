package com.dapp.outng.messaging.consumers;

public interface KafkaConsumerRunner extends Runnable {
	public void processMessage(String key, String value) throws Exception;
}
