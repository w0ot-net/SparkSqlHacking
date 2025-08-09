package spire.math;

public interface JetIsField$mcF$sp extends JetIsField, JetIsEuclideanRing$mcF$sp {
   // $FF: synthetic method
   static Jet fromDouble$(final JetIsField$mcF$sp $this, final double n) {
      return $this.fromDouble(n);
   }

   default Jet fromDouble(final double n) {
      return this.fromDouble$mcF$sp(n);
   }

   // $FF: synthetic method
   static Jet fromDouble$mcF$sp$(final JetIsField$mcF$sp $this, final double n) {
      return $this.fromDouble$mcF$sp(n);
   }

   default Jet fromDouble$mcF$sp(final double n) {
      return Jet$.MODULE$.apply$mFc$sp(this.f$mcF$sp().fromDouble$mcF$sp(n), this.c(), this.d(), this.f$mcF$sp());
   }

   // $FF: synthetic method
   static Jet div$(final JetIsField$mcF$sp $this, final Jet a, final Jet b) {
      return $this.div(a, b);
   }

   default Jet div(final Jet a, final Jet b) {
      return this.div$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static Jet div$mcF$sp$(final JetIsField$mcF$sp $this, final Jet a, final Jet b) {
      return $this.div$mcF$sp(a, b);
   }

   default Jet div$mcF$sp(final Jet a, final Jet b) {
      return a.$div$mcF$sp(b, this.f$mcF$sp(), this.v$mcF$sp());
   }
}
