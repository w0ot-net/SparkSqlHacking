package spire.math;

public interface PolynomialOverRing$mcD$sp extends PolynomialOverRing, PolynomialOverRng$mcD$sp {
   // $FF: synthetic method
   static Polynomial one$(final PolynomialOverRing$mcD$sp $this) {
      return $this.one();
   }

   default Polynomial one() {
      return this.one$mcD$sp();
   }

   // $FF: synthetic method
   static Polynomial one$mcD$sp$(final PolynomialOverRing$mcD$sp $this) {
      return $this.one$mcD$sp();
   }

   default Polynomial one$mcD$sp() {
      return Polynomial$.MODULE$.one$mDc$sp(this.eq$mcD$sp(), this.scalar$mcD$sp(), this.ct());
   }
}
