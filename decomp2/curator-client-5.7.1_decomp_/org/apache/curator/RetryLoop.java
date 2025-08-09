package org.apache.curator;

import java.util.concurrent.Callable;
import org.apache.curator.connection.ThreadLocalRetryLoop;
import org.apache.curator.utils.ThreadUtils;

public abstract class RetryLoop {
   public static RetrySleeper getDefaultRetrySleeper() {
      return RetryLoopImpl.getRetrySleeper();
   }

   public static Object callWithRetry(CuratorZookeeperClient client, Callable proc) throws Exception {
      client.internalBlockUntilConnectedOrTimedOut();
      T result = (T)null;
      ThreadLocalRetryLoop threadLocalRetryLoop = new ThreadLocalRetryLoop();
      client.getClass();
      RetryLoop retryLoop = threadLocalRetryLoop.getRetryLoop(client::newRetryLoop);

      try {
         while(retryLoop.shouldContinue()) {
            try {
               result = (T)proc.call();
               retryLoop.markComplete();
            } catch (Exception e) {
               ThreadUtils.checkInterrupted(e);
               retryLoop.takeException(e);
            }
         }
      } finally {
         threadLocalRetryLoop.release();
      }

      return result;
   }

   public abstract boolean shouldContinue();

   public abstract void markComplete();

   public abstract void takeException(Exception var1) throws Exception;
}
