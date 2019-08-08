package com.dapp.outng.messaging.producers;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dapp.outng.messaging.configs.OutngKafkaConfigs;
import com.dapp.outng.messaging.utils.KafkaUtil;

@Component
public class MessageProducerImpl implements MessageProducer {

	private static final Logger LOG = LoggerFactory.getLogger(MessageProducerImpl.class);

	@Autowired
	private OutngKafkaConfigs kafkaConfig;

	private Producer<String, String> kafkaProducer;
	
	@PostConstruct
	public void init() {
		kafkaProducer = new KafkaProducer<>(KafkaUtil.setUpProducerProperties(kafkaConfig));
	}

	@Override
	public void sendMessage(String topic, String key, String value) {
		kafkaProducer.send(new ProducerRecord<String, String>(topic, key, value), new MessageSendCallback(topic, key));
		LOG.debug("[PRODUCER] offer message -- topic:{} key:{} message:{}", topic, key, value);
	}
	
	@PreDestroy
	public void close() {
		kafkaProducer.close();
	}

	class MessageSendCallback implements Callback {

		private final long start = System.currentTimeMillis();
		private final String topic;
		private final String key;
		private final String event;

		MessageSendCallback(String topic, String key) {
			this.topic = topic;
			this.key = key;
//			this.event = String.format(PRODUCER_PUT_INTO_QUEUE_TMP, StringUtils.replace(StringUtils.upperCase(topic), "-", "_"));
			this.event = "event";
		}

		@Override
		public void onCompletion(RecordMetadata metadata, Exception exception) {

			long stop = System.currentTimeMillis();
			long duration = (stop - start);
		

			if (exception == null) {
				LOG.info("[PRODUCER] accepted message -- topic:{} key:{} time:{} ms", topic, key, duration);
//				if (LOG.isDebugEnabled()) {
//					
//				}
			} else {
				if (metadata == null) {
					LOG.error("[PRODUCER] error while sending message to kafka", exception);
				} else {
					LOG.error("[PRODUCER] error while sending message to kafka -- {}", metadata, exception);
				}
			}
		}
	}

}
