package org.sparkproject.jetty.util.component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Graceful {
   CompletableFuture shutdown();

   boolean isShutdown();

   static CompletableFuture shutdown(Container component) {
      Logger log = LoggerFactory.getLogger(component.getClass());
      log.info("Shutdown {}", component);
      List<Graceful> gracefuls = new ArrayList();
      if (component instanceof Graceful) {
         gracefuls.add((Graceful)component);
      }

      gracefuls.addAll(component.getContainedBeans(Graceful.class));
      if (log.isDebugEnabled()) {
         gracefuls.forEach((g) -> log.debug("graceful {}", g));
      }

      return CompletableFuture.allOf((CompletableFuture[])gracefuls.stream().map(Graceful::shutdown).toArray((x$0) -> new CompletableFuture[x$0]));
   }

   static CompletableFuture shutdown(ThrowingRunnable runnable) {
      final AtomicReference<Thread> stopThreadReference = new AtomicReference();
      CompletableFuture<Void> shutdown = new CompletableFuture() {
         public boolean cancel(boolean mayInterruptIfRunning) {
            boolean canceled = super.cancel(mayInterruptIfRunning);
            if (canceled && mayInterruptIfRunning) {
               Thread thread = (Thread)stopThreadReference.get();
               if (thread != null) {
                  thread.interrupt();
               }
            }

            return canceled;
         }
      };
      Thread stopThread = new Thread(() -> {
         try {
            runnable.run();
            shutdown.complete((Object)null);
         } catch (Throwable t) {
            shutdown.completeExceptionally(t);
         }

      });
      stopThread.setDaemon(true);
      stopThreadReference.set(stopThread);
      stopThread.start();
      return shutdown;
   }

   public abstract static class Shutdown implements Graceful {
      final Object _component;
      final AtomicReference _done = new AtomicReference();

      protected Shutdown(Object component) {
         this._component = component;
      }

      public CompletableFuture shutdown() {
         if (this._done.get() == null) {
            this._done.compareAndSet((Object)null, new CompletableFuture() {
               public String toString() {
                  return String.format("Shutdown<%s>@%x", Shutdown.this._component, this.hashCode());
               }
            });
         }

         CompletableFuture<Void> done = (CompletableFuture)this._done.get();
         this.check();
         return done;
      }

      public boolean isShutdown() {
         return this._done.get() != null;
      }

      public void check() {
         CompletableFuture<Void> done = (CompletableFuture)this._done.get();
         if (done != null && this.isShutdownDone()) {
            done.complete((Object)null);
         }

      }

      public void cancel() {
         CompletableFuture<Void> done = (CompletableFuture)this._done.get();
         if (done != null && !done.isDone()) {
            done.cancel(true);
         }

         this._done.set((Object)null);
      }

      public abstract boolean isShutdownDone();
   }

   @FunctionalInterface
   public interface ThrowingRunnable {
      void run() throws Exception;
   }
}
