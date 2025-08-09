package io.vertx.ext.auth;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import java.util.Objects;

/** @deprecated */
@Deprecated
@VertxGen
public interface VertxContextPRNG extends io.vertx.ext.auth.prng.VertxContextPRNG {
   static VertxContextPRNG current() {
      Context currentContext = Vertx.currentContext();
      if (currentContext != null) {
         return current(currentContext);
      } else {
         throw new IllegalStateException("Not running in a Vert.x Context.");
      }
   }

   @GenIgnore
   static VertxContextPRNG current(Context context) {
      Objects.requireNonNull(context, "context can not be null");

      try {
         String contextKey = "__vertx.VertxContextPRNG";
         PRNG random = (PRNG)context.get("__vertx.VertxContextPRNG");
         if (random == null) {
            synchronized(context) {
               random = (PRNG)context.get("__vertx.VertxContextPRNG");
               if (random == null) {
                  random = new PRNG(context.owner());
                  context.put("__vertx.VertxContextPRNG", random);
               }
            }
         }

         return random;
      } catch (UnsupportedOperationException var7) {
         Vertx vertx = context.owner();
         if (vertx != null) {
            return new PRNG(vertx);
         } else {
            throw new IllegalStateException("Not running in a Vert.x Context.");
         }
      }
   }

   static VertxContextPRNG current(Vertx vertx) {
      Context currentContext = Vertx.currentContext();
      if (currentContext != null) {
         return current(currentContext);
      } else {
         Objects.requireNonNull(vertx, "vertx can not be null");
         return new PRNG(vertx);
      }
   }
}
