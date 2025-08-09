package io.vertx.ext.auth.prng;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Context;
import io.vertx.core.Vertx;

@VertxGen
public interface VertxContextPRNG {
   static VertxContextPRNG current() {
      return io.vertx.ext.auth.VertxContextPRNG.current();
   }

   @GenIgnore
   static VertxContextPRNG current(Context context) {
      return io.vertx.ext.auth.VertxContextPRNG.current(context);
   }

   static VertxContextPRNG current(Vertx vertx) {
      return io.vertx.ext.auth.VertxContextPRNG.current(vertx);
   }

   void close();

   @GenIgnore({"permitted-type"})
   void nextBytes(byte[] var1);

   String nextString(int var1);

   int nextInt();

   int nextInt(int var1);

   boolean nextBoolean();

   long nextLong();

   float nextFloat();

   double nextDouble();

   double nextGaussian();
}
