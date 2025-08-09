package breeze.signal.support;

import breeze.numerics.package$ceil$ceilDoubleImpl$;
import breeze.numerics.package$log$logDoubleImpl$;
import breeze.numerics.package$pow$powDoubleDoubleImpl$;

public final class package$ {
   public static final package$ MODULE$ = new package$();

   public double nextPower(final double x, final int base) {
      return breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp((double)2.0F, breeze.numerics.package.ceil$.MODULE$.apply$mDDc$sp(breeze.numerics.package.log$.MODULE$.apply$mDDc$sp(x, package$log$logDoubleImpl$.MODULE$) / breeze.numerics.package.log$.MODULE$.apply$mDDc$sp((double)base, package$log$logDoubleImpl$.MODULE$), package$ceil$ceilDoubleImpl$.MODULE$), package$pow$powDoubleDoubleImpl$.MODULE$);
   }

   public double nextPower2(final double x) {
      return this.nextPower(x, 2);
   }

   private package$() {
   }
}
