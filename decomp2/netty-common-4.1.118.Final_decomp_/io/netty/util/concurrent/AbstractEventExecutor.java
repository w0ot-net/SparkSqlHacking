package io.netty.util.concurrent;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.Async.Execute;

public abstract class AbstractEventExecutor extends AbstractExecutorService implements EventExecutor {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(AbstractEventExecutor.class);
   static final long DEFAULT_SHUTDOWN_QUIET_PERIOD = 2L;
   static final long DEFAULT_SHUTDOWN_TIMEOUT = 15L;
   private final EventExecutorGroup parent;
   private final Collection selfCollection;

   protected AbstractEventExecutor() {
      this((EventExecutorGroup)null);
   }

   protected AbstractEventExecutor(EventExecutorGroup parent) {
      this.selfCollection = Collections.singleton(this);
      this.parent = parent;
   }

   public EventExecutorGroup parent() {
      return this.parent;
   }

   public EventExecutor next() {
      return this;
   }

   public boolean inEventLoop() {
      return this.inEventLoop(Thread.currentThread());
   }

   public Iterator iterator() {
      return this.selfCollection.iterator();
   }

   public Future shutdownGracefully() {
      return this.shutdownGracefully(2L, 15L, TimeUnit.SECONDS);
   }

   /** @deprecated */
   @Deprecated
   public abstract void shutdown();

   /** @deprecated */
   @Deprecated
   public List shutdownNow() {
      this.shutdown();
      return Collections.emptyList();
   }

   public Promise newPromise() {
      return new DefaultPromise(this);
   }

   public ProgressivePromise newProgressivePromise() {
      return new DefaultProgressivePromise(this);
   }

   public Future newSucceededFuture(Object result) {
      return new SucceededFuture(this, result);
   }

   public Future newFailedFuture(Throwable cause) {
      return new FailedFuture(this, cause);
   }

   public Future submit(Runnable task) {
      return (Future)super.submit(task);
   }

   public Future submit(Runnable task, Object result) {
      return (Future)super.submit(task, result);
   }

   public Future submit(Callable task) {
      return (Future)super.submit(task);
   }

   protected final RunnableFuture newTaskFor(Runnable runnable, Object value) {
      return new PromiseTask(this, runnable, value);
   }

   protected final RunnableFuture newTaskFor(Callable callable) {
      return new PromiseTask(this, callable);
   }

   public ScheduledFuture schedule(Runnable command, long delay, TimeUnit unit) {
      throw new UnsupportedOperationException();
   }

   public ScheduledFuture schedule(Callable callable, long delay, TimeUnit unit) {
      throw new UnsupportedOperationException();
   }

   public ScheduledFuture scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
      throw new UnsupportedOperationException();
   }

   public ScheduledFuture scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
      throw new UnsupportedOperationException();
   }

   protected static void safeExecute(Runnable task) {
      try {
         runTask(task);
      } catch (Throwable t) {
         logger.warn("A task raised an exception. Task: {}", task, t);
      }

   }

   protected static void runTask(@Execute Runnable task) {
      task.run();
   }

   public void lazyExecute(Runnable task) {
      this.execute(task);
   }

   /** @deprecated */
   @Deprecated
   public interface LazyRunnable extends Runnable {
   }
}
