package breeze.optimize;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.runtime.ModuleSerializationProxy;

public final class SpectralProjectedGradient$ implements Serializable {
   public static final SpectralProjectedGradient$ MODULE$ = new SpectralProjectedGradient$();

   public Function1 $lessinit$greater$default$1() {
      return (t) -> t;
   }

   public double $lessinit$greater$default$2() {
      return 1.0E-6;
   }

   public double $lessinit$greater$default$3() {
      return 1.0E-4;
   }

   public int $lessinit$greater$default$4() {
      return 30;
   }

   public double $lessinit$greater$default$5() {
      return (double)1.0E10F;
   }

   public double $lessinit$greater$default$6() {
      return 1.0E-10;
   }

   public int $lessinit$greater$default$7() {
      return 10;
   }

   public int $lessinit$greater$default$8() {
      return -1;
   }

   public boolean $lessinit$greater$default$9() {
      return false;
   }

   public boolean $lessinit$greater$default$10() {
      return false;
   }

   public int $lessinit$greater$default$11() {
      return 1;
   }

   public int $lessinit$greater$default$12() {
      return 30;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SpectralProjectedGradient$.class);
   }

   private SpectralProjectedGradient$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
