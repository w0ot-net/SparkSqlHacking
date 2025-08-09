package spire.math;

public interface JetIsRing$mcF$sp extends JetIsRing {
   // $FF: synthetic method
   static Jet minus$(final JetIsRing$mcF$sp $this, final Jet a, final Jet b) {
      return $this.minus(a, b);
   }

   default Jet minus(final Jet a, final Jet b) {
      return this.minus$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static Jet minus$mcF$sp$(final JetIsRing$mcF$sp $this, final Jet a, final Jet b) {
      return $this.minus$mcF$sp(a, b);
   }

   default Jet minus$mcF$sp(final Jet a, final Jet b) {
      return a.$minus$mcF$sp(b, this.f$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet negate$(final JetIsRing$mcF$sp $this, final Jet a) {
      return $this.negate(a);
   }

   default Jet negate(final Jet a) {
      return this.negate$mcF$sp(a);
   }

   // $FF: synthetic method
   static Jet negate$mcF$sp$(final JetIsRing$mcF$sp $this, final Jet a) {
      return $this.negate$mcF$sp(a);
   }

   default Jet negate$mcF$sp(final Jet a) {
      return a.unary_$minus$mcF$sp(this.f$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet one$(final JetIsRing$mcF$sp $this) {
      return $this.one();
   }

   default Jet one() {
      return this.one$mcF$sp();
   }

   // $FF: synthetic method
   static Jet one$mcF$sp$(final JetIsRing$mcF$sp $this) {
      return $this.one$mcF$sp();
   }

   default Jet one$mcF$sp() {
      return Jet$.MODULE$.one$mFc$sp(this.c(), this.d(), this.f$mcF$sp());
   }

   // $FF: synthetic method
   static Jet plus$(final JetIsRing$mcF$sp $this, final Jet a, final Jet b) {
      return $this.plus(a, b);
   }

   default Jet plus(final Jet a, final Jet b) {
      return this.plus$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static Jet plus$mcF$sp$(final JetIsRing$mcF$sp $this, final Jet a, final Jet b) {
      return $this.plus$mcF$sp(a, b);
   }

   default Jet plus$mcF$sp(final Jet a, final Jet b) {
      return a.$plus$mcF$sp(b, this.f$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet pow$(final JetIsRing$mcF$sp $this, final Jet a, final int b) {
      return $this.pow(a, b);
   }

   default Jet pow(final Jet a, final int b) {
      return this.pow$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static Jet pow$mcF$sp$(final JetIsRing$mcF$sp $this, final Jet a, final int b) {
      return $this.pow$mcF$sp(a, b);
   }

   default Jet pow$mcF$sp(final Jet a, final int b) {
      return a.pow$mcF$sp(b, this.f$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet times$(final JetIsRing$mcF$sp $this, final Jet a, final Jet b) {
      return $this.times(a, b);
   }

   default Jet times(final Jet a, final Jet b) {
      return this.times$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static Jet times$mcF$sp$(final JetIsRing$mcF$sp $this, final Jet a, final Jet b) {
      return $this.times$mcF$sp(a, b);
   }

   default Jet times$mcF$sp(final Jet a, final Jet b) {
      return a.$times$mcF$sp(b, this.f$mcF$sp(), this.v$mcF$sp());
   }

   // $FF: synthetic method
   static Jet zero$(final JetIsRing$mcF$sp $this) {
      return $this.zero();
   }

   default Jet zero() {
      return this.zero$mcF$sp();
   }

   // $FF: synthetic method
   static Jet zero$mcF$sp$(final JetIsRing$mcF$sp $this) {
      return $this.zero$mcF$sp();
   }

   default Jet zero$mcF$sp() {
      return Jet$.MODULE$.zero$mFc$sp(this.c(), this.d(), this.f$mcF$sp());
   }

   // $FF: synthetic method
   static Jet fromInt$(final JetIsRing$mcF$sp $this, final int n) {
      return $this.fromInt(n);
   }

   default Jet fromInt(final int n) {
      return this.fromInt$mcF$sp(n);
   }

   // $FF: synthetic method
   static Jet fromInt$mcF$sp$(final JetIsRing$mcF$sp $this, final int n) {
      return $this.fromInt$mcF$sp(n);
   }

   default Jet fromInt$mcF$sp(final int n) {
      return Jet$.MODULE$.fromInt$mFc$sp(n, this.c(), this.d(), this.f$mcF$sp());
   }
}
