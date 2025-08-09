package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.ImmutableCollection;
import org.apache.curator.shaded.com.google.errorprone.annotations.ForOverride;
import org.apache.curator.shaded.com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AggregateFuture extends AggregateFutureState {
   private static final Logger logger = Logger.getLogger(AggregateFuture.class.getName());
   @CheckForNull
   private ImmutableCollection futures;
   private final boolean allMustSucceed;
   private final boolean collectsValues;

   AggregateFuture(ImmutableCollection futures, boolean allMustSucceed, boolean collectsValues) {
      super(futures.size());
      this.futures = (ImmutableCollection)Preconditions.checkNotNull(futures);
      this.allMustSucceed = allMustSucceed;
      this.collectsValues = collectsValues;
   }

   protected final void afterDone() {
      super.afterDone();
      ImmutableCollection<? extends Future<?>> localFutures = this.futures;
      this.releaseResources(AggregateFuture.ReleaseResourcesReason.OUTPUT_FUTURE_DONE);
      if (this.isCancelled() & localFutures != null) {
         boolean wasInterrupted = this.wasInterrupted();

         for(Future future : localFutures) {
            future.cancel(wasInterrupted);
         }
      }

   }

   @CheckForNull
   protected final String pendingToString() {
      ImmutableCollection<? extends Future<?>> localFutures = this.futures;
      return localFutures != null ? "futures=" + localFutures : super.pendingToString();
   }

   final void init() {
      Objects.requireNonNull(this.futures);
      if (this.futures.isEmpty()) {
         this.handleAllCompleted();
      } else {
         if (this.allMustSucceed) {
            int i = 0;

            for(ListenableFuture future : this.futures) {
               int index = i++;
               future.addListener(() -> {
                  try {
                     if (future.isCancelled()) {
                        this.futures = null;
                        this.cancel(false);
                     } else {
                        this.collectValueFromNonCancelledFuture(index, future);
                     }
                  } finally {
                     this.decrementCountAndMaybeComplete((ImmutableCollection)null);
                  }

               }, MoreExecutors.directExecutor());
            }
         } else {
            ImmutableCollection<? extends Future<? extends InputT>> localFutures = this.collectsValues ? this.futures : null;
            Runnable listener = () -> this.decrementCountAndMaybeComplete(localFutures);

            for(ListenableFuture future : this.futures) {
               future.addListener(listener, MoreExecutors.directExecutor());
            }
         }

      }
   }

   private void handleException(Throwable throwable) {
      Preconditions.checkNotNull(throwable);
      if (this.allMustSucceed) {
         boolean completedWithFailure = this.setException(throwable);
         if (!completedWithFailure) {
            boolean firstTimeSeeingThisException = addCausalChain(this.getOrInitSeenExceptions(), throwable);
            if (firstTimeSeeingThisException) {
               log(throwable);
               return;
            }
         }
      }

      if (throwable instanceof Error) {
         log(throwable);
      }

   }

   private static void log(Throwable throwable) {
      String message = throwable instanceof Error ? "Input Future failed with Error" : "Got more than one input Future failure. Logging failures after the first";
      logger.log(Level.SEVERE, message, throwable);
   }

   final void addInitialException(Set seen) {
      Preconditions.checkNotNull(seen);
      if (!this.isCancelled()) {
         addCausalChain(seen, (Throwable)Objects.requireNonNull(this.tryInternalFastPathGetFailure()));
      }

   }

   private void collectValueFromNonCancelledFuture(int index, Future future) {
      try {
         this.collectOneValue(index, Futures.getDone(future));
      } catch (ExecutionException e) {
         this.handleException(e.getCause());
      } catch (Error | RuntimeException t) {
         this.handleException(t);
      }

   }

   private void decrementCountAndMaybeComplete(@CheckForNull ImmutableCollection futuresIfNeedToCollectAtCompletion) {
      int newRemaining = this.decrementRemainingAndGet();
      Preconditions.checkState(newRemaining >= 0, "Less than 0 remaining futures");
      if (newRemaining == 0) {
         this.processCompleted(futuresIfNeedToCollectAtCompletion);
      }

   }

   private void processCompleted(@CheckForNull ImmutableCollection futuresIfNeedToCollectAtCompletion) {
      if (futuresIfNeedToCollectAtCompletion != null) {
         int i = 0;

         for(Future future : futuresIfNeedToCollectAtCompletion) {
            if (!future.isCancelled()) {
               this.collectValueFromNonCancelledFuture(i, future);
            }

            ++i;
         }
      }

      this.clearSeenExceptions();
      this.handleAllCompleted();
      this.releaseResources(AggregateFuture.ReleaseResourcesReason.ALL_INPUT_FUTURES_PROCESSED);
   }

   @ForOverride
   @OverridingMethodsMustInvokeSuper
   void releaseResources(ReleaseResourcesReason reason) {
      Preconditions.checkNotNull(reason);
      this.futures = null;
   }

   abstract void collectOneValue(int index, @ParametricNullness Object returnValue);

   abstract void handleAllCompleted();

   private static boolean addCausalChain(Set seen, Throwable param) {
      for(Throwable t = param; t != null; t = t.getCause()) {
         boolean firstTimeSeen = seen.add(t);
         if (!firstTimeSeen) {
            return false;
         }
      }

      return true;
   }

   static enum ReleaseResourcesReason {
      OUTPUT_FUTURE_DONE,
      ALL_INPUT_FUTURES_PROCESSED;

      // $FF: synthetic method
      private static ReleaseResourcesReason[] $values() {
         return new ReleaseResourcesReason[]{OUTPUT_FUTURE_DONE, ALL_INPUT_FUTURES_PROCESSED};
      }
   }
}
