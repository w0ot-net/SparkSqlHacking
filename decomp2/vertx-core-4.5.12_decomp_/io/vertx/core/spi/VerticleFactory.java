package io.vertx.core.spi;

import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public interface VerticleFactory {
   static String removePrefix(String identifer) {
      int pos = identifer.indexOf(58);
      if (pos != -1) {
         if (pos == identifer.length() - 1) {
            throw new IllegalArgumentException("Invalid identifier: " + identifer);
         } else {
            return identifer.substring(pos + 1);
         }
      } else {
         return identifer;
      }
   }

   default int order() {
      return 0;
   }

   default void init(Vertx vertx) {
   }

   default void close() {
   }

   String prefix();

   void createVerticle(String var1, ClassLoader var2, Promise var3);
}
