package com.dapp.web.outng.chat.services;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisService {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	
	public void set(String key, Object value) {
		ValueOperations<String, Object> valueOperation = redisTemplate.opsForValue();
		valueOperation.set(key, value);
	}

	public void setWithExpire(String key, Object value, long time, TimeUnit timeUnit) {
		BoundValueOperations<String, Object> boundValueOperations = redisTemplate.boundValueOps(key);
		boundValueOperations.set(value);
		boundValueOperations.expire(time, timeUnit);
	}

	
	public <K> K get(String key) {
		ValueOperations<String, Object> valueOperation = redisTemplate.opsForValue();

		return (K) valueOperation.get(key);
	}


	public boolean delete(String key) {
		return redisTemplate.delete(key);
	}

	
	public void addToListLeft(String listKey,Object... values) {
		// 绑定操作
		BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
		// 插入数据
		boundValueOperations.leftPushAll(values);
		// 设置过期时间
		boundValueOperations.expire(30L, TimeUnit.DAYS);
	}

	
	public void addToListRight(String listKey, Object... values) {
		// 绑定操作
		BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
		// 插入数据
		boundValueOperations.rightPushAll(values);
		// 设置过期时间
		boundValueOperations.expire(30L, TimeUnit.DAYS);
	}

	public List<Object> rangeList(String listKey, long start, long end) {
		// 绑定操作
		BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
		// 查询数据
		return boundValueOperations.range(start, end);
	}

	public void addToSet(String setKey, Object... values) {
		SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();
		opsForSet.add(setKey, values);
	}

	public Boolean isSetMember(String setKey, Object value) {
		SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();

		return opsForSet.isMember(setKey, value);
	}

	public void removeFromSet(String setKey, Object... values) {
		SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();
		opsForSet.remove(setKey, values);
	}

	public void convertAndSend(String channel, Object message) {
		redisTemplate.convertAndSend(channel, message);
	}
}
