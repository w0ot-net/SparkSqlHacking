package spire.math;

public interface PolynomialOverSemiring$mcD$sp extends PolynomialOverSemiring {
   // $FF: synthetic method
   static Polynomial zero$(final PolynomialOverSemiring$mcD$sp $this) {
      return $this.zero();
   }

   default Polynomial zero() {
      return this.zero$mcD$sp();
   }

   // $FF: synthetic method
   static Polynomial zero$mcD$sp$(final PolynomialOverSemiring$mcD$sp $this) {
      return $this.zero$mcD$sp();
   }

   default Polynomial zero$mcD$sp() {
      return Polynomial$.MODULE$.zero$mDc$sp(this.eq$mcD$sp(), this.scalar$mcD$sp(), this.ct());
   }

   // $FF: synthetic method
   static Polynomial plus$(final PolynomialOverSemiring$mcD$sp $this, final Polynomial x, final Polynomial y) {
      return $this.plus(x, y);
   }

   default Polynomial plus(final Polynomial x, final Polynomial y) {
      return this.plus$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static Polynomial plus$mcD$sp$(final PolynomialOverSemiring$mcD$sp $this, final Polynomial x, final Polynomial y) {
      return $this.plus$mcD$sp(x, y);
   }

   default Polynomial plus$mcD$sp(final Polynomial x, final Polynomial y) {
      return x.$plus$mcD$sp(y, this.scalar$mcD$sp(), this.eq$mcD$sp());
   }

   // $FF: synthetic method
   static Polynomial times$(final PolynomialOverSemiring$mcD$sp $this, final Polynomial x, final Polynomial y) {
      return $this.times(x, y);
   }

   default Polynomial times(final Polynomial x, final Polynomial y) {
      return this.times$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static Polynomial times$mcD$sp$(final PolynomialOverSemiring$mcD$sp $this, final Polynomial x, final Polynomial y) {
      return $this.times$mcD$sp(x, y);
   }

   default Polynomial times$mcD$sp(final Polynomial x, final Polynomial y) {
      return x.$times$mcD$sp(y, this.scalar$mcD$sp(), this.eq$mcD$sp());
   }
}
