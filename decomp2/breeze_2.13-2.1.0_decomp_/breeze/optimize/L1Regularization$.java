package breeze.optimize;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class L1Regularization$ extends AbstractFunction1 implements Serializable {
   public static final L1Regularization$ MODULE$ = new L1Regularization$();

   public double $lessinit$greater$default$1() {
      return (double)1.0F;
   }

   public final String toString() {
      return "L1Regularization";
   }

   public L1Regularization apply(final double value) {
      return new L1Regularization(value);
   }

   public double apply$default$1() {
      return (double)1.0F;
   }

   public Option unapply(final L1Regularization x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToDouble(x$0.value())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(L1Regularization$.class);
   }

   private L1Regularization$() {
   }
}
