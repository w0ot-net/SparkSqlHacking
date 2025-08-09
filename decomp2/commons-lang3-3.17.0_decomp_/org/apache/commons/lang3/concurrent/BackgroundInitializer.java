package org.apache.commons.lang3.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableSupplier;

public class BackgroundInitializer extends AbstractConcurrentInitializer {
   private ExecutorService externalExecutor;
   private ExecutorService executor;
   private Future future;

   public static Builder builder() {
      return new Builder();
   }

   protected BackgroundInitializer() {
      this((ExecutorService)null);
   }

   protected BackgroundInitializer(ExecutorService exec) {
      this.setExternalExecutor(exec);
   }

   private BackgroundInitializer(FailableSupplier initializer, FailableConsumer closer, ExecutorService exec) {
      super(initializer, closer);
      this.setExternalExecutor(exec);
   }

   private ExecutorService createExecutor() {
      return Executors.newFixedThreadPool(this.getTaskCount());
   }

   private Callable createTask(ExecutorService execDestroy) {
      return new InitializationTask(execDestroy);
   }

   public Object get() throws ConcurrentException {
      try {
         return this.getFuture().get();
      } catch (ExecutionException execex) {
         ConcurrentUtils.handleCause(execex);
         return null;
      } catch (InterruptedException iex) {
         Thread.currentThread().interrupt();
         throw new ConcurrentException(iex);
      }
   }

   protected final synchronized ExecutorService getActiveExecutor() {
      return this.executor;
   }

   public final synchronized ExecutorService getExternalExecutor() {
      return this.externalExecutor;
   }

   public synchronized Future getFuture() {
      if (this.future == null) {
         throw new IllegalStateException("start() must be called first!");
      } else {
         return this.future;
      }
   }

   protected int getTaskCount() {
      return 1;
   }

   protected Exception getTypedException(Exception e) {
      return new Exception(e);
   }

   public synchronized boolean isInitialized() {
      if (this.future != null && this.future.isDone()) {
         try {
            this.future.get();
            return true;
         } catch (ExecutionException | InterruptedException | CancellationException var2) {
            return false;
         }
      } else {
         return false;
      }
   }

   public synchronized boolean isStarted() {
      return this.future != null;
   }

   public final synchronized void setExternalExecutor(ExecutorService externalExecutor) {
      if (this.isStarted()) {
         throw new IllegalStateException("Cannot set ExecutorService after start()!");
      } else {
         this.externalExecutor = externalExecutor;
      }
   }

   public synchronized boolean start() {
      if (!this.isStarted()) {
         this.executor = this.getExternalExecutor();
         ExecutorService tempExec;
         if (this.executor == null) {
            this.executor = tempExec = this.createExecutor();
         } else {
            tempExec = null;
         }

         this.future = this.executor.submit(this.createTask(tempExec));
         return true;
      } else {
         return false;
      }
   }

   public static class Builder extends AbstractConcurrentInitializer.AbstractBuilder {
      private ExecutorService externalExecutor;

      public BackgroundInitializer get() {
         return new BackgroundInitializer(this.getInitializer(), this.getCloser(), this.externalExecutor);
      }

      public Builder setExternalExecutor(ExecutorService externalExecutor) {
         this.externalExecutor = externalExecutor;
         return (Builder)this.asThis();
      }
   }

   private final class InitializationTask implements Callable {
      private final ExecutorService execFinally;

      InitializationTask(ExecutorService exec) {
         this.execFinally = exec;
      }

      public Object call() throws Exception {
         Object var1;
         try {
            var1 = BackgroundInitializer.this.initialize();
         } finally {
            if (this.execFinally != null) {
               this.execFinally.shutdown();
            }

         }

         return var1;
      }
   }
}
