package spire.math;

import java.lang.invoke.SerializedLambda;
import scala.runtime.BoxesRunTime;
import spire.algebra.IsReal;
import spire.algebra.IsReal$;

public final class FpFilterApprox$ {
   public static final FpFilterApprox$ MODULE$ = new FpFilterApprox$();

   public FpFilter liftApprox(final Object approx, final IsReal evidence$1) {
      double apx = IsReal$.MODULE$.apply(evidence$1).toDouble(approx);
      return new FpFilter(apx, package$.MODULE$.abs(apx), 1, () -> approx);
   }

   public final int hashCode$extension(final Object $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final Object $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof FpFilterApprox) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var10000;
      if (var3) {
         Object var5 = x$1 == null ? null : ((FpFilterApprox)x$1).exact();
         if (BoxesRunTime.equals($this, var5)) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private FpFilterApprox$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
