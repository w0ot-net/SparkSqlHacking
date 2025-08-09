package spire.math;

import java.io.Serializable;
import java.math.BigInteger;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class SafeLongBigInteger$ extends AbstractFunction1 implements Serializable {
   public static final SafeLongBigInteger$ MODULE$ = new SafeLongBigInteger$();

   public final String toString() {
      return "SafeLongBigInteger";
   }

   public SafeLongBigInteger apply(final BigInteger x) {
      return new SafeLongBigInteger(x);
   }

   public Option unapply(final SafeLongBigInteger x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.x()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SafeLongBigInteger$.class);
   }

   private SafeLongBigInteger$() {
   }
}
