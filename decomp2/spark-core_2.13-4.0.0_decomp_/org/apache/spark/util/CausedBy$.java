package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.Option.;

public final class CausedBy$ {
   public static final CausedBy$ MODULE$ = new CausedBy$();

   public Option unapply(final Throwable e) {
      return .MODULE$.apply(e.getCause()).flatMap((cause) -> MODULE$.unapply(cause)).orElse(() -> new Some(e));
   }

   private CausedBy$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
