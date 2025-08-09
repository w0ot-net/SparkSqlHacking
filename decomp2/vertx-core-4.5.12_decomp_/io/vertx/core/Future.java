package io.vertx.core;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.Utils;
import io.vertx.core.impl.future.CompositeFutureImpl;
import io.vertx.core.impl.future.FailedFuture;
import io.vertx.core.impl.future.SucceededFuture;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Future extends AsyncResult {
   static CompositeFuture all(Future f1, Future f2) {
      return CompositeFutureImpl.all(f1, f2);
   }

   static CompositeFuture all(Future f1, Future f2, Future f3) {
      return CompositeFutureImpl.all(f1, f2, f3);
   }

   static CompositeFuture all(Future f1, Future f2, Future f3, Future f4) {
      return CompositeFutureImpl.all(f1, f2, f3, f4);
   }

   static CompositeFuture all(Future f1, Future f2, Future f3, Future f4, Future f5) {
      return CompositeFutureImpl.all(f1, f2, f3, f4, f5);
   }

   static CompositeFuture all(Future f1, Future f2, Future f3, Future f4, Future f5, Future f6) {
      return CompositeFutureImpl.all(f1, f2, f3, f4, f5, f6);
   }

   static CompositeFuture all(List futures) {
      return CompositeFutureImpl.all((Future[])futures.toArray(new Future[0]));
   }

   static CompositeFuture any(Future f1, Future f2) {
      return CompositeFutureImpl.any(f1, f2);
   }

   static CompositeFuture any(Future f1, Future f2, Future f3) {
      return CompositeFutureImpl.any(f1, f2, f3);
   }

   static CompositeFuture any(Future f1, Future f2, Future f3, Future f4) {
      return CompositeFutureImpl.any(f1, f2, f3, f4);
   }

   static CompositeFuture any(Future f1, Future f2, Future f3, Future f4, Future f5) {
      return CompositeFutureImpl.any(f1, f2, f3, f4, f5);
   }

   static CompositeFuture any(Future f1, Future f2, Future f3, Future f4, Future f5, Future f6) {
      return CompositeFutureImpl.any(f1, f2, f3, f4, f5, f6);
   }

   static CompositeFuture any(List futures) {
      return CompositeFutureImpl.any((Future[])futures.toArray(new Future[0]));
   }

   static CompositeFuture join(Future f1, Future f2) {
      return CompositeFutureImpl.join(f1, f2);
   }

   static CompositeFuture join(Future f1, Future f2, Future f3) {
      return CompositeFutureImpl.join(f1, f2, f3);
   }

   static CompositeFuture join(Future f1, Future f2, Future f3, Future f4) {
      return CompositeFutureImpl.join(f1, f2, f3, f4);
   }

   static CompositeFuture join(Future f1, Future f2, Future f3, Future f4, Future f5) {
      return CompositeFutureImpl.join(f1, f2, f3, f4, f5);
   }

   static CompositeFuture join(Future f1, Future f2, Future f3, Future f4, Future f5, Future f6) {
      return CompositeFutureImpl.join(f1, f2, f3, f4, f5, f6);
   }

   static CompositeFuture join(List futures) {
      return CompositeFutureImpl.join((Future[])futures.toArray(new Future[0]));
   }

   static Future future(Handler handler) {
      Promise<T> promise = Promise.promise();

      try {
         handler.handle(promise);
      } catch (Throwable e) {
         promise.tryFail(e);
      }

      return promise.future();
   }

   static Future succeededFuture() {
      return SucceededFuture.EMPTY;
   }

   static Future succeededFuture(Object result) {
      return (Future)(result == null ? succeededFuture() : new SucceededFuture(result));
   }

   static Future failedFuture(Throwable t) {
      return new FailedFuture(t);
   }

   static Future failedFuture(String failureMessage) {
      return new FailedFuture(failureMessage);
   }

   boolean isComplete();

   @Fluent
   Future onComplete(Handler var1);

   default Future onComplete(Handler successHandler, Handler failureHandler) {
      return this.onComplete((ar) -> {
         if (successHandler != null && ar.succeeded()) {
            successHandler.handle(ar.result());
         } else if (failureHandler != null && ar.failed()) {
            failureHandler.handle(ar.cause());
         }

      });
   }

   @Fluent
   default Future onSuccess(Handler handler) {
      return this.onComplete(handler, (Handler)null);
   }

   @Fluent
   default Future onFailure(Handler handler) {
      return this.onComplete((Handler)null, handler);
   }

   Object result();

   Throwable cause();

   boolean succeeded();

   boolean failed();

   default Future flatMap(Function mapper) {
      return this.compose(mapper);
   }

   default Future compose(Function mapper) {
      return this.compose(mapper, Future::failedFuture);
   }

   default Future recover(Function mapper) {
      return this.compose(Future::succeededFuture, mapper);
   }

   Future compose(Function var1, Function var2);

   Future transform(Function var1);

   /** @deprecated */
   @Deprecated
   Future eventually(Function var1);

   default Future eventually(Supplier supplier) {
      return this.eventually((Function)((v) -> (Future)supplier.get()));
   }

   Future map(Function var1);

   Future map(Object var1);

   default Future mapEmpty() {
      return (Future)AsyncResult.super.mapEmpty();
   }

   Future otherwise(Function var1);

   Future otherwise(Object var1);

   default Future otherwiseEmpty() {
      return (Future)AsyncResult.super.otherwiseEmpty();
   }

   default Future andThen(Handler handler) {
      return this.transform((ar) -> {
         handler.handle(ar);
         return (Future)ar;
      });
   }

   Future expecting(Expectation var1);

   Future timeout(long var1, TimeUnit var3);

   @GenIgnore
   default CompletionStage toCompletionStage() {
      CompletableFuture<T> completableFuture = new CompletableFuture();
      this.onComplete((ar) -> {
         if (ar.succeeded()) {
            completableFuture.complete(ar.result());
         } else {
            completableFuture.completeExceptionally(ar.cause());
         }

      });
      return completableFuture;
   }

   @GenIgnore
   static Future fromCompletionStage(CompletionStage completionStage) {
      Promise<T> promise = Promise.promise();
      completionStage.whenComplete((value, err) -> {
         if (err != null) {
            promise.fail(err);
         } else {
            promise.complete(value);
         }

      });
      return promise.future();
   }

   @GenIgnore
   static Future fromCompletionStage(CompletionStage completionStage, Context context) {
      Promise<T> promise = ((ContextInternal)context).promise();
      completionStage.whenComplete((value, err) -> {
         if (err != null) {
            promise.fail(err);
         } else {
            promise.complete(value);
         }

      });
      return promise.future();
   }

   static Object await(Future future) {
      io.vertx.core.impl.WorkerExecutor executor = io.vertx.core.impl.WorkerExecutor.unwrapWorkerExecutor();
      if (executor == null) {
         throw new IllegalStateException();
      } else {
         CountDownLatch latch = executor.suspend((cont) -> future.onComplete((ar) -> cont.resume()));
         if (latch != null) {
            try {
               latch.await();
            } catch (InterruptedException e) {
               Utils.throwAsUnchecked(e);
               return null;
            }
         }

         if (future.succeeded()) {
            return future.result();
         } else if (future.failed()) {
            Utils.throwAsUnchecked(future.cause());
            return null;
         } else {
            Utils.throwAsUnchecked(new InterruptedException("Context closed"));
            return null;
         }
      }
   }
}
