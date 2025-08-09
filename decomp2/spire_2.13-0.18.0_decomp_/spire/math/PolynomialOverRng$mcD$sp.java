package spire.math;

public interface PolynomialOverRng$mcD$sp extends PolynomialOverRng, PolynomialOverSemiring$mcD$sp {
   // $FF: synthetic method
   static Polynomial timesl$(final PolynomialOverRng$mcD$sp $this, final double r, final Polynomial v) {
      return $this.timesl(r, v);
   }

   default Polynomial timesl(final double r, final Polynomial v) {
      return this.timesl$mcD$sp(r, v);
   }

   // $FF: synthetic method
   static Polynomial timesl$mcD$sp$(final PolynomialOverRng$mcD$sp $this, final double r, final Polynomial v) {
      return $this.timesl$mcD$sp(r, v);
   }

   default Polynomial timesl$mcD$sp(final double r, final Polynomial v) {
      return v.$times$colon$mcD$sp(r, this.scalar$mcD$sp(), this.eq$mcD$sp());
   }

   // $FF: synthetic method
   static Polynomial negate$(final PolynomialOverRng$mcD$sp $this, final Polynomial x) {
      return $this.negate(x);
   }

   default Polynomial negate(final Polynomial x) {
      return this.negate$mcD$sp(x);
   }

   // $FF: synthetic method
   static Polynomial negate$mcD$sp$(final PolynomialOverRng$mcD$sp $this, final Polynomial x) {
      return $this.negate$mcD$sp(x);
   }

   default Polynomial negate$mcD$sp(final Polynomial x) {
      return x.unary_$minus$mcD$sp(this.scalar$mcD$sp());
   }
}
