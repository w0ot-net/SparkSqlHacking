package breeze.stats.regression;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class LassoResult$ extends AbstractFunction3 implements Serializable {
   public static final LassoResult$ MODULE$ = new LassoResult$();

   public final String toString() {
      return "LassoResult";
   }

   public LassoResult apply(final DenseVector coefficients, final double rSquared, final double lambda) {
      return new LassoResult(coefficients, rSquared, lambda);
   }

   public Option unapply(final LassoResult x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.coefficients(), BoxesRunTime.boxToDouble(x$0.rSquared()), BoxesRunTime.boxToDouble(x$0.lambda()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LassoResult$.class);
   }

   private LassoResult$() {
   }
}
