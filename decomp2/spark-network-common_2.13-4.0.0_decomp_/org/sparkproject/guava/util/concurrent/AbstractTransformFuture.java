package org.sparkproject.guava.util.concurrent;

import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Function;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractTransformFuture extends FluentFuture.TrustedFuture implements Runnable {
   @CheckForNull
   @LazyInit
   ListenableFuture inputFuture;
   @CheckForNull
   @LazyInit
   Object function;

   static ListenableFuture createAsync(ListenableFuture input, AsyncFunction function, Executor executor) {
      Preconditions.checkNotNull(executor);
      AsyncTransformFuture<I, O> output = new AsyncTransformFuture(input, function);
      input.addListener(output, MoreExecutors.rejectionPropagatingExecutor(executor, output));
      return output;
   }

   static ListenableFuture create(ListenableFuture input, Function function, Executor executor) {
      Preconditions.checkNotNull(function);
      TransformFuture<I, O> output = new TransformFuture(input, function);
      input.addListener(output, MoreExecutors.rejectionPropagatingExecutor(executor, output));
      return output;
   }

   AbstractTransformFuture(ListenableFuture inputFuture, Object function) {
      this.inputFuture = (ListenableFuture)Preconditions.checkNotNull(inputFuture);
      this.function = Preconditions.checkNotNull(function);
   }

   public final void run() {
      ListenableFuture<? extends I> localInputFuture = this.inputFuture;
      F localFunction = (F)this.function;
      if (!(this.isCancelled() | localInputFuture == null | localFunction == null)) {
         this.inputFuture = null;
         if (localInputFuture.isCancelled()) {
            this.setFuture(localInputFuture);
         } else {
            I sourceResult;
            try {
               sourceResult = (I)Futures.getDone(localInputFuture);
            } catch (CancellationException var13) {
               this.cancel(false);
               return;
            } catch (ExecutionException e) {
               this.setException(e.getCause());
               return;
            } catch (Exception e) {
               this.setException(e);
               return;
            } catch (Error e) {
               this.setException(e);
               return;
            }

            T transformResult;
            label85: {
               try {
                  transformResult = (T)this.doTransform(localFunction, sourceResult);
                  break label85;
               } catch (Throwable t) {
                  Platform.restoreInterruptIfIsInterruptedException(t);
                  this.setException(t);
               } finally {
                  this.function = null;
               }

               return;
            }

            this.setResult(transformResult);
         }
      }
   }

   @ParametricNullness
   @ForOverride
   abstract Object doTransform(Object function, @ParametricNullness Object result) throws Exception;

   @ForOverride
   abstract void setResult(@ParametricNullness Object result);

   protected final void afterDone() {
      ListenableFuture<? extends I> localInputFuture = this.inputFuture;
      this.maybePropagateCancellationTo(localInputFuture);
      this.inputFuture = null;
      this.function = null;
   }

   @CheckForNull
   protected String pendingToString() {
      ListenableFuture<? extends I> localInputFuture = this.inputFuture;
      F localFunction = (F)this.function;
      String superString = super.pendingToString();
      String resultString = "";
      if (localInputFuture != null) {
         resultString = "inputFuture=[" + localInputFuture + "], ";
      }

      if (localFunction != null) {
         return resultString + "function=[" + localFunction + "]";
      } else {
         return superString != null ? resultString + superString : null;
      }
   }

   private static final class AsyncTransformFuture extends AbstractTransformFuture {
      AsyncTransformFuture(ListenableFuture inputFuture, AsyncFunction function) {
         super(inputFuture, function);
      }

      ListenableFuture doTransform(AsyncFunction function, @ParametricNullness Object input) throws Exception {
         ListenableFuture<? extends O> outputFuture = function.apply(input);
         Preconditions.checkNotNull(outputFuture, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", (Object)function);
         return outputFuture;
      }

      void setResult(ListenableFuture result) {
         this.setFuture(result);
      }
   }

   private static final class TransformFuture extends AbstractTransformFuture {
      TransformFuture(ListenableFuture inputFuture, Function function) {
         super(inputFuture, function);
      }

      @ParametricNullness
      Object doTransform(Function function, @ParametricNullness Object input) {
         return function.apply(input);
      }

      void setResult(@ParametricNullness Object result) {
         this.set(result);
      }
   }
}
