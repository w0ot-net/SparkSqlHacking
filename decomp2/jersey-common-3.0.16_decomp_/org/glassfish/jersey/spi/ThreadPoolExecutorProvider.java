package org.glassfish.jersey.spi;

import jakarta.annotation.PreDestroy;
import jakarta.ws.rs.core.Configuration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorProvider extends AbstractThreadPoolProvider implements ExecutorServiceProvider {
   private static final long CACHED_POOL_KEEP_ALIVE_DEFAULT_TIMEOUT = 60L;

   public ThreadPoolExecutorProvider(String name) {
      super(name);
   }

   public ThreadPoolExecutorProvider(String name, Configuration configuration) {
      super(name, configuration);
   }

   public ExecutorService getExecutorService() {
      return super.getExecutor();
   }

   protected final ThreadPoolExecutor createExecutor(int corePoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
      return this.createExecutor(corePoolSize, this.getMaximumPoolSize(), this.getKeepAliveTime(), this.getWorkQueue(), threadFactory, handler);
   }

   protected ThreadPoolExecutor createExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, BlockingQueue workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
      return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, threadFactory, handler);
   }

   protected int getMaximumPoolSize() {
      return Integer.MAX_VALUE;
   }

   protected long getKeepAliveTime() {
      return 60L;
   }

   protected BlockingQueue getWorkQueue() {
      return (BlockingQueue)(this.getMaximumPoolSize() == Integer.MAX_VALUE ? new SynchronousQueue() : new LinkedBlockingQueue());
   }

   public void dispose(ExecutorService executorService) {
   }

   @PreDestroy
   public void preDestroy() {
      this.close();
   }
}
