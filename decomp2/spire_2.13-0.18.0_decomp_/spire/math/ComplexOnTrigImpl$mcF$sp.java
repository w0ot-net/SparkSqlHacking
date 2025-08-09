package spire.math;

import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Order;
import spire.algebra.CModule$mcF$sp;
import spire.algebra.NRoot;
import spire.algebra.Trig;
import spire.algebra.VectorSpace$mcF$sp;

public class ComplexOnTrigImpl$mcF$sp extends ComplexOnTrigImpl implements ComplexOnTrig$mcF$sp, ComplexOnField$mcF$sp {
   private static final long serialVersionUID = 1L;
   public final Field scalar$mcF$sp;
   public final NRoot nroot$mcF$sp;
   public final Order order$mcF$sp;
   public final Trig trig$mcF$sp;
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

   public Complex e() {
      return ComplexOnTrig$mcF$sp.e$(this);
   }

   public Complex e$mcF$sp() {
      return ComplexOnTrig$mcF$sp.e$mcF$sp$(this);
   }

   public Complex pi() {
      return ComplexOnTrig$mcF$sp.pi$(this);
   }

   public Complex pi$mcF$sp() {
      return ComplexOnTrig$mcF$sp.pi$mcF$sp$(this);
   }

   public Complex exp(final Complex a) {
      return ComplexOnTrig$mcF$sp.exp$(this, a);
   }

   public Complex exp$mcF$sp(final Complex a) {
      return ComplexOnTrig$mcF$sp.exp$mcF$sp$(this, a);
   }

   public Complex expm1(final Complex a) {
      return ComplexOnTrig$mcF$sp.expm1$(this, a);
   }

   public Complex expm1$mcF$sp(final Complex a) {
      return ComplexOnTrig$mcF$sp.expm1$mcF$sp$(this, a);
   }

   public Complex log(final Complex a) {
      return ComplexOnTrig$mcF$sp.log$(this, a);
   }

   public Complex log$mcF$sp(final Complex a) {
      return ComplexOnTrig$mcF$sp.log$mcF$sp$(this, a);
   }

   public Complex log1p(final Complex a) {
      return ComplexOnTrig$mcF$sp.log1p$(this, a);
   }

   public Complex log1p$mcF$sp(final Complex a) {
      return ComplexOnTrig$mcF$sp.log1p$mcF$sp$(this, a);
   }

   public Complex sin(final Complex a) {
      return ComplexOnTrig$mcF$sp.sin$(this, a);
   }

   public Complex sin$mcF$sp(final Complex a) {
      return ComplexOnTrig$mcF$sp.sin$mcF$sp$(this, a);
   }

   public Complex cos(final Complex a) {
      return ComplexOnTrig$mcF$sp.cos$(this, a);
   }

   public Complex cos$mcF$sp(final Complex a) {
      return ComplexOnTrig$mcF$sp.cos$mcF$sp$(this, a);
   }

   public Complex tan(final Complex a) {
      return ComplexOnTrig$mcF$sp.tan$(this, a);
   }

   public Complex tan$mcF$sp(final Complex a) {
      return ComplexOnTrig$mcF$sp.tan$mcF$sp$(this, a);
   }

   public Complex asin(final Complex a) {
      return ComplexOnTrig$mcF$sp.asin$(this, a);
   }

   public Complex asin$mcF$sp(final Complex a) {
      return ComplexOnTrig$mcF$sp.asin$mcF$sp$(this, a);
   }

   public Complex acos(final Complex a) {
      return ComplexOnTrig$mcF$sp.acos$(this, a);
   }

   public Complex acos$mcF$sp(final Complex a) {
      return ComplexOnTrig$mcF$sp.acos$mcF$sp$(this, a);
   }

   public Complex atan(final Complex a) {
      return ComplexOnTrig$mcF$sp.atan$(this, a);
   }

   public Complex atan$mcF$sp(final Complex a) {
      return ComplexOnTrig$mcF$sp.atan$mcF$sp$(this, a);
   }

   public Complex atan2(final Complex y, final Complex x) {
      return ComplexOnTrig$mcF$sp.atan2$(this, y, x);
   }

   public Complex atan2$mcF$sp(final Complex y, final Complex x) {
      return ComplexOnTrig$mcF$sp.atan2$mcF$sp$(this, y, x);
   }

   public Complex sinh(final Complex x) {
      return ComplexOnTrig$mcF$sp.sinh$(this, x);
   }

   public Complex sinh$mcF$sp(final Complex x) {
      return ComplexOnTrig$mcF$sp.sinh$mcF$sp$(this, x);
   }

   public Complex cosh(final Complex x) {
      return ComplexOnTrig$mcF$sp.cosh$(this, x);
   }

   public Complex cosh$mcF$sp(final Complex x) {
      return ComplexOnTrig$mcF$sp.cosh$mcF$sp$(this, x);
   }

   public Complex tanh(final Complex x) {
      return ComplexOnTrig$mcF$sp.tanh$(this, x);
   }

   public Complex tanh$mcF$sp(final Complex x) {
      return ComplexOnTrig$mcF$sp.tanh$mcF$sp$(this, x);
   }

   public Complex toRadians(final Complex a) {
      return ComplexOnTrig$mcF$sp.toRadians$(this, a);
   }

   public Complex toRadians$mcF$sp(final Complex a) {
      return ComplexOnTrig$mcF$sp.toRadians$mcF$sp$(this, a);
   }

   public Complex toDegrees(final Complex a) {
      return ComplexOnTrig$mcF$sp.toDegrees$(this, a);
   }

   public Complex toDegrees$mcF$sp(final Complex a) {
      return ComplexOnTrig$mcF$sp.toDegrees$mcF$sp$(this, a);
   }

   public Field scalar$mcF$sp() {
      return this.scalar$mcF$sp;
   }

   public Field scalar() {
      return this.scalar$mcF$sp();
   }

   public NRoot nroot$mcF$sp() {
      return this.nroot$mcF$sp;
   }

   public NRoot nroot() {
      return this.nroot$mcF$sp();
   }

   public Order order$mcF$sp() {
      return this.order$mcF$sp;
   }

   public Order order() {
      return this.order$mcF$sp();
   }

   public Trig trig$mcF$sp() {
      return this.trig$mcF$sp;
   }

   public Trig trig() {
      return this.trig$mcF$sp();
   }

   public Signed signed$mcF$sp() {
      return this.signed$mcF$sp;
   }

   public Signed signed() {
      return this.signed$mcF$sp();
   }

   public Complex pow(final Complex a, final int b) {
      return this.pow$mcF$sp(a, b);
   }

   public Complex pow$mcF$sp(final Complex a, final int b) {
      return a.pow$mcF$sp(b, this.scalar(), this.nroot(), this.order(), this.signed(), this.trig());
   }

   public boolean specInstance$() {
      return true;
   }

   public ComplexOnTrigImpl$mcF$sp(final Field scalar$mcF$sp, final NRoot nroot$mcF$sp, final Order order$mcF$sp, final Trig trig$mcF$sp, final Signed signed$mcF$sp) {
      super((Field)null, (NRoot)null, (Order)null, (Trig)null, (Signed)null);
      this.scalar$mcF$sp = scalar$mcF$sp;
      this.nroot$mcF$sp = nroot$mcF$sp;
      this.order$mcF$sp = order$mcF$sp;
      this.trig$mcF$sp = trig$mcF$sp;
      this.signed$mcF$sp = signed$mcF$sp;
   }
}
