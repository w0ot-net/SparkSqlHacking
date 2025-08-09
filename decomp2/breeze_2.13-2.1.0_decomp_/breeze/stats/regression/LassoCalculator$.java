package breeze.stats.regression;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class LassoCalculator$ extends AbstractFunction6 implements Serializable {
   public static final LassoCalculator$ MODULE$ = new LassoCalculator$();

   public int $lessinit$greater$default$5() {
      return 100;
   }

   public double $lessinit$greater$default$6() {
      return 1.0E-8;
   }

   public final String toString() {
      return "LassoCalculator";
   }

   public LassoCalculator apply(final DenseMatrix data, final DenseVector outputs, final double lambda, final double[] workArray, final int MAX_ITER, final double IMPROVE_THRESHOLD) {
      return new LassoCalculator(data, outputs, lambda, workArray, MAX_ITER, IMPROVE_THRESHOLD);
   }

   public int apply$default$5() {
      return 100;
   }

   public double apply$default$6() {
      return 1.0E-8;
   }

   public Option unapply(final LassoCalculator x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(x$0.data(), x$0.outputs(), BoxesRunTime.boxToDouble(x$0.lambda()), x$0.workArray(), BoxesRunTime.boxToInteger(x$0.MAX_ITER()), BoxesRunTime.boxToDouble(x$0.IMPROVE_THRESHOLD()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LassoCalculator$.class);
   }

   private LassoCalculator$() {
   }
}
