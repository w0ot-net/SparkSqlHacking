package spire.math;

import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.collection.ArrayOps.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import spire.algebra.CModule$mcF$sp;
import spire.algebra.FieldAssociativeAlgebra$mcF$sp;
import spire.algebra.NRoot;
import spire.algebra.Trig;
import spire.algebra.VectorSpace;
import spire.algebra.VectorSpace$mcF$sp;

public class JetAlgebra$mcF$sp extends JetAlgebra implements FieldAssociativeAlgebra$mcF$sp, JetIsTrig$mcF$sp, JetIsField$mcF$sp {
   private static final long serialVersionUID = 0L;
   public final Eq eq$mcF$sp;
   public final Field f$mcF$sp;
   public final NRoot n$mcF$sp;
   public final Order o$mcF$sp;
   public final Trig t$mcF$sp;
   public final Signed s$mcF$sp;
   public final VectorSpace v$mcF$sp;

   public Jet fromDouble(final double n) {
      return JetIsField$mcF$sp.fromDouble$(this, n);
   }

   public Jet fromDouble$mcF$sp(final double n) {
      return JetIsField$mcF$sp.fromDouble$mcF$sp$(this, n);
   }

   public Jet div(final Jet a, final Jet b) {
      return JetIsField$mcF$sp.div$(this, a, b);
   }

   public Jet div$mcF$sp(final Jet a, final Jet b) {
      return JetIsField$mcF$sp.div$mcF$sp$(this, a, b);
   }

   public Jet minus(final Jet a, final Jet b) {
      return JetIsRing$mcF$sp.minus$(this, a, b);
   }

   public Jet minus$mcF$sp(final Jet a, final Jet b) {
      return JetIsRing$mcF$sp.minus$mcF$sp$(this, a, b);
   }

   public Jet negate(final Jet a) {
      return JetIsRing$mcF$sp.negate$(this, a);
   }

   public Jet negate$mcF$sp(final Jet a) {
      return JetIsRing$mcF$sp.negate$mcF$sp$(this, a);
   }

   public Jet one() {
      return JetIsRing$mcF$sp.one$(this);
   }

   public Jet one$mcF$sp() {
      return JetIsRing$mcF$sp.one$mcF$sp$(this);
   }

   public Jet plus(final Jet a, final Jet b) {
      return JetIsRing$mcF$sp.plus$(this, a, b);
   }

   public Jet plus$mcF$sp(final Jet a, final Jet b) {
      return JetIsRing$mcF$sp.plus$mcF$sp$(this, a, b);
   }

   public Jet pow(final Jet a, final int b) {
      return JetIsRing$mcF$sp.pow$(this, a, b);
   }

   public Jet pow$mcF$sp(final Jet a, final int b) {
      return JetIsRing$mcF$sp.pow$mcF$sp$(this, a, b);
   }

   public Jet times(final Jet a, final Jet b) {
      return JetIsRing$mcF$sp.times$(this, a, b);
   }

   public Jet times$mcF$sp(final Jet a, final Jet b) {
      return JetIsRing$mcF$sp.times$mcF$sp$(this, a, b);
   }

   public Jet zero() {
      return JetIsRing$mcF$sp.zero$(this);
   }

   public Jet zero$mcF$sp() {
      return JetIsRing$mcF$sp.zero$mcF$sp$(this);
   }

   public Jet fromInt(final int n) {
      return JetIsRing$mcF$sp.fromInt$(this, n);
   }

   public Jet fromInt$mcF$sp(final int n) {
      return JetIsRing$mcF$sp.fromInt$mcF$sp$(this, n);
   }

   public Jet e() {
      return JetIsTrig$mcF$sp.e$(this);
   }

   public Jet e$mcF$sp() {
      return JetIsTrig$mcF$sp.e$mcF$sp$(this);
   }

   public Jet pi() {
      return JetIsTrig$mcF$sp.pi$(this);
   }

   public Jet pi$mcF$sp() {
      return JetIsTrig$mcF$sp.pi$mcF$sp$(this);
   }

   public Jet exp(final Jet a) {
      return JetIsTrig$mcF$sp.exp$(this, a);
   }

   public Jet exp$mcF$sp(final Jet a) {
      return JetIsTrig$mcF$sp.exp$mcF$sp$(this, a);
   }

   public Jet expm1(final Jet a) {
      return JetIsTrig$mcF$sp.expm1$(this, a);
   }

   public Jet expm1$mcF$sp(final Jet a) {
      return JetIsTrig$mcF$sp.expm1$mcF$sp$(this, a);
   }

   public Jet log(final Jet a) {
      return JetIsTrig$mcF$sp.log$(this, a);
   }

   public Jet log$mcF$sp(final Jet a) {
      return JetIsTrig$mcF$sp.log$mcF$sp$(this, a);
   }

   public Jet log1p(final Jet a) {
      return JetIsTrig$mcF$sp.log1p$(this, a);
   }

   public Jet log1p$mcF$sp(final Jet a) {
      return JetIsTrig$mcF$sp.log1p$mcF$sp$(this, a);
   }

   public Jet sin(final Jet a) {
      return JetIsTrig$mcF$sp.sin$(this, a);
   }

   public Jet sin$mcF$sp(final Jet a) {
      return JetIsTrig$mcF$sp.sin$mcF$sp$(this, a);
   }

   public Jet cos(final Jet a) {
      return JetIsTrig$mcF$sp.cos$(this, a);
   }

   public Jet cos$mcF$sp(final Jet a) {
      return JetIsTrig$mcF$sp.cos$mcF$sp$(this, a);
   }

   public Jet tan(final Jet a) {
      return JetIsTrig$mcF$sp.tan$(this, a);
   }

   public Jet tan$mcF$sp(final Jet a) {
      return JetIsTrig$mcF$sp.tan$mcF$sp$(this, a);
   }

   public Jet asin(final Jet a) {
      return JetIsTrig$mcF$sp.asin$(this, a);
   }

   public Jet asin$mcF$sp(final Jet a) {
      return JetIsTrig$mcF$sp.asin$mcF$sp$(this, a);
   }

   public Jet acos(final Jet a) {
      return JetIsTrig$mcF$sp.acos$(this, a);
   }

   public Jet acos$mcF$sp(final Jet a) {
      return JetIsTrig$mcF$sp.acos$mcF$sp$(this, a);
   }

   public Jet atan(final Jet a) {
      return JetIsTrig$mcF$sp.atan$(this, a);
   }

   public Jet atan$mcF$sp(final Jet a) {
      return JetIsTrig$mcF$sp.atan$mcF$sp$(this, a);
   }

   public Jet atan2(final Jet y, final Jet x) {
      return JetIsTrig$mcF$sp.atan2$(this, y, x);
   }

   public Jet atan2$mcF$sp(final Jet y, final Jet x) {
      return JetIsTrig$mcF$sp.atan2$mcF$sp$(this, y, x);
   }

   public Jet sinh(final Jet x) {
      return JetIsTrig$mcF$sp.sinh$(this, x);
   }

   public Jet sinh$mcF$sp(final Jet x) {
      return JetIsTrig$mcF$sp.sinh$mcF$sp$(this, x);
   }

   public Jet cosh(final Jet x) {
      return JetIsTrig$mcF$sp.cosh$(this, x);
   }

   public Jet cosh$mcF$sp(final Jet x) {
      return JetIsTrig$mcF$sp.cosh$mcF$sp$(this, x);
   }

   public Jet tanh(final Jet x) {
      return JetIsTrig$mcF$sp.tanh$(this, x);
   }

   public Jet tanh$mcF$sp(final Jet x) {
      return JetIsTrig$mcF$sp.tanh$mcF$sp$(this, x);
   }

   public Jet toRadians(final Jet a) {
      return JetIsTrig$mcF$sp.toRadians$(this, a);
   }

   public Jet toRadians$mcF$sp(final Jet a) {
      return JetIsTrig$mcF$sp.toRadians$mcF$sp$(this, a);
   }

   public Jet toDegrees(final Jet a) {
      return JetIsTrig$mcF$sp.toDegrees$(this, a);
   }

   public Jet toDegrees$mcF$sp(final Jet a) {
      return JetIsTrig$mcF$sp.toDegrees$mcF$sp$(this, a);
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

   public Eq eq$mcF$sp() {
      return this.eq$mcF$sp;
   }

   public Eq eq() {
      return this.eq$mcF$sp();
   }

   public Field f$mcF$sp() {
      return this.f$mcF$sp;
   }

   public Field f() {
      return this.f$mcF$sp();
   }

   public NRoot n$mcF$sp() {
      return this.n$mcF$sp;
   }

   public NRoot n() {
      return this.n$mcF$sp();
   }

   public Order o$mcF$sp() {
      return this.o$mcF$sp;
   }

   public Order o() {
      return this.o$mcF$sp();
   }

   public Trig t$mcF$sp() {
      return this.t$mcF$sp;
   }

   public Trig t() {
      return this.t$mcF$sp();
   }

   public Signed s$mcF$sp() {
      return this.s$mcF$sp;
   }

   public Signed s() {
      return this.s$mcF$sp();
   }

   public VectorSpace v$mcF$sp() {
      return this.v$mcF$sp;
   }

   public VectorSpace v() {
      return this.v$mcF$sp();
   }

   public Field scalar() {
      return this.scalar$mcF$sp();
   }

   public Field scalar$mcF$sp() {
      return this.f();
   }

   public NRoot nroot() {
      return this.nroot$mcF$sp();
   }

   public NRoot nroot$mcF$sp() {
      return this.n();
   }

   public Jet timesl(final float a, final Jet w) {
      return this.timesl$mcF$sp(a, w);
   }

   public Jet timesl$mcF$sp(final float a, final Jet w) {
      return Jet$.MODULE$.apply$mFc$sp(a, this.c(), this.d(), this.f()).$times$mcF$sp(w, this.f(), this.v());
   }

   public float dot(final Jet x, final Jet y) {
      return this.dot$mcF$sp(x, y);
   }

   public float dot$mcF$sp(final Jet x, final Jet y) {
      return BoxesRunTime.unboxToFloat(.MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zip$extension(scala.Predef..MODULE$.genericArrayOps(x.infinitesimal$mcF$sp()), scala.Predef..MODULE$.genericWrapArray(y.infinitesimal$mcF$sp()))), BoxesRunTime.boxToFloat(this.scalar$mcF$sp().times$mcF$sp(x.real$mcF$sp(), y.real$mcF$sp())), (xx, yy) -> BoxesRunTime.boxToFloat($anonfun$dot$3(this, BoxesRunTime.unboxToFloat(xx), yy))));
   }

   public boolean specInstance$() {
      return true;
   }

   // $FF: synthetic method
   public static final float $anonfun$dot$3(final JetAlgebra$mcF$sp $this, final float xx, final Tuple2 yy) {
      return $this.scalar$mcF$sp().plus$mcF$sp(xx, $this.scalar$mcF$sp().times$mcF$sp(BoxesRunTime.unboxToFloat(yy._1()), BoxesRunTime.unboxToFloat(yy._2())));
   }

   public JetAlgebra$mcF$sp(final ClassTag c, final JetDim d, final Eq eq$mcF$sp, final Field f$mcF$sp, final NRoot n$mcF$sp, final Order o$mcF$sp, final Trig t$mcF$sp, final Signed s$mcF$sp, final VectorSpace v$mcF$sp) {
      super(c, d, (Eq)null, (Field)null, (NRoot)null, (Order)null, (Trig)null, (Signed)null, (VectorSpace)null);
      this.eq$mcF$sp = eq$mcF$sp;
      this.f$mcF$sp = f$mcF$sp;
      this.n$mcF$sp = n$mcF$sp;
      this.o$mcF$sp = o$mcF$sp;
      this.t$mcF$sp = t$mcF$sp;
      this.s$mcF$sp = s$mcF$sp;
      this.v$mcF$sp = v$mcF$sp;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
