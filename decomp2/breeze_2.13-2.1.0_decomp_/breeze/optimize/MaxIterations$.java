package breeze.optimize;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class MaxIterations$ extends AbstractFunction1 implements Serializable {
   public static final MaxIterations$ MODULE$ = new MaxIterations$();

   public final String toString() {
      return "MaxIterations";
   }

   public MaxIterations apply(final int num) {
      return new MaxIterations(num);
   }

   public Option unapply(final MaxIterations x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.num())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MaxIterations$.class);
   }

   private MaxIterations$() {
   }
}
