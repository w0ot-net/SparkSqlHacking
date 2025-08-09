package spire.math;

import algebra.ring.CommutativeRing;
import spire.algebra.CModule$mcF$sp;

public final class ComplexOnCRingImpl$mcF$sp extends ComplexOnCRingImpl implements ComplexOnCRing$mcF$sp {
   private static final long serialVersionUID = 1L;
   public final CommutativeRing scalar$mcF$sp;

   public Complex minus(final Complex a, final Complex b) {
      return ComplexOnCRing$mcF$sp.minus$(this, a, b);
   }

   public Complex minus$mcF$sp(final Complex a, final Complex b) {
      return ComplexOnCRing$mcF$sp.minus$mcF$sp$(this, a, b);
   }

   public Complex negate(final Complex a) {
      return ComplexOnCRing$mcF$sp.negate$(this, a);
   }

   public Complex negate$mcF$sp(final Complex a) {
      return ComplexOnCRing$mcF$sp.negate$mcF$sp$(this, a);
   }

   public Complex one() {
      return ComplexOnCRing$mcF$sp.one$(this);
   }

   public Complex one$mcF$sp() {
      return ComplexOnCRing$mcF$sp.one$mcF$sp$(this);
   }

   public Complex plus(final Complex a, final Complex b) {
      return ComplexOnCRing$mcF$sp.plus$(this, a, b);
   }

   public Complex plus$mcF$sp(final Complex a, final Complex b) {
      return ComplexOnCRing$mcF$sp.plus$mcF$sp$(this, a, b);
   }

   public Complex times(final Complex a, final Complex b) {
      return ComplexOnCRing$mcF$sp.times$(this, a, b);
   }

   public Complex times$mcF$sp(final Complex a, final Complex b) {
      return ComplexOnCRing$mcF$sp.times$mcF$sp$(this, a, b);
   }

   public Complex timesl(final float a, final Complex v) {
      return ComplexOnCRing$mcF$sp.timesl$(this, a, v);
   }

   public Complex timesl$mcF$sp(final float a, final Complex v) {
      return ComplexOnCRing$mcF$sp.timesl$mcF$sp$(this, a, v);
   }

   public Complex zero() {
      return ComplexOnCRing$mcF$sp.zero$(this);
   }

   public Complex zero$mcF$sp() {
      return ComplexOnCRing$mcF$sp.zero$mcF$sp$(this);
   }

   public Complex adjoint(final Complex a) {
      return ComplexOnCRing$mcF$sp.adjoint$(this, a);
   }

   public Complex adjoint$mcF$sp(final Complex a) {
      return ComplexOnCRing$mcF$sp.adjoint$mcF$sp$(this, a);
   }

   public Complex fromInt(final int n) {
      return ComplexOnCRing$mcF$sp.fromInt$(this, n);
   }

   public Complex fromInt$mcF$sp(final int n) {
      return ComplexOnCRing$mcF$sp.fromInt$mcF$sp$(this, n);
   }

   public Object timesr(final Object v, final float r) {
      return CModule$mcF$sp.timesr$(this, v, r);
   }

   public Object timesr$mcF$sp(final Object v, final float r) {
      return CModule$mcF$sp.timesr$mcF$sp$(this, v, r);
   }

   public CommutativeRing scalar$mcF$sp() {
      return this.scalar$mcF$sp;
   }

   public CommutativeRing scalar() {
      return this.scalar$mcF$sp();
   }

   public boolean specInstance$() {
      return true;
   }

   public ComplexOnCRingImpl$mcF$sp(final CommutativeRing scalar$mcF$sp) {
      super((CommutativeRing)null);
      this.scalar$mcF$sp = scalar$mcF$sp;
   }
}
