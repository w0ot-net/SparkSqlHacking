package spire.math;

import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Order;
import spire.algebra.CModule$mcD$sp;
import spire.algebra.VectorSpace$mcD$sp;

public final class ComplexOnFieldImpl$mcD$sp extends ComplexOnFieldImpl implements ComplexOnField$mcD$sp {
   private static final long serialVersionUID = 1L;
   public final Field scalar$mcD$sp;
   public final Order order$mcD$sp;
   public final Signed signed$mcD$sp;

   public Complex fromDouble(final double n) {
      return ComplexOnField$mcD$sp.fromDouble$(this, n);
   }

   public Complex fromDouble$mcD$sp(final double n) {
      return ComplexOnField$mcD$sp.fromDouble$mcD$sp$(this, n);
   }

   public Complex div(final Complex a, final Complex b) {
      return ComplexOnField$mcD$sp.div$(this, a, b);
   }

   public Complex div$mcD$sp(final Complex a, final Complex b) {
      return ComplexOnField$mcD$sp.div$mcD$sp$(this, a, b);
   }

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

   public Object divr(final Object v, final double f) {
      return VectorSpace$mcD$sp.divr$(this, v, f);
   }

   public Object divr$mcD$sp(final Object v, final double f) {
      return VectorSpace$mcD$sp.divr$mcD$sp$(this, v, f);
   }

   public Object timesr(final Object v, final double r) {
      return CModule$mcD$sp.timesr$(this, v, r);
   }

   public Object timesr$mcD$sp(final Object v, final double r) {
      return CModule$mcD$sp.timesr$mcD$sp$(this, v, r);
   }

   public Field scalar$mcD$sp() {
      return this.scalar$mcD$sp;
   }

   public Field scalar() {
      return this.scalar$mcD$sp();
   }

   public Order order$mcD$sp() {
      return this.order$mcD$sp;
   }

   public Order order() {
      return this.order$mcD$sp();
   }

   public Signed signed$mcD$sp() {
      return this.signed$mcD$sp;
   }

   public Signed signed() {
      return this.signed$mcD$sp();
   }

   public boolean specInstance$() {
      return true;
   }

   public ComplexOnFieldImpl$mcD$sp(final Field scalar$mcD$sp, final Order order$mcD$sp, final Signed signed$mcD$sp) {
      super((Field)null, (Order)null, (Signed)null);
      this.scalar$mcD$sp = scalar$mcD$sp;
      this.order$mcD$sp = order$mcD$sp;
      this.signed$mcD$sp = signed$mcD$sp;
   }
}
