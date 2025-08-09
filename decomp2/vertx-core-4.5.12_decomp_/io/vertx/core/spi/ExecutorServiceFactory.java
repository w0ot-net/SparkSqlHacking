package io.vertx.core.spi;

import io.vertx.core.impl.VertxBuilder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public interface ExecutorServiceFactory extends VertxServiceProvider {
   ExecutorServiceFactory INSTANCE = (threadFactory, concurrency, maxConcurrency) -> Executors.newFixedThreadPool(maxConcurrency, threadFactory);

   default void init(VertxBuilder builder) {
      if (builder.executorServiceFactory() == null) {
         builder.executorServiceFactory(this);
      }

   }

   ExecutorService createExecutor(ThreadFactory var1, Integer var2, Integer var3);
}
