package org.sparkproject.jetty.util;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import org.slf4j.LoggerFactory;

public interface Promise {
   default void completeWith(CompletableFuture cf) {
      cf.whenComplete((c, x) -> {
         if (x == null) {
            this.succeeded(c);
         } else {
            this.failed(x);
         }

      });
   }

   default void succeeded(Object result) {
   }

   default void failed(Throwable x) {
   }

   static Promise from(final Consumer success, final Consumer failure) {
      return new Promise() {
         public void succeeded(Object result) {
            success.accept(result);
         }

         public void failed(Throwable x) {
            failure.accept(x);
         }
      };
   }

   static Promise from(final CompletableFuture completable) {
      return completable instanceof Promise ? (Promise)completable : new Promise() {
         public void succeeded(Object result) {
            completable.complete(result);
         }

         public void failed(Throwable x) {
            completable.completeExceptionally(x);
         }
      };
   }

   public static class Adapter implements Promise {
      public void failed(Throwable x) {
         LoggerFactory.getLogger(this.getClass()).warn("Failed", x);
      }
   }

   public static class Completable extends CompletableFuture implements Promise {
      public void succeeded(Object result) {
         this.complete(result);
      }

      public void failed(Throwable x) {
         this.completeExceptionally(x);
      }
   }

   public static class Wrapper implements Promise {
      private final Promise promise;

      public Wrapper(Promise promise) {
         this.promise = (Promise)Objects.requireNonNull(promise);
      }

      public void succeeded(Object result) {
         this.promise.succeeded(result);
      }

      public void failed(Throwable x) {
         this.promise.failed(x);
      }

      public Promise getPromise() {
         return this.promise;
      }

      public Promise unwrap() {
         Promise<W> result;
         for(result = this.promise; result instanceof Wrapper; result = ((Wrapper)result).unwrap()) {
         }

         return result;
      }
   }
}
