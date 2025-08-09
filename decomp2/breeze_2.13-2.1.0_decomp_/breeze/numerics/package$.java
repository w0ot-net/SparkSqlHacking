package breeze.numerics;

import scala.math.package.;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static final double breeze$numerics$package$$log2D;
   private static final double breeze$numerics$package$$log10D;
   private static final double inf;
   private static final double Inf;
   private static final double nan;
   private static final double NaN;

   static {
      breeze$numerics$package$$log2D = .MODULE$.log((double)2.0F);
      breeze$numerics$package$$log10D = .MODULE$.log((double)10.0F);
      inf = Double.POSITIVE_INFINITY;
      Inf = Double.POSITIVE_INFINITY;
      nan = Double.NaN;
      NaN = Double.NaN;
   }

   public double breeze$numerics$package$$log2D() {
      return breeze$numerics$package$$log2D;
   }

   public double breeze$numerics$package$$log10D() {
      return breeze$numerics$package$$log10D;
   }

   public double inf() {
      return inf;
   }

   public double Inf() {
      return Inf;
   }

   public double nan() {
      return nan;
   }

   public double NaN() {
      return NaN;
   }

   public double polyval(final double[] coefs, final double x) {
      int i = coefs.length - 1;

      double p;
      for(p = coefs[i]; i > 0; p = p * x + coefs[i]) {
         --i;
      }

      return p;
   }

   public boolean closeTo(final double a, final double b, final double relDiff) {
      return a == b || .MODULE$.abs(a - b) < .MODULE$.max(.MODULE$.max(.MODULE$.abs(a), .MODULE$.abs(b)), (double)1.0F) * relDiff;
   }

   public double closeTo$default$3() {
      return 1.0E-4;
   }

   private package$() {
   }
}
