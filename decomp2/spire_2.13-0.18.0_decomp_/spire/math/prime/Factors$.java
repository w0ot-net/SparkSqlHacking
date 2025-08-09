package spire.math.prime;

import algebra.ring.Signed;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.math.BigInt;
import scala.runtime.ModuleSerializationProxy;
import spire.math.SafeLong;
import spire.math.SafeLong$;

public final class Factors$ implements Serializable {
   public static final Factors$ MODULE$ = new Factors$();
   private static final Factors zero;
   private static final Factors one;

   static {
      zero = new Factors(.MODULE$.Map().empty(), algebra.ring.Signed.Zero..MODULE$);
      one = new Factors(.MODULE$.Map().empty(), algebra.ring.Signed.Positive..MODULE$);
   }

   public Factors zero() {
      return zero;
   }

   public Factors one() {
      return one;
   }

   public Factors apply(final long n) {
      return package$.MODULE$.factor(SafeLong$.MODULE$.apply(n));
   }

   public Factors apply(final BigInt n) {
      return package$.MODULE$.factor(SafeLong$.MODULE$.apply(n));
   }

   public Factors apply(final SafeLong n) {
      return package$.MODULE$.factor(n);
   }

   public Factors apply(final String s) {
      return package$.MODULE$.factor(SafeLong$.MODULE$.apply(s));
   }

   public Factors apply(final Map elements, final Signed.Sign sign) {
      return new Factors(elements, sign);
   }

   public Option unapply(final Factors x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.elements(), x$0.sign())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Factors$.class);
   }

   private Factors$() {
   }
}
