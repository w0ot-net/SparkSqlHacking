package spire.math;

public interface PolynomialEq$mcD$sp extends PolynomialEq {
   // $FF: synthetic method
   static boolean eqv$(final PolynomialEq$mcD$sp $this, final Polynomial x, final Polynomial y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final Polynomial x, final Polynomial y) {
      return this.eqv$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcD$sp$(final PolynomialEq$mcD$sp $this, final Polynomial x, final Polynomial y) {
      return $this.eqv$mcD$sp(x, y);
   }

   default boolean eqv$mcD$sp(final Polynomial x, final Polynomial y) {
      return spire.std.package.array$.MODULE$.ArrayEq$mDc$sp(this.eq$mcD$sp()).eqv(x.coeffsArray$mcD$sp(this.scalar$mcD$sp()), y.coeffsArray$mcD$sp(this.scalar$mcD$sp()));
   }
}
