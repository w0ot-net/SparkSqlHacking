package spire.math;

import spire.algebra.RingAssociativeAlgebra$mcF$sp;

public interface ComplexOnCRing$mcF$sp extends ComplexOnCRing, RingAssociativeAlgebra$mcF$sp {
   // $FF: synthetic method
   static Complex minus$(final ComplexOnCRing$mcF$sp $this, final Complex a, final Complex b) {
      return $this.minus(a, b);
   }

   default Complex minus(final Complex a, final Complex b) {
      return this.minus$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static Complex minus$mcF$sp$(final ComplexOnCRing$mcF$sp $this, final Complex a, final Complex b) {
      return $this.minus$mcF$sp(a, b);
   }

   default Complex minus$mcF$sp(final Complex a, final Complex b) {
      return a.$minus$mcF$sp(b, this.scalar$mcF$sp());
   }

   // $FF: synthetic method
   static Complex negate$(final ComplexOnCRing$mcF$sp $this, final Complex a) {
      return $this.negate(a);
   }

   default Complex negate(final Complex a) {
      return this.negate$mcF$sp(a);
   }

   // $FF: synthetic method
   static Complex negate$mcF$sp$(final ComplexOnCRing$mcF$sp $this, final Complex a) {
      return $this.negate$mcF$sp(a);
   }

   default Complex negate$mcF$sp(final Complex a) {
      return a.unary_$minus$mcF$sp(this.scalar$mcF$sp());
   }

   // $FF: synthetic method
   static Complex one$(final ComplexOnCRing$mcF$sp $this) {
      return $this.one();
   }

   default Complex one() {
      return this.one$mcF$sp();
   }

   // $FF: synthetic method
   static Complex one$mcF$sp$(final ComplexOnCRing$mcF$sp $this) {
      return $this.one$mcF$sp();
   }

   default Complex one$mcF$sp() {
      return Complex$.MODULE$.one$mFc$sp(this.scalar$mcF$sp());
   }

   // $FF: synthetic method
   static Complex plus$(final ComplexOnCRing$mcF$sp $this, final Complex a, final Complex b) {
      return $this.plus(a, b);
   }

   default Complex plus(final Complex a, final Complex b) {
      return this.plus$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static Complex plus$mcF$sp$(final ComplexOnCRing$mcF$sp $this, final Complex a, final Complex b) {
      return $this.plus$mcF$sp(a, b);
   }

   default Complex plus$mcF$sp(final Complex a, final Complex b) {
      return a.$plus$mcF$sp(b, this.scalar$mcF$sp());
   }

   // $FF: synthetic method
   static Complex times$(final ComplexOnCRing$mcF$sp $this, final Complex a, final Complex b) {
      return $this.times(a, b);
   }

   default Complex times(final Complex a, final Complex b) {
      return this.times$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static Complex times$mcF$sp$(final ComplexOnCRing$mcF$sp $this, final Complex a, final Complex b) {
      return $this.times$mcF$sp(a, b);
   }

   default Complex times$mcF$sp(final Complex a, final Complex b) {
      return a.$times$mcF$sp(b, this.scalar$mcF$sp());
   }

   // $FF: synthetic method
   static Complex timesl$(final ComplexOnCRing$mcF$sp $this, final float a, final Complex v) {
      return $this.timesl(a, v);
   }

   default Complex timesl(final float a, final Complex v) {
      return this.timesl$mcF$sp(a, v);
   }

   // $FF: synthetic method
   static Complex timesl$mcF$sp$(final ComplexOnCRing$mcF$sp $this, final float a, final Complex v) {
      return $this.timesl$mcF$sp(a, v);
   }

   default Complex timesl$mcF$sp(final float a, final Complex v) {
      return (new Complex$mcF$sp(a, this.scalar$mcF$sp().zero$mcF$sp())).$times$mcF$sp(v, this.scalar$mcF$sp());
   }

   // $FF: synthetic method
   static Complex zero$(final ComplexOnCRing$mcF$sp $this) {
      return $this.zero();
   }

   default Complex zero() {
      return this.zero$mcF$sp();
   }

   // $FF: synthetic method
   static Complex zero$mcF$sp$(final ComplexOnCRing$mcF$sp $this) {
      return $this.zero$mcF$sp();
   }

   default Complex zero$mcF$sp() {
      return Complex$.MODULE$.zero$mFc$sp(this.scalar$mcF$sp());
   }

   // $FF: synthetic method
   static Complex adjoint$(final ComplexOnCRing$mcF$sp $this, final Complex a) {
      return $this.adjoint(a);
   }

   default Complex adjoint(final Complex a) {
      return this.adjoint$mcF$sp(a);
   }

   // $FF: synthetic method
   static Complex adjoint$mcF$sp$(final ComplexOnCRing$mcF$sp $this, final Complex a) {
      return $this.adjoint$mcF$sp(a);
   }

   default Complex adjoint$mcF$sp(final Complex a) {
      return a.conjugate$mcF$sp(this.scalar$mcF$sp());
   }

   // $FF: synthetic method
   static Complex fromInt$(final ComplexOnCRing$mcF$sp $this, final int n) {
      return $this.fromInt(n);
   }

   default Complex fromInt(final int n) {
      return this.fromInt$mcF$sp(n);
   }

   // $FF: synthetic method
   static Complex fromInt$mcF$sp$(final ComplexOnCRing$mcF$sp $this, final int n) {
      return $this.fromInt$mcF$sp(n);
   }

   default Complex fromInt$mcF$sp(final int n) {
      return Complex$.MODULE$.fromInt$mFc$sp(n, this.scalar$mcF$sp());
   }
}
