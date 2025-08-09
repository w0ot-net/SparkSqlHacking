package org.glassfish.jersey.spi;

import jakarta.annotation.PreDestroy;
import jakarta.ws.rs.core.Configuration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

public class ScheduledThreadPoolExecutorProvider extends AbstractThreadPoolProvider implements ScheduledExecutorServiceProvider {
   public ScheduledThreadPoolExecutorProvider(String name) {
      super(name);
   }

   public ScheduledThreadPoolExecutorProvider(String name, Configuration configuration) {
      super(name, configuration);
   }

   public ScheduledExecutorService getExecutorService() {
      return (ScheduledExecutorService)super.getExecutor();
   }

   protected ScheduledThreadPoolExecutor createExecutor(int corePoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
      return new ScheduledThreadPoolExecutor(corePoolSize, threadFactory, handler);
   }

   public void dispose(ExecutorService executorService) {
   }

   @PreDestroy
   public void preDestroy() {
      this.close();
   }
}
