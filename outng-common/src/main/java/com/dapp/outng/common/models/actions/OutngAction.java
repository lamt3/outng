package com.dapp.outng.common.models.actions;

import com.dapp.outng.common.models.user.OutngUser;
import com.dapp.outng.common.models.user.SeenUsers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OutngAction {
	private String actionType;
	private OutngUser outngUser;
	private SeenUsers seenUsers;
}
