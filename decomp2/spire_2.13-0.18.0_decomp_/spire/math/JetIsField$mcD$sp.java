package spire.math;

public interface JetIsField$mcD$sp extends JetIsField, JetIsEuclideanRing$mcD$sp {
   // $FF: synthetic method
   static Jet fromDouble$(final JetIsField$mcD$sp $this, final double n) {
      return $this.fromDouble(n);
   }

   default Jet fromDouble(final double n) {
      return this.fromDouble$mcD$sp(n);
   }

   // $FF: synthetic method
   static Jet fromDouble$mcD$sp$(final JetIsField$mcD$sp $this, final double n) {
      return $this.fromDouble$mcD$sp(n);
   }

   default Jet fromDouble$mcD$sp(final double n) {
      return Jet$.MODULE$.apply$mDc$sp(this.f$mcD$sp().fromDouble$mcD$sp(n), this.c(), this.d(), this.f$mcD$sp());
   }

   // $FF: synthetic method
   static Jet div$(final JetIsField$mcD$sp $this, final Jet a, final Jet b) {
      return $this.div(a, b);
   }

   default Jet div(final Jet a, final Jet b) {
      return this.div$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static Jet div$mcD$sp$(final JetIsField$mcD$sp $this, final Jet a, final Jet b) {
      return $this.div$mcD$sp(a, b);
   }

   default Jet div$mcD$sp(final Jet a, final Jet b) {
      return a.$div$mcD$sp(b, this.f$mcD$sp(), this.v$mcD$sp());
   }
}
