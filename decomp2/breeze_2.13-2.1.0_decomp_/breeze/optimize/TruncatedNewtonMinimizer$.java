package breeze.optimize;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class TruncatedNewtonMinimizer$ implements Serializable {
   public static final TruncatedNewtonMinimizer$ MODULE$ = new TruncatedNewtonMinimizer$();

   public int $lessinit$greater$default$1() {
      return -1;
   }

   public double $lessinit$greater$default$2() {
      return 1.0E-6;
   }

   public double $lessinit$greater$default$3() {
      return (double)0.0F;
   }

   public int $lessinit$greater$default$4() {
      return 0;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TruncatedNewtonMinimizer$.class);
   }

   private TruncatedNewtonMinimizer$() {
   }
}
