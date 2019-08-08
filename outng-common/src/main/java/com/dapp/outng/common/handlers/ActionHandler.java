package com.dapp.outng.common.handlers;

import com.dapp.outng.common.models.actions.OutngAction;

public interface ActionHandler {

	public void processMessage(OutngAction action);
}
