package org.apache.thrift.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.thrift.transport.TNonblockingServerTransport;

public class THsHaServer extends TNonblockingServer {
   private final ExecutorService invoker;
   private final Args args;

   public THsHaServer(Args args) {
      super(args);
      this.invoker = args.executorService == null ? createInvokerPool(args) : args.executorService;
      this.args = args;
   }

   protected void waitForShutdown() {
      this.joinSelector();
      this.gracefullyShutdownInvokerPool();
   }

   protected static ExecutorService createInvokerPool(Args options) {
      int minWorkerThreads = options.minWorkerThreads;
      int maxWorkerThreads = options.maxWorkerThreads;
      int stopTimeoutVal = options.stopTimeoutVal;
      TimeUnit stopTimeoutUnit = options.stopTimeoutUnit;
      LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue();
      ExecutorService invoker = new ThreadPoolExecutor(minWorkerThreads, maxWorkerThreads, (long)stopTimeoutVal, stopTimeoutUnit, queue);
      return invoker;
   }

   protected ExecutorService getInvoker() {
      return this.invoker;
   }

   protected void gracefullyShutdownInvokerPool() {
      this.invoker.shutdown();
      long timeoutMS = this.args.stopTimeoutUnit.toMillis((long)this.args.stopTimeoutVal);

      long newnow;
      for(long now = System.currentTimeMillis(); timeoutMS >= 0L; now = newnow) {
         try {
            this.invoker.awaitTermination(timeoutMS, TimeUnit.MILLISECONDS);
            break;
         } catch (InterruptedException var8) {
            newnow = System.currentTimeMillis();
            timeoutMS -= newnow - now;
         }
      }

   }

   protected boolean requestInvoke(AbstractNonblockingServer.FrameBuffer frameBuffer) {
      try {
         Runnable invocation = this.getRunnable(frameBuffer);
         this.invoker.execute(invocation);
         return true;
      } catch (RejectedExecutionException rx) {
         this.LOGGER.warn("ExecutorService rejected execution!", rx);
         return false;
      }
   }

   protected Runnable getRunnable(AbstractNonblockingServer.FrameBuffer frameBuffer) {
      return new Invocation(frameBuffer);
   }

   public static class Args extends AbstractNonblockingServer.AbstractNonblockingServerArgs {
      public int minWorkerThreads = 5;
      public int maxWorkerThreads = Integer.MAX_VALUE;
      private int stopTimeoutVal = 60;
      private TimeUnit stopTimeoutUnit;
      private ExecutorService executorService;

      public Args(TNonblockingServerTransport transport) {
         super(transport);
         this.stopTimeoutUnit = TimeUnit.SECONDS;
         this.executorService = null;
      }

      /** @deprecated */
      @Deprecated
      public Args workerThreads(int n) {
         this.minWorkerThreads = n;
         this.maxWorkerThreads = n;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public int getWorkerThreads() {
         return this.minWorkerThreads;
      }

      public Args minWorkerThreads(int n) {
         this.minWorkerThreads = n;
         return this;
      }

      public Args maxWorkerThreads(int n) {
         this.maxWorkerThreads = n;
         return this;
      }

      public int getMinWorkerThreads() {
         return this.minWorkerThreads;
      }

      public int getMaxWorkerThreads() {
         return this.maxWorkerThreads;
      }

      public int getStopTimeoutVal() {
         return this.stopTimeoutVal;
      }

      public Args stopTimeoutVal(int stopTimeoutVal) {
         this.stopTimeoutVal = stopTimeoutVal;
         return this;
      }

      public TimeUnit getStopTimeoutUnit() {
         return this.stopTimeoutUnit;
      }

      public Args stopTimeoutUnit(TimeUnit stopTimeoutUnit) {
         this.stopTimeoutUnit = stopTimeoutUnit;
         return this;
      }

      public ExecutorService getExecutorService() {
         return this.executorService;
      }

      public Args executorService(ExecutorService executorService) {
         this.executorService = executorService;
         return this;
      }
   }
}
