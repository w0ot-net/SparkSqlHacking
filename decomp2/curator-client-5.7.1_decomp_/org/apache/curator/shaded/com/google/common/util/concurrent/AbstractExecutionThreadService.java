package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
public abstract class AbstractExecutionThreadService implements Service {
   private static final Logger logger = Logger.getLogger(AbstractExecutionThreadService.class.getName());
   private final Service delegate = new AbstractService() {
      protected final void doStart() {
         Executor executor = MoreExecutors.renamingDecorator((Executor)AbstractExecutionThreadService.this.executor(), () -> AbstractExecutionThreadService.this.serviceName());
         executor.execute(() -> {
            try {
               AbstractExecutionThreadService.this.startUp();
               this.notifyStarted();
               if (this.isRunning()) {
                  try {
                     AbstractExecutionThreadService.this.run();
                  } catch (Throwable t) {
                     Platform.restoreInterruptIfIsInterruptedException(t);

                     try {
                        AbstractExecutionThreadService.this.shutDown();
                     } catch (Exception ignored) {
                        Platform.restoreInterruptIfIsInterruptedException(ignored);
                        AbstractExecutionThreadService.logger.log(Level.WARNING, "Error while attempting to shut down the service after failure.", ignored);
                     }

                     this.notifyFailed(t);
                     return;
                  }
               }

               AbstractExecutionThreadService.this.shutDown();
               this.notifyStopped();
            } catch (Throwable t) {
               Platform.restoreInterruptIfIsInterruptedException(t);
               this.notifyFailed(t);
            }

         });
      }

      protected void doStop() {
         AbstractExecutionThreadService.this.triggerShutdown();
      }

      public String toString() {
         return AbstractExecutionThreadService.this.toString();
      }
   };

   protected AbstractExecutionThreadService() {
   }

   protected void startUp() throws Exception {
   }

   protected abstract void run() throws Exception;

   protected void shutDown() throws Exception {
   }

   protected void triggerShutdown() {
   }

   protected Executor executor() {
      return (command) -> MoreExecutors.newThread(this.serviceName(), command).start();
   }

   public String toString() {
      return this.serviceName() + " [" + this.state() + "]";
   }

   public final boolean isRunning() {
      return this.delegate.isRunning();
   }

   public final Service.State state() {
      return this.delegate.state();
   }

   public final void addListener(Service.Listener listener, Executor executor) {
      this.delegate.addListener(listener, executor);
   }

   public final Throwable failureCause() {
      return this.delegate.failureCause();
   }

   @CanIgnoreReturnValue
   public final Service startAsync() {
      this.delegate.startAsync();
      return this;
   }

   @CanIgnoreReturnValue
   public final Service stopAsync() {
      this.delegate.stopAsync();
      return this;
   }

   public final void awaitRunning() {
      this.delegate.awaitRunning();
   }

   public final void awaitRunning(Duration timeout) throws TimeoutException {
      Service.super.awaitRunning(timeout);
   }

   public final void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException {
      this.delegate.awaitRunning(timeout, unit);
   }

   public final void awaitTerminated() {
      this.delegate.awaitTerminated();
   }

   public final void awaitTerminated(Duration timeout) throws TimeoutException {
      Service.super.awaitTerminated(timeout);
   }

   public final void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException {
      this.delegate.awaitTerminated(timeout, unit);
   }

   protected String serviceName() {
      return this.getClass().getSimpleName();
   }
}
