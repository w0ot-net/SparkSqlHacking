package spire.math;

public interface JetIsRing$mcD$sp extends JetIsRing {
   // $FF: synthetic method
   static Jet minus$(final JetIsRing$mcD$sp $this, final Jet a, final Jet b) {
      return $this.minus(a, b);
   }

   default Jet minus(final Jet a, final Jet b) {
      return this.minus$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static Jet minus$mcD$sp$(final JetIsRing$mcD$sp $this, final Jet a, final Jet b) {
      return $this.minus$mcD$sp(a, b);
   }

   default Jet minus$mcD$sp(final Jet a, final Jet b) {
      return a.$minus$mcD$sp(b, this.f$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet negate$(final JetIsRing$mcD$sp $this, final Jet a) {
      return $this.negate(a);
   }

   default Jet negate(final Jet a) {
      return this.negate$mcD$sp(a);
   }

   // $FF: synthetic method
   static Jet negate$mcD$sp$(final JetIsRing$mcD$sp $this, final Jet a) {
      return $this.negate$mcD$sp(a);
   }

   default Jet negate$mcD$sp(final Jet a) {
      return a.unary_$minus$mcD$sp(this.f$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet one$(final JetIsRing$mcD$sp $this) {
      return $this.one();
   }

   default Jet one() {
      return this.one$mcD$sp();
   }

   // $FF: synthetic method
   static Jet one$mcD$sp$(final JetIsRing$mcD$sp $this) {
      return $this.one$mcD$sp();
   }

   default Jet one$mcD$sp() {
      return Jet$.MODULE$.one$mDc$sp(this.c(), this.d(), this.f$mcD$sp());
   }

   // $FF: synthetic method
   static Jet plus$(final JetIsRing$mcD$sp $this, final Jet a, final Jet b) {
      return $this.plus(a, b);
   }

   default Jet plus(final Jet a, final Jet b) {
      return this.plus$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static Jet plus$mcD$sp$(final JetIsRing$mcD$sp $this, final Jet a, final Jet b) {
      return $this.plus$mcD$sp(a, b);
   }

   default Jet plus$mcD$sp(final Jet a, final Jet b) {
      return a.$plus$mcD$sp(b, this.f$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet pow$(final JetIsRing$mcD$sp $this, final Jet a, final int b) {
      return $this.pow(a, b);
   }

   default Jet pow(final Jet a, final int b) {
      return this.pow$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static Jet pow$mcD$sp$(final JetIsRing$mcD$sp $this, final Jet a, final int b) {
      return $this.pow$mcD$sp(a, b);
   }

   default Jet pow$mcD$sp(final Jet a, final int b) {
      return a.pow$mcD$sp(b, this.f$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet times$(final JetIsRing$mcD$sp $this, final Jet a, final Jet b) {
      return $this.times(a, b);
   }

   default Jet times(final Jet a, final Jet b) {
      return this.times$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static Jet times$mcD$sp$(final JetIsRing$mcD$sp $this, final Jet a, final Jet b) {
      return $this.times$mcD$sp(a, b);
   }

   default Jet times$mcD$sp(final Jet a, final Jet b) {
      return a.$times$mcD$sp(b, this.f$mcD$sp(), this.v$mcD$sp());
   }

   // $FF: synthetic method
   static Jet zero$(final JetIsRing$mcD$sp $this) {
      return $this.zero();
   }

   default Jet zero() {
      return this.zero$mcD$sp();
   }

   // $FF: synthetic method
   static Jet zero$mcD$sp$(final JetIsRing$mcD$sp $this) {
      return $this.zero$mcD$sp();
   }

   default Jet zero$mcD$sp() {
      return Jet$.MODULE$.zero$mDc$sp(this.c(), this.d(), this.f$mcD$sp());
   }

   // $FF: synthetic method
   static Jet fromInt$(final JetIsRing$mcD$sp $this, final int n) {
      return $this.fromInt(n);
   }

   default Jet fromInt(final int n) {
      return this.fromInt$mcD$sp(n);
   }

   // $FF: synthetic method
   static Jet fromInt$mcD$sp$(final JetIsRing$mcD$sp $this, final int n) {
      return $this.fromInt$mcD$sp(n);
   }

   default Jet fromInt$mcD$sp(final int n) {
      return Jet$.MODULE$.fromInt$mDc$sp(n, this.c(), this.d(), this.f$mcD$sp());
   }
}
