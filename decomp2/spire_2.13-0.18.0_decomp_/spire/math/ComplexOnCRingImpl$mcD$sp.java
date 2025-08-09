package spire.math;

import algebra.ring.CommutativeRing;
import spire.algebra.CModule$mcD$sp;

public final class ComplexOnCRingImpl$mcD$sp extends ComplexOnCRingImpl implements ComplexOnCRing$mcD$sp {
   private static final long serialVersionUID = 1L;
   public final CommutativeRing scalar$mcD$sp;

   public Complex minus(final Complex a, final Complex b) {
      return ComplexOnCRing$mcD$sp.minus$(this, a, b);
   }

   public Complex minus$mcD$sp(final Complex a, final Complex b) {
      return ComplexOnCRing$mcD$sp.minus$mcD$sp$(this, a, b);
   }

   public Complex negate(final Complex a) {
      return ComplexOnCRing$mcD$sp.negate$(this, a);
   }

   public Complex negate$mcD$sp(final Complex a) {
      return ComplexOnCRing$mcD$sp.negate$mcD$sp$(this, a);
   }

   public Complex one() {
      return ComplexOnCRing$mcD$sp.one$(this);
   }

   public Complex one$mcD$sp() {
      return ComplexOnCRing$mcD$sp.one$mcD$sp$(this);
   }

   public Complex plus(final Complex a, final Complex b) {
      return ComplexOnCRing$mcD$sp.plus$(this, a, b);
   }

   public Complex plus$mcD$sp(final Complex a, final Complex b) {
      return ComplexOnCRing$mcD$sp.plus$mcD$sp$(this, a, b);
   }

   public Complex times(final Complex a, final Complex b) {
      return ComplexOnCRing$mcD$sp.times$(this, a, b);
   }

   public Complex times$mcD$sp(final Complex a, final Complex b) {
      return ComplexOnCRing$mcD$sp.times$mcD$sp$(this, a, b);
   }

   public Complex timesl(final double a, final Complex v) {
      return ComplexOnCRing$mcD$sp.timesl$(this, a, v);
   }

   public Complex timesl$mcD$sp(final double a, final Complex v) {
      return ComplexOnCRing$mcD$sp.timesl$mcD$sp$(this, a, v);
   }

   public Complex zero() {
      return ComplexOnCRing$mcD$sp.zero$(this);
   }

   public Complex zero$mcD$sp() {
      return ComplexOnCRing$mcD$sp.zero$mcD$sp$(this);
   }

   public Complex adjoint(final Complex a) {
      return ComplexOnCRing$mcD$sp.adjoint$(this, a);
   }

   public Complex adjoint$mcD$sp(final Complex a) {
      return ComplexOnCRing$mcD$sp.adjoint$mcD$sp$(this, a);
   }

   public Complex fromInt(final int n) {
      return ComplexOnCRing$mcD$sp.fromInt$(this, n);
   }

   public Complex fromInt$mcD$sp(final int n) {
      return ComplexOnCRing$mcD$sp.fromInt$mcD$sp$(this, n);
   }

   public Object timesr(final Object v, final double r) {
      return CModule$mcD$sp.timesr$(this, v, r);
   }

   public Object timesr$mcD$sp(final Object v, final double r) {
      return CModule$mcD$sp.timesr$mcD$sp$(this, v, r);
   }

   public CommutativeRing scalar$mcD$sp() {
      return this.scalar$mcD$sp;
   }

   public CommutativeRing scalar() {
      return this.scalar$mcD$sp();
   }

   public boolean specInstance$() {
      return true;
   }

   public ComplexOnCRingImpl$mcD$sp(final CommutativeRing scalar$mcD$sp) {
      super((CommutativeRing)null);
      this.scalar$mcD$sp = scalar$mcD$sp;
   }
}
