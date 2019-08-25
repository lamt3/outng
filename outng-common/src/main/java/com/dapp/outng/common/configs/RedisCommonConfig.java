package com.dapp.outng.common.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisCluster;

@Configuration
@ConditionalOnClass({ JedisCluster.class })
public class RedisCommonConfig {

	@Value("${spring.redis.timeout}")
	private String timeOut;

	@Value("${spring.redis.cluster.nodes}")
	private String nodes;

	@Value("${spring.redis.cluster.max-redirects}")
	private int maxRedirects;

	@Value("${spring.redis.jedis.pool.max-active}")
	private int maxActive;

	@Value("${spring.redis.jedis.pool.max-wait}")
	private int maxWait;

	@Value("${spring.redis.jedis.pool.max-idle}")
	private int maxIdle;

	@Value("${spring.redis.jedis.pool.min-idle}")
	private int minIdle;

	@Value("${spring.redis.message.topic-name}")
	private String topicName;

	

//	@Bean
//	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, @Qualifier("chatMessageListenerAdapter") MessageListenerAdapter chatMessageListenerAdapter) {
//		System.out.println(connectionFactory.getConnection().getClientName());
//		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
////		ExecutorService executorService = Executors.newFixedThreadPool(2);
////		container.setTaskExecutor(executorService);
//		container.setConnectionFactory(connectionFactory);
//		container.addMessageListener(chatMessageListenerAdapter, new PatternTopic("chat"));
//		container.afterPropertiesSet();
//		return container;
//	}

	@Bean("jedisConnectionFactory")
	protected JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("localhost", 6379);
		JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build();
		JedisConnectionFactory factory = new JedisConnectionFactory(configuration, jedisClientConfiguration);
		factory.afterPropertiesSet();
		return factory;
	}

	@Bean
	@DependsOn("jedisConnectionFactory")
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
		redisTemplate.setHashValueSerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

}