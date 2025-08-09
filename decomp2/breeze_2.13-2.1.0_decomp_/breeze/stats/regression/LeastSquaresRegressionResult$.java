package breeze.stats.regression;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class LeastSquaresRegressionResult$ extends AbstractFunction2 implements Serializable {
   public static final LeastSquaresRegressionResult$ MODULE$ = new LeastSquaresRegressionResult$();

   public final String toString() {
      return "LeastSquaresRegressionResult";
   }

   public LeastSquaresRegressionResult apply(final DenseVector coefficients, final double rSquared) {
      return new LeastSquaresRegressionResult(coefficients, rSquared);
   }

   public Option unapply(final LeastSquaresRegressionResult x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.coefficients(), BoxesRunTime.boxToDouble(x$0.rSquared()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LeastSquaresRegressionResult$.class);
   }

   private LeastSquaresRegressionResult$() {
   }
}
