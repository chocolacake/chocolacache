package com.chocolacake.chocolacache.common.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolUtil {


    public static ExecutorService createFixThreadPool(int coreThread, String factoryName) {
        return Executors.newFixedThreadPool(coreThread, new DefaultThreadFactory(factoryName));
    }

    public static ExecutorService createThreadPool(int coreThread, int maxThread,
                                                   int queueSize, int keepAlive, TimeUnit timeUnit, String factoryName) {
        return new ThreadPoolExecutor(coreThread, maxThread, keepAlive, timeUnit,
                new ArrayBlockingQueue<>(queueSize), new DefaultThreadFactory(factoryName));
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
