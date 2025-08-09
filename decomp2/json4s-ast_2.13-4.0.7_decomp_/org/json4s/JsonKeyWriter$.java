package org.json4s;

import java.lang.invoke.SerializedLambda;
import scala.Function1;

public final class JsonKeyWriter$ {
   public static final JsonKeyWriter$ MODULE$ = new JsonKeyWriter$();
   private static final JsonKeyWriter string;

   static {
      string = MODULE$.of((x) -> x);
   }

   public JsonKeyWriter apply(final JsonKeyWriter a) {
      return a;
   }

   public JsonKeyWriter of(final Function1 f) {
      return new JsonKeyWriter(f) {
         private final Function1 f$2;

         public JsonKeyWriter contramap(final Function1 f) {
            return JsonKeyWriter.contramap$(this, f);
         }

         public String write(final Object key) {
            return (String)this.f$2.apply(key);
         }

         public {
            this.f$2 = f$2;
            JsonKeyWriter.$init$(this);
         }
      };
   }

   public JsonKeyWriter fromToString() {
      return this.of((x$1) -> x$1.toString());
   }

   public JsonKeyWriter string() {
      return string;
   }

   private JsonKeyWriter$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
