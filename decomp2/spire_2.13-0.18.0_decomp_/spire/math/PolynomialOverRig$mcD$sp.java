package spire.math;

public interface PolynomialOverRig$mcD$sp extends PolynomialOverRig, PolynomialOverSemiring$mcD$sp {
   // $FF: synthetic method
   static Polynomial one$(final PolynomialOverRig$mcD$sp $this) {
      return $this.one();
   }

   default Polynomial one() {
      return this.one$mcD$sp();
   }

   // $FF: synthetic method
   static Polynomial one$mcD$sp$(final PolynomialOverRig$mcD$sp $this) {
      return $this.one$mcD$sp();
   }

   default Polynomial one$mcD$sp() {
      return Polynomial$.MODULE$.one$mDc$sp(this.eq$mcD$sp(), this.scalar$mcD$sp(), this.ct());
   }
}
