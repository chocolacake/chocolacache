package com.chocolacake.chocolacache.common.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolUtil {

    public static ExecutorService createExecutorService(int coreThread, int maxThread, String name) {
        return new ThreadPoolExecutor(coreThread, maxThread, 1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1000), new DefaultThreadFactory(name));
    }


    private static class DefaultThreadFactory implements ThreadFactory {
        private String factoryName;
        private AtomicInteger atomicInteger = new AtomicInteger(0);


        public DefaultThreadFactory(String name) {
            this.factoryName = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(factoryName + "-" + atomicInteger.getAndIncrement());
            thread.setDaemon(true);
            return thread;
        }
    }
}
