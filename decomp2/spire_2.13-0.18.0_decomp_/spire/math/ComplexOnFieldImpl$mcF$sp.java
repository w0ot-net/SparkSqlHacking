package spire.math;

import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Order;
import spire.algebra.CModule$mcF$sp;
import spire.algebra.VectorSpace$mcF$sp;

public final class ComplexOnFieldImpl$mcF$sp extends ComplexOnFieldImpl implements ComplexOnField$mcF$sp {
   private static final long serialVersionUID = 1L;
   public final Field scalar$mcF$sp;
   public final Order order$mcF$sp;
   public final Signed signed$mcF$sp;

   public Complex fromDouble(final double n) {
      return ComplexOnField$mcF$sp.fromDouble$(this, n);
   }

   public Complex fromDouble$mcF$sp(final double n) {
      return ComplexOnField$mcF$sp.fromDouble$mcF$sp$(this, n);
   }

   public Complex div(final Complex a, final Complex b) {
      return ComplexOnField$mcF$sp.div$(this, a, b);
   }

   public Complex div$mcF$sp(final Complex a, final Complex b) {
      return ComplexOnField$mcF$sp.div$mcF$sp$(this, a, b);
   }

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

   public Object divr(final Object v, final float f) {
      return VectorSpace$mcF$sp.divr$(this, v, f);
   }

   public Object divr$mcF$sp(final Object v, final float f) {
      return VectorSpace$mcF$sp.divr$mcF$sp$(this, v, f);
   }

   public Object timesr(final Object v, final float r) {
      return CModule$mcF$sp.timesr$(this, v, r);
   }

   public Object timesr$mcF$sp(final Object v, final float r) {
      return CModule$mcF$sp.timesr$mcF$sp$(this, v, r);
   }

   public Field scalar$mcF$sp() {
      return this.scalar$mcF$sp;
   }

   public Field scalar() {
      return this.scalar$mcF$sp();
   }

   public Order order$mcF$sp() {
      return this.order$mcF$sp;
   }

   public Order order() {
      return this.order$mcF$sp();
   }

   public Signed signed$mcF$sp() {
      return this.signed$mcF$sp;
   }

   public Signed signed() {
      return this.signed$mcF$sp();
   }

   public boolean specInstance$() {
      return true;
   }

   public ComplexOnFieldImpl$mcF$sp(final Field scalar$mcF$sp, final Order order$mcF$sp, final Signed signed$mcF$sp) {
      super((Field)null, (Order)null, (Signed)null);
      this.scalar$mcF$sp = scalar$mcF$sp;
      this.order$mcF$sp = order$mcF$sp;
      this.signed$mcF$sp = signed$mcF$sp;
   }
}
