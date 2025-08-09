package spire;

import algebra.ring.EuclideanRing;
import algebra.ring.Field;
import algebra.ring.Ring;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.math.Equiv;
import scala.math.Fractional;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.PartialOrdering;
import scala.reflect.ScalaSignature;
import spire.math.ConvertableFrom;

@ScalaSignature(
   bytes = "\u0006\u0005Y9Qa\u0001\u0003\t\u0002\u001d1Q!\u0003\u0003\t\u0002)AQ\u0001F\u0001\u0005\u0002U\taaY8na\u0006$(\"A\u0003\u0002\u000bM\u0004\u0018N]3\u0004\u0001A\u0011\u0001\"A\u0007\u0002\t\t11m\\7qCR\u001c2!A\u0006\u0012!\taq\"D\u0001\u000e\u0015\u0005q\u0011!B:dC2\f\u0017B\u0001\t\u000e\u0005\u0019\te.\u001f*fMB\u0011\u0001BE\u0005\u0003'\u0011\u0011qbQ8na\u0006$\bK]5pe&$\u0018pM\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u001d\u0001"
)
public final class compat {
   public static Integral integral(final EuclideanRing evidence$12, final ConvertableFrom evidence$13, final Signed evidence$14, final Order evidence$15) {
      return compat$.MODULE$.integral(evidence$12, evidence$13, evidence$14, evidence$15);
   }

   public static Fractional fractional(final Field evidence$8, final ConvertableFrom evidence$9, final Signed evidence$10, final Order evidence$11) {
      return compat$.MODULE$.fractional(evidence$8, evidence$9, evidence$10, evidence$11);
   }

   public static Equiv equiv(final Eq evidence$7) {
      return compat$.MODULE$.equiv(evidence$7);
   }

   public static PartialOrdering partialOrdering(final PartialOrder evidence$6) {
      return compat$.MODULE$.partialOrdering(evidence$6);
   }

   public static Ordering ordering(final Order evidence$5) {
      return compat$.MODULE$.ordering(evidence$5);
   }

   public static Numeric numeric(final Ring evidence$1, final ConvertableFrom evidence$2, final Signed evidence$3, final Order evidence$4) {
      return compat$.MODULE$.numeric(evidence$1, evidence$2, evidence$3, evidence$4);
   }
}
