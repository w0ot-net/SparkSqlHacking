package breeze.linalg.support;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.runtime.BoxesRunTime;

public final class TensorValues$ {
   public static final TensorValues$ MODULE$ = new TensorValues$();

   public boolean $lessinit$greater$default$2() {
      return false;
   }

   public Function1 $lessinit$greater$default$3() {
      return (x) -> BoxesRunTime.boxToBoolean($anonfun$$lessinit$greater$default$3$1(x));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$$lessinit$greater$default$3$1(final Object x) {
      return true;
   }

   private TensorValues$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
