package com.dapp.outng.common.models.actions;

import com.dapp.outng.common.models.user.OutngUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OutngAction {
	private String actionType;
	private OutngUser outngUser;
	
}
