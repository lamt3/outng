package com.dapp.outng.messaging.consumers;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dapp.outng.messaging.configs.OutngKafkaConfigs;
import com.dapp.outng.messaging.utils.KafkaUtil;

public abstract class AbstractQueueConsumer implements KafkaConsumerRunner{

	private static final Logger LOG = LoggerFactory.getLogger(AbstractQueueConsumer.class);
	private static final int DEFAULT = 100;

	private String topicName;
	private String consumerGroup;

	private KafkaConsumer<String, String> consumer;
	
	@Autowired
	private OutngKafkaConfigs outngKafkaConfigs;
	
	public AbstractQueueConsumer(String topicName, String consumerGroup) {
		this.topicName = topicName;
		this.consumerGroup = consumerGroup;
	}
	
	@PostConstruct
	public void init() {
		consumer = new KafkaConsumer<String, String>(KafkaUtil.setupConsumerProperties(outngKafkaConfigs, consumerGroup));
	}
	
	@Override
	public void run() {
		try {

			consumer.subscribe(Arrays.asList(topicName));
			while (RUN.get()) {

				try {
					ConsumerRecords<String, String> records = consumer.poll(getTimeout());
					if (records != null && !records.isEmpty()) {
						for (ConsumerRecord<String, String> record : records) {
							long offset = record.offset();
							String key = record.key();
							String value = record.value();
							LOG.debug("[consumer] message-received.{} topic={} key={} value={}", offset, topicName, key,
									value);
							processMessage(key, value);
						}
					}
				} catch (Exception e) {
					LOG.error("Error consuming message due to {}", e.toString(), e);
				}
			}
		} catch (Exception e) {
			if (RUN.get()) {
				LOG.info("Unexpectedly exiting run loop with wakeup exception- topic name:{}", topicName, e);
			} else {
				LOG.info("Exiting run loop - topic name:{}", topicName);
			}
		} finally {
			LOG.info("Closing the consumer for topic name:{}", topicName);
			consumer.close();
		}
		
	}

	
	private Integer getTimeout() {
		Integer pollingInterval = DEFAULT;
//		Integer consumerConfigPollingInterval = kafkaConfig.getConsumer().getPollingIntervals().get(topicName);
//		if (consumerConfigPollingInterval != null) {
//			pollingInterval = consumerConfigPollingInterval;
//		}
		return pollingInterval;
	}

	public String getTopicName() {
		return this.topicName;
	}

	private static final AtomicBoolean RUN = new AtomicBoolean(true);

	@PreDestroy
	public void shutdown() {
		KafkaUtil.shutDown(RUN, consumer, topicName);
	}


}
