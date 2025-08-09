package breeze.optimize;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.TensorLike;
import breeze.linalg.norm$;
import breeze.linalg.operators.HasOps$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction2;

public final class LBFGSB$ implements Serializable {
   public static final LBFGSB$ MODULE$ = new LBFGSB$();
   private static final double PROJ_GRADIENT_EPS = 1.0E-5;

   public int $lessinit$greater$default$3() {
      return 100;
   }

   public int $lessinit$greater$default$4() {
      return 5;
   }

   public double $lessinit$greater$default$5() {
      return 1.0E-8;
   }

   public int $lessinit$greater$default$6() {
      return 64;
   }

   public int $lessinit$greater$default$7() {
      return 64;
   }

   public FirstOrderMinimizer.ConvergenceCheck defaultConvergenceCheck(final DenseVector lowerBounds, final DenseVector upperBounds, final double tolerance, final int maxIter) {
      return this.bfgsbConvergenceTest(lowerBounds, upperBounds).$bar$bar(FirstOrderMinimizer$.MODULE$.defaultConvergenceCheck(maxIter, tolerance, FirstOrderMinimizer$.MODULE$.defaultConvergenceCheck$default$3(), FirstOrderMinimizer$.MODULE$.defaultConvergenceCheck$default$4(), DenseVector$.MODULE$.space_Double()));
   }

   public double PROJ_GRADIENT_EPS() {
      return PROJ_GRADIENT_EPS;
   }

   public FirstOrderMinimizer.ConvergenceCheck bfgsbConvergenceTest(final DenseVector lowerBounds, final DenseVector upperBounds) {
      return FirstOrderMinimizer.ConvergenceCheck$.MODULE$.fromPartialFunction(new Serializable(lowerBounds, upperBounds) {
         private static final long serialVersionUID = 0L;
         private final DenseVector lowerBounds$1;
         private final DenseVector upperBounds$1;

         public final Object applyOrElse(final FirstOrderMinimizer.State x1, final Function1 default) {
            Object var3;
            if (LBFGSB$.MODULE$.breeze$optimize$LBFGSB$$boundedConvCheck(x1, this.lowerBounds$1, this.upperBounds$1)) {
               var3 = FirstOrderMinimizer.ProjectedStepConverged$.MODULE$;
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final FirstOrderMinimizer.State x1) {
            boolean var2;
            if (LBFGSB$.MODULE$.breeze$optimize$LBFGSB$$boundedConvCheck(x1, this.lowerBounds$1, this.upperBounds$1)) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }

         public {
            this.lowerBounds$1 = lowerBounds$1;
            this.upperBounds$1 = upperBounds$1;
         }
      });
   }

   public boolean breeze$optimize$LBFGSB$$boundedConvCheck(final FirstOrderMinimizer.State state, final DenseVector lowerBounds, final DenseVector upperBounds) {
      DenseVector x = (DenseVector)state.x();
      DenseVector g = (DenseVector)state.grad();
      DenseVector pMinusX = (DenseVector)((TensorLike)x.$minus(g, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double())).mapPairs$mcID$sp((JFunction2.mcDID.sp)(i, v) -> v <= lowerBounds.apply$mcD$sp(i) ? lowerBounds.apply$mcD$sp(i) - x.apply$mcD$sp(i) : (upperBounds.apply$mcD$sp(i) <= v ? upperBounds.apply$mcD$sp(i) - x.apply$mcD$sp(i) : v - x.apply$mcD$sp(i)), HasOps$.MODULE$.canMapPairs(.MODULE$.Double()));
      double normPMinusX = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(pMinusX, BoxesRunTime.boxToDouble(Double.POSITIVE_INFINITY), norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double())));
      return scala.math.package..MODULE$.abs(normPMinusX) < this.PROJ_GRADIENT_EPS();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LBFGSB$.class);
   }

   private LBFGSB$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
