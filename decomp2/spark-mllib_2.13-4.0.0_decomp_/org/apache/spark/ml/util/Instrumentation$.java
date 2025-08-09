package org.apache.spark.ml.util;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;
import scala.util.Try.;

public final class Instrumentation$ {
   public static final Instrumentation$ MODULE$ = new Instrumentation$();

   public Object instrumented(final Function1 body) {
      Instrumentation instr = new Instrumentation();
      boolean var4 = false;
      Failure var5 = null;
      Try var6 = .MODULE$.apply(() -> body.apply(instr));
      if (var6 instanceof Failure) {
         var4 = true;
         var5 = (Failure)var6;
         Throwable var7 = var5.exception();
         if (var7 != null) {
            Option var8 = scala.util.control.NonFatal..MODULE$.unapply(var7);
            if (!var8.isEmpty()) {
               Throwable e = (Throwable)var8.get();
               instr.logFailure(e);
               throw e;
            }
         }
      }

      if (var4) {
         Throwable e = var5.exception();
         throw e;
      } else if (var6 instanceof Success) {
         Success var11 = (Success)var6;
         Object result = var11.value();
         instr.logSuccess();
         return result;
      } else {
         throw new MatchError(var6);
      }
   }

   private Instrumentation$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
