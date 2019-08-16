package com.dapp.web.outng.chat.interceptors;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AppAuth implements Authentication {

	private String userName; 
	
	public AppAuth(String user) {
		this.userName = user;
	}
	
	@Override
	public String getName() {
		return this.userName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton((GrantedAuthority) () -> "USER");
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.userName;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		
		
	}

}
