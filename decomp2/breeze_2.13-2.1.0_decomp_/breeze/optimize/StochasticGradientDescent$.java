package breeze.optimize;

import breeze.math.NormedModule;
import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class StochasticGradientDescent$ implements Serializable {
   public static final StochasticGradientDescent$ MODULE$ = new StochasticGradientDescent$();

   public double $lessinit$greater$default$3() {
      return 1.0E-5;
   }

   public int $lessinit$greater$default$4() {
      return 100;
   }

   public StochasticGradientDescent apply(final double initialStepSize, final int maxIter, final NormedModule vs) {
      return new StochasticGradientDescent.SimpleSGD(initialStepSize, maxIter, vs);
   }

   public double apply$default$1() {
      return (double)4.0F;
   }

   public int apply$default$2() {
      return 100;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StochasticGradientDescent$.class);
   }

   private StochasticGradientDescent$() {
   }
}
