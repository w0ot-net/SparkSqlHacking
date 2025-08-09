package breeze.linalg.support;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.runtime.BoxesRunTime;

public final class TensorKeys$ {
   public static final TensorKeys$ MODULE$ = new TensorKeys$();

   public boolean $lessinit$greater$default$2() {
      return false;
   }

   public Function1 $lessinit$greater$default$3() {
      return (k) -> BoxesRunTime.boxToBoolean($anonfun$$lessinit$greater$default$3$1(k));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$$lessinit$greater$default$3$1(final Object k) {
      return true;
   }

   private TensorKeys$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
