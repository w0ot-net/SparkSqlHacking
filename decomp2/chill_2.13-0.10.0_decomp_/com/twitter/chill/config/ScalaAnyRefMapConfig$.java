package com.twitter.chill.config;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;

public final class ScalaAnyRefMapConfig$ {
   public static final ScalaAnyRefMapConfig$ MODULE$ = new ScalaAnyRefMapConfig$();

   public ScalaAnyRefMapConfig apply(final Map m) {
      return new ScalaAnyRefMapConfig((Map)m.map((x0$1) -> {
         if (x0$1 != null) {
            Object k = x0$1._1();
            Object v = x0$1._2();
            Tuple2 var1 = new Tuple2(k, v);
            return var1;
         } else {
            throw new MatchError(x0$1);
         }
      }));
   }

   public ScalaAnyRefMapConfig empty() {
      return new ScalaAnyRefMapConfig(.MODULE$.Map().empty());
   }

   private ScalaAnyRefMapConfig$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
