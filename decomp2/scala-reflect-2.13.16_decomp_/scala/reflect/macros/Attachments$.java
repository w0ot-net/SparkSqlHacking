package scala.reflect.macros;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ClassValueCompat;

public final class Attachments$ {
   public static final Attachments$ MODULE$ = new Attachments$();
   private static final ClassValueCompat scala$reflect$macros$Attachments$$matchesTagCache = new ClassValueCompat() {
      public Function1 computeValue(final Class cls) {
         return (x$1) -> BoxesRunTime.boxToBoolean($anonfun$computeValue$1(cls, x$1));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$computeValue$1(final Class cls$1, final Object x$1) {
         return cls$1.isInstance(x$1);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   };

   public ClassValueCompat scala$reflect$macros$Attachments$$matchesTagCache() {
      return scala$reflect$macros$Attachments$$matchesTagCache;
   }

   private Attachments$() {
   }
}
