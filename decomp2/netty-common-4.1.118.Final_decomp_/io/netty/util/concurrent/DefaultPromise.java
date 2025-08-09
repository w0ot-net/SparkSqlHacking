package io.netty.util.concurrent;

import io.netty.util.internal.InternalThreadLocalMap;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.ThrowableUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class DefaultPromise extends AbstractFuture implements Promise {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(DefaultPromise.class);
   private static final InternalLogger rejectedExecutionLogger = InternalLoggerFactory.getInstance(DefaultPromise.class.getName() + ".rejectedExecution");
   private static final int MAX_LISTENER_STACK_DEPTH = Math.min(8, SystemPropertyUtil.getInt("io.netty.defaultPromise.maxListenerStackDepth", 8));
   private static final AtomicReferenceFieldUpdater RESULT_UPDATER = AtomicReferenceFieldUpdater.newUpdater(DefaultPromise.class, Object.class, "result");
   private static final Object SUCCESS = new Object();
   private static final Object UNCANCELLABLE = new Object();
   private static final CauseHolder CANCELLATION_CAUSE_HOLDER = new CauseHolder(DefaultPromise.StacklessCancellationException.newInstance(DefaultPromise.class, "cancel(...)"));
   private static final StackTraceElement[] CANCELLATION_STACK;
   private volatile Object result;
   private final EventExecutor executor;
   private GenericFutureListener listener;
   private DefaultFutureListeners listeners;
   private short waiters;
   private boolean notifyingListeners;

   public DefaultPromise(EventExecutor executor) {
      this.executor = (EventExecutor)ObjectUtil.checkNotNull(executor, "executor");
   }

   protected DefaultPromise() {
      this.executor = null;
   }

   public Promise setSuccess(Object result) {
      if (this.setSuccess0(result)) {
         return this;
      } else {
         throw new IllegalStateException("complete already: " + this);
      }
   }

   public boolean trySuccess(Object result) {
      return this.setSuccess0(result);
   }

   public Promise setFailure(Throwable cause) {
      if (this.setFailure0(cause)) {
         return this;
      } else {
         throw new IllegalStateException("complete already: " + this, cause);
      }
   }

   public boolean tryFailure(Throwable cause) {
      return this.setFailure0(cause);
   }

   public boolean setUncancellable() {
      if (RESULT_UPDATER.compareAndSet(this, (Object)null, UNCANCELLABLE)) {
         return true;
      } else {
         Object result = this.result;
         return !isDone0(result) || !isCancelled0(result);
      }
   }

   public boolean isSuccess() {
      Object result = this.result;
      return result != null && result != UNCANCELLABLE && !(result instanceof CauseHolder);
   }

   public boolean isCancellable() {
      return this.result == null;
   }

   public Throwable cause() {
      return this.cause0(this.result);
   }

   private Throwable cause0(Object result) {
      if (!(result instanceof CauseHolder)) {
         return null;
      } else {
         if (result == CANCELLATION_CAUSE_HOLDER) {
            CancellationException ce = new LeanCancellationException();
            if (RESULT_UPDATER.compareAndSet(this, CANCELLATION_CAUSE_HOLDER, new CauseHolder(ce))) {
               return ce;
            }

            result = this.result;
         }

         return ((CauseHolder)result).cause;
      }
   }

   public Promise addListener(GenericFutureListener listener) {
      ObjectUtil.checkNotNull(listener, "listener");
      synchronized(this) {
         this.addListener0(listener);
      }

      if (this.isDone()) {
         this.notifyListeners();
      }

      return this;
   }

   public Promise addListeners(GenericFutureListener... listeners) {
      ObjectUtil.checkNotNull(listeners, "listeners");
      synchronized(this) {
         for(GenericFutureListener listener : listeners) {
            if (listener == null) {
               break;
            }

            this.addListener0(listener);
         }
      }

      if (this.isDone()) {
         this.notifyListeners();
      }

      return this;
   }

   public Promise removeListener(GenericFutureListener listener) {
      ObjectUtil.checkNotNull(listener, "listener");
      synchronized(this) {
         this.removeListener0(listener);
         return this;
      }
   }

   public Promise removeListeners(GenericFutureListener... listeners) {
      ObjectUtil.checkNotNull(listeners, "listeners");
      synchronized(this) {
         for(GenericFutureListener listener : listeners) {
            if (listener == null) {
               break;
            }

            this.removeListener0(listener);
         }

         return this;
      }
   }

   public Promise await() throws InterruptedException {
      if (this.isDone()) {
         return this;
      } else if (Thread.interrupted()) {
         throw new InterruptedException(this.toString());
      } else {
         this.checkDeadLock();
         synchronized(this) {
            while(!this.isDone()) {
               this.incWaiters();

               try {
                  this.wait();
               } finally {
                  this.decWaiters();
               }
            }

            return this;
         }
      }
   }

   public Promise awaitUninterruptibly() {
      if (this.isDone()) {
         return this;
      } else {
         this.checkDeadLock();
         boolean interrupted = false;
         synchronized(this) {
            while(!this.isDone()) {
               this.incWaiters();

               try {
                  this.wait();
               } catch (InterruptedException var9) {
                  interrupted = true;
               } finally {
                  this.decWaiters();
               }
            }
         }

         if (interrupted) {
            Thread.currentThread().interrupt();
         }

         return this;
      }
   }

   public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
      return this.await0(unit.toNanos(timeout), true);
   }

   public boolean await(long timeoutMillis) throws InterruptedException {
      return this.await0(TimeUnit.MILLISECONDS.toNanos(timeoutMillis), true);
   }

   public boolean awaitUninterruptibly(long timeout, TimeUnit unit) {
      try {
         return this.await0(unit.toNanos(timeout), false);
      } catch (InterruptedException var5) {
         throw new InternalError();
      }
   }

   public boolean awaitUninterruptibly(long timeoutMillis) {
      try {
         return this.await0(TimeUnit.MILLISECONDS.toNanos(timeoutMillis), false);
      } catch (InterruptedException var4) {
         throw new InternalError();
      }
   }

   public Object getNow() {
      Object result = this.result;
      return !(result instanceof CauseHolder) && result != SUCCESS && result != UNCANCELLABLE ? result : null;
   }

   public Object get() throws InterruptedException, ExecutionException {
      Object result = this.result;
      if (!isDone0(result)) {
         this.await();
         result = this.result;
      }

      if (result != SUCCESS && result != UNCANCELLABLE) {
         Throwable cause = this.cause0(result);
         if (cause == null) {
            return result;
         } else if (cause instanceof CancellationException) {
            throw (CancellationException)cause;
         } else {
            throw new ExecutionException(cause);
         }
      } else {
         return null;
      }
   }

   public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
      Object result = this.result;
      if (!isDone0(result)) {
         if (!this.await(timeout, unit)) {
            throw new TimeoutException();
         }

         result = this.result;
      }

      if (result != SUCCESS && result != UNCANCELLABLE) {
         Throwable cause = this.cause0(result);
         if (cause == null) {
            return result;
         } else if (cause instanceof CancellationException) {
            throw (CancellationException)cause;
         } else {
            throw new ExecutionException(cause);
         }
      } else {
         return null;
      }
   }

   public boolean cancel(boolean mayInterruptIfRunning) {
      if (RESULT_UPDATER.compareAndSet(this, (Object)null, CANCELLATION_CAUSE_HOLDER)) {
         if (this.checkNotifyWaiters()) {
            this.notifyListeners();
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean isCancelled() {
      return isCancelled0(this.result);
   }

   public boolean isDone() {
      return isDone0(this.result);
   }

   public Promise sync() throws InterruptedException {
      this.await();
      this.rethrowIfFailed();
      return this;
   }

   public Promise syncUninterruptibly() {
      this.awaitUninterruptibly();
      this.rethrowIfFailed();
      return this;
   }

   public String toString() {
      return this.toStringBuilder().toString();
   }

   protected StringBuilder toStringBuilder() {
      StringBuilder buf = (new StringBuilder(64)).append(StringUtil.simpleClassName((Object)this)).append('@').append(Integer.toHexString(this.hashCode()));
      Object result = this.result;
      if (result == SUCCESS) {
         buf.append("(success)");
      } else if (result == UNCANCELLABLE) {
         buf.append("(uncancellable)");
      } else if (result instanceof CauseHolder) {
         buf.append("(failure: ").append(((CauseHolder)result).cause).append(')');
      } else if (result != null) {
         buf.append("(success: ").append(result).append(')');
      } else {
         buf.append("(incomplete)");
      }

      return buf;
   }

   protected EventExecutor executor() {
      return this.executor;
   }

   protected void checkDeadLock() {
      EventExecutor e = this.executor();
      if (e != null && e.inEventLoop()) {
         throw new BlockingOperationException(this.toString());
      }
   }

   protected static void notifyListener(EventExecutor eventExecutor, Future future, GenericFutureListener listener) {
      notifyListenerWithStackOverFlowProtection((EventExecutor)ObjectUtil.checkNotNull(eventExecutor, "eventExecutor"), (Future)ObjectUtil.checkNotNull(future, "future"), (GenericFutureListener)ObjectUtil.checkNotNull(listener, "listener"));
   }

   private void notifyListeners() {
      EventExecutor executor = this.executor();
      if (executor.inEventLoop()) {
         InternalThreadLocalMap threadLocals = InternalThreadLocalMap.get();
         int stackDepth = threadLocals.futureListenerStackDepth();
         if (stackDepth < MAX_LISTENER_STACK_DEPTH) {
            threadLocals.setFutureListenerStackDepth(stackDepth + 1);

            try {
               this.notifyListenersNow();
            } finally {
               threadLocals.setFutureListenerStackDepth(stackDepth);
            }

            return;
         }
      }

      safeExecute(executor, new Runnable() {
         public void run() {
            DefaultPromise.this.notifyListenersNow();
         }
      });
   }

   private static void notifyListenerWithStackOverFlowProtection(EventExecutor executor, final Future future, final GenericFutureListener listener) {
      if (executor.inEventLoop()) {
         InternalThreadLocalMap threadLocals = InternalThreadLocalMap.get();
         int stackDepth = threadLocals.futureListenerStackDepth();
         if (stackDepth < MAX_LISTENER_STACK_DEPTH) {
            threadLocals.setFutureListenerStackDepth(stackDepth + 1);

            try {
               notifyListener0(future, listener);
            } finally {
               threadLocals.setFutureListenerStackDepth(stackDepth);
            }

            return;
         }
      }

      safeExecute(executor, new Runnable() {
         public void run() {
            DefaultPromise.notifyListener0(future, listener);
         }
      });
   }

   private void notifyListenersNow() {
      GenericFutureListener listener;
      DefaultFutureListeners listeners;
      synchronized(this) {
         listener = this.listener;
         listeners = this.listeners;
         if (this.notifyingListeners || listener == null && listeners == null) {
            return;
         }

         this.notifyingListeners = true;
         if (listener != null) {
            this.listener = null;
         } else {
            this.listeners = null;
         }
      }

      while(true) {
         if (listener != null) {
            notifyListener0(this, listener);
         } else {
            this.notifyListeners0(listeners);
         }

         synchronized(this) {
            if (this.listener == null && this.listeners == null) {
               this.notifyingListeners = false;
               return;
            }

            listener = this.listener;
            listeners = this.listeners;
            if (listener != null) {
               this.listener = null;
            } else {
               this.listeners = null;
            }
         }
      }
   }

   private void notifyListeners0(DefaultFutureListeners listeners) {
      GenericFutureListener<?>[] a = listeners.listeners();
      int size = listeners.size();

      for(int i = 0; i < size; ++i) {
         notifyListener0(this, a[i]);
      }

   }

   private static void notifyListener0(Future future, GenericFutureListener l) {
      try {
         l.operationComplete(future);
      } catch (Throwable t) {
         if (logger.isWarnEnabled()) {
            logger.warn("An exception was thrown by " + l.getClass().getName() + ".operationComplete()", t);
         }
      }

   }

   private void addListener0(GenericFutureListener listener) {
      if (this.listener == null) {
         if (this.listeners == null) {
            this.listener = listener;
         } else {
            this.listeners.add(listener);
         }
      } else {
         assert this.listeners == null;

         this.listeners = new DefaultFutureListeners(this.listener, listener);
         this.listener = null;
      }

   }

   private void removeListener0(GenericFutureListener toRemove) {
      if (this.listener == toRemove) {
         this.listener = null;
      } else if (this.listeners != null) {
         this.listeners.remove(toRemove);
         if (this.listeners.size() == 0) {
            this.listeners = null;
         }
      }

   }

   private boolean setSuccess0(Object result) {
      return this.setValue0(result == null ? SUCCESS : result);
   }

   private boolean setFailure0(Throwable cause) {
      return this.setValue0(new CauseHolder((Throwable)ObjectUtil.checkNotNull(cause, "cause")));
   }

   private boolean setValue0(Object objResult) {
      if (!RESULT_UPDATER.compareAndSet(this, (Object)null, objResult) && !RESULT_UPDATER.compareAndSet(this, UNCANCELLABLE, objResult)) {
         return false;
      } else {
         if (this.checkNotifyWaiters()) {
            this.notifyListeners();
         }

         return true;
      }
   }

   private synchronized boolean checkNotifyWaiters() {
      if (this.waiters > 0) {
         this.notifyAll();
      }

      return this.listener != null || this.listeners != null;
   }

   private void incWaiters() {
      if (this.waiters == 32767) {
         throw new IllegalStateException("too many waiters: " + this);
      } else {
         ++this.waiters;
      }
   }

   private void decWaiters() {
      --this.waiters;
   }

   private void rethrowIfFailed() {
      Throwable cause = this.cause();
      if (cause != null) {
         PlatformDependent.throwException(cause);
      }
   }

   private boolean await0(long timeoutNanos, boolean interruptable) throws InterruptedException {
      if (this.isDone()) {
         return true;
      } else if (timeoutNanos <= 0L) {
         return this.isDone();
      } else if (interruptable && Thread.interrupted()) {
         throw new InterruptedException(this.toString());
      } else {
         this.checkDeadLock();
         long startTime = System.nanoTime();
         synchronized(this) {
            boolean interrupted = false;

            try {
               for(long waitTime = timeoutNanos; !this.isDone() && waitTime > 0L; waitTime = timeoutNanos - (System.nanoTime() - startTime)) {
                  this.incWaiters();

                  try {
                     this.wait(waitTime / 1000000L, (int)(waitTime % 1000000L));
                  } catch (InterruptedException var22) {
                     InterruptedException e = var22;
                     if (interruptable) {
                        throw var22;
                     }

                     interrupted = true;
                  } finally {
                     this.decWaiters();
                  }

                  if (this.isDone()) {
                     boolean var26 = true;
                     return var26;
                  }
               }

               boolean var27 = this.isDone();
               return var27;
            } finally {
               if (interrupted) {
                  Thread.currentThread().interrupt();
               }

            }
         }
      }
   }

   void notifyProgressiveListeners(final long progress, final long total) {
      Object listeners = this.progressiveListeners();
      if (listeners != null) {
         final ProgressiveFuture<V> self = (ProgressiveFuture)this;
         EventExecutor executor = this.executor();
         if (executor.inEventLoop()) {
            if (listeners instanceof GenericProgressiveFutureListener[]) {
               notifyProgressiveListeners0(self, (GenericProgressiveFutureListener[])listeners, progress, total);
            } else {
               notifyProgressiveListener0(self, (GenericProgressiveFutureListener)listeners, progress, total);
            }
         } else if (listeners instanceof GenericProgressiveFutureListener[]) {
            final GenericProgressiveFutureListener<?>[] array = (GenericProgressiveFutureListener[])listeners;
            safeExecute(executor, new Runnable() {
               public void run() {
                  DefaultPromise.notifyProgressiveListeners0(self, array, progress, total);
               }
            });
         } else {
            final GenericProgressiveFutureListener<ProgressiveFuture<V>> l = (GenericProgressiveFutureListener)listeners;
            safeExecute(executor, new Runnable() {
               public void run() {
                  DefaultPromise.notifyProgressiveListener0(self, l, progress, total);
               }
            });
         }

      }
   }

   private synchronized Object progressiveListeners() {
      GenericFutureListener listener = this.listener;
      DefaultFutureListeners listeners = this.listeners;
      if (listener == null && listeners == null) {
         return null;
      } else if (listeners != null) {
         int progressiveSize = listeners.progressiveSize();
         switch (progressiveSize) {
            case 0:
               return null;
            case 1:
               for(GenericFutureListener l : listeners.listeners()) {
                  if (l instanceof GenericProgressiveFutureListener) {
                     return l;
                  }
               }

               return null;
            default:
               GenericFutureListener<?>[] array = listeners.listeners();
               GenericProgressiveFutureListener<?>[] copy = new GenericProgressiveFutureListener[progressiveSize];
               int i = 0;

               for(int j = 0; j < progressiveSize; ++i) {
                  GenericFutureListener<?> l = array[i];
                  if (l instanceof GenericProgressiveFutureListener) {
                     copy[j++] = (GenericProgressiveFutureListener)l;
                  }
               }

               return copy;
         }
      } else {
         return listener instanceof GenericProgressiveFutureListener ? listener : null;
      }
   }

   private static void notifyProgressiveListeners0(ProgressiveFuture future, GenericProgressiveFutureListener[] listeners, long progress, long total) {
      for(GenericProgressiveFutureListener l : listeners) {
         if (l == null) {
            break;
         }

         notifyProgressiveListener0(future, l, progress, total);
      }

   }

   private static void notifyProgressiveListener0(ProgressiveFuture future, GenericProgressiveFutureListener l, long progress, long total) {
      try {
         l.operationProgressed(future, progress, total);
      } catch (Throwable t) {
         if (logger.isWarnEnabled()) {
            logger.warn("An exception was thrown by " + l.getClass().getName() + ".operationProgressed()", t);
         }
      }

   }

   private static boolean isCancelled0(Object result) {
      return result instanceof CauseHolder && ((CauseHolder)result).cause instanceof CancellationException;
   }

   private static boolean isDone0(Object result) {
      return result != null && result != UNCANCELLABLE;
   }

   private static void safeExecute(EventExecutor executor, Runnable task) {
      try {
         executor.execute(task);
      } catch (Throwable t) {
         rejectedExecutionLogger.error("Failed to submit a listener notification task. Event loop shut down?", t);
      }

   }

   static {
      CANCELLATION_STACK = CANCELLATION_CAUSE_HOLDER.cause.getStackTrace();
   }

   private static final class LeanCancellationException extends CancellationException {
      private static final long serialVersionUID = 2794674970981187807L;

      private LeanCancellationException() {
      }

      public Throwable fillInStackTrace() {
         this.setStackTrace(DefaultPromise.CANCELLATION_STACK);
         return this;
      }

      public String toString() {
         return CancellationException.class.getName();
      }
   }

   private static final class CauseHolder {
      final Throwable cause;

      CauseHolder(Throwable cause) {
         this.cause = cause;
      }
   }

   private static final class StacklessCancellationException extends CancellationException {
      private static final long serialVersionUID = -2974906711413716191L;

      public Throwable fillInStackTrace() {
         return this;
      }

      static StacklessCancellationException newInstance(Class clazz, String method) {
         return (StacklessCancellationException)ThrowableUtil.unknownStackTrace(new StacklessCancellationException(), clazz, method);
      }
   }
}
