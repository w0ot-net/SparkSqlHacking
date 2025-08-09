package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.math.BigDecimal;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class DecimalNumber$ extends AbstractFunction1 implements Serializable {
   public static final DecimalNumber$ MODULE$ = new DecimalNumber$();

   public final String toString() {
      return "DecimalNumber";
   }

   public DecimalNumber apply(final BigDecimal n) {
      return new DecimalNumber(n);
   }

   public Option unapply(final DecimalNumber x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.n()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DecimalNumber$.class);
   }

   private DecimalNumber$() {
   }
}
