package org.glassfish.jersey.internal.guava;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Futures {
   private Futures() {
   }

   public static ListenableFuture immediateFuture(Object value) {
      return new ImmediateSuccessfulFuture(value);
   }

   public static ListenableFuture immediateFailedFuture(Throwable throwable) {
      Preconditions.checkNotNull(throwable);
      return new ImmediateFailedFuture(throwable);
   }

   public static ListenableFuture transform(ListenableFuture input, Function function) {
      Preconditions.checkNotNull(function);
      ChainingListenableFuture<I, O> output = new ChainingListenableFuture(asAsyncFunction(function), input);
      input.addListener(output, MoreExecutors.directExecutor());
      return output;
   }

   private static AsyncFunction asAsyncFunction(final Function function) {
      return new AsyncFunction() {
         public ListenableFuture apply(Object input) {
            O output = (O)function.apply(input);
            return Futures.immediateFuture(output);
         }
      };
   }

   private abstract static class ImmediateFuture implements ListenableFuture {
      private static final Logger log = Logger.getLogger(ImmediateFuture.class.getName());

      private ImmediateFuture() {
      }

      public void addListener(Runnable listener, Executor executor) {
         Preconditions.checkNotNull(listener, "Runnable was null.");
         Preconditions.checkNotNull(executor, "Executor was null.");

         try {
            executor.execute(listener);
         } catch (RuntimeException e) {
            log.log(Level.SEVERE, "RuntimeException while executing runnable " + listener + " with executor " + executor, e);
         }

      }

      public boolean cancel(boolean mayInterruptIfRunning) {
         return false;
      }

      public abstract Object get() throws ExecutionException;

      public Object get(long timeout, TimeUnit unit) throws ExecutionException {
         Preconditions.checkNotNull(unit);
         return this.get();
      }

      public boolean isCancelled() {
         return false;
      }

      public boolean isDone() {
         return true;
      }
   }

   private static class ImmediateSuccessfulFuture extends ImmediateFuture {
      private final Object value;

      ImmediateSuccessfulFuture(Object value) {
         this.value = value;
      }

      public Object get() {
         return this.value;
      }
   }

   private static class ImmediateFailedFuture extends ImmediateFuture {
      private final Throwable thrown;

      ImmediateFailedFuture(Throwable thrown) {
         this.thrown = thrown;
      }

      public Object get() throws ExecutionException {
         throw new ExecutionException(this.thrown);
      }
   }

   private static class ChainingListenableFuture extends AbstractFuture implements Runnable {
      private AsyncFunction function;
      private ListenableFuture inputFuture;
      private volatile ListenableFuture outputFuture;

      private ChainingListenableFuture(AsyncFunction function, ListenableFuture inputFuture) {
         this.function = (AsyncFunction)Preconditions.checkNotNull(function);
         this.inputFuture = (ListenableFuture)Preconditions.checkNotNull(inputFuture);
      }

      public boolean cancel(boolean mayInterruptIfRunning) {
         if (super.cancel(mayInterruptIfRunning)) {
            this.cancel(this.inputFuture, mayInterruptIfRunning);
            this.cancel(this.outputFuture, mayInterruptIfRunning);
            return true;
         } else {
            return false;
         }
      }

      private void cancel(Future future, boolean mayInterruptIfRunning) {
         if (future != null) {
            future.cancel(mayInterruptIfRunning);
         }

      }

      public void run() {
         try {
            try {
               I sourceResult;
               try {
                  sourceResult = (I)Uninterruptibles.getUninterruptibly(this.inputFuture);
               } catch (CancellationException var9) {
                  this.cancel(false);
                  return;
               } catch (ExecutionException e) {
                  this.setException(e.getCause());
                  return;
               }

               final ListenableFuture<? extends O> outputFuture = this.outputFuture = (ListenableFuture)Preconditions.checkNotNull(this.function.apply(sourceResult), "AsyncFunction may not return null.");
               if (this.isCancelled()) {
                  outputFuture.cancel(this.wasInterrupted());
                  this.outputFuture = null;
                  return;
               }

               outputFuture.addListener(new Runnable() {
                  public void run() {
                     try {
                        ChainingListenableFuture.this.set(Uninterruptibles.getUninterruptibly(outputFuture));
                     } catch (CancellationException var6) {
                        ChainingListenableFuture.this.cancel(false);
                     } catch (ExecutionException e) {
                        ChainingListenableFuture.this.setException(e.getCause());
                     } finally {
                        ChainingListenableFuture.this.outputFuture = null;
                     }

                  }
               }, MoreExecutors.directExecutor());
            } catch (UndeclaredThrowableException e) {
               this.setException(e.getCause());
            } catch (Throwable t) {
               this.setException(t);
            }

         } finally {
            this.function = null;
            this.inputFuture = null;
         }
      }
   }
}
