package spire.math;

import spire.algebra.RingAssociativeAlgebra$mcD$sp;

public interface ComplexOnCRing$mcD$sp extends ComplexOnCRing, RingAssociativeAlgebra$mcD$sp {
   // $FF: synthetic method
   static Complex minus$(final ComplexOnCRing$mcD$sp $this, final Complex a, final Complex b) {
      return $this.minus(a, b);
   }

   default Complex minus(final Complex a, final Complex b) {
      return this.minus$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static Complex minus$mcD$sp$(final ComplexOnCRing$mcD$sp $this, final Complex a, final Complex b) {
      return $this.minus$mcD$sp(a, b);
   }

   default Complex minus$mcD$sp(final Complex a, final Complex b) {
      return a.$minus$mcD$sp(b, this.scalar$mcD$sp());
   }

   // $FF: synthetic method
   static Complex negate$(final ComplexOnCRing$mcD$sp $this, final Complex a) {
      return $this.negate(a);
   }

   default Complex negate(final Complex a) {
      return this.negate$mcD$sp(a);
   }

   // $FF: synthetic method
   static Complex negate$mcD$sp$(final ComplexOnCRing$mcD$sp $this, final Complex a) {
      return $this.negate$mcD$sp(a);
   }

   default Complex negate$mcD$sp(final Complex a) {
      return a.unary_$minus$mcD$sp(this.scalar$mcD$sp());
   }

   // $FF: synthetic method
   static Complex one$(final ComplexOnCRing$mcD$sp $this) {
      return $this.one();
   }

   default Complex one() {
      return this.one$mcD$sp();
   }

   // $FF: synthetic method
   static Complex one$mcD$sp$(final ComplexOnCRing$mcD$sp $this) {
      return $this.one$mcD$sp();
   }

   default Complex one$mcD$sp() {
      return Complex$.MODULE$.one$mDc$sp(this.scalar$mcD$sp());
   }

   // $FF: synthetic method
   static Complex plus$(final ComplexOnCRing$mcD$sp $this, final Complex a, final Complex b) {
      return $this.plus(a, b);
   }

   default Complex plus(final Complex a, final Complex b) {
      return this.plus$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static Complex plus$mcD$sp$(final ComplexOnCRing$mcD$sp $this, final Complex a, final Complex b) {
      return $this.plus$mcD$sp(a, b);
   }

   default Complex plus$mcD$sp(final Complex a, final Complex b) {
      return a.$plus$mcD$sp(b, this.scalar$mcD$sp());
   }

   // $FF: synthetic method
   static Complex times$(final ComplexOnCRing$mcD$sp $this, final Complex a, final Complex b) {
      return $this.times(a, b);
   }

   default Complex times(final Complex a, final Complex b) {
      return this.times$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static Complex times$mcD$sp$(final ComplexOnCRing$mcD$sp $this, final Complex a, final Complex b) {
      return $this.times$mcD$sp(a, b);
   }

   default Complex times$mcD$sp(final Complex a, final Complex b) {
      return a.$times$mcD$sp(b, this.scalar$mcD$sp());
   }

   // $FF: synthetic method
   static Complex timesl$(final ComplexOnCRing$mcD$sp $this, final double a, final Complex v) {
      return $this.timesl(a, v);
   }

   default Complex timesl(final double a, final Complex v) {
      return this.timesl$mcD$sp(a, v);
   }

   // $FF: synthetic method
   static Complex timesl$mcD$sp$(final ComplexOnCRing$mcD$sp $this, final double a, final Complex v) {
      return $this.timesl$mcD$sp(a, v);
   }

   default Complex timesl$mcD$sp(final double a, final Complex v) {
      return (new Complex$mcD$sp(a, this.scalar$mcD$sp().zero$mcD$sp())).$times$mcD$sp(v, this.scalar$mcD$sp());
   }

   // $FF: synthetic method
   static Complex zero$(final ComplexOnCRing$mcD$sp $this) {
      return $this.zero();
   }

   default Complex zero() {
      return this.zero$mcD$sp();
   }

   // $FF: synthetic method
   static Complex zero$mcD$sp$(final ComplexOnCRing$mcD$sp $this) {
      return $this.zero$mcD$sp();
   }

   default Complex zero$mcD$sp() {
      return Complex$.MODULE$.zero$mDc$sp(this.scalar$mcD$sp());
   }

   // $FF: synthetic method
   static Complex adjoint$(final ComplexOnCRing$mcD$sp $this, final Complex a) {
      return $this.adjoint(a);
   }

   default Complex adjoint(final Complex a) {
      return this.adjoint$mcD$sp(a);
   }

   // $FF: synthetic method
   static Complex adjoint$mcD$sp$(final ComplexOnCRing$mcD$sp $this, final Complex a) {
      return $this.adjoint$mcD$sp(a);
   }

   default Complex adjoint$mcD$sp(final Complex a) {
      return a.conjugate$mcD$sp(this.scalar$mcD$sp());
   }

   // $FF: synthetic method
   static Complex fromInt$(final ComplexOnCRing$mcD$sp $this, final int n) {
      return $this.fromInt(n);
   }

   default Complex fromInt(final int n) {
      return this.fromInt$mcD$sp(n);
   }

   // $FF: synthetic method
   static Complex fromInt$mcD$sp$(final ComplexOnCRing$mcD$sp $this, final int n) {
      return $this.fromInt$mcD$sp(n);
   }

   default Complex fromInt$mcD$sp(final int n) {
      return Complex$.MODULE$.fromInt$mDc$sp(n, this.scalar$mcD$sp());
   }
}
