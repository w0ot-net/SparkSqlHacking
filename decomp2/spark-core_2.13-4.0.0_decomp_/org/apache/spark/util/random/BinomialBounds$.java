package org.apache.spark.util.random;

import scala.math.package.;

public final class BinomialBounds$ {
   public static final BinomialBounds$ MODULE$ = new BinomialBounds$();
   private static final double minSamplingRate = 1.0E-10;

   public double minSamplingRate() {
      return minSamplingRate;
   }

   public double getLowerBound(final double delta, final long n, final double fraction) {
      double gamma = -.MODULE$.log(delta) / (double)n * 0.6666666666666666;
      return fraction + gamma - .MODULE$.sqrt(gamma * gamma + (double)3 * gamma * fraction);
   }

   public double getUpperBound(final double delta, final long n, final double fraction) {
      double gamma = -.MODULE$.log(delta) / (double)n;
      return .MODULE$.min((double)1.0F, .MODULE$.max(this.minSamplingRate(), fraction + gamma + .MODULE$.sqrt(gamma * gamma + (double)2 * gamma * fraction)));
   }

   private BinomialBounds$() {
   }
}
