package com.dapp.outng.messaging.configs;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(name = "props", value = "classpath:outng-kafka.properties", ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "kafka")
public class OutngKafkaConfigs {

	@Value("${kafka.servers}")
	private String servers;

	public String getServersToConnect() {
		return this.servers;
	}

	/* Producer Properties */
	private Producer producer;

	public Producer getProducer() {
		return this.producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public static class Producer {

		public String acks;
		public String retries;
		public int batchSize;
		public int lingerTime;
		public int bufferMemory;
		public int maxMessageSizeMB;
		public int requestTimeoutMS;
		public int metaDataFetchTimeoutMS;
		public int ackTimeoutMS;

		public String getAcks() {
			return acks;
		}

		public void setAcks(String acks) {
			this.acks = acks;
		}

		public String getRetries() {
			return retries;
		}

		public void setRetries(String retries) {
			this.retries = retries;
		}

		public int getBatchSize() {
			return batchSize;
		}

		public void setBatchSize(int batchSize) {
			this.batchSize = batchSize;
		}

		public int getLingerTime() {
			return lingerTime;
		}

		public void setLingerTime(int lingerTime) {
			this.lingerTime = lingerTime;
		}

		public int getBufferMemory() {
			return bufferMemory;
		}

		public void setBufferMemory(int bufferMemory) {
			this.bufferMemory = bufferMemory;
		}

		public int getMaxMessageSizeMB() {
			return maxMessageSizeMB;
		}

		public void setMaxMessageSizeMB(int maxMessageSizeMB) {
			this.maxMessageSizeMB = maxMessageSizeMB;
		}

		public int getRequestTimeoutMS() {
			return requestTimeoutMS;
		}

		public void setRequestTimeoutMS(int requestTimeoutMS) {
			this.requestTimeoutMS = requestTimeoutMS;
		}

		public int getMetaDataFetchTimeoutMS() {
			return metaDataFetchTimeoutMS;
		}

		public void setMetaDataFetchTimeoutMS(int metaDataFetchTimeoutMS) {
			this.metaDataFetchTimeoutMS = metaDataFetchTimeoutMS;
		}

		public int getAckTimeoutMS() {
			return ackTimeoutMS;
		}

		public void setAckTimeoutMS(int ackTimeoutMS) {
			this.ackTimeoutMS = ackTimeoutMS;
		}

	}

	public Properties getOutngProducerConfig() {
		// BasicConfigurator.configure();
		String servers = this.servers;
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");
		return props;
	}

	/* Consumer Properties */
	private Consumer consumer;

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public static class Consumer {
		private boolean enableAutoCommit;
		private int autoCommitInterval;
		private String sessionTimeOut;
		private int maxMessageSizeMB;

		public boolean isEnableAutoCommit() {
			return enableAutoCommit;
		}

		public void setEnableAutoCommit(boolean enableAutoCommit) {
			this.enableAutoCommit = enableAutoCommit;
		}

		public int getAutoCommitInterval() {
			return autoCommitInterval;
		}

		public void setAutoCommitInterval(int autoCommitInterval) {
			this.autoCommitInterval = autoCommitInterval;
		}

		public String getSessionTimeOut() {
			return sessionTimeOut;
		}

		public void setSessionTimeOut(String sessionTimeOut) {
			this.sessionTimeOut = sessionTimeOut;
		}

		public int getMaxMessageSizeMB() {
			return maxMessageSizeMB;
		}

		public void setMaxMessageSizeMB(int maxMessageSizeMB) {
			this.maxMessageSizeMB = maxMessageSizeMB;
		}

	}

}
