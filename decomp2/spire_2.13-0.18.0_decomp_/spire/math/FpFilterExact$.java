package spire.math;

import algebra.ring.Field;
import java.lang.invoke.SerializedLambda;

public final class FpFilterExact$ {
   public static final FpFilterExact$ MODULE$ = new FpFilterExact$();

   public FpFilter liftExact(final double exact, final Field evidence$2) {
      return new FpFilter(exact, package$.MODULE$.abs(exact), 0, () -> spire.algebra.package$.MODULE$.Field().apply(evidence$2).fromDouble(exact));
   }

   public final double unary_$minus$extension(final double $this) {
      return -$this;
   }

   public final int hashCode$extension(final double $this) {
      return Double.hashCode($this);
   }

   public final boolean equals$extension(final double $this, final Object x$1) {
      boolean var4;
      if (x$1 instanceof FpFilterExact) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var10000;
      if (var4) {
         double var6 = ((FpFilterExact)x$1).value();
         if ($this == var6) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private FpFilterExact$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
