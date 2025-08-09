package spire.math;

import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Order;
import spire.algebra.CModule$mcD$sp;
import spire.algebra.NRoot;
import spire.algebra.Trig;
import spire.algebra.VectorSpace$mcD$sp;

public class ComplexOnTrigImpl$mcD$sp extends ComplexOnTrigImpl implements ComplexOnTrig$mcD$sp, ComplexOnField$mcD$sp {
   private static final long serialVersionUID = 1L;
   public final Field scalar$mcD$sp;
   public final NRoot nroot$mcD$sp;
   public final Order order$mcD$sp;
   public final Trig trig$mcD$sp;
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

   public Complex e() {
      return ComplexOnTrig$mcD$sp.e$(this);
   }

   public Complex e$mcD$sp() {
      return ComplexOnTrig$mcD$sp.e$mcD$sp$(this);
   }

   public Complex pi() {
      return ComplexOnTrig$mcD$sp.pi$(this);
   }

   public Complex pi$mcD$sp() {
      return ComplexOnTrig$mcD$sp.pi$mcD$sp$(this);
   }

   public Complex exp(final Complex a) {
      return ComplexOnTrig$mcD$sp.exp$(this, a);
   }

   public Complex exp$mcD$sp(final Complex a) {
      return ComplexOnTrig$mcD$sp.exp$mcD$sp$(this, a);
   }

   public Complex expm1(final Complex a) {
      return ComplexOnTrig$mcD$sp.expm1$(this, a);
   }

   public Complex expm1$mcD$sp(final Complex a) {
      return ComplexOnTrig$mcD$sp.expm1$mcD$sp$(this, a);
   }

   public Complex log(final Complex a) {
      return ComplexOnTrig$mcD$sp.log$(this, a);
   }

   public Complex log$mcD$sp(final Complex a) {
      return ComplexOnTrig$mcD$sp.log$mcD$sp$(this, a);
   }

   public Complex log1p(final Complex a) {
      return ComplexOnTrig$mcD$sp.log1p$(this, a);
   }

   public Complex log1p$mcD$sp(final Complex a) {
      return ComplexOnTrig$mcD$sp.log1p$mcD$sp$(this, a);
   }

   public Complex sin(final Complex a) {
      return ComplexOnTrig$mcD$sp.sin$(this, a);
   }

   public Complex sin$mcD$sp(final Complex a) {
      return ComplexOnTrig$mcD$sp.sin$mcD$sp$(this, a);
   }

   public Complex cos(final Complex a) {
      return ComplexOnTrig$mcD$sp.cos$(this, a);
   }

   public Complex cos$mcD$sp(final Complex a) {
      return ComplexOnTrig$mcD$sp.cos$mcD$sp$(this, a);
   }

   public Complex tan(final Complex a) {
      return ComplexOnTrig$mcD$sp.tan$(this, a);
   }

   public Complex tan$mcD$sp(final Complex a) {
      return ComplexOnTrig$mcD$sp.tan$mcD$sp$(this, a);
   }

   public Complex asin(final Complex a) {
      return ComplexOnTrig$mcD$sp.asin$(this, a);
   }

   public Complex asin$mcD$sp(final Complex a) {
      return ComplexOnTrig$mcD$sp.asin$mcD$sp$(this, a);
   }

   public Complex acos(final Complex a) {
      return ComplexOnTrig$mcD$sp.acos$(this, a);
   }

   public Complex acos$mcD$sp(final Complex a) {
      return ComplexOnTrig$mcD$sp.acos$mcD$sp$(this, a);
   }

   public Complex atan(final Complex a) {
      return ComplexOnTrig$mcD$sp.atan$(this, a);
   }

   public Complex atan$mcD$sp(final Complex a) {
      return ComplexOnTrig$mcD$sp.atan$mcD$sp$(this, a);
   }

   public Complex atan2(final Complex y, final Complex x) {
      return ComplexOnTrig$mcD$sp.atan2$(this, y, x);
   }

   public Complex atan2$mcD$sp(final Complex y, final Complex x) {
      return ComplexOnTrig$mcD$sp.atan2$mcD$sp$(this, y, x);
   }

   public Complex sinh(final Complex x) {
      return ComplexOnTrig$mcD$sp.sinh$(this, x);
   }

   public Complex sinh$mcD$sp(final Complex x) {
      return ComplexOnTrig$mcD$sp.sinh$mcD$sp$(this, x);
   }

   public Complex cosh(final Complex x) {
      return ComplexOnTrig$mcD$sp.cosh$(this, x);
   }

   public Complex cosh$mcD$sp(final Complex x) {
      return ComplexOnTrig$mcD$sp.cosh$mcD$sp$(this, x);
   }

   public Complex tanh(final Complex x) {
      return ComplexOnTrig$mcD$sp.tanh$(this, x);
   }

   public Complex tanh$mcD$sp(final Complex x) {
      return ComplexOnTrig$mcD$sp.tanh$mcD$sp$(this, x);
   }

   public Complex toRadians(final Complex a) {
      return ComplexOnTrig$mcD$sp.toRadians$(this, a);
   }

   public Complex toRadians$mcD$sp(final Complex a) {
      return ComplexOnTrig$mcD$sp.toRadians$mcD$sp$(this, a);
   }

   public Complex toDegrees(final Complex a) {
      return ComplexOnTrig$mcD$sp.toDegrees$(this, a);
   }

   public Complex toDegrees$mcD$sp(final Complex a) {
      return ComplexOnTrig$mcD$sp.toDegrees$mcD$sp$(this, a);
   }

   public Field scalar$mcD$sp() {
      return this.scalar$mcD$sp;
   }

   public Field scalar() {
      return this.scalar$mcD$sp();
   }

   public NRoot nroot$mcD$sp() {
      return this.nroot$mcD$sp;
   }

   public NRoot nroot() {
      return this.nroot$mcD$sp();
   }

   public Order order$mcD$sp() {
      return this.order$mcD$sp;
   }

   public Order order() {
      return this.order$mcD$sp();
   }

   public Trig trig$mcD$sp() {
      return this.trig$mcD$sp;
   }

   public Trig trig() {
      return this.trig$mcD$sp();
   }

   public Signed signed$mcD$sp() {
      return this.signed$mcD$sp;
   }

   public Signed signed() {
      return this.signed$mcD$sp();
   }

   public Complex pow(final Complex a, final int b) {
      return this.pow$mcD$sp(a, b);
   }

   public Complex pow$mcD$sp(final Complex a, final int b) {
      return a.pow$mcD$sp(b, this.scalar(), this.nroot(), this.order(), this.signed(), this.trig());
   }

   public boolean specInstance$() {
      return true;
   }

   public ComplexOnTrigImpl$mcD$sp(final Field scalar$mcD$sp, final NRoot nroot$mcD$sp, final Order order$mcD$sp, final Trig trig$mcD$sp, final Signed signed$mcD$sp) {
      super((Field)null, (NRoot)null, (Order)null, (Trig)null, (Signed)null);
      this.scalar$mcD$sp = scalar$mcD$sp;
      this.nroot$mcD$sp = nroot$mcD$sp;
      this.order$mcD$sp = order$mcD$sp;
      this.trig$mcD$sp = trig$mcD$sp;
      this.signed$mcD$sp = signed$mcD$sp;
   }
}
