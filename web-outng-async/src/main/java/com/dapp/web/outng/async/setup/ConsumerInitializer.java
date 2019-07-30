package com.dapp.web.outng.async.setup;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.dapp.outng.common.factories.CoreThreadPoolFactory;
import com.dapp.outng.messaging.consumers.KafkaConsumerRunner;


public class ConsumerInitializer implements ApplicationContextAware, InitializingBean {
	private static final Logger LOG = LoggerFactory.getLogger(ConsumerInitializer.class);
	
	private ApplicationContext applicationContext;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		try {
			String servers = "localhost:9092";

			String topic = "outng-async-topic";
			int threadPoolSize = 4;

			ExecutorService ces = CoreThreadPoolFactory.createThreadPool("async-event",
					2, threadPoolSize, 60, TimeUnit.SECONDS);
			for (int i = 0; i < threadPoolSize; i++) {
				String groupid = "async-event-group";
				KafkaConsumerRunner consumer = (KafkaConsumerRunner) applicationContext.getBean("asyncEventConsumer", topic, groupid);
				ces.submit(consumer);
			}
			LOG.info("Initialized {} consumer(s) for nodes:{} @ topic:{}", threadPoolSize, servers, topic);

		} catch (Exception e) {
			LOG.error("Exception: {}", e);

		}

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
