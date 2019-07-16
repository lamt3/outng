package com.dapp.outng.messaging.utils;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dapp.outng.messaging.configs.OutngKafkaConfigs;
import com.dapp.outng.messaging.configs.OutngKafkaConfigs.Consumer;

public class KafkaUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(KafkaUtil.class);
	
	@SuppressWarnings("deprecation")
	public static Properties setUpProducerProperties(OutngKafkaConfigs kafkaConfig) {
		String servers = kafkaConfig.getServersToConnect();
		OutngKafkaConfigs.Producer producerConfig = kafkaConfig.getProducer();
		Properties props = new Properties();
		LOG.info("Kafka producer initialized for nodes: " + servers);
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
		props.put(ProducerConfig.ACKS_CONFIG, producerConfig.getAcks());
		props.put(ProducerConfig.RETRIES_CONFIG, producerConfig.getRetries());
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, producerConfig.getBatchSize());
		props.put(ProducerConfig.LINGER_MS_CONFIG, producerConfig.getLingerTime());
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, producerConfig.getBufferMemory());
		props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, producerConfig.getRequestTimeoutMS());
		props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, producerConfig.getMetaDataFetchTimeoutMS());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		
		return props;
	}
	
	public static Properties setupConsumerProperties(OutngKafkaConfigs kafkaConfig, String consumerGroup) {
		String servers = kafkaConfig.getServersToConnect();
		Consumer consumerConfig = kafkaConfig.getConsumer();

		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, consumerConfig.isEnableAutoCommit());
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, consumerConfig.getAutoCommitInterval());
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, consumerConfig.getSessionTimeOut());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

		return props;
	}
	
	public static void shutDown(AtomicBoolean RUN, KafkaConsumer<String, String> consumer, String topicName) {
		try {
			RUN.set(false);
			consumer.wakeup();
			LOG.info("Successfully closed consumer and stopped working -- topic name:{}", topicName);
		} catch (Exception e) {
			LOG.error("Encountered error while attempting to close consumer/stop working -- topic name:{}", topicName, e);
		}
	}


}
