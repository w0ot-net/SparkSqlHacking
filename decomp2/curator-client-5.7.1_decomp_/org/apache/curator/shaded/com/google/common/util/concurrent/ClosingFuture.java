package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.io.Closeable;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Functions;
import org.apache.curator.shaded.com.google.common.base.MoreObjects;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.FluentIterable;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;
import org.apache.curator.shaded.com.google.j2objc.annotations.RetainedWith;

@DoNotMock("Use ClosingFuture.from(Futures.immediate*Future)")
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
public final class ClosingFuture {
   private static final Logger logger = Logger.getLogger(ClosingFuture.class.getName());
   private final AtomicReference state;
   private final CloseableList closeables;
   private final FluentFuture future;

   public static ClosingFuture submit(ClosingCallable callable, Executor executor) {
      return new ClosingFuture(callable, executor);
   }

   public static ClosingFuture submitAsync(AsyncClosingCallable callable, Executor executor) {
      return new ClosingFuture(callable, executor);
   }

   public static ClosingFuture from(ListenableFuture future) {
      return new ClosingFuture(future);
   }

   /** @deprecated */
   @Deprecated
   public static ClosingFuture eventuallyClosing(ListenableFuture future, final Executor closingExecutor) {
      Preconditions.checkNotNull(closingExecutor);
      final ClosingFuture<C> closingFuture = new ClosingFuture(Futures.nonCancellationPropagating(future));
      Futures.addCallback(future, new FutureCallback() {
         public void onSuccess(@CheckForNull AutoCloseable result) {
            closingFuture.closeables.closer.eventuallyClose(result, closingExecutor);
         }

         public void onFailure(Throwable t) {
         }
      }, MoreExecutors.directExecutor());
      return closingFuture;
   }

   public static Combiner whenAllComplete(Iterable futures) {
      return new Combiner(false, futures);
   }

   public static Combiner whenAllComplete(ClosingFuture future1, ClosingFuture... moreFutures) {
      return whenAllComplete(Lists.asList(future1, moreFutures));
   }

   public static Combiner whenAllSucceed(Iterable futures) {
      return new Combiner(true, futures);
   }

   public static Combiner2 whenAllSucceed(ClosingFuture future1, ClosingFuture future2) {
      return new Combiner2(future1, future2);
   }

   public static Combiner3 whenAllSucceed(ClosingFuture future1, ClosingFuture future2, ClosingFuture future3) {
      return new Combiner3(future1, future2, future3);
   }

   public static Combiner4 whenAllSucceed(ClosingFuture future1, ClosingFuture future2, ClosingFuture future3, ClosingFuture future4) {
      return new Combiner4(future1, future2, future3, future4);
   }

   public static Combiner5 whenAllSucceed(ClosingFuture future1, ClosingFuture future2, ClosingFuture future3, ClosingFuture future4, ClosingFuture future5) {
      return new Combiner5(future1, future2, future3, future4, future5);
   }

   public static Combiner whenAllSucceed(ClosingFuture future1, ClosingFuture future2, ClosingFuture future3, ClosingFuture future4, ClosingFuture future5, ClosingFuture future6, ClosingFuture... moreFutures) {
      return whenAllSucceed(FluentIterable.of(future1, future2, future3, future4, future5, future6).append((Object[])moreFutures));
   }

   private ClosingFuture(ListenableFuture future) {
      this.state = new AtomicReference(ClosingFuture.State.OPEN);
      this.closeables = new CloseableList();
      this.future = FluentFuture.from(future);
   }

   private ClosingFuture(final ClosingCallable callable, Executor executor) {
      this.state = new AtomicReference(ClosingFuture.State.OPEN);
      this.closeables = new CloseableList();
      Preconditions.checkNotNull(callable);
      TrustedListenableFutureTask<V> task = TrustedListenableFutureTask.create(new Callable() {
         @ParametricNullness
         public Object call() throws Exception {
            return callable.call(ClosingFuture.this.closeables.closer);
         }

         public String toString() {
            return callable.toString();
         }
      });
      executor.execute(task);
      this.future = task;
   }

   private ClosingFuture(final AsyncClosingCallable callable, Executor executor) {
      this.state = new AtomicReference(ClosingFuture.State.OPEN);
      this.closeables = new CloseableList();
      Preconditions.checkNotNull(callable);
      TrustedListenableFutureTask<V> task = TrustedListenableFutureTask.create(new AsyncCallable() {
         public ListenableFuture call() throws Exception {
            CloseableList newCloseables = new CloseableList();

            FluentFuture var3;
            try {
               ClosingFuture<V> closingFuture = callable.call(newCloseables.closer);
               closingFuture.becomeSubsumedInto(ClosingFuture.this.closeables);
               var3 = closingFuture.future;
            } finally {
               ClosingFuture.this.closeables.add(newCloseables, MoreExecutors.directExecutor());
            }

            return var3;
         }

         public String toString() {
            return callable.toString();
         }
      });
      executor.execute(task);
      this.future = task;
   }

   public ListenableFuture statusFuture() {
      return Futures.nonCancellationPropagating(this.future.transform(Functions.constant((Object)null), MoreExecutors.directExecutor()));
   }

   public ClosingFuture transform(final ClosingFunction function, Executor executor) {
      Preconditions.checkNotNull(function);
      AsyncFunction<V, U> applyFunction = new AsyncFunction() {
         public ListenableFuture apply(Object input) throws Exception {
            return ClosingFuture.this.closeables.applyClosingFunction(function, input);
         }

         public String toString() {
            return function.toString();
         }
      };
      return this.derive(this.future.transformAsync(applyFunction, executor));
   }

   public ClosingFuture transformAsync(final AsyncClosingFunction function, Executor executor) {
      Preconditions.checkNotNull(function);
      AsyncFunction<V, U> applyFunction = new AsyncFunction() {
         public ListenableFuture apply(Object input) throws Exception {
            return ClosingFuture.this.closeables.applyAsyncClosingFunction(function, input);
         }

         public String toString() {
            return function.toString();
         }
      };
      return this.derive(this.future.transformAsync(applyFunction, executor));
   }

   public static AsyncClosingFunction withoutCloser(final AsyncFunction function) {
      Preconditions.checkNotNull(function);
      return new AsyncClosingFunction() {
         public ClosingFuture apply(DeferredCloser closer, Object input) throws Exception {
            return ClosingFuture.from(function.apply(input));
         }
      };
   }

   public ClosingFuture catching(Class exceptionType, ClosingFunction fallback, Executor executor) {
      return this.catchingMoreGeneric(exceptionType, fallback, executor);
   }

   private ClosingFuture catchingMoreGeneric(Class exceptionType, final ClosingFunction fallback, Executor executor) {
      Preconditions.checkNotNull(fallback);
      AsyncFunction<X, W> applyFallback = new AsyncFunction() {
         public ListenableFuture apply(Throwable exception) throws Exception {
            return ClosingFuture.this.closeables.applyClosingFunction(fallback, exception);
         }

         public String toString() {
            return fallback.toString();
         }
      };
      return this.derive(this.future.catchingAsync(exceptionType, applyFallback, executor));
   }

   public ClosingFuture catchingAsync(Class exceptionType, AsyncClosingFunction fallback, Executor executor) {
      return this.catchingAsyncMoreGeneric(exceptionType, fallback, executor);
   }

   private ClosingFuture catchingAsyncMoreGeneric(Class exceptionType, final AsyncClosingFunction fallback, Executor executor) {
      Preconditions.checkNotNull(fallback);
      AsyncFunction<X, W> asyncFunction = new AsyncFunction() {
         public ListenableFuture apply(Throwable exception) throws Exception {
            return ClosingFuture.this.closeables.applyAsyncClosingFunction(fallback, exception);
         }

         public String toString() {
            return fallback.toString();
         }
      };
      return this.derive(this.future.catchingAsync(exceptionType, asyncFunction, executor));
   }

   public FluentFuture finishToFuture() {
      if (this.compareAndUpdateState(ClosingFuture.State.OPEN, ClosingFuture.State.WILL_CLOSE)) {
         logger.log(Level.FINER, "will close {0}", this);
         this.future.addListener(new Runnable() {
            public void run() {
               ClosingFuture.this.checkAndUpdateState(ClosingFuture.State.WILL_CLOSE, ClosingFuture.State.CLOSING);
               ClosingFuture.this.close();
               ClosingFuture.this.checkAndUpdateState(ClosingFuture.State.CLOSING, ClosingFuture.State.CLOSED);
            }
         }, MoreExecutors.directExecutor());
      } else {
         switch ((State)this.state.get()) {
            case SUBSUMED:
               throw new IllegalStateException("Cannot call finishToFuture() after deriving another step");
            case WILL_CREATE_VALUE_AND_CLOSER:
               throw new IllegalStateException("Cannot call finishToFuture() after calling finishToValueAndCloser()");
            case WILL_CLOSE:
            case CLOSING:
            case CLOSED:
               throw new IllegalStateException("Cannot call finishToFuture() twice");
            case OPEN:
               throw new AssertionError();
         }
      }

      return this.future;
   }

   public void finishToValueAndCloser(final ValueAndCloserConsumer consumer, Executor executor) {
      Preconditions.checkNotNull(consumer);
      if (!this.compareAndUpdateState(ClosingFuture.State.OPEN, ClosingFuture.State.WILL_CREATE_VALUE_AND_CLOSER)) {
         switch ((State)this.state.get()) {
            case SUBSUMED:
               throw new IllegalStateException("Cannot call finishToValueAndCloser() after deriving another step");
            case WILL_CREATE_VALUE_AND_CLOSER:
               throw new IllegalStateException("Cannot call finishToValueAndCloser() twice");
            case WILL_CLOSE:
            case CLOSING:
            case CLOSED:
               throw new IllegalStateException("Cannot call finishToValueAndCloser() after calling finishToFuture()");
            case OPEN:
            default:
               throw new AssertionError(this.state);
         }
      } else {
         this.future.addListener(new Runnable() {
            public void run() {
               ClosingFuture.provideValueAndCloser(consumer, ClosingFuture.this);
            }
         }, executor);
      }
   }

   private static void provideValueAndCloser(ValueAndCloserConsumer consumer, ClosingFuture closingFuture) {
      consumer.accept(new ValueAndCloser(closingFuture));
   }

   @CanIgnoreReturnValue
   public boolean cancel(boolean mayInterruptIfRunning) {
      logger.log(Level.FINER, "cancelling {0}", this);
      boolean cancelled = this.future.cancel(mayInterruptIfRunning);
      if (cancelled) {
         this.close();
      }

      return cancelled;
   }

   private void close() {
      logger.log(Level.FINER, "closing {0}", this);
      this.closeables.close();
   }

   private ClosingFuture derive(FluentFuture future) {
      ClosingFuture<U> derived = new ClosingFuture(future);
      this.becomeSubsumedInto(derived.closeables);
      return derived;
   }

   private void becomeSubsumedInto(CloseableList otherCloseables) {
      this.checkAndUpdateState(ClosingFuture.State.OPEN, ClosingFuture.State.SUBSUMED);
      otherCloseables.add(this.closeables, MoreExecutors.directExecutor());
   }

   public String toString() {
      return MoreObjects.toStringHelper((Object)this).add("state", this.state.get()).addValue(this.future).toString();
   }

   protected void finalize() {
      if (((State)this.state.get()).equals(ClosingFuture.State.OPEN)) {
         logger.log(Level.SEVERE, "Uh oh! An open ClosingFuture has leaked and will close: {0}", this);
         FluentFuture var1 = this.finishToFuture();
      }

   }

   private static void closeQuietly(@CheckForNull final AutoCloseable closeable, Executor executor) {
      if (closeable != null) {
         try {
            executor.execute(() -> {
               try {
                  closeable.close();
               } catch (Exception e) {
                  Platform.restoreInterruptIfIsInterruptedException(e);
                  logger.log(Level.WARNING, "thrown by close()", e);
               }

            });
         } catch (RejectedExecutionException e) {
            if (logger.isLoggable(Level.WARNING)) {
               logger.log(Level.WARNING, String.format("while submitting close to %s; will close inline", executor), e);
            }

            closeQuietly(closeable, MoreExecutors.directExecutor());
         }

      }
   }

   private void checkAndUpdateState(State oldState, State newState) {
      Preconditions.checkState(this.compareAndUpdateState(oldState, newState), "Expected state to be %s, but it was %s", oldState, newState);
   }

   private boolean compareAndUpdateState(State oldState, State newState) {
      return this.state.compareAndSet(oldState, newState);
   }

   @VisibleForTesting
   CountDownLatch whenClosedCountDown() {
      return this.closeables.whenClosedCountDown();
   }

   public static final class DeferredCloser {
      @RetainedWith
      private final CloseableList list;

      DeferredCloser(CloseableList list) {
         this.list = list;
      }

      @ParametricNullness
      @CanIgnoreReturnValue
      public Object eventuallyClose(@ParametricNullness Object closeable, Executor closingExecutor) {
         Preconditions.checkNotNull(closingExecutor);
         if (closeable != null) {
            this.list.add((AutoCloseable)closeable, closingExecutor);
         }

         return closeable;
      }
   }

   public static final class ValueAndCloser {
      private final ClosingFuture closingFuture;

      ValueAndCloser(ClosingFuture closingFuture) {
         this.closingFuture = (ClosingFuture)Preconditions.checkNotNull(closingFuture);
      }

      @ParametricNullness
      public Object get() throws ExecutionException {
         return Futures.getDone(this.closingFuture.future);
      }

      public void closeAsync() {
         this.closingFuture.close();
      }
   }

   public static final class Peeker {
      private final ImmutableList futures;
      private volatile boolean beingCalled;

      private Peeker(ImmutableList futures) {
         this.futures = (ImmutableList)Preconditions.checkNotNull(futures);
      }

      @ParametricNullness
      public final Object getDone(ClosingFuture closingFuture) throws ExecutionException {
         Preconditions.checkState(this.beingCalled);
         Preconditions.checkArgument(this.futures.contains(closingFuture));
         return Futures.getDone(closingFuture.future);
      }

      @ParametricNullness
      private Object call(Combiner.CombiningCallable combiner, CloseableList closeables) throws Exception {
         this.beingCalled = true;
         CloseableList newCloseables = new CloseableList();

         Object var4;
         try {
            var4 = combiner.call(newCloseables.closer, this);
         } finally {
            closeables.add(newCloseables, MoreExecutors.directExecutor());
            this.beingCalled = false;
         }

         return var4;
      }

      private FluentFuture callAsync(Combiner.AsyncCombiningCallable combiner, CloseableList closeables) throws Exception {
         this.beingCalled = true;
         CloseableList newCloseables = new CloseableList();

         FluentFuture var5;
         try {
            ClosingFuture<V> closingFuture = combiner.call(newCloseables.closer, this);
            closingFuture.becomeSubsumedInto(closeables);
            var5 = closingFuture.future;
         } finally {
            closeables.add(newCloseables, MoreExecutors.directExecutor());
            this.beingCalled = false;
         }

         return var5;
      }
   }

   @DoNotMock("Use ClosingFuture.whenAllSucceed() or .whenAllComplete() instead.")
   public static class Combiner {
      private final CloseableList closeables;
      private final boolean allMustSucceed;
      protected final ImmutableList inputs;

      private Combiner(boolean allMustSucceed, Iterable inputs) {
         this.closeables = new CloseableList();
         this.allMustSucceed = allMustSucceed;
         this.inputs = ImmutableList.copyOf(inputs);

         for(ClosingFuture input : inputs) {
            input.becomeSubsumedInto(this.closeables);
         }

      }

      public ClosingFuture call(final CombiningCallable combiningCallable, Executor executor) {
         Callable<V> callable = new Callable() {
            @ParametricNullness
            public Object call() throws Exception {
               return (new Peeker(Combiner.this.inputs)).call(combiningCallable, Combiner.this.closeables);
            }

            public String toString() {
               return combiningCallable.toString();
            }
         };
         ClosingFuture<V> derived = new ClosingFuture(this.futureCombiner().call(callable, executor));
         derived.closeables.add(this.closeables, MoreExecutors.directExecutor());
         return derived;
      }

      public ClosingFuture callAsync(final AsyncCombiningCallable combiningCallable, Executor executor) {
         AsyncCallable<V> asyncCallable = new AsyncCallable() {
            public ListenableFuture call() throws Exception {
               return (new Peeker(Combiner.this.inputs)).callAsync(combiningCallable, Combiner.this.closeables);
            }

            public String toString() {
               return combiningCallable.toString();
            }
         };
         ClosingFuture<V> derived = new ClosingFuture(this.futureCombiner().callAsync(asyncCallable, executor));
         derived.closeables.add(this.closeables, MoreExecutors.directExecutor());
         return derived;
      }

      private Futures.FutureCombiner futureCombiner() {
         return this.allMustSucceed ? Futures.whenAllSucceed((Iterable)this.inputFutures()) : Futures.whenAllComplete((Iterable)this.inputFutures());
      }

      private ImmutableList inputFutures() {
         return FluentIterable.from((Iterable)this.inputs).transform((future) -> future.future).toList();
      }

      @FunctionalInterface
      public interface AsyncCombiningCallable {
         ClosingFuture call(DeferredCloser closer, Peeker peeker) throws Exception;
      }

      @FunctionalInterface
      public interface CombiningCallable {
         @ParametricNullness
         Object call(DeferredCloser closer, Peeker peeker) throws Exception;
      }
   }

   public static final class Combiner2 extends Combiner {
      private final ClosingFuture future1;
      private final ClosingFuture future2;

      private Combiner2(ClosingFuture future1, ClosingFuture future2) {
         super(true, ImmutableList.of(future1, future2), null);
         this.future1 = future1;
         this.future2 = future2;
      }

      public ClosingFuture call(final ClosingFunction2 function, Executor executor) {
         return this.call(new Combiner.CombiningCallable() {
            @ParametricNullness
            public Object call(DeferredCloser closer, Peeker peeker) throws Exception {
               return function.apply(closer, peeker.getDone(Combiner2.this.future1), peeker.getDone(Combiner2.this.future2));
            }

            public String toString() {
               return function.toString();
            }
         }, executor);
      }

      public ClosingFuture callAsync(final AsyncClosingFunction2 function, Executor executor) {
         return this.callAsync(new Combiner.AsyncCombiningCallable() {
            public ClosingFuture call(DeferredCloser closer, Peeker peeker) throws Exception {
               return function.apply(closer, peeker.getDone(Combiner2.this.future1), peeker.getDone(Combiner2.this.future2));
            }

            public String toString() {
               return function.toString();
            }
         }, executor);
      }

      @FunctionalInterface
      public interface AsyncClosingFunction2 {
         ClosingFuture apply(DeferredCloser closer, @ParametricNullness Object value1, @ParametricNullness Object value2) throws Exception;
      }

      @FunctionalInterface
      public interface ClosingFunction2 {
         @ParametricNullness
         Object apply(DeferredCloser closer, @ParametricNullness Object value1, @ParametricNullness Object value2) throws Exception;
      }
   }

   public static final class Combiner3 extends Combiner {
      private final ClosingFuture future1;
      private final ClosingFuture future2;
      private final ClosingFuture future3;

      private Combiner3(ClosingFuture future1, ClosingFuture future2, ClosingFuture future3) {
         super(true, ImmutableList.of(future1, future2, future3), null);
         this.future1 = future1;
         this.future2 = future2;
         this.future3 = future3;
      }

      public ClosingFuture call(final ClosingFunction3 function, Executor executor) {
         return this.call(new Combiner.CombiningCallable() {
            @ParametricNullness
            public Object call(DeferredCloser closer, Peeker peeker) throws Exception {
               return function.apply(closer, peeker.getDone(Combiner3.this.future1), peeker.getDone(Combiner3.this.future2), peeker.getDone(Combiner3.this.future3));
            }

            public String toString() {
               return function.toString();
            }
         }, executor);
      }

      public ClosingFuture callAsync(final AsyncClosingFunction3 function, Executor executor) {
         return this.callAsync(new Combiner.AsyncCombiningCallable() {
            public ClosingFuture call(DeferredCloser closer, Peeker peeker) throws Exception {
               return function.apply(closer, peeker.getDone(Combiner3.this.future1), peeker.getDone(Combiner3.this.future2), peeker.getDone(Combiner3.this.future3));
            }

            public String toString() {
               return function.toString();
            }
         }, executor);
      }

      @FunctionalInterface
      public interface AsyncClosingFunction3 {
         ClosingFuture apply(DeferredCloser closer, @ParametricNullness Object value1, @ParametricNullness Object value2, @ParametricNullness Object value3) throws Exception;
      }

      @FunctionalInterface
      public interface ClosingFunction3 {
         @ParametricNullness
         Object apply(DeferredCloser closer, @ParametricNullness Object value1, @ParametricNullness Object value2, @ParametricNullness Object value3) throws Exception;
      }
   }

   public static final class Combiner4 extends Combiner {
      private final ClosingFuture future1;
      private final ClosingFuture future2;
      private final ClosingFuture future3;
      private final ClosingFuture future4;

      private Combiner4(ClosingFuture future1, ClosingFuture future2, ClosingFuture future3, ClosingFuture future4) {
         super(true, ImmutableList.of(future1, future2, future3, future4), null);
         this.future1 = future1;
         this.future2 = future2;
         this.future3 = future3;
         this.future4 = future4;
      }

      public ClosingFuture call(final ClosingFunction4 function, Executor executor) {
         return this.call(new Combiner.CombiningCallable() {
            @ParametricNullness
            public Object call(DeferredCloser closer, Peeker peeker) throws Exception {
               return function.apply(closer, peeker.getDone(Combiner4.this.future1), peeker.getDone(Combiner4.this.future2), peeker.getDone(Combiner4.this.future3), peeker.getDone(Combiner4.this.future4));
            }

            public String toString() {
               return function.toString();
            }
         }, executor);
      }

      public ClosingFuture callAsync(final AsyncClosingFunction4 function, Executor executor) {
         return this.callAsync(new Combiner.AsyncCombiningCallable() {
            public ClosingFuture call(DeferredCloser closer, Peeker peeker) throws Exception {
               return function.apply(closer, peeker.getDone(Combiner4.this.future1), peeker.getDone(Combiner4.this.future2), peeker.getDone(Combiner4.this.future3), peeker.getDone(Combiner4.this.future4));
            }

            public String toString() {
               return function.toString();
            }
         }, executor);
      }

      @FunctionalInterface
      public interface AsyncClosingFunction4 {
         ClosingFuture apply(DeferredCloser closer, @ParametricNullness Object value1, @ParametricNullness Object value2, @ParametricNullness Object value3, @ParametricNullness Object value4) throws Exception;
      }

      @FunctionalInterface
      public interface ClosingFunction4 {
         @ParametricNullness
         Object apply(DeferredCloser closer, @ParametricNullness Object value1, @ParametricNullness Object value2, @ParametricNullness Object value3, @ParametricNullness Object value4) throws Exception;
      }
   }

   public static final class Combiner5 extends Combiner {
      private final ClosingFuture future1;
      private final ClosingFuture future2;
      private final ClosingFuture future3;
      private final ClosingFuture future4;
      private final ClosingFuture future5;

      private Combiner5(ClosingFuture future1, ClosingFuture future2, ClosingFuture future3, ClosingFuture future4, ClosingFuture future5) {
         super(true, ImmutableList.of(future1, future2, future3, future4, future5), null);
         this.future1 = future1;
         this.future2 = future2;
         this.future3 = future3;
         this.future4 = future4;
         this.future5 = future5;
      }

      public ClosingFuture call(final ClosingFunction5 function, Executor executor) {
         return this.call(new Combiner.CombiningCallable() {
            @ParametricNullness
            public Object call(DeferredCloser closer, Peeker peeker) throws Exception {
               return function.apply(closer, peeker.getDone(Combiner5.this.future1), peeker.getDone(Combiner5.this.future2), peeker.getDone(Combiner5.this.future3), peeker.getDone(Combiner5.this.future4), peeker.getDone(Combiner5.this.future5));
            }

            public String toString() {
               return function.toString();
            }
         }, executor);
      }

      public ClosingFuture callAsync(final AsyncClosingFunction5 function, Executor executor) {
         return this.callAsync(new Combiner.AsyncCombiningCallable() {
            public ClosingFuture call(DeferredCloser closer, Peeker peeker) throws Exception {
               return function.apply(closer, peeker.getDone(Combiner5.this.future1), peeker.getDone(Combiner5.this.future2), peeker.getDone(Combiner5.this.future3), peeker.getDone(Combiner5.this.future4), peeker.getDone(Combiner5.this.future5));
            }

            public String toString() {
               return function.toString();
            }
         }, executor);
      }

      @FunctionalInterface
      public interface AsyncClosingFunction5 {
         ClosingFuture apply(DeferredCloser closer, @ParametricNullness Object value1, @ParametricNullness Object value2, @ParametricNullness Object value3, @ParametricNullness Object value4, @ParametricNullness Object value5) throws Exception;
      }

      @FunctionalInterface
      public interface ClosingFunction5 {
         @ParametricNullness
         Object apply(DeferredCloser closer, @ParametricNullness Object value1, @ParametricNullness Object value2, @ParametricNullness Object value3, @ParametricNullness Object value4, @ParametricNullness Object value5) throws Exception;
      }
   }

   private static final class CloseableList extends IdentityHashMap implements Closeable {
      private final DeferredCloser closer;
      private volatile boolean closed;
      @CheckForNull
      private volatile CountDownLatch whenClosed;

      private CloseableList() {
         this.closer = new DeferredCloser(this);
      }

      ListenableFuture applyClosingFunction(ClosingFunction transformation, @ParametricNullness Object input) throws Exception {
         CloseableList newCloseables = new CloseableList();

         ListenableFuture var4;
         try {
            var4 = Futures.immediateFuture(transformation.apply(newCloseables.closer, input));
         } finally {
            this.add(newCloseables, MoreExecutors.directExecutor());
         }

         return var4;
      }

      FluentFuture applyAsyncClosingFunction(AsyncClosingFunction transformation, @ParametricNullness Object input) throws Exception {
         CloseableList newCloseables = new CloseableList();

         FluentFuture var5;
         try {
            ClosingFuture<U> closingFuture = transformation.apply(newCloseables.closer, input);
            closingFuture.becomeSubsumedInto(newCloseables);
            var5 = closingFuture.future;
         } finally {
            this.add(newCloseables, MoreExecutors.directExecutor());
         }

         return var5;
      }

      public void close() {
         if (!this.closed) {
            synchronized(this) {
               if (this.closed) {
                  return;
               }

               this.closed = true;
            }

            for(Map.Entry entry : this.entrySet()) {
               ClosingFuture.closeQuietly((AutoCloseable)entry.getKey(), (Executor)entry.getValue());
            }

            this.clear();
            if (this.whenClosed != null) {
               this.whenClosed.countDown();
            }

         }
      }

      void add(@CheckForNull AutoCloseable closeable, Executor executor) {
         Preconditions.checkNotNull(executor);
         if (closeable != null) {
            synchronized(this) {
               if (!this.closed) {
                  this.put(closeable, executor);
                  return;
               }
            }

            ClosingFuture.closeQuietly(closeable, executor);
         }
      }

      CountDownLatch whenClosedCountDown() {
         if (this.closed) {
            return new CountDownLatch(0);
         } else {
            synchronized(this) {
               if (this.closed) {
                  return new CountDownLatch(0);
               } else {
                  Preconditions.checkState(this.whenClosed == null);
                  return this.whenClosed = new CountDownLatch(1);
               }
            }
         }
      }
   }

   static enum State {
      OPEN,
      SUBSUMED,
      WILL_CLOSE,
      CLOSING,
      CLOSED,
      WILL_CREATE_VALUE_AND_CLOSER;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{OPEN, SUBSUMED, WILL_CLOSE, CLOSING, CLOSED, WILL_CREATE_VALUE_AND_CLOSER};
      }
   }

   @FunctionalInterface
   public interface AsyncClosingCallable {
      ClosingFuture call(DeferredCloser closer) throws Exception;
   }

   @FunctionalInterface
   public interface AsyncClosingFunction {
      ClosingFuture apply(DeferredCloser closer, @ParametricNullness Object input) throws Exception;
   }

   @FunctionalInterface
   public interface ClosingCallable {
      @ParametricNullness
      Object call(DeferredCloser closer) throws Exception;
   }

   @FunctionalInterface
   public interface ClosingFunction {
      @ParametricNullness
      Object apply(DeferredCloser closer, @ParametricNullness Object input) throws Exception;
   }

   @FunctionalInterface
   public interface ValueAndCloserConsumer {
      void accept(ValueAndCloser valueAndCloser);
   }
}
