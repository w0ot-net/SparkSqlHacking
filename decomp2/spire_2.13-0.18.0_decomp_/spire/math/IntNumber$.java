package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class IntNumber$ extends AbstractFunction1 implements Serializable {
   public static final IntNumber$ MODULE$ = new IntNumber$();

   public final String toString() {
      return "IntNumber";
   }

   public IntNumber apply(final SafeLong n) {
      return new IntNumber(n);
   }

   public Option unapply(final IntNumber x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.n()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IntNumber$.class);
   }

   private IntNumber$() {
   }
}
