package com.dapp.outng.profile.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dapp.outng.common.models.user.SeenUsers;

@Component
public class UserSeenService extends UserBaseService{
	
	public List<String> getSeenUsers(String userId) {
		SeenUsers seenUsers = mapper.load(SeenUsers.class, userId);
		return seenUsers == null ? null : seenUsers.getSeenUserIds();
	}
	
	public void addSeenUsers(String userId, List<String> seenUserIds) {
		
	}
	
	public void createSeenUsersItem(String userId) {
		SeenUsers seenUserItem = new SeenUsers();
		seenUserItem.setUserId(userId);
		mapper.save(seenUserItem);
	}
	
	
}
