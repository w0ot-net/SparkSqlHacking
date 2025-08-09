package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Function;
import org.apache.curator.shaded.com.google.common.base.MoreObjects;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import org.apache.curator.shaded.com.google.common.util.concurrent.internal.InternalFutures;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class Futures extends GwtFuturesCatchingSpecialization {
   private Futures() {
   }

   public static ListenableFuture immediateFuture(@ParametricNullness Object value) {
      if (value == null) {
         ListenableFuture<V> typedNull = ImmediateFuture.NULL;
         return typedNull;
      } else {
         return new ImmediateFuture(value);
      }
   }

   public static ListenableFuture immediateVoidFuture() {
      return ImmediateFuture.NULL;
   }

   public static ListenableFuture immediateFailedFuture(Throwable throwable) {
      Preconditions.checkNotNull(throwable);
      return new ImmediateFuture.ImmediateFailedFuture(throwable);
   }

   public static ListenableFuture immediateCancelledFuture() {
      ListenableFuture<Object> instance = ImmediateFuture.ImmediateCancelledFuture.INSTANCE;
      return (ListenableFuture)(instance != null ? instance : new ImmediateFuture.ImmediateCancelledFuture());
   }

   public static ListenableFuture submit(Callable callable, Executor executor) {
      TrustedListenableFutureTask<O> task = TrustedListenableFutureTask.create(callable);
      executor.execute(task);
      return task;
   }

   public static ListenableFuture submit(Runnable runnable, Executor executor) {
      TrustedListenableFutureTask<Void> task = TrustedListenableFutureTask.create(runnable, (Object)null);
      executor.execute(task);
      return task;
   }

   public static ListenableFuture submitAsync(AsyncCallable callable, Executor executor) {
      TrustedListenableFutureTask<O> task = TrustedListenableFutureTask.create(callable);
      executor.execute(task);
      return task;
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static ListenableFuture scheduleAsync(AsyncCallable callable, Duration delay, ScheduledExecutorService executorService) {
      return scheduleAsync(callable, Internal.toNanosSaturated(delay), TimeUnit.NANOSECONDS, executorService);
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static ListenableFuture scheduleAsync(AsyncCallable callable, long delay, TimeUnit timeUnit, ScheduledExecutorService executorService) {
      TrustedListenableFutureTask<O> task = TrustedListenableFutureTask.create(callable);
      Future<?> scheduled = executorService.schedule(task, delay, timeUnit);
      task.addListener(() -> scheduled.cancel(false), MoreExecutors.directExecutor());
      return task;
   }

   @J2ktIncompatible
   @Partially.GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
   public static ListenableFuture catching(ListenableFuture input, Class exceptionType, Function fallback, Executor executor) {
      return AbstractCatchingFuture.create(input, exceptionType, fallback, executor);
   }

   @J2ktIncompatible
   @Partially.GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
   public static ListenableFuture catchingAsync(ListenableFuture input, Class exceptionType, AsyncFunction fallback, Executor executor) {
      return AbstractCatchingFuture.create(input, exceptionType, fallback, executor);
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static ListenableFuture withTimeout(ListenableFuture delegate, Duration time, ScheduledExecutorService scheduledExecutor) {
      return withTimeout(delegate, Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS, scheduledExecutor);
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static ListenableFuture withTimeout(ListenableFuture delegate, long time, TimeUnit unit, ScheduledExecutorService scheduledExecutor) {
      return delegate.isDone() ? delegate : TimeoutFuture.create(delegate, time, unit, scheduledExecutor);
   }

   public static ListenableFuture transformAsync(ListenableFuture input, AsyncFunction function, Executor executor) {
      return AbstractTransformFuture.create(input, function, executor);
   }

   public static ListenableFuture transform(ListenableFuture input, Function function, Executor executor) {
      return AbstractTransformFuture.create(input, function, executor);
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static Future lazyTransform(final Future input, final Function function) {
      Preconditions.checkNotNull(input);
      Preconditions.checkNotNull(function);
      return new Future() {
         public boolean cancel(boolean mayInterruptIfRunning) {
            return input.cancel(mayInterruptIfRunning);
         }

         public boolean isCancelled() {
            return input.isCancelled();
         }

         public boolean isDone() {
            return input.isDone();
         }

         public Object get() throws InterruptedException, ExecutionException {
            return this.applyTransformation(input.get());
         }

         public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return this.applyTransformation(input.get(timeout, unit));
         }

         private Object applyTransformation(Object inputx) throws ExecutionException {
            try {
               return function.apply(input);
            } catch (Error | RuntimeException t) {
               throw new ExecutionException(t);
            }
         }
      };
   }

   @SafeVarargs
   public static ListenableFuture allAsList(ListenableFuture... futures) {
      ListenableFuture<List<V>> nullable = new CollectionFuture.ListFuture(ImmutableList.copyOf((Object[])futures), true);
      return nullable;
   }

   public static ListenableFuture allAsList(Iterable futures) {
      ListenableFuture<List<V>> nullable = new CollectionFuture.ListFuture(ImmutableList.copyOf(futures), true);
      return nullable;
   }

   @SafeVarargs
   public static FutureCombiner whenAllComplete(ListenableFuture... futures) {
      return new FutureCombiner(false, ImmutableList.copyOf((Object[])futures));
   }

   public static FutureCombiner whenAllComplete(Iterable futures) {
      return new FutureCombiner(false, ImmutableList.copyOf(futures));
   }

   @SafeVarargs
   public static FutureCombiner whenAllSucceed(ListenableFuture... futures) {
      return new FutureCombiner(true, ImmutableList.copyOf((Object[])futures));
   }

   public static FutureCombiner whenAllSucceed(Iterable futures) {
      return new FutureCombiner(true, ImmutableList.copyOf(futures));
   }

   public static ListenableFuture nonCancellationPropagating(ListenableFuture future) {
      if (future.isDone()) {
         return future;
      } else {
         NonCancellationPropagatingFuture<V> output = new NonCancellationPropagatingFuture(future);
         future.addListener(output, MoreExecutors.directExecutor());
         return output;
      }
   }

   @SafeVarargs
   public static ListenableFuture successfulAsList(ListenableFuture... futures) {
      return new CollectionFuture.ListFuture(ImmutableList.copyOf((Object[])futures), false);
   }

   public static ListenableFuture successfulAsList(Iterable futures) {
      return new CollectionFuture.ListFuture(ImmutableList.copyOf(futures), false);
   }

   public static ImmutableList inCompletionOrder(Iterable futures) {
      ListenableFuture<? extends T>[] copy = gwtCompatibleToArray(futures);
      InCompletionOrderState<T> state = new InCompletionOrderState(copy);
      ImmutableList.Builder<AbstractFuture<T>> delegatesBuilder = ImmutableList.builderWithExpectedSize(copy.length);

      for(int i = 0; i < copy.length; ++i) {
         delegatesBuilder.add((Object)(new InCompletionOrderFuture(state)));
      }

      ImmutableList<AbstractFuture<T>> delegates = delegatesBuilder.build();

      for(int i = 0; i < copy.length; ++i) {
         copy[i].addListener(() -> state.recordInputCompletion(delegates, i), MoreExecutors.directExecutor());
      }

      return delegates;
   }

   private static ListenableFuture[] gwtCompatibleToArray(Iterable futures) {
      Collection<ListenableFuture<? extends T>> collection;
      if (futures instanceof Collection) {
         collection = (Collection)futures;
      } else {
         collection = ImmutableList.copyOf(futures);
      }

      return (ListenableFuture[])collection.toArray(new ListenableFuture[0]);
   }

   public static void addCallback(final ListenableFuture future, final FutureCallback callback, Executor executor) {
      Preconditions.checkNotNull(callback);
      future.addListener(new CallbackListener(future, callback), executor);
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public static Object getDone(Future future) throws ExecutionException {
      Preconditions.checkState(future.isDone(), "Future was expected to be done: %s", (Object)future);
      return Uninterruptibles.getUninterruptibly(future);
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   @J2ktIncompatible
   @GwtIncompatible
   public static Object getChecked(Future future, Class exceptionClass) throws Exception {
      return FuturesGetChecked.getChecked(future, exceptionClass);
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   @J2ktIncompatible
   @GwtIncompatible
   public static Object getChecked(Future future, Class exceptionClass, Duration timeout) throws Exception {
      return getChecked(future, exceptionClass, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   @J2ktIncompatible
   @GwtIncompatible
   public static Object getChecked(Future future, Class exceptionClass, long timeout, TimeUnit unit) throws Exception {
      return FuturesGetChecked.getChecked(future, exceptionClass, timeout, unit);
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public static Object getUnchecked(Future future) {
      Preconditions.checkNotNull(future);

      try {
         return Uninterruptibles.getUninterruptibly(future);
      } catch (ExecutionException e) {
         wrapAndThrowUnchecked(e.getCause());
         throw new AssertionError();
      }
   }

   private static void wrapAndThrowUnchecked(Throwable cause) {
      if (cause instanceof Error) {
         throw new ExecutionError((Error)cause);
      } else {
         throw new UncheckedExecutionException(cause);
      }
   }

   @GwtCompatible
   public static final class FutureCombiner {
      private final boolean allMustSucceed;
      private final ImmutableList futures;

      private FutureCombiner(boolean allMustSucceed, ImmutableList futures) {
         this.allMustSucceed = allMustSucceed;
         this.futures = futures;
      }

      public ListenableFuture callAsync(AsyncCallable combiner, Executor executor) {
         return new CombinedFuture(this.futures, this.allMustSucceed, executor, combiner);
      }

      public ListenableFuture call(Callable combiner, Executor executor) {
         return new CombinedFuture(this.futures, this.allMustSucceed, executor, combiner);
      }

      public ListenableFuture run(final Runnable combiner, Executor executor) {
         return this.call(new Callable() {
            @CheckForNull
            public Void call() throws Exception {
               combiner.run();
               return null;
            }
         }, executor);
      }
   }

   private static final class NonCancellationPropagatingFuture extends AbstractFuture.TrustedFuture implements Runnable {
      @CheckForNull
      private ListenableFuture delegate;

      NonCancellationPropagatingFuture(final ListenableFuture delegate) {
         this.delegate = delegate;
      }

      public void run() {
         ListenableFuture<V> localDelegate = this.delegate;
         if (localDelegate != null) {
            this.setFuture(localDelegate);
         }

      }

      @CheckForNull
      protected String pendingToString() {
         ListenableFuture<V> localDelegate = this.delegate;
         return localDelegate != null ? "delegate=[" + localDelegate + "]" : null;
      }

      protected void afterDone() {
         this.delegate = null;
      }
   }

   private static final class InCompletionOrderFuture extends AbstractFuture {
      @CheckForNull
      private InCompletionOrderState state;

      private InCompletionOrderFuture(InCompletionOrderState state) {
         this.state = state;
      }

      public boolean cancel(boolean interruptIfRunning) {
         InCompletionOrderState<T> localState = this.state;
         if (super.cancel(interruptIfRunning)) {
            ((InCompletionOrderState)Objects.requireNonNull(localState)).recordOutputCancellation(interruptIfRunning);
            return true;
         } else {
            return false;
         }
      }

      protected void afterDone() {
         this.state = null;
      }

      @CheckForNull
      protected String pendingToString() {
         InCompletionOrderState<T> localState = this.state;
         return localState != null ? "inputCount=[" + localState.inputFutures.length + "], remaining=[" + localState.incompleteOutputCount.get() + "]" : null;
      }
   }

   private static final class InCompletionOrderState {
      private boolean wasCancelled;
      private boolean shouldInterrupt;
      private final AtomicInteger incompleteOutputCount;
      private final @Nullable ListenableFuture[] inputFutures;
      private volatile int delegateIndex;

      private InCompletionOrderState(ListenableFuture[] inputFutures) {
         this.wasCancelled = false;
         this.shouldInterrupt = true;
         this.delegateIndex = 0;
         this.inputFutures = inputFutures;
         this.incompleteOutputCount = new AtomicInteger(inputFutures.length);
      }

      private void recordOutputCancellation(boolean interruptIfRunning) {
         this.wasCancelled = true;
         if (!interruptIfRunning) {
            this.shouldInterrupt = false;
         }

         this.recordCompletion();
      }

      private void recordInputCompletion(ImmutableList delegates, int inputFutureIndex) {
         ListenableFuture<? extends T> inputFuture = (ListenableFuture)Objects.requireNonNull(this.inputFutures[inputFutureIndex]);
         this.inputFutures[inputFutureIndex] = null;

         for(int i = this.delegateIndex; i < delegates.size(); ++i) {
            if (((AbstractFuture)delegates.get(i)).setFuture(inputFuture)) {
               this.recordCompletion();
               this.delegateIndex = i + 1;
               return;
            }
         }

         this.delegateIndex = delegates.size();
      }

      private void recordCompletion() {
         if (this.incompleteOutputCount.decrementAndGet() == 0 && this.wasCancelled) {
            for(ListenableFuture toCancel : this.inputFutures) {
               if (toCancel != null) {
                  toCancel.cancel(this.shouldInterrupt);
               }
            }
         }

      }
   }

   private static final class CallbackListener implements Runnable {
      final Future future;
      final FutureCallback callback;

      CallbackListener(Future future, FutureCallback callback) {
         this.future = future;
         this.callback = callback;
      }

      public void run() {
         if (this.future instanceof InternalFutureFailureAccess) {
            Throwable failure = InternalFutures.tryInternalFastPathGetFailure((InternalFutureFailureAccess)this.future);
            if (failure != null) {
               this.callback.onFailure(failure);
               return;
            }
         }

         V value;
         try {
            value = (V)Futures.getDone(this.future);
         } catch (ExecutionException e) {
            this.callback.onFailure(e.getCause());
            return;
         } catch (Error | RuntimeException e) {
            this.callback.onFailure(e);
            return;
         }

         this.callback.onSuccess(value);
      }

      public String toString() {
         return MoreObjects.toStringHelper((Object)this).addValue(this.callback).toString();
      }
   }
}
