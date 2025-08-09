package io.netty.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

class PromiseTask extends DefaultPromise implements RunnableFuture {
   private static final Runnable COMPLETED = new SentinelRunnable("COMPLETED");
   private static final Runnable CANCELLED = new SentinelRunnable("CANCELLED");
   private static final Runnable FAILED = new SentinelRunnable("FAILED");
   private Object task;

   PromiseTask(EventExecutor executor, Runnable runnable, Object result) {
      super(executor);
      this.task = result == null ? runnable : new RunnableAdapter(runnable, result);
   }

   PromiseTask(EventExecutor executor, Runnable runnable) {
      super(executor);
      this.task = runnable;
   }

   PromiseTask(EventExecutor executor, Callable callable) {
      super(executor);
      this.task = callable;
   }

   public final int hashCode() {
      return System.identityHashCode(this);
   }

   public final boolean equals(Object obj) {
      return this == obj;
   }

   Object runTask() throws Throwable {
      Object task = this.task;
      if (task instanceof Callable) {
         return ((Callable)task).call();
      } else {
         ((Runnable)task).run();
         return null;
      }
   }

   public void run() {
      try {
         if (this.setUncancellableInternal()) {
            V result = (V)this.runTask();
            this.setSuccessInternal(result);
         }
      } catch (Throwable e) {
         this.setFailureInternal(e);
      }

   }

   private boolean clearTaskAfterCompletion(boolean done, Runnable result) {
      if (done) {
         this.task = result;
      }

      return done;
   }

   public final Promise setFailure(Throwable cause) {
      throw new IllegalStateException();
   }

   protected final Promise setFailureInternal(Throwable cause) {
      super.setFailure(cause);
      this.clearTaskAfterCompletion(true, FAILED);
      return this;
   }

   public final boolean tryFailure(Throwable cause) {
      return false;
   }

   protected final boolean tryFailureInternal(Throwable cause) {
      return this.clearTaskAfterCompletion(super.tryFailure(cause), FAILED);
   }

   public final Promise setSuccess(Object result) {
      throw new IllegalStateException();
   }

   protected final Promise setSuccessInternal(Object result) {
      super.setSuccess(result);
      this.clearTaskAfterCompletion(true, COMPLETED);
      return this;
   }

   public final boolean trySuccess(Object result) {
      return false;
   }

   protected final boolean trySuccessInternal(Object result) {
      return this.clearTaskAfterCompletion(super.trySuccess(result), COMPLETED);
   }

   public final boolean setUncancellable() {
      throw new IllegalStateException();
   }

   protected final boolean setUncancellableInternal() {
      return super.setUncancellable();
   }

   public boolean cancel(boolean mayInterruptIfRunning) {
      return this.clearTaskAfterCompletion(super.cancel(mayInterruptIfRunning), CANCELLED);
   }

   protected StringBuilder toStringBuilder() {
      StringBuilder buf = super.toStringBuilder();
      buf.setCharAt(buf.length() - 1, ',');
      return buf.append(" task: ").append(this.task).append(')');
   }

   private static final class RunnableAdapter implements Callable {
      final Runnable task;
      final Object result;

      RunnableAdapter(Runnable task, Object result) {
         this.task = task;
         this.result = result;
      }

      public Object call() {
         this.task.run();
         return this.result;
      }

      public String toString() {
         return "Callable(task: " + this.task + ", result: " + this.result + ')';
      }
   }

   private static class SentinelRunnable implements Runnable {
      private final String name;

      SentinelRunnable(String name) {
         this.name = name;
      }

      public void run() {
      }

      public String toString() {
         return this.name;
      }
   }
}
