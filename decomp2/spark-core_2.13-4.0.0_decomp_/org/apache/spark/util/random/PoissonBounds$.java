package org.apache.spark.util.random;

import scala.math.package.;

public final class PoissonBounds$ {
   public static final PoissonBounds$ MODULE$ = new PoissonBounds$();

   public double getLowerBound(final double s) {
      return .MODULE$.max(s - this.numStd(s) * .MODULE$.sqrt(s), 1.0E-15);
   }

   public double getUpperBound(final double s) {
      return .MODULE$.max(s + this.numStd(s) * .MODULE$.sqrt(s), 1.0E-10);
   }

   private double numStd(final double s) {
      if (s < (double)6.0F) {
         return (double)12.0F;
      } else {
         return s < (double)16.0F ? (double)9.0F : (double)6.0F;
      }
   }

   private PoissonBounds$() {
   }
}
