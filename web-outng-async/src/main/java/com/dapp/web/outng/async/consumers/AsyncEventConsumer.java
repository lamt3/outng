package com.dapp.web.outng.async.consumers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dapp.outng.messaging.consumers.AbstractQueueConsumer;

@Component("asyncEventConsumer")
@Scope(value="prototype")
public class AsyncEventConsumer extends AbstractQueueConsumer {

	public AsyncEventConsumer(String topicName, String consumerGroup) {
		super(topicName, consumerGroup);
	}

	@Override
	public void processMessage(String key, String value) throws Exception {
		
	}

	
	
}
