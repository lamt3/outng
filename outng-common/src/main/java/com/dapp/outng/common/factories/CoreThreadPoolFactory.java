package com.dapp.outng.common.factories;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoreThreadPoolFactory {
	private static final Logger LOG = LoggerFactory.getLogger(CoreThreadPoolFactory.class);

	public static ExecutorService createThreadPool(String poolName, int corePoolSize, int maximumPoolSize,
			int keepAliveTime, TimeUnit timeUnit) {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
				timeUnit, new LinkedBlockingQueue<Runnable>(), new ThreadFactoryWithNamePrefix(poolName));
		threadPoolExecutor.prestartAllCoreThreads();
		// Add shutdown hook to stop executorService on shutdown
		addShutdownHook(threadPoolExecutor, keepAliveTime, timeUnit);
		return threadPoolExecutor;
	}

	///////////////////////////////////////////////////////////////////////////
	// Privates
	///////////////////////////////////////////////////////////////////////////

	private static void addShutdownHook(final ExecutorService executor, long timeout, TimeUnit timeUnit) {

		if (executor == null) {
			LOG.info("executorService is null");
			return;
		}

		LOG.info("Adding shutdown hook to stop threadPools on shutdown");

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					LOG.trace("Shutting down the executor {}", executor.getClass().getSimpleName());
					executor.shutdown();
					if (!executor.awaitTermination(timeout, timeUnit)) {
						executor.shutdownNow();
						LOG.error("Executor did not terminate in the specified time: {} {} & was abruptly shut down",
								timeout, timeUnit);
					}
				} catch (Throwable e) {
					LOG.error("Failed to shutdown the executor {}", executor.getClass().getSimpleName());
				}
			}
		});
	}

	private static class ThreadFactoryWithNamePrefix implements ThreadFactory {

		private final AtomicInteger threadNumber;
		private String name;

		private ThreadFactoryWithNamePrefix(String name) {
			this.name = name;
			threadNumber = new AtomicInteger(1);
		}

		@Override
		public Thread newThread(Runnable r) {
			String threadName = name + threadNumber.getAndIncrement();
			Thread t = new Thread(null, r, threadName);
			LOG.trace("Creating Thread : {} ", threadName);
			return t;
		}

	}
}
