package breeze.optimize;

import breeze.linalg.norm$;
import breeze.math.NormedModule;
import java.io.Serializable;
import scala.Function1;
import scala.math.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class FirstOrderMinimizer$ implements Serializable {
   public static final FirstOrderMinimizer$ MODULE$ = new FirstOrderMinimizer$();

   public int $lessinit$greater$default$1() {
      return -1;
   }

   public double $lessinit$greater$default$2() {
      return 1.0E-6;
   }

   public int $lessinit$greater$default$3() {
      return 100;
   }

   public boolean $lessinit$greater$default$4() {
      return true;
   }

   public FirstOrderMinimizer.ConvergenceCheck maxIterationsReached(final int maxIter) {
      return FirstOrderMinimizer.ConvergenceCheck$.MODULE$.fromPartialFunction(new Serializable(maxIter) {
         private static final long serialVersionUID = 0L;
         private final int maxIter$1;

         public final Object applyOrElse(final FirstOrderMinimizer.State x1, final Function1 default) {
            Object var3;
            if (x1 instanceof FirstOrderMinimizer.State && x1.iter() >= this.maxIter$1 && this.maxIter$1 >= 0) {
               var3 = FirstOrderMinimizer.MaxIterations$.MODULE$;
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final FirstOrderMinimizer.State x1) {
            boolean var2;
            if (x1 instanceof FirstOrderMinimizer.State && x1.iter() >= this.maxIter$1 && this.maxIter$1 >= 0) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }

         public {
            this.maxIter$1 = maxIter$1;
         }
      });
   }

   public FirstOrderMinimizer.ConvergenceCheck functionValuesConverged(final double tolerance, final boolean relative, final int historyLength) {
      return new FirstOrderMinimizer.FunctionValuesConverged(tolerance, relative, historyLength);
   }

   public double functionValuesConverged$default$1() {
      return 1.0E-9;
   }

   public boolean functionValuesConverged$default$2() {
      return true;
   }

   public int functionValuesConverged$default$3() {
      return 10;
   }

   public FirstOrderMinimizer.ConvergenceCheck gradientConverged(final double tolerance, final boolean relative, final NormedModule space) {
      return FirstOrderMinimizer.ConvergenceCheck$.MODULE$.fromPartialFunction(new Serializable(space, tolerance, relative) {
         private static final long serialVersionUID = 0L;
         private final NormedModule space$1;
         private final double tolerance$1;
         private final boolean relative$1;

         public final Object applyOrElse(final FirstOrderMinimizer.State x1, final Function1 default) {
            Object var3;
            if (x1 instanceof FirstOrderMinimizer.State && BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(x1.adjustedGradient(), this.space$1.normImpl())) <= .MODULE$.max(this.tolerance$1 * (this.relative$1 ? scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(x1.adjustedValue())) : (double)1.0F), 1.0E-8)) {
               var3 = FirstOrderMinimizer.GradientConverged$.MODULE$;
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final FirstOrderMinimizer.State x1) {
            boolean var2;
            if (x1 instanceof FirstOrderMinimizer.State && BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(x1.adjustedGradient(), this.space$1.normImpl())) <= .MODULE$.max(this.tolerance$1 * (this.relative$1 ? scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(x1.adjustedValue())) : (double)1.0F), 1.0E-8)) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }

         public {
            this.space$1 = space$1;
            this.tolerance$1 = tolerance$1;
            this.relative$1 = relative$1;
         }
      });
   }

   public boolean gradientConverged$default$2() {
      return true;
   }

   public FirstOrderMinimizer.ConvergenceCheck searchFailed() {
      return FirstOrderMinimizer.ConvergenceCheck$.MODULE$.fromPartialFunction(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final FirstOrderMinimizer.State x1, final Function1 default) {
            Object var3;
            if (x1 instanceof FirstOrderMinimizer.State && x1.searchFailed()) {
               var3 = FirstOrderMinimizer.SearchFailed$.MODULE$;
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final FirstOrderMinimizer.State x1) {
            boolean var2;
            if (x1 instanceof FirstOrderMinimizer.State && x1.searchFailed()) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }
      });
   }

   public FirstOrderMinimizer.ConvergenceCheck monitorFunctionValues(final Function1 f, final int numFailures, final double improvementRequirement, final int evalFrequency) {
      return new FirstOrderMinimizer.MonitorFunctionValuesCheck(f, numFailures, improvementRequirement, evalFrequency);
   }

   public int monitorFunctionValues$default$2() {
      return 5;
   }

   public double monitorFunctionValues$default$3() {
      return 0.01;
   }

   public int monitorFunctionValues$default$4() {
      return 10;
   }

   public FirstOrderMinimizer.ConvergenceCheck defaultConvergenceCheck(final int maxIter, final double tolerance, final boolean relative, final int fvalMemory, final NormedModule space) {
      return this.maxIterationsReached(maxIter).$bar$bar(this.functionValuesConverged(tolerance, relative, fvalMemory)).$bar$bar(this.gradientConverged(tolerance, relative, space)).$bar$bar(this.searchFailed());
   }

   public boolean defaultConvergenceCheck$default$3() {
      return true;
   }

   public int defaultConvergenceCheck$default$4() {
      return 20;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FirstOrderMinimizer$.class);
   }

   private FirstOrderMinimizer$() {
   }
}
