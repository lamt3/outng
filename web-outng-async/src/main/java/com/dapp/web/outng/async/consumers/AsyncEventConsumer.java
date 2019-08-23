package com.dapp.web.outng.async.consumers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dapp.outng.common.factories.ActionHandlerFactory;
import com.dapp.outng.common.handlers.ActionHandler;
import com.dapp.outng.common.models.actions.OutngAction;
import com.dapp.outng.messaging.consumers.AbstractQueueConsumer;
import com.google.gson.Gson;

@Component("asyncEventConsumer")
@Scope(value="prototype")
public class AsyncEventConsumer extends AbstractQueueConsumer {

	public AsyncEventConsumer(String topicName, String consumerGroup) {
		super(topicName, consumerGroup);
	}

	@Override
	public void processMessage(String key, String value) throws Exception {
		Gson gson = new Gson();
		OutngAction action = gson.fromJson(value, OutngAction.class);
		ActionHandler actionHandler = ActionHandlerFactory.getActionHandler(action.getActionType());
		actionHandler.processMessage(action);
	}

	
	
}
