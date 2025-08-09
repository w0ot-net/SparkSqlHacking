package org.apache.curator.connection;

import java.util.Objects;
import java.util.function.Supplier;
import org.apache.curator.RetryLoop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadLocalRetryLoop {
   private static final Logger log = LoggerFactory.getLogger(ThreadLocalRetryLoop.class);
   private static final ThreadLocal threadLocal = new ThreadLocal();

   public RetryLoop getRetryLoop(Supplier newRetryLoopSupplier) {
      Entry entry = (Entry)threadLocal.get();
      if (entry == null) {
         entry = new Entry(new WrappedRetryLoop((RetryLoop)newRetryLoopSupplier.get()));
         threadLocal.set(entry);
      }

      ++entry.counter;
      return entry.retryLoop;
   }

   public void release() {
      Entry entry = (Entry)Objects.requireNonNull(threadLocal.get(), "No retry loop was set - unbalanced call to release()");
      if (--entry.counter <= 0) {
         threadLocal.remove();
      }

   }

   private static class Entry {
      private final RetryLoop retryLoop;
      private int counter;

      Entry(RetryLoop retryLoop) {
         this.retryLoop = retryLoop;
      }
   }

   private static class WrappedRetryLoop extends RetryLoop {
      private final RetryLoop retryLoop;
      private Exception takenException;

      public WrappedRetryLoop(RetryLoop retryLoop) {
         this.retryLoop = retryLoop;
      }

      public boolean shouldContinue() {
         return this.retryLoop.shouldContinue() && this.takenException == null;
      }

      public void markComplete() {
         this.retryLoop.markComplete();
      }

      public void takeException(Exception exception) throws Exception {
         if (this.takenException != null) {
            if (exception.getClass() != this.takenException.getClass()) {
               ThreadLocalRetryLoop.log.error("Multiple exceptions in retry loop", exception);
            }

            throw this.takenException;
         } else {
            try {
               this.retryLoop.takeException(exception);
            } catch (Exception e) {
               this.takenException = e;
               throw e;
            }
         }
      }
   }
}
