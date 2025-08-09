package breeze.optimize;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class StochasticAveragedGradient$ implements Serializable {
   public static final StochasticAveragedGradient$ MODULE$ = new StochasticAveragedGradient$();

   public int $lessinit$greater$default$1() {
      return -1;
   }

   public double $lessinit$greater$default$2() {
      return (double)0.25F;
   }

   public int $lessinit$greater$default$3() {
      return 10;
   }

   public double $lessinit$greater$default$4() {
      return (double)0.0F;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StochasticAveragedGradient$.class);
   }

   private StochasticAveragedGradient$() {
   }
}
