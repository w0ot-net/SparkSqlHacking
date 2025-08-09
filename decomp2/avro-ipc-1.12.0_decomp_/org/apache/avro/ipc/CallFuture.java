package org.apache.avro.ipc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallFuture implements Future, Callback {
   private final CountDownLatch latch;
   private final Callback chainedCallback;
   private Object result;
   private Throwable error;

   public CallFuture() {
      this((Callback)null);
   }

   public CallFuture(Callback chainedCallback) {
      this.latch = new CountDownLatch(1);
      this.result = null;
      this.error = null;
      this.chainedCallback = chainedCallback;
   }

   public void handleResult(Object result) {
      this.result = result;
      this.latch.countDown();
      if (this.chainedCallback != null) {
         this.chainedCallback.handleResult(result);
      }

   }

   public void handleError(Throwable error) {
      this.error = error;
      this.latch.countDown();
      if (this.chainedCallback != null) {
         this.chainedCallback.handleError(error);
      }

   }

   public Object getResult() {
      return this.result;
   }

   public Throwable getError() {
      return this.error;
   }

   public boolean cancel(boolean mayInterruptIfRunning) {
      return false;
   }

   public boolean isCancelled() {
      return false;
   }

   public Object get() throws InterruptedException, ExecutionException {
      this.latch.await();
      if (this.error != null) {
         throw new ExecutionException(this.error);
      } else {
         return this.result;
      }
   }

   public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
      if (this.latch.await(timeout, unit)) {
         if (this.error != null) {
            throw new ExecutionException(this.error);
         } else {
            return this.result;
         }
      } else {
         throw new TimeoutException();
      }
   }

   public void await() throws InterruptedException {
      this.latch.await();
   }

   public void await(long timeout, TimeUnit unit) throws InterruptedException, TimeoutException {
      if (!this.latch.await(timeout, unit)) {
         throw new TimeoutException();
      }
   }

   public boolean isDone() {
      return this.latch.getCount() <= 0L;
   }
}
