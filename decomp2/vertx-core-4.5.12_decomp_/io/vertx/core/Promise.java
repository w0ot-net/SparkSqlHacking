package io.vertx.core;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.impl.NoStackTraceThrowable;
import io.vertx.core.impl.future.PromiseImpl;

@VertxGen
public interface Promise extends Handler {
   static Promise promise() {
      return new PromiseImpl();
   }

   @GenIgnore
   default void handle(AsyncResult asyncResult) {
      if (asyncResult.succeeded()) {
         this.complete(asyncResult.result());
      } else {
         this.fail(asyncResult.cause());
      }

   }

   default void complete(Object result) {
      if (!this.tryComplete(result)) {
         throw new IllegalStateException("Result is already complete");
      }
   }

   default void complete() {
      if (!this.tryComplete()) {
         throw new IllegalStateException("Result is already complete");
      }
   }

   default void fail(Throwable cause) {
      if (!this.tryFail(cause)) {
         throw new IllegalStateException("Result is already complete");
      }
   }

   default void fail(String message) {
      if (!this.tryFail(message)) {
         throw new IllegalStateException("Result is already complete");
      }
   }

   boolean tryComplete(Object var1);

   default boolean tryComplete() {
      return this.tryComplete((Object)null);
   }

   boolean tryFail(Throwable var1);

   default boolean tryFail(String message) {
      return this.tryFail((Throwable)(new NoStackTraceThrowable(message)));
   }

   @CacheReturn
   Future future();
}
