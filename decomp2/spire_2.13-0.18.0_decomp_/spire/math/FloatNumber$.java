package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class FloatNumber$ extends AbstractFunction1 implements Serializable {
   public static final FloatNumber$ MODULE$ = new FloatNumber$();

   public final String toString() {
      return "FloatNumber";
   }

   public FloatNumber apply(final double n) {
      return new FloatNumber(n);
   }

   public Option unapply(final FloatNumber x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToDouble(x$0.n())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FloatNumber$.class);
   }

   private FloatNumber$() {
   }
}
