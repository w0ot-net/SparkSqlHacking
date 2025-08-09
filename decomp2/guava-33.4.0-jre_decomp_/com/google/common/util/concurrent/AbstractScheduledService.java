package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
public abstract class AbstractScheduledService implements Service {
   private static final LazyLogger logger = new LazyLogger(AbstractScheduledService.class);
   private final AbstractService delegate = new ServiceDelegate();

   protected AbstractScheduledService() {
   }

   protected abstract void runOneIteration() throws Exception;

   protected void startUp() throws Exception {
   }

   protected void shutDown() throws Exception {
   }

   protected abstract Scheduler scheduler();

   protected ScheduledExecutorService executor() {
      class ThreadFactoryImpl implements ThreadFactory {
         public Thread newThread(Runnable runnable) {
            return MoreExecutors.newThread(AbstractScheduledService.this.serviceName(), runnable);
         }
      }

      final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryImpl());
      this.addListener(new Service.Listener() {
         public void terminated(Service.State from) {
            executor.shutdown();
         }

         public void failed(Service.State from, Throwable failure) {
            executor.shutdown();
         }
      }, MoreExecutors.directExecutor());
      return executor;
   }

   protected String serviceName() {
      return this.getClass().getSimpleName();
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

   public abstract static class Scheduler {
      public static Scheduler newFixedDelaySchedule(Duration initialDelay, Duration delay) {
         return newFixedDelaySchedule(Internal.toNanosSaturated(initialDelay), Internal.toNanosSaturated(delay), TimeUnit.NANOSECONDS);
      }

      public static Scheduler newFixedDelaySchedule(final long initialDelay, final long delay, final TimeUnit unit) {
         Preconditions.checkNotNull(unit);
         Preconditions.checkArgument(delay > 0L, "delay must be > 0, found %s", delay);
         return new Scheduler() {
            public Cancellable schedule(AbstractService service, ScheduledExecutorService executor, Runnable task) {
               return new FutureAsCancellable(executor.scheduleWithFixedDelay(task, initialDelay, delay, unit));
            }
         };
      }

      public static Scheduler newFixedRateSchedule(Duration initialDelay, Duration period) {
         return newFixedRateSchedule(Internal.toNanosSaturated(initialDelay), Internal.toNanosSaturated(period), TimeUnit.NANOSECONDS);
      }

      public static Scheduler newFixedRateSchedule(final long initialDelay, final long period, final TimeUnit unit) {
         Preconditions.checkNotNull(unit);
         Preconditions.checkArgument(period > 0L, "period must be > 0, found %s", period);
         return new Scheduler() {
            public Cancellable schedule(AbstractService service, ScheduledExecutorService executor, Runnable task) {
               return new FutureAsCancellable(executor.scheduleAtFixedRate(task, initialDelay, period, unit));
            }
         };
      }

      abstract Cancellable schedule(AbstractService service, ScheduledExecutorService executor, Runnable runnable);

      private Scheduler() {
      }
   }

   private final class ServiceDelegate extends AbstractService {
      @CheckForNull
      private volatile Cancellable runningTask;
      @CheckForNull
      private volatile ScheduledExecutorService executorService;
      private final ReentrantLock lock;
      private final Runnable task;

      private ServiceDelegate() {
         this.lock = new ReentrantLock();
         this.task = new Task();
      }

      protected final void doStart() {
         this.executorService = MoreExecutors.renamingDecorator((ScheduledExecutorService)AbstractScheduledService.this.executor(), () -> AbstractScheduledService.this.serviceName() + " " + this.state());
         this.executorService.execute(() -> {
            this.lock.lock();

            try {
               AbstractScheduledService.this.startUp();
               Objects.requireNonNull(this.executorService);
               this.runningTask = AbstractScheduledService.this.scheduler().schedule(AbstractScheduledService.this.delegate, this.executorService, this.task);
               this.notifyStarted();
            } catch (Throwable t) {
               Platform.restoreInterruptIfIsInterruptedException(t);
               this.notifyFailed(t);
               if (this.runningTask != null) {
                  this.runningTask.cancel(false);
               }
            } finally {
               this.lock.unlock();
            }

         });
      }

      protected final void doStop() {
         Objects.requireNonNull(this.runningTask);
         Objects.requireNonNull(this.executorService);
         this.runningTask.cancel(false);
         this.executorService.execute(() -> {
            try {
               this.lock.lock();

               label50: {
                  try {
                     if (this.state() == Service.State.STOPPING) {
                        AbstractScheduledService.this.shutDown();
                        break label50;
                     }
                  } finally {
                     this.lock.unlock();
                  }

                  return;
               }

               this.notifyStopped();
            } catch (Throwable t) {
               Platform.restoreInterruptIfIsInterruptedException(t);
               this.notifyFailed(t);
            }

         });
      }

      public String toString() {
         return AbstractScheduledService.this.toString();
      }

      class Task implements Runnable {
         public void run() {
            ServiceDelegate.this.lock.lock();

            try {
               if (!((Cancellable)Objects.requireNonNull(ServiceDelegate.this.runningTask)).isCancelled()) {
                  AbstractScheduledService.this.runOneIteration();
                  return;
               }
            } catch (Throwable t) {
               Platform.restoreInterruptIfIsInterruptedException(t);

               try {
                  AbstractScheduledService.this.shutDown();
               } catch (Exception ignored) {
                  Platform.restoreInterruptIfIsInterruptedException(ignored);
                  AbstractScheduledService.logger.get().log(Level.WARNING, "Error while attempting to shut down the service after failure.", ignored);
               }

               ServiceDelegate.this.notifyFailed(t);
               ((Cancellable)Objects.requireNonNull(ServiceDelegate.this.runningTask)).cancel(false);
               return;
            } finally {
               ServiceDelegate.this.lock.unlock();
            }

         }
      }
   }

   private static final class FutureAsCancellable implements Cancellable {
      private final Future delegate;

      FutureAsCancellable(Future delegate) {
         this.delegate = delegate;
      }

      public void cancel(boolean mayInterruptIfRunning) {
         this.delegate.cancel(mayInterruptIfRunning);
      }

      public boolean isCancelled() {
         return this.delegate.isCancelled();
      }
   }

   public abstract static class CustomScheduler extends Scheduler {
      final Cancellable schedule(AbstractService service, ScheduledExecutorService executor, Runnable runnable) {
         return (new ReschedulableCallable(service, executor, runnable)).reschedule();
      }

      protected abstract Schedule getNextSchedule() throws Exception;

      private final class ReschedulableCallable implements Callable {
         private final Runnable wrappedRunnable;
         private final ScheduledExecutorService executor;
         private final AbstractService service;
         private final ReentrantLock lock = new ReentrantLock();
         @CheckForNull
         @GuardedBy("lock")
         private SupplantableFuture cancellationDelegate;

         ReschedulableCallable(AbstractService service, ScheduledExecutorService executor, Runnable runnable) {
            this.wrappedRunnable = runnable;
            this.executor = executor;
            this.service = service;
         }

         @CheckForNull
         public Void call() throws Exception {
            this.wrappedRunnable.run();
            this.reschedule();
            return null;
         }

         @CanIgnoreReturnValue
         public Cancellable reschedule() {
            Schedule schedule;
            try {
               schedule = CustomScheduler.this.getNextSchedule();
            } catch (Throwable t) {
               Platform.restoreInterruptIfIsInterruptedException(t);
               this.service.notifyFailed(t);
               return new FutureAsCancellable(Futures.immediateCancelledFuture());
            }

            Throwable scheduleFailure = null;
            this.lock.lock();

            Cancellable toReturn;
            try {
               toReturn = this.initializeOrUpdateCancellationDelegate(schedule);
            } catch (Throwable e) {
               scheduleFailure = e;
               toReturn = new FutureAsCancellable(Futures.immediateCancelledFuture());
            } finally {
               this.lock.unlock();
            }

            if (scheduleFailure != null) {
               this.service.notifyFailed(scheduleFailure);
            }

            return toReturn;
         }

         @GuardedBy("lock")
         private Cancellable initializeOrUpdateCancellationDelegate(Schedule schedule) {
            if (this.cancellationDelegate == null) {
               return this.cancellationDelegate = new SupplantableFuture(this.lock, this.submitToExecutor(schedule));
            } else {
               if (!this.cancellationDelegate.currentFuture.isCancelled()) {
                  this.cancellationDelegate.currentFuture = this.submitToExecutor(schedule);
               }

               return this.cancellationDelegate;
            }
         }

         private ScheduledFuture submitToExecutor(Schedule schedule) {
            return this.executor.schedule(this, schedule.delay, schedule.unit);
         }
      }

      private static final class SupplantableFuture implements Cancellable {
         private final ReentrantLock lock;
         @GuardedBy("lock")
         private Future currentFuture;

         SupplantableFuture(ReentrantLock lock, Future currentFuture) {
            this.lock = lock;
            this.currentFuture = currentFuture;
         }

         public void cancel(boolean mayInterruptIfRunning) {
            this.lock.lock();

            try {
               this.currentFuture.cancel(mayInterruptIfRunning);
            } finally {
               this.lock.unlock();
            }

         }

         public boolean isCancelled() {
            this.lock.lock();

            boolean var1;
            try {
               var1 = this.currentFuture.isCancelled();
            } finally {
               this.lock.unlock();
            }

            return var1;
         }
      }

      protected static final class Schedule {
         private final long delay;
         private final TimeUnit unit;

         public Schedule(long delay, TimeUnit unit) {
            this.delay = delay;
            this.unit = (TimeUnit)Preconditions.checkNotNull(unit);
         }

         public Schedule(Duration delay) {
            this(Internal.toNanosSaturated(delay), TimeUnit.NANOSECONDS);
         }
      }
   }

   interface Cancellable {
      void cancel(boolean mayInterruptIfRunning);

      boolean isCancelled();
   }
}
