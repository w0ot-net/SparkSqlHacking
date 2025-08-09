package org.apache.logging.log4j.core.util;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.AbstractLifeCycle;
import org.apache.logging.log4j.core.LifeCycle;
import org.apache.logging.log4j.core.LifeCycle2;
import org.apache.logging.log4j.status.StatusLogger;

public class DefaultShutdownCallbackRegistry implements ShutdownCallbackRegistry, LifeCycle2, Runnable {
   protected static final Logger LOGGER = StatusLogger.getLogger();
   private final AtomicReference state;
   private final ThreadFactory threadFactory;
   private final Collection hooks;
   private Reference shutdownHookRef;

   public DefaultShutdownCallbackRegistry() {
      this(Executors.defaultThreadFactory());
   }

   protected DefaultShutdownCallbackRegistry(final ThreadFactory threadFactory) {
      this.state = new AtomicReference(LifeCycle.State.INITIALIZED);
      this.hooks = new CopyOnWriteArrayList();
      this.threadFactory = threadFactory;
   }

   public void run() {
      if (this.state.compareAndSet(LifeCycle.State.STARTED, LifeCycle.State.STOPPING)) {
         for(Reference hookRef : this.hooks) {
            Cancellable hook = (Cancellable)hookRef.get();
            if (hook != null) {
               try {
                  hook.run();
               } catch (Throwable var7) {
                  Throwable t1 = var7;

                  try {
                     LOGGER.error(SHUTDOWN_HOOK_MARKER, "Caught exception executing shutdown hook {}", hook, t1);
                  } catch (Throwable t2) {
                     System.err.println("Caught exception " + t2.getClass() + " logging exception " + var7.getClass());
                     var7.printStackTrace();
                  }
               }
            }
         }

         this.state.set(LifeCycle.State.STOPPED);
      }

   }

   public Cancellable addShutdownCallback(final Runnable callback) {
      if (this.isStarted()) {
         Cancellable receipt = new RegisteredCancellable(callback, this.hooks);
         this.hooks.add(new SoftReference(receipt));
         return receipt;
      } else {
         throw new IllegalStateException("Cannot add new shutdown hook as this is not started. Current state: " + ((LifeCycle.State)this.state.get()).name());
      }
   }

   public void initialize() {
   }

   public void start() {
      if (this.state.compareAndSet(LifeCycle.State.INITIALIZED, LifeCycle.State.STARTING)) {
         try {
            this.addShutdownHook(this.threadFactory.newThread(this));
            this.state.set(LifeCycle.State.STARTED);
         } catch (IllegalStateException ex) {
            this.state.set(LifeCycle.State.STOPPED);
            throw ex;
         } catch (Exception e) {
            LOGGER.catching(e);
            this.state.set(LifeCycle.State.STOPPED);
         }
      }

   }

   private void addShutdownHook(final Thread thread) {
      this.shutdownHookRef = new WeakReference(thread);
      Runtime.getRuntime().addShutdownHook(thread);
   }

   public void stop() {
      this.stop(0L, AbstractLifeCycle.DEFAULT_STOP_TIMEUNIT);
   }

   public boolean stop(final long timeout, final TimeUnit timeUnit) {
      if (this.state.compareAndSet(LifeCycle.State.STARTED, LifeCycle.State.STOPPING)) {
         try {
            this.removeShutdownHook();
         } finally {
            this.state.set(LifeCycle.State.STOPPED);
         }
      }

      return true;
   }

   private void removeShutdownHook() {
      Thread shutdownThread = (Thread)this.shutdownHookRef.get();
      if (shutdownThread != null) {
         Runtime.getRuntime().removeShutdownHook(shutdownThread);
         this.shutdownHookRef.enqueue();
      }

   }

   public LifeCycle.State getState() {
      return (LifeCycle.State)this.state.get();
   }

   public boolean isStarted() {
      return this.state.get() == LifeCycle.State.STARTED;
   }

   public boolean isStopped() {
      return this.state.get() == LifeCycle.State.STOPPED;
   }

   private static class RegisteredCancellable implements Cancellable {
      private Runnable callback;
      private Collection registered;

      RegisteredCancellable(final Runnable callback, final Collection registered) {
         this.callback = callback;
         this.registered = registered;
      }

      public void cancel() {
         this.callback = null;
         Collection<Reference<Cancellable>> references = this.registered;
         if (references != null) {
            this.registered = null;
            references.removeIf((ref) -> {
               Cancellable value = (Cancellable)ref.get();
               return value == null || value == this;
            });
         }

      }

      public void run() {
         Runnable runnableHook = this.callback;
         if (runnableHook != null) {
            runnableHook.run();
            this.callback = null;
         }

      }

      public String toString() {
         return String.valueOf(this.callback);
      }
   }
}
