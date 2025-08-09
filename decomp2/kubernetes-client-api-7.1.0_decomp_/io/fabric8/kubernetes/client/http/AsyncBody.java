package io.fabric8.kubernetes.client.http;

import java.util.concurrent.CompletableFuture;

public interface AsyncBody {
   void consume();

   CompletableFuture done();

   void cancel();

   @FunctionalInterface
   public interface Consumer {
      void consume(Object var1, AsyncBody var2) throws Exception;

      default Object unwrap(Class target) {
         return this.getClass().equals(target) ? this : null;
      }
   }
}
