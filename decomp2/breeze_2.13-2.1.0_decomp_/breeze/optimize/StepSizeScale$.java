package breeze.optimize;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class StepSizeScale$ extends AbstractFunction1 implements Serializable {
   public static final StepSizeScale$ MODULE$ = new StepSizeScale$();

   public double $lessinit$greater$default$1() {
      return (double)1.0F;
   }

   public final String toString() {
      return "StepSizeScale";
   }

   public StepSizeScale apply(final double alpha) {
      return new StepSizeScale(alpha);
   }

   public double apply$default$1() {
      return (double)1.0F;
   }

   public Option unapply(final StepSizeScale x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToDouble(x$0.alpha())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StepSizeScale$.class);
   }

   private StepSizeScale$() {
   }
}
