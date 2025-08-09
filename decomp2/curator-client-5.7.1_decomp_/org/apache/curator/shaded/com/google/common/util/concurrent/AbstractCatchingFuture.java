package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Function;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import org.apache.curator.shaded.com.google.common.util.concurrent.internal.InternalFutures;
import org.apache.curator.shaded.com.google.errorprone.annotations.ForOverride;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractCatchingFuture extends FluentFuture.TrustedFuture implements Runnable {
   @CheckForNull
   ListenableFuture inputFuture;
   @CheckForNull
   Class exceptionType;
   @CheckForNull
   Object fallback;

   static ListenableFuture create(ListenableFuture input, Class exceptionType, Function fallback, Executor executor) {
      CatchingFuture<V, X> future = new CatchingFuture(input, exceptionType, fallback);
      input.addListener(future, MoreExecutors.rejectionPropagatingExecutor(executor, future));
      return future;
   }

   static ListenableFuture create(ListenableFuture input, Class exceptionType, AsyncFunction fallback, Executor executor) {
      AsyncCatchingFuture<V, X> future = new AsyncCatchingFuture(input, exceptionType, fallback);
      input.addListener(future, MoreExecutors.rejectionPropagatingExecutor(executor, future));
      return future;
   }

   AbstractCatchingFuture(ListenableFuture inputFuture, Class exceptionType, Object fallback) {
      this.inputFuture = (ListenableFuture)Preconditions.checkNotNull(inputFuture);
      this.exceptionType = (Class)Preconditions.checkNotNull(exceptionType);
      this.fallback = Preconditions.checkNotNull(fallback);
   }

   public final void run() {
      ListenableFuture<? extends V> localInputFuture = this.inputFuture;
      Class<X> localExceptionType = this.exceptionType;
      F localFallback = (F)this.fallback;
      if (!(localInputFuture == null | localExceptionType == null | localFallback == null) && !this.isCancelled()) {
         this.inputFuture = null;
         V sourceResult = (V)null;
         Throwable throwable = null;

         try {
            if (localInputFuture instanceof InternalFutureFailureAccess) {
               throwable = InternalFutures.tryInternalFastPathGetFailure((InternalFutureFailureAccess)localInputFuture);
            }

            if (throwable == null) {
               sourceResult = (V)Futures.getDone(localInputFuture);
            }
         } catch (ExecutionException e) {
            throwable = e.getCause();
            if (throwable == null) {
               throwable = new NullPointerException("Future type " + localInputFuture.getClass() + " threw " + e.getClass() + " without a cause");
            }
         } catch (Error | RuntimeException e) {
            throwable = e;
         }

         if (throwable == null) {
            this.set(NullnessCasts.uncheckedCastNullableTToT(sourceResult));
         } else if (!Platform.isInstanceOfThrowableClass(throwable, localExceptionType)) {
            this.setFuture(localInputFuture);
         } else {
            X castThrowable = (X)throwable;

            T fallbackResult;
            label121: {
               try {
                  fallbackResult = (T)this.doFallback(localFallback, castThrowable);
                  break label121;
               } catch (Throwable t) {
                  Platform.restoreInterruptIfIsInterruptedException(t);
                  this.setException(t);
               } finally {
                  this.exceptionType = null;
                  this.fallback = null;
               }

               return;
            }

            this.setResult(fallbackResult);
         }
      }
   }

   @CheckForNull
   protected String pendingToString() {
      ListenableFuture<? extends V> localInputFuture = this.inputFuture;
      Class<X> localExceptionType = this.exceptionType;
      F localFallback = (F)this.fallback;
      String superString = super.pendingToString();
      String resultString = "";
      if (localInputFuture != null) {
         resultString = "inputFuture=[" + localInputFuture + "], ";
      }

      if (localExceptionType != null && localFallback != null) {
         return resultString + "exceptionType=[" + localExceptionType + "], fallback=[" + localFallback + "]";
      } else {
         return superString != null ? resultString + superString : null;
      }
   }

   @ParametricNullness
   @ForOverride
   abstract Object doFallback(Object fallback, Throwable throwable) throws Exception;

   @ForOverride
   abstract void setResult(@ParametricNullness Object result);

   protected final void afterDone() {
      this.maybePropagateCancellationTo(this.inputFuture);
      this.inputFuture = null;
      this.exceptionType = null;
      this.fallback = null;
   }

   private static final class AsyncCatchingFuture extends AbstractCatchingFuture {
      AsyncCatchingFuture(ListenableFuture input, Class exceptionType, AsyncFunction fallback) {
         super(input, exceptionType, fallback);
      }

      ListenableFuture doFallback(AsyncFunction fallback, Throwable cause) throws Exception {
         ListenableFuture<? extends V> replacement = fallback.apply(cause);
         Preconditions.checkNotNull(replacement, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", (Object)fallback);
         return replacement;
      }

      void setResult(ListenableFuture result) {
         this.setFuture(result);
      }
   }

   private static final class CatchingFuture extends AbstractCatchingFuture {
      CatchingFuture(ListenableFuture input, Class exceptionType, Function fallback) {
         super(input, exceptionType, fallback);
      }

      @ParametricNullness
      Object doFallback(Function fallback, Throwable cause) throws Exception {
         return fallback.apply(cause);
      }

      void setResult(@ParametricNullness Object result) {
         this.set(result);
      }
   }
}
