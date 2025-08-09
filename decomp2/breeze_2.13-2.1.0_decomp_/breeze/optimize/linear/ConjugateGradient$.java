package breeze.optimize.linear;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class ConjugateGradient$ implements Serializable {
   public static final ConjugateGradient$ MODULE$ = new ConjugateGradient$();

   public double $lessinit$greater$default$1() {
      return Double.POSITIVE_INFINITY;
   }

   public int $lessinit$greater$default$2() {
      return -1;
   }

   public double $lessinit$greater$default$3() {
      return (double)0.0F;
   }

   public double $lessinit$greater$default$4() {
      return 1.0E-5;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ConjugateGradient$.class);
   }

   private ConjugateGradient$() {
   }
}
