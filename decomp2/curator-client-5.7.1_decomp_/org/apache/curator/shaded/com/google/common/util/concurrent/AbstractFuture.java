package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.base.Strings;
import org.apache.curator.shaded.com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import org.apache.curator.shaded.com.google.common.util.concurrent.internal.InternalFutures;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.ForOverride;
import org.apache.curator.shaded.com.google.j2objc.annotations.ReflectionSupport;
import org.apache.curator.shaded.com.google.j2objc.annotations.ReflectionSupport.Level;
import sun.misc.Unsafe;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
@ReflectionSupport(Level.FULL)
public abstract class AbstractFuture extends InternalFutureFailureAccess implements ListenableFuture {
   static final boolean GENERATE_CANCELLATION_CAUSES;
   private static final Logger log;
   private static final long SPIN_THRESHOLD_NANOS = 1000L;
   private static final AtomicHelper ATOMIC_HELPER;
   private static final Object NULL;
   @CheckForNull
   private volatile Object value;
   @CheckForNull
   private volatile Listener listeners;
   @CheckForNull
   private volatile Waiter waiters;

   private void removeWaiter(Waiter node) {
      node.thread = null;

      label28:
      while(true) {
         Waiter pred = null;
         Waiter curr = this.waiters;
         if (curr == AbstractFuture.Waiter.TOMBSTONE) {
            return;
         }

         Waiter succ;
         for(; curr != null; curr = succ) {
            succ = curr.next;
            if (curr.thread != null) {
               pred = curr;
            } else if (pred != null) {
               pred.next = succ;
               if (pred.thread == null) {
                  continue label28;
               }
            } else if (!ATOMIC_HELPER.casWaiters(this, curr, succ)) {
               continue label28;
            }
         }

         return;
      }
   }

   protected AbstractFuture() {
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object get(long timeout, TimeUnit unit) throws InterruptedException, TimeoutException, ExecutionException {
      long timeoutNanos = unit.toNanos(timeout);
      long remainingNanos = timeoutNanos;
      if (Thread.interrupted()) {
         throw new InterruptedException();
      } else {
         Object localValue = this.value;
         if (localValue != null & !(localValue instanceof SetFuture)) {
            return this.getDoneValue(localValue);
         } else {
            long endNanos = timeoutNanos > 0L ? System.nanoTime() + timeoutNanos : 0L;
            if (timeoutNanos >= 1000L) {
               Waiter oldHead = this.waiters;
               if (oldHead == AbstractFuture.Waiter.TOMBSTONE) {
                  return this.getDoneValue(Objects.requireNonNull(this.value));
               }

               Waiter node = new Waiter();

               while(true) {
                  node.setNext(oldHead);
                  if (ATOMIC_HELPER.casWaiters(this, oldHead, node)) {
                     do {
                        OverflowAvoidingLockSupport.parkNanos(this, remainingNanos);
                        if (Thread.interrupted()) {
                           this.removeWaiter(node);
                           throw new InterruptedException();
                        }

                        localValue = this.value;
                        if (localValue != null & !(localValue instanceof SetFuture)) {
                           return this.getDoneValue(localValue);
                        }

                        remainingNanos = endNanos - System.nanoTime();
                     } while(remainingNanos >= 1000L);

                     this.removeWaiter(node);
                     break;
                  }

                  oldHead = this.waiters;
                  if (oldHead == AbstractFuture.Waiter.TOMBSTONE) {
                     return this.getDoneValue(Objects.requireNonNull(this.value));
                  }
               }
            }

            while(remainingNanos > 0L) {
               localValue = this.value;
               if (localValue != null & !(localValue instanceof SetFuture)) {
                  return this.getDoneValue(localValue);
               }

               if (Thread.interrupted()) {
                  throw new InterruptedException();
               }

               remainingNanos = endNanos - System.nanoTime();
            }

            String futureToString = this.toString();
            String unitString = unit.toString().toLowerCase(Locale.ROOT);
            String message = "Waited " + timeout + " " + unit.toString().toLowerCase(Locale.ROOT);
            if (remainingNanos + 1000L < 0L) {
               message = message + " (plus ";
               long overWaitNanos = -remainingNanos;
               long overWaitUnits = unit.convert(overWaitNanos, TimeUnit.NANOSECONDS);
               long overWaitLeftoverNanos = overWaitNanos - unit.toNanos(overWaitUnits);
               boolean shouldShowExtraNanos = overWaitUnits == 0L || overWaitLeftoverNanos > 1000L;
               if (overWaitUnits > 0L) {
                  message = message + overWaitUnits + " " + unitString;
                  if (shouldShowExtraNanos) {
                     message = message + ",";
                  }

                  message = message + " ";
               }

               if (shouldShowExtraNanos) {
                  message = message + overWaitLeftoverNanos + " nanoseconds ";
               }

               message = message + "delay)";
            }

            if (this.isDone()) {
               throw new TimeoutException(message + " but future completed as timeout expired");
            } else {
               throw new TimeoutException(message + " for " + futureToString);
            }
         }
      }
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object get() throws InterruptedException, ExecutionException {
      if (Thread.interrupted()) {
         throw new InterruptedException();
      } else {
         Object localValue = this.value;
         if (localValue != null & !(localValue instanceof SetFuture)) {
            return this.getDoneValue(localValue);
         } else {
            Waiter oldHead = this.waiters;
            if (oldHead != AbstractFuture.Waiter.TOMBSTONE) {
               Waiter node = new Waiter();

               do {
                  node.setNext(oldHead);
                  if (ATOMIC_HELPER.casWaiters(this, oldHead, node)) {
                     do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                           this.removeWaiter(node);
                           throw new InterruptedException();
                        }

                        localValue = this.value;
                     } while(!(localValue != null & !(localValue instanceof SetFuture)));

                     return this.getDoneValue(localValue);
                  }

                  oldHead = this.waiters;
               } while(oldHead != AbstractFuture.Waiter.TOMBSTONE);
            }

            return this.getDoneValue(Objects.requireNonNull(this.value));
         }
      }
   }

   @ParametricNullness
   private Object getDoneValue(Object obj) throws ExecutionException {
      if (obj instanceof Cancellation) {
         throw cancellationExceptionWithCause("Task was cancelled.", ((Cancellation)obj).cause);
      } else if (obj instanceof Failure) {
         throw new ExecutionException(((Failure)obj).exception);
      } else {
         return obj == NULL ? NullnessCasts.uncheckedNull() : obj;
      }
   }

   public boolean isDone() {
      Object localValue = this.value;
      return localValue != null & !(localValue instanceof SetFuture);
   }

   public boolean isCancelled() {
      Object localValue = this.value;
      return localValue instanceof Cancellation;
   }

   @CanIgnoreReturnValue
   public boolean cancel(boolean mayInterruptIfRunning) {
      Object localValue = this.value;
      boolean rValue = false;
      if (localValue == null | localValue instanceof SetFuture) {
         Object valueToSet = GENERATE_CANCELLATION_CAUSES ? new Cancellation(mayInterruptIfRunning, new CancellationException("Future.cancel() was called.")) : Objects.requireNonNull(mayInterruptIfRunning ? AbstractFuture.Cancellation.CAUSELESS_INTERRUPTED : AbstractFuture.Cancellation.CAUSELESS_CANCELLED);
         AbstractFuture<?> abstractFuture = this;

         while(true) {
            while(!ATOMIC_HELPER.casValue(abstractFuture, localValue, valueToSet)) {
               localValue = abstractFuture.value;
               if (!(localValue instanceof SetFuture)) {
                  return rValue;
               }
            }

            rValue = true;
            complete(abstractFuture, mayInterruptIfRunning);
            if (!(localValue instanceof SetFuture)) {
               break;
            }

            ListenableFuture<?> futureToPropagateTo = ((SetFuture)localValue).future;
            if (!(futureToPropagateTo instanceof Trusted)) {
               futureToPropagateTo.cancel(mayInterruptIfRunning);
               break;
            }

            AbstractFuture<?> trusted = (AbstractFuture)futureToPropagateTo;
            localValue = trusted.value;
            if (!(localValue == null | localValue instanceof SetFuture)) {
               break;
            }

            abstractFuture = trusted;
         }
      }

      return rValue;
   }

   protected void interruptTask() {
   }

   protected final boolean wasInterrupted() {
      Object localValue = this.value;
      return localValue instanceof Cancellation && ((Cancellation)localValue).wasInterrupted;
   }

   public void addListener(Runnable listener, Executor executor) {
      Preconditions.checkNotNull(listener, "Runnable was null.");
      Preconditions.checkNotNull(executor, "Executor was null.");
      if (!this.isDone()) {
         Listener oldHead = this.listeners;
         if (oldHead != AbstractFuture.Listener.TOMBSTONE) {
            Listener newNode = new Listener(listener, executor);

            do {
               newNode.next = oldHead;
               if (ATOMIC_HELPER.casListeners(this, oldHead, newNode)) {
                  return;
               }

               oldHead = this.listeners;
            } while(oldHead != AbstractFuture.Listener.TOMBSTONE);
         }
      }

      executeListener(listener, executor);
   }

   @CanIgnoreReturnValue
   protected boolean set(@ParametricNullness Object value) {
      Object valueToSet = value == null ? NULL : value;
      if (ATOMIC_HELPER.casValue(this, (Object)null, valueToSet)) {
         complete(this, false);
         return true;
      } else {
         return false;
      }
   }

   @CanIgnoreReturnValue
   protected boolean setException(Throwable throwable) {
      Object valueToSet = new Failure((Throwable)Preconditions.checkNotNull(throwable));
      if (ATOMIC_HELPER.casValue(this, (Object)null, valueToSet)) {
         complete(this, false);
         return true;
      } else {
         return false;
      }
   }

   @CanIgnoreReturnValue
   protected boolean setFuture(ListenableFuture future) {
      Preconditions.checkNotNull(future);
      Object localValue = this.value;
      if (localValue == null) {
         if (future.isDone()) {
            Object value = getFutureValue(future);
            if (ATOMIC_HELPER.casValue(this, (Object)null, value)) {
               complete(this, false);
               return true;
            }

            return false;
         }

         SetFuture<V> valueToSet = new SetFuture(this, future);
         if (ATOMIC_HELPER.casValue(this, (Object)null, valueToSet)) {
            try {
               future.addListener(valueToSet, DirectExecutor.INSTANCE);
            } catch (Error | RuntimeException var8) {
               Throwable t = var8;

               Failure failure;
               try {
                  failure = new Failure(t);
               } catch (Error | RuntimeException var7) {
                  failure = AbstractFuture.Failure.FALLBACK_INSTANCE;
               }

               ATOMIC_HELPER.casValue(this, valueToSet, failure);
            }

            return true;
         }

         localValue = this.value;
      }

      if (localValue instanceof Cancellation) {
         future.cancel(((Cancellation)localValue).wasInterrupted);
      }

      return false;
   }

   private static Object getFutureValue(ListenableFuture future) {
      if (future instanceof Trusted) {
         Object v = ((AbstractFuture)future).value;
         if (v instanceof Cancellation) {
            Cancellation c = (Cancellation)v;
            if (c.wasInterrupted) {
               v = c.cause != null ? new Cancellation(false, c.cause) : AbstractFuture.Cancellation.CAUSELESS_CANCELLED;
            }
         }

         return Objects.requireNonNull(v);
      } else {
         if (future instanceof InternalFutureFailureAccess) {
            Throwable throwable = InternalFutures.tryInternalFastPathGetFailure((InternalFutureFailureAccess)future);
            if (throwable != null) {
               return new Failure(throwable);
            }
         }

         boolean wasCancelled = future.isCancelled();
         if (!GENERATE_CANCELLATION_CAUSES & wasCancelled) {
            return Objects.requireNonNull(AbstractFuture.Cancellation.CAUSELESS_CANCELLED);
         } else {
            try {
               Object v = getUninterruptibly(future);
               if (wasCancelled) {
                  return new Cancellation(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + future));
               } else {
                  return v == null ? NULL : v;
               }
            } catch (ExecutionException exception) {
               return wasCancelled ? new Cancellation(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + future, exception)) : new Failure(exception.getCause());
            } catch (CancellationException cancellation) {
               return !wasCancelled ? new Failure(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + future, cancellation)) : new Cancellation(false, cancellation);
            } catch (Error | RuntimeException t) {
               return new Failure(t);
            }
         }
      }
   }

   @ParametricNullness
   private static Object getUninterruptibly(Future future) throws ExecutionException {
      boolean interrupted = false;

      try {
         while(true) {
            try {
               Object var2 = future.get();
               return var2;
            } catch (InterruptedException var6) {
               interrupted = true;
            }
         }
      } finally {
         if (interrupted) {
            Thread.currentThread().interrupt();
         }

      }
   }

   private static void complete(AbstractFuture param, boolean callInterruptTask) {
      AbstractFuture<?> future = param;
      Listener next = null;

      label27:
      while(true) {
         future.releaseWaiters();
         if (callInterruptTask) {
            future.interruptTask();
            callInterruptTask = false;
         }

         future.afterDone();
         next = future.clearListeners(next);
         AbstractFuture var8 = null;

         while(next != null) {
            Listener curr = next;
            next = next.next;
            Runnable task = (Runnable)Objects.requireNonNull(curr.task);
            if (task instanceof SetFuture) {
               SetFuture<?> setFuture = (SetFuture)task;
               future = setFuture.owner;
               if (future.value == setFuture) {
                  Object valueToSet = getFutureValue(setFuture.future);
                  if (ATOMIC_HELPER.casValue(future, setFuture, valueToSet)) {
                     continue label27;
                  }
               }
            } else {
               executeListener(task, (Executor)Objects.requireNonNull(curr.executor));
            }
         }

         return;
      }
   }

   @ForOverride
   protected void afterDone() {
   }

   @CheckForNull
   protected final Throwable tryInternalFastPathGetFailure() {
      if (this instanceof Trusted) {
         Object obj = this.value;
         if (obj instanceof Failure) {
            return ((Failure)obj).exception;
         }
      }

      return null;
   }

   final void maybePropagateCancellationTo(@CheckForNull Future related) {
      if (related != null & this.isCancelled()) {
         related.cancel(this.wasInterrupted());
      }

   }

   private void releaseWaiters() {
      Waiter head = ATOMIC_HELPER.gasWaiters(this, AbstractFuture.Waiter.TOMBSTONE);

      for(Waiter currentWaiter = head; currentWaiter != null; currentWaiter = currentWaiter.next) {
         currentWaiter.unpark();
      }

   }

   @CheckForNull
   private Listener clearListeners(@CheckForNull Listener onto) {
      Listener head = ATOMIC_HELPER.gasListeners(this, AbstractFuture.Listener.TOMBSTONE);

      Listener reversedList;
      Listener tmp;
      for(reversedList = onto; head != null; reversedList = tmp) {
         tmp = head;
         head = head.next;
         tmp.next = reversedList;
      }

      return reversedList;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();
      if (this.getClass().getName().startsWith("org.apache.curator.shaded.com.google.common.util.concurrent.")) {
         builder.append(this.getClass().getSimpleName());
      } else {
         builder.append(this.getClass().getName());
      }

      builder.append('@').append(Integer.toHexString(System.identityHashCode(this))).append("[status=");
      if (this.isCancelled()) {
         builder.append("CANCELLED");
      } else if (this.isDone()) {
         this.addDoneString(builder);
      } else {
         this.addPendingString(builder);
      }

      return builder.append("]").toString();
   }

   @CheckForNull
   protected String pendingToString() {
      return this instanceof ScheduledFuture ? "remaining delay=[" + ((ScheduledFuture)this).getDelay(TimeUnit.MILLISECONDS) + " ms]" : null;
   }

   private void addPendingString(StringBuilder builder) {
      int truncateLength = builder.length();
      builder.append("PENDING");
      Object localValue = this.value;
      if (localValue instanceof SetFuture) {
         builder.append(", setFuture=[");
         this.appendUserObject(builder, ((SetFuture)localValue).future);
         builder.append("]");
      } else {
         String pendingDescription;
         try {
            pendingDescription = Strings.emptyToNull(this.pendingToString());
         } catch (StackOverflowError | RuntimeException e) {
            pendingDescription = "Exception thrown from implementation: " + e.getClass();
         }

         if (pendingDescription != null) {
            builder.append(", info=[").append(pendingDescription).append("]");
         }
      }

      if (this.isDone()) {
         builder.delete(truncateLength, builder.length());
         this.addDoneString(builder);
      }

   }

   private void addDoneString(StringBuilder builder) {
      try {
         V value = (V)getUninterruptibly(this);
         builder.append("SUCCESS, result=[");
         this.appendResultObject(builder, value);
         builder.append("]");
      } catch (ExecutionException e) {
         builder.append("FAILURE, cause=[").append(e.getCause()).append("]");
      } catch (CancellationException var4) {
         builder.append("CANCELLED");
      } catch (RuntimeException e) {
         builder.append("UNKNOWN, cause=[").append(e.getClass()).append(" thrown from get()]");
      }

   }

   private void appendResultObject(StringBuilder builder, @CheckForNull Object o) {
      if (o == null) {
         builder.append("null");
      } else if (o == this) {
         builder.append("this future");
      } else {
         builder.append(o.getClass().getName()).append("@").append(Integer.toHexString(System.identityHashCode(o)));
      }

   }

   private void appendUserObject(StringBuilder builder, @CheckForNull Object o) {
      try {
         if (o == this) {
            builder.append("this future");
         } else {
            builder.append(o);
         }
      } catch (StackOverflowError | RuntimeException e) {
         builder.append("Exception thrown from implementation: ").append(e.getClass());
      }

   }

   private static void executeListener(Runnable runnable, Executor executor) {
      try {
         executor.execute(runnable);
      } catch (RuntimeException e) {
         log.log(java.util.logging.Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e);
      }

   }

   private static CancellationException cancellationExceptionWithCause(String message, @CheckForNull Throwable cause) {
      CancellationException exception = new CancellationException(message);
      exception.initCause(cause);
      return exception;
   }

   static {
      boolean generateCancellationCauses;
      try {
         generateCancellationCauses = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
      } catch (SecurityException var7) {
         generateCancellationCauses = false;
      }

      GENERATE_CANCELLATION_CAUSES = generateCancellationCauses;
      log = Logger.getLogger(AbstractFuture.class.getName());
      Throwable thrownUnsafeFailure = null;
      Throwable thrownAtomicReferenceFieldUpdaterFailure = null;

      try {
         helper = new UnsafeAtomicHelper();
      } catch (Error | RuntimeException unsafeFailure) {
         thrownUnsafeFailure = unsafeFailure;

         try {
            helper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Waiter.class, "next"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, Waiter.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, Listener.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, Object.class, "value"));
         } catch (Error | RuntimeException atomicReferenceFieldUpdaterFailure) {
            thrownAtomicReferenceFieldUpdaterFailure = atomicReferenceFieldUpdaterFailure;
            helper = new SynchronizedHelper();
         }
      }

      ATOMIC_HELPER = helper;
      Class<?> ensureLoaded = LockSupport.class;
      if (thrownAtomicReferenceFieldUpdaterFailure != null) {
         log.log(java.util.logging.Level.SEVERE, "UnsafeAtomicHelper is broken!", thrownUnsafeFailure);
         log.log(java.util.logging.Level.SEVERE, "SafeAtomicHelper is broken!", thrownAtomicReferenceFieldUpdaterFailure);
      }

      NULL = new Object();
   }

   abstract static class TrustedFuture extends AbstractFuture implements Trusted {
      @ParametricNullness
      @CanIgnoreReturnValue
      public final Object get() throws InterruptedException, ExecutionException {
         return super.get();
      }

      @ParametricNullness
      @CanIgnoreReturnValue
      public final Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
         return super.get(timeout, unit);
      }

      public final boolean isDone() {
         return super.isDone();
      }

      public final boolean isCancelled() {
         return super.isCancelled();
      }

      public final void addListener(Runnable listener, Executor executor) {
         super.addListener(listener, executor);
      }

      @CanIgnoreReturnValue
      public final boolean cancel(boolean mayInterruptIfRunning) {
         return super.cancel(mayInterruptIfRunning);
      }
   }

   private static final class Waiter {
      static final Waiter TOMBSTONE = new Waiter(false);
      @CheckForNull
      volatile Thread thread;
      @CheckForNull
      volatile Waiter next;

      Waiter(boolean unused) {
      }

      Waiter() {
         AbstractFuture.ATOMIC_HELPER.putThread(this, Thread.currentThread());
      }

      void setNext(@CheckForNull Waiter next) {
         AbstractFuture.ATOMIC_HELPER.putNext(this, next);
      }

      void unpark() {
         Thread w = this.thread;
         if (w != null) {
            this.thread = null;
            LockSupport.unpark(w);
         }

      }
   }

   private static final class Listener {
      static final Listener TOMBSTONE = new Listener();
      @CheckForNull
      final Runnable task;
      @CheckForNull
      final Executor executor;
      @CheckForNull
      Listener next;

      Listener(Runnable task, Executor executor) {
         this.task = task;
         this.executor = executor;
      }

      Listener() {
         this.task = null;
         this.executor = null;
      }
   }

   private static final class Failure {
      static final Failure FALLBACK_INSTANCE = new Failure(new Throwable("Failure occurred while trying to finish a future.") {
         public synchronized Throwable fillInStackTrace() {
            return this;
         }
      });
      final Throwable exception;

      Failure(Throwable exception) {
         this.exception = (Throwable)Preconditions.checkNotNull(exception);
      }
   }

   private static final class Cancellation {
      @CheckForNull
      static final Cancellation CAUSELESS_INTERRUPTED;
      @CheckForNull
      static final Cancellation CAUSELESS_CANCELLED;
      final boolean wasInterrupted;
      @CheckForNull
      final Throwable cause;

      Cancellation(boolean wasInterrupted, @CheckForNull Throwable cause) {
         this.wasInterrupted = wasInterrupted;
         this.cause = cause;
      }

      static {
         if (AbstractFuture.GENERATE_CANCELLATION_CAUSES) {
            CAUSELESS_CANCELLED = null;
            CAUSELESS_INTERRUPTED = null;
         } else {
            CAUSELESS_CANCELLED = new Cancellation(false, (Throwable)null);
            CAUSELESS_INTERRUPTED = new Cancellation(true, (Throwable)null);
         }

      }
   }

   private static final class SetFuture implements Runnable {
      final AbstractFuture owner;
      final ListenableFuture future;

      SetFuture(AbstractFuture owner, ListenableFuture future) {
         this.owner = owner;
         this.future = future;
      }

      public void run() {
         if (this.owner.value == this) {
            Object valueToSet = AbstractFuture.getFutureValue(this.future);
            if (AbstractFuture.ATOMIC_HELPER.casValue(this.owner, this, valueToSet)) {
               AbstractFuture.complete(this.owner, false);
            }

         }
      }
   }

   private abstract static class AtomicHelper {
      private AtomicHelper() {
      }

      abstract void putThread(Waiter waiter, Thread newValue);

      abstract void putNext(Waiter waiter, @CheckForNull Waiter newValue);

      abstract boolean casWaiters(AbstractFuture future, @CheckForNull Waiter expect, @CheckForNull Waiter update);

      abstract boolean casListeners(AbstractFuture future, @CheckForNull Listener expect, Listener update);

      abstract Waiter gasWaiters(AbstractFuture future, Waiter update);

      abstract Listener gasListeners(AbstractFuture future, Listener update);

      abstract boolean casValue(AbstractFuture future, @CheckForNull Object expect, Object update);
   }

   private static final class UnsafeAtomicHelper extends AtomicHelper {
      static final Unsafe UNSAFE;
      static final long LISTENERS_OFFSET;
      static final long WAITERS_OFFSET;
      static final long VALUE_OFFSET;
      static final long WAITER_THREAD_OFFSET;
      static final long WAITER_NEXT_OFFSET;

      private UnsafeAtomicHelper() {
      }

      void putThread(Waiter waiter, Thread newValue) {
         UNSAFE.putObject(waiter, WAITER_THREAD_OFFSET, newValue);
      }

      void putNext(Waiter waiter, @CheckForNull Waiter newValue) {
         UNSAFE.putObject(waiter, WAITER_NEXT_OFFSET, newValue);
      }

      boolean casWaiters(AbstractFuture future, @CheckForNull Waiter expect, @CheckForNull Waiter update) {
         return UNSAFE.compareAndSwapObject(future, WAITERS_OFFSET, expect, update);
      }

      boolean casListeners(AbstractFuture future, @CheckForNull Listener expect, Listener update) {
         return UNSAFE.compareAndSwapObject(future, LISTENERS_OFFSET, expect, update);
      }

      Listener gasListeners(AbstractFuture future, Listener update) {
         return (Listener)UNSAFE.getAndSetObject(future, LISTENERS_OFFSET, update);
      }

      Waiter gasWaiters(AbstractFuture future, Waiter update) {
         return (Waiter)UNSAFE.getAndSetObject(future, WAITERS_OFFSET, update);
      }

      boolean casValue(AbstractFuture future, @CheckForNull Object expect, Object update) {
         return UNSAFE.compareAndSwapObject(future, VALUE_OFFSET, expect, update);
      }

      static {
         Unsafe unsafe = null;

         try {
            unsafe = Unsafe.getUnsafe();
         } catch (SecurityException var6) {
            try {
               unsafe = (Unsafe)AccessController.doPrivileged(new PrivilegedExceptionAction() {
                  public Unsafe run() throws Exception {
                     Class<Unsafe> k = Unsafe.class;

                     for(Field f : k.getDeclaredFields()) {
                        f.setAccessible(true);
                        Object x = f.get((Object)null);
                        if (k.isInstance(x)) {
                           return (Unsafe)k.cast(x);
                        }
                     }

                     throw new NoSuchFieldError("the Unsafe");
                  }
               });
            } catch (PrivilegedActionException e) {
               throw new RuntimeException("Could not initialize intrinsics", e.getCause());
            }
         }

         try {
            Class<?> abstractFuture = AbstractFuture.class;
            WAITERS_OFFSET = unsafe.objectFieldOffset(abstractFuture.getDeclaredField("waiters"));
            LISTENERS_OFFSET = unsafe.objectFieldOffset(abstractFuture.getDeclaredField("listeners"));
            VALUE_OFFSET = unsafe.objectFieldOffset(abstractFuture.getDeclaredField("value"));
            WAITER_THREAD_OFFSET = unsafe.objectFieldOffset(Waiter.class.getDeclaredField("thread"));
            WAITER_NEXT_OFFSET = unsafe.objectFieldOffset(Waiter.class.getDeclaredField("next"));
            UNSAFE = unsafe;
         } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
         } catch (RuntimeException e) {
            throw e;
         }
      }
   }

   private static final class SafeAtomicHelper extends AtomicHelper {
      final AtomicReferenceFieldUpdater waiterThreadUpdater;
      final AtomicReferenceFieldUpdater waiterNextUpdater;
      final AtomicReferenceFieldUpdater waitersUpdater;
      final AtomicReferenceFieldUpdater listenersUpdater;
      final AtomicReferenceFieldUpdater valueUpdater;

      SafeAtomicHelper(AtomicReferenceFieldUpdater waiterThreadUpdater, AtomicReferenceFieldUpdater waiterNextUpdater, AtomicReferenceFieldUpdater waitersUpdater, AtomicReferenceFieldUpdater listenersUpdater, AtomicReferenceFieldUpdater valueUpdater) {
         this.waiterThreadUpdater = waiterThreadUpdater;
         this.waiterNextUpdater = waiterNextUpdater;
         this.waitersUpdater = waitersUpdater;
         this.listenersUpdater = listenersUpdater;
         this.valueUpdater = valueUpdater;
      }

      void putThread(Waiter waiter, Thread newValue) {
         this.waiterThreadUpdater.lazySet(waiter, newValue);
      }

      void putNext(Waiter waiter, @CheckForNull Waiter newValue) {
         this.waiterNextUpdater.lazySet(waiter, newValue);
      }

      boolean casWaiters(AbstractFuture future, @CheckForNull Waiter expect, @CheckForNull Waiter update) {
         return this.waitersUpdater.compareAndSet(future, expect, update);
      }

      boolean casListeners(AbstractFuture future, @CheckForNull Listener expect, Listener update) {
         return this.listenersUpdater.compareAndSet(future, expect, update);
      }

      Listener gasListeners(AbstractFuture future, Listener update) {
         return (Listener)this.listenersUpdater.getAndSet(future, update);
      }

      Waiter gasWaiters(AbstractFuture future, Waiter update) {
         return (Waiter)this.waitersUpdater.getAndSet(future, update);
      }

      boolean casValue(AbstractFuture future, @CheckForNull Object expect, Object update) {
         return this.valueUpdater.compareAndSet(future, expect, update);
      }
   }

   private static final class SynchronizedHelper extends AtomicHelper {
      private SynchronizedHelper() {
      }

      void putThread(Waiter waiter, Thread newValue) {
         waiter.thread = newValue;
      }

      void putNext(Waiter waiter, @CheckForNull Waiter newValue) {
         waiter.next = newValue;
      }

      boolean casWaiters(AbstractFuture future, @CheckForNull Waiter expect, @CheckForNull Waiter update) {
         synchronized(future) {
            if (future.waiters == expect) {
               future.waiters = update;
               return true;
            } else {
               return false;
            }
         }
      }

      boolean casListeners(AbstractFuture future, @CheckForNull Listener expect, Listener update) {
         synchronized(future) {
            if (future.listeners == expect) {
               future.listeners = update;
               return true;
            } else {
               return false;
            }
         }
      }

      Listener gasListeners(AbstractFuture future, Listener update) {
         synchronized(future) {
            Listener old = future.listeners;
            if (old != update) {
               future.listeners = update;
            }

            return old;
         }
      }

      Waiter gasWaiters(AbstractFuture future, Waiter update) {
         synchronized(future) {
            Waiter old = future.waiters;
            if (old != update) {
               future.waiters = update;
            }

            return old;
         }
      }

      boolean casValue(AbstractFuture future, @CheckForNull Object expect, Object update) {
         synchronized(future) {
            if (future.value == expect) {
               future.value = update;
               return true;
            } else {
               return false;
            }
         }
      }
   }

   interface Trusted extends ListenableFuture {
   }
}
