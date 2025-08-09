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
import spire.algebra.CModule$mcD$sp;
import spire.algebra.FieldAssociativeAlgebra$mcD$sp;
import spire.algebra.NRoot;
import spire.algebra.Trig;
import spire.algebra.VectorSpace;
import spire.algebra.VectorSpace$mcD$sp;

public class JetAlgebra$mcD$sp extends JetAlgebra implements FieldAssociativeAlgebra$mcD$sp, JetIsTrig$mcD$sp, JetIsField$mcD$sp {
   private static final long serialVersionUID = 0L;
   public final Eq eq$mcD$sp;
   public final Field f$mcD$sp;
   public final NRoot n$mcD$sp;
   public final Order o$mcD$sp;
   public final Trig t$mcD$sp;
   public final Signed s$mcD$sp;
   public final VectorSpace v$mcD$sp;

   public Jet fromDouble(final double n) {
      return JetIsField$mcD$sp.fromDouble$(this, n);
   }

   public Jet fromDouble$mcD$sp(final double n) {
      return JetIsField$mcD$sp.fromDouble$mcD$sp$(this, n);
   }

   public Jet div(final Jet a, final Jet b) {
      return JetIsField$mcD$sp.div$(this, a, b);
   }

   public Jet div$mcD$sp(final Jet a, final Jet b) {
      return JetIsField$mcD$sp.div$mcD$sp$(this, a, b);
   }

   public Jet minus(final Jet a, final Jet b) {
      return JetIsRing$mcD$sp.minus$(this, a, b);
   }

   public Jet minus$mcD$sp(final Jet a, final Jet b) {
      return JetIsRing$mcD$sp.minus$mcD$sp$(this, a, b);
   }

   public Jet negate(final Jet a) {
      return JetIsRing$mcD$sp.negate$(this, a);
   }

   public Jet negate$mcD$sp(final Jet a) {
      return JetIsRing$mcD$sp.negate$mcD$sp$(this, a);
   }

   public Jet one() {
      return JetIsRing$mcD$sp.one$(this);
   }

   public Jet one$mcD$sp() {
      return JetIsRing$mcD$sp.one$mcD$sp$(this);
   }

   public Jet plus(final Jet a, final Jet b) {
      return JetIsRing$mcD$sp.plus$(this, a, b);
   }

   public Jet plus$mcD$sp(final Jet a, final Jet b) {
      return JetIsRing$mcD$sp.plus$mcD$sp$(this, a, b);
   }

   public Jet pow(final Jet a, final int b) {
      return JetIsRing$mcD$sp.pow$(this, a, b);
   }

   public Jet pow$mcD$sp(final Jet a, final int b) {
      return JetIsRing$mcD$sp.pow$mcD$sp$(this, a, b);
   }

   public Jet times(final Jet a, final Jet b) {
      return JetIsRing$mcD$sp.times$(this, a, b);
   }

   public Jet times$mcD$sp(final Jet a, final Jet b) {
      return JetIsRing$mcD$sp.times$mcD$sp$(this, a, b);
   }

   public Jet zero() {
      return JetIsRing$mcD$sp.zero$(this);
   }

   public Jet zero$mcD$sp() {
      return JetIsRing$mcD$sp.zero$mcD$sp$(this);
   }

   public Jet fromInt(final int n) {
      return JetIsRing$mcD$sp.fromInt$(this, n);
   }

   public Jet fromInt$mcD$sp(final int n) {
      return JetIsRing$mcD$sp.fromInt$mcD$sp$(this, n);
   }

   public Jet e() {
      return JetIsTrig$mcD$sp.e$(this);
   }

   public Jet e$mcD$sp() {
      return JetIsTrig$mcD$sp.e$mcD$sp$(this);
   }

   public Jet pi() {
      return JetIsTrig$mcD$sp.pi$(this);
   }

   public Jet pi$mcD$sp() {
      return JetIsTrig$mcD$sp.pi$mcD$sp$(this);
   }

   public Jet exp(final Jet a) {
      return JetIsTrig$mcD$sp.exp$(this, a);
   }

   public Jet exp$mcD$sp(final Jet a) {
      return JetIsTrig$mcD$sp.exp$mcD$sp$(this, a);
   }

   public Jet expm1(final Jet a) {
      return JetIsTrig$mcD$sp.expm1$(this, a);
   }

   public Jet expm1$mcD$sp(final Jet a) {
      return JetIsTrig$mcD$sp.expm1$mcD$sp$(this, a);
   }

   public Jet log(final Jet a) {
      return JetIsTrig$mcD$sp.log$(this, a);
   }

   public Jet log$mcD$sp(final Jet a) {
      return JetIsTrig$mcD$sp.log$mcD$sp$(this, a);
   }

   public Jet log1p(final Jet a) {
      return JetIsTrig$mcD$sp.log1p$(this, a);
   }

   public Jet log1p$mcD$sp(final Jet a) {
      return JetIsTrig$mcD$sp.log1p$mcD$sp$(this, a);
   }

   public Jet sin(final Jet a) {
      return JetIsTrig$mcD$sp.sin$(this, a);
   }

   public Jet sin$mcD$sp(final Jet a) {
      return JetIsTrig$mcD$sp.sin$mcD$sp$(this, a);
   }

   public Jet cos(final Jet a) {
      return JetIsTrig$mcD$sp.cos$(this, a);
   }

   public Jet cos$mcD$sp(final Jet a) {
      return JetIsTrig$mcD$sp.cos$mcD$sp$(this, a);
   }

   public Jet tan(final Jet a) {
      return JetIsTrig$mcD$sp.tan$(this, a);
   }

   public Jet tan$mcD$sp(final Jet a) {
      return JetIsTrig$mcD$sp.tan$mcD$sp$(this, a);
   }

   public Jet asin(final Jet a) {
      return JetIsTrig$mcD$sp.asin$(this, a);
   }

   public Jet asin$mcD$sp(final Jet a) {
      return JetIsTrig$mcD$sp.asin$mcD$sp$(this, a);
   }

   public Jet acos(final Jet a) {
      return JetIsTrig$mcD$sp.acos$(this, a);
   }

   public Jet acos$mcD$sp(final Jet a) {
      return JetIsTrig$mcD$sp.acos$mcD$sp$(this, a);
   }

   public Jet atan(final Jet a) {
      return JetIsTrig$mcD$sp.atan$(this, a);
   }

   public Jet atan$mcD$sp(final Jet a) {
      return JetIsTrig$mcD$sp.atan$mcD$sp$(this, a);
   }

   public Jet atan2(final Jet y, final Jet x) {
      return JetIsTrig$mcD$sp.atan2$(this, y, x);
   }

   public Jet atan2$mcD$sp(final Jet y, final Jet x) {
      return JetIsTrig$mcD$sp.atan2$mcD$sp$(this, y, x);
   }

   public Jet sinh(final Jet x) {
      return JetIsTrig$mcD$sp.sinh$(this, x);
   }

   public Jet sinh$mcD$sp(final Jet x) {
      return JetIsTrig$mcD$sp.sinh$mcD$sp$(this, x);
   }

   public Jet cosh(final Jet x) {
      return JetIsTrig$mcD$sp.cosh$(this, x);
   }

   public Jet cosh$mcD$sp(final Jet x) {
      return JetIsTrig$mcD$sp.cosh$mcD$sp$(this, x);
   }

   public Jet tanh(final Jet x) {
      return JetIsTrig$mcD$sp.tanh$(this, x);
   }

   public Jet tanh$mcD$sp(final Jet x) {
      return JetIsTrig$mcD$sp.tanh$mcD$sp$(this, x);
   }

   public Jet toRadians(final Jet a) {
      return JetIsTrig$mcD$sp.toRadians$(this, a);
   }

   public Jet toRadians$mcD$sp(final Jet a) {
      return JetIsTrig$mcD$sp.toRadians$mcD$sp$(this, a);
   }

   public Jet toDegrees(final Jet a) {
      return JetIsTrig$mcD$sp.toDegrees$(this, a);
   }

   public Jet toDegrees$mcD$sp(final Jet a) {
      return JetIsTrig$mcD$sp.toDegrees$mcD$sp$(this, a);
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

   public Eq eq$mcD$sp() {
      return this.eq$mcD$sp;
   }

   public Eq eq() {
      return this.eq$mcD$sp();
   }

   public Field f$mcD$sp() {
      return this.f$mcD$sp;
   }

   public Field f() {
      return this.f$mcD$sp();
   }

   public NRoot n$mcD$sp() {
      return this.n$mcD$sp;
   }

   public NRoot n() {
      return this.n$mcD$sp();
   }

   public Order o$mcD$sp() {
      return this.o$mcD$sp;
   }

   public Order o() {
      return this.o$mcD$sp();
   }

   public Trig t$mcD$sp() {
      return this.t$mcD$sp;
   }

   public Trig t() {
      return this.t$mcD$sp();
   }

   public Signed s$mcD$sp() {
      return this.s$mcD$sp;
   }

   public Signed s() {
      return this.s$mcD$sp();
   }

   public VectorSpace v$mcD$sp() {
      return this.v$mcD$sp;
   }

   public VectorSpace v() {
      return this.v$mcD$sp();
   }

   public Field scalar() {
      return this.scalar$mcD$sp();
   }

   public Field scalar$mcD$sp() {
      return this.f();
   }

   public NRoot nroot() {
      return this.nroot$mcD$sp();
   }

   public NRoot nroot$mcD$sp() {
      return this.n();
   }

   public Jet timesl(final double a, final Jet w) {
      return this.timesl$mcD$sp(a, w);
   }

   public Jet timesl$mcD$sp(final double a, final Jet w) {
      return Jet$.MODULE$.apply$mDc$sp(a, this.c(), this.d(), this.f()).$times$mcD$sp(w, this.f(), this.v());
   }

   public double dot(final Jet x, final Jet y) {
      return this.dot$mcD$sp(x, y);
   }

   public double dot$mcD$sp(final Jet x, final Jet y) {
      return BoxesRunTime.unboxToDouble(.MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zip$extension(scala.Predef..MODULE$.genericArrayOps(x.infinitesimal$mcD$sp()), scala.Predef..MODULE$.genericWrapArray(y.infinitesimal$mcD$sp()))), BoxesRunTime.boxToDouble(this.scalar$mcD$sp().times$mcD$sp(x.real$mcD$sp(), y.real$mcD$sp())), (xx, yy) -> BoxesRunTime.boxToDouble($anonfun$dot$2(this, BoxesRunTime.unboxToDouble(xx), yy))));
   }

   public boolean specInstance$() {
      return true;
   }

   // $FF: synthetic method
   public static final double $anonfun$dot$2(final JetAlgebra$mcD$sp $this, final double xx, final Tuple2 yy) {
      return $this.scalar$mcD$sp().plus$mcD$sp(xx, $this.scalar$mcD$sp().times$mcD$sp(yy._1$mcD$sp(), yy._2$mcD$sp()));
   }

   public JetAlgebra$mcD$sp(final ClassTag c, final JetDim d, final Eq eq$mcD$sp, final Field f$mcD$sp, final NRoot n$mcD$sp, final Order o$mcD$sp, final Trig t$mcD$sp, final Signed s$mcD$sp, final VectorSpace v$mcD$sp) {
      super(c, d, (Eq)null, (Field)null, (NRoot)null, (Order)null, (Trig)null, (Signed)null, (VectorSpace)null);
      this.eq$mcD$sp = eq$mcD$sp;
      this.f$mcD$sp = f$mcD$sp;
      this.n$mcD$sp = n$mcD$sp;
      this.o$mcD$sp = o$mcD$sp;
      this.t$mcD$sp = t$mcD$sp;
      this.s$mcD$sp = s$mcD$sp;
      this.v$mcD$sp = v$mcD$sp;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
