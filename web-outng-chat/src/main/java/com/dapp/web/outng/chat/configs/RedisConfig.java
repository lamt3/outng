package com.dapp.web.outng.chat.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.dapp.web.outng.chat.receiver.RedisReceiver;

import redis.clients.jedis.JedisCluster;

@Configuration
@ConditionalOnClass({ JedisCluster.class })
public class RedisConfig {

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

//    @Bean
//    public JedisPoolConfig jedisPoolConfig(){
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(maxActive);
//        config.setMaxIdle(maxIdle);
//        config.setMinIdle(minIdle);
//        config.setMaxWaitMillis(maxWait);
//
//        return config;
//    }
//
//    @Bean
//    public RedisClusterConfiguration redisClusterConfiguration(){
//        RedisClusterConfiguration configuration = new RedisClusterConfiguration(Arrays.asList(nodes));
//        configuration.setMaxRedirects(maxRedirects);
//
//        return configuration;
//    }
//
//    /**
//     * JedisConnectionFactory
//     */
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(RedisClusterConfiguration configuration,JedisPoolConfig jedisPoolConfig){
////        return new JedisConnectionFactory(configuration,jedisPoolConfig);
//    	 JedisConnectionFactory jedisConFactory
//         = new JedisConnectionFactory();
//       jedisConFactory.setHostName("localhost");
//       jedisConFactory.setPort(6379);
//       return jedisConFactory;
//    }
//
//    /**
//     * 使用Jackson序列化对象
//     */
//    @Bean
//    public Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer(){
//        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        serializer.setObjectMapper(objectMapper);
//
//        return serializer;
//    }
//
//    /**
//     * RedisTemplate
//     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory factory, Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer){
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory);
//
//        //字符串方式序列化KEY
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(stringRedisSerializer);
//        redisTemplate.setHashKeySerializer(stringRedisSerializer);
//
//        //JSON方式序列化VALUE
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//
//        redisTemplate.afterPropertiesSet();
//
//        return redisTemplate;
//    }
//
//    /**
//     * 消息监听器
//     */
//    @Bean
//    MessageListenerAdapter messageListenerAdapter(MessageReceiver messageReceiver, Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer){
//        //消息接收者以及对应的默认处理方法
//        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(messageReceiver, "receiveMessage");
//        //消息的反序列化方式
//
//        return messageListenerAdapter;
//    }
//
//    /**
//     * message listener container
//     */
//    @Bean
//    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory
//            , MessageListenerAdapter messageListenerAdapter){
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        //添加消息监听器
//        container.addMessageListener((MessageListener) messageListenerAdapter, new PatternTopic(topicName));
//
//        return container;
//    }

	@Bean
	RedisReceiver receiver() {
		return new RedisReceiver();
	}

	@Bean("chatMessageListenerAdapter")
	MessageListenerAdapter chatMessageListenerAdapter(RedisReceiver redisReceiver) {
		return new MessageListenerAdapter(redisReceiver, "receiveMessage");
	}

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			@Qualifier("chatMessageListenerAdapter") MessageListenerAdapter chatMessageListenerAdapter) {
		System.out.println(connectionFactory.getConnection().getClientName());
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(chatMessageListenerAdapter, new PatternTopic("chat"));
		container.afterPropertiesSet();
		return container;
	}

	@Bean
	protected JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("localhost", 6379);
		JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build();
		JedisConnectionFactory factory = new JedisConnectionFactory(configuration, jedisClientConfiguration);
		factory.afterPropertiesSet();
		return factory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

}