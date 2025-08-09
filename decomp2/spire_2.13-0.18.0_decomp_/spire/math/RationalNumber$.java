package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class RationalNumber$ extends AbstractFunction1 implements Serializable {
   public static final RationalNumber$ MODULE$ = new RationalNumber$();

   public final String toString() {
      return "RationalNumber";
   }

   public RationalNumber apply(final Rational n) {
      return new RationalNumber(n);
   }

   public Option unapply(final RationalNumber x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.n()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RationalNumber$.class);
   }

   private RationalNumber$() {
   }
}
