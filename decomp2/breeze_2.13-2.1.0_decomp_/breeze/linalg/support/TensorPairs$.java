package breeze.linalg.support;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public final class TensorPairs$ {
   public static final TensorPairs$ MODULE$ = new TensorPairs$();

   public Function1 $lessinit$greater$default$3() {
      return (x) -> BoxesRunTime.boxToBoolean($anonfun$$lessinit$greater$default$3$1(x));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$$lessinit$greater$default$3$1(final Tuple2 x) {
      return true;
   }

   private TensorPairs$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
