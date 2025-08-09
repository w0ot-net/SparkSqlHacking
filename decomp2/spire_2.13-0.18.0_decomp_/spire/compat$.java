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
import spire.math.ConvertableFrom;

public final class compat$ implements CompatPriority3 {
   public static final compat$ MODULE$ = new compat$();

   static {
      CompatPriority1.$init$(MODULE$);
      CompatPriority2.$init$(MODULE$);
      CompatPriority3.$init$(MODULE$);
   }

   public Integral integral(final EuclideanRing evidence$12, final ConvertableFrom evidence$13, final Signed evidence$14, final Order evidence$15) {
      return CompatPriority3.integral$(this, evidence$12, evidence$13, evidence$14, evidence$15);
   }

   public Fractional fractional(final Field evidence$8, final ConvertableFrom evidence$9, final Signed evidence$10, final Order evidence$11) {
      return CompatPriority2.fractional$(this, evidence$8, evidence$9, evidence$10, evidence$11);
   }

   public Numeric numeric(final Ring evidence$1, final ConvertableFrom evidence$2, final Signed evidence$3, final Order evidence$4) {
      return CompatPriority1.numeric$(this, evidence$1, evidence$2, evidence$3, evidence$4);
   }

   public Ordering ordering(final Order evidence$5) {
      return CompatPriority1.ordering$(this, evidence$5);
   }

   public PartialOrdering partialOrdering(final PartialOrder evidence$6) {
      return CompatPriority1.partialOrdering$(this, evidence$6);
   }

   public Equiv equiv(final Eq evidence$7) {
      return CompatPriority1.equiv$(this, evidence$7);
   }

   private compat$() {
   }
}
