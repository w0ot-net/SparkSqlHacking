package spire.math;

import algebra.ring.Field;
import algebra.ring.Rig;
import algebra.ring.Ring;
import algebra.ring.Semiring;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Array.;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;
import spire.algebra.NRoot;
import spire.algebra.Trig;

public final class Jet$ implements JetInstances, Serializable {
   public static final Jet$ MODULE$ = new Jet$();

   static {
      JetInstances.$init$(MODULE$);
   }

   public JetAlgebra JetAlgebra(final ClassTag c, final JetDim d, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return JetInstances.JetAlgebra$(this, c, d, f, n, o, s, t);
   }

   public JetAlgebra JetAlgebra$mDc$sp(final ClassTag c, final JetDim d, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return JetInstances.JetAlgebra$mDc$sp$(this, c, d, f, n, o, s, t);
   }

   public JetAlgebra JetAlgebra$mFc$sp(final ClassTag c, final JetDim d, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return JetInstances.JetAlgebra$mFc$sp$(this, c, d, f, n, o, s, t);
   }

   public Eq JetEq(final Eq evidence$1) {
      return JetInstances.JetEq$(this, evidence$1);
   }

   public Jet apply(final ClassTag c, final JetDim d, final Semiring s) {
      return this.apply(s.zero(), c, d, s);
   }

   public Jet apply(final Object real, final ClassTag c, final JetDim d, final Semiring s) {
      return new Jet(real, .MODULE$.fill(d.dimension(), () -> s.zero(), c));
   }

   public Jet apply(final Object a, final int k, final ClassTag c, final JetDim d, final Rig r) {
      Object v = .MODULE$.fill(d.dimension(), () -> r.zero(), c);
      scala.runtime.ScalaRunTime..MODULE$.array_update(v, k, r.one());
      return new Jet(a, v);
   }

   public Jet h(final int k, final ClassTag c, final JetDim d, final Rig r) {
      return this.apply(r.zero(), k, c, d, r);
   }

   public Jet one(final ClassTag c, final JetDim d, final Rig r) {
      return this.apply(r.one(), c, d, r);
   }

   public Jet zero(final ClassTag c, final JetDim d, final Semiring s) {
      return this.apply(s.zero(), c, d, s);
   }

   public Jet fromInt(final int n, final ClassTag c, final JetDim d, final Ring r) {
      return this.apply(r.fromInt(n), c, d, r);
   }

   public Jet intToJet(final int n, final JetDim d) {
      return this.doubleToJet((double)n, d);
   }

   public Jet longToJet(final long n, final JetDim d) {
      return this.doubleToJet((double)n, d);
   }

   public Jet floatToJet(final float n, final JetDim d) {
      return new Jet$mcF$sp(n, (float[]).MODULE$.fill(d.dimension(), (JFunction0.mcF.sp)() -> 0.0F, scala.reflect.ClassTag..MODULE$.Float()));
   }

   public Jet doubleToJet(final double n, final JetDim d) {
      return new Jet$mcD$sp(n, (double[]).MODULE$.fill(d.dimension(), (JFunction0.mcD.sp)() -> (double)0.0F, scala.reflect.ClassTag..MODULE$.Double()));
   }

   public Jet bigIntToJet(final BigInt n, final JetDim d) {
      return this.bigDecimalToJet(scala.math.BigDecimal..MODULE$.apply(n), d);
   }

   public Jet bigDecimalToJet(final BigDecimal n, final JetDim d) {
      return new Jet(n, .MODULE$.fill(d.dimension(), () -> scala.math.BigDecimal..MODULE$.double2bigDecimal((double)0.0F), scala.reflect.ClassTag..MODULE$.apply(BigDecimal.class)));
   }

   public Jet apply(final Object real, final Object infinitesimal) {
      return new Jet(real, infinitesimal);
   }

   public Option unapply(final Jet x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.real(), x$0.infinitesimal())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Jet$.class);
   }

   public Jet apply$mDc$sp(final ClassTag c, final JetDim d, final Semiring s) {
      return this.apply$mDc$sp(s.zero$mcD$sp(), c, d, s);
   }

   public Jet apply$mFc$sp(final ClassTag c, final JetDim d, final Semiring s) {
      return this.apply$mFc$sp(s.zero$mcF$sp(), c, d, s);
   }

   public Jet apply$mDc$sp(final double real, final ClassTag c, final JetDim d, final Semiring s) {
      return new Jet$mcD$sp(real, (double[]).MODULE$.fill(d.dimension(), (JFunction0.mcD.sp)() -> s.zero$mcD$sp(), c));
   }

   public Jet apply$mFc$sp(final float real, final ClassTag c, final JetDim d, final Semiring s) {
      return new Jet$mcF$sp(real, (float[]).MODULE$.fill(d.dimension(), (JFunction0.mcF.sp)() -> s.zero$mcF$sp(), c));
   }

   public Jet apply$mDc$sp(final double a, final int k, final ClassTag c, final JetDim d, final Rig r) {
      double[] v = (double[]).MODULE$.fill(d.dimension(), (JFunction0.mcD.sp)() -> r.zero$mcD$sp(), c);
      v[k] = r.one$mcD$sp();
      return new Jet$mcD$sp(a, v);
   }

   public Jet apply$mFc$sp(final float a, final int k, final ClassTag c, final JetDim d, final Rig r) {
      float[] v = (float[]).MODULE$.fill(d.dimension(), (JFunction0.mcF.sp)() -> r.zero$mcF$sp(), c);
      v[k] = r.one$mcF$sp();
      return new Jet$mcF$sp(a, v);
   }

   public Jet h$mDc$sp(final int k, final ClassTag c, final JetDim d, final Rig r) {
      return this.apply$mDc$sp(r.zero$mcD$sp(), k, c, d, r);
   }

   public Jet h$mFc$sp(final int k, final ClassTag c, final JetDim d, final Rig r) {
      return this.apply$mFc$sp(r.zero$mcF$sp(), k, c, d, r);
   }

   public Jet one$mDc$sp(final ClassTag c, final JetDim d, final Rig r) {
      return this.apply$mDc$sp(r.one$mcD$sp(), c, d, r);
   }

   public Jet one$mFc$sp(final ClassTag c, final JetDim d, final Rig r) {
      return this.apply$mFc$sp(r.one$mcF$sp(), c, d, r);
   }

   public Jet zero$mDc$sp(final ClassTag c, final JetDim d, final Semiring s) {
      return this.apply$mDc$sp(s.zero$mcD$sp(), c, d, s);
   }

   public Jet zero$mFc$sp(final ClassTag c, final JetDim d, final Semiring s) {
      return this.apply$mFc$sp(s.zero$mcF$sp(), c, d, s);
   }

   public Jet fromInt$mDc$sp(final int n, final ClassTag c, final JetDim d, final Ring r) {
      return this.apply$mDc$sp(r.fromInt$mcD$sp(n), c, d, r);
   }

   public Jet fromInt$mFc$sp(final int n, final ClassTag c, final JetDim d, final Ring r) {
      return this.apply$mFc$sp(r.fromInt$mcF$sp(n), c, d, r);
   }

   public Jet apply$mDc$sp(final double real, final double[] infinitesimal) {
      return new Jet$mcD$sp(real, infinitesimal);
   }

   public Jet apply$mFc$sp(final float real, final float[] infinitesimal) {
      return new Jet$mcF$sp(real, infinitesimal);
   }

   public Option unapply$mDc$sp(final Jet x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToDouble(x$0.real$mcD$sp()), x$0.infinitesimal$mcD$sp())));
   }

   public Option unapply$mFc$sp(final Jet x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToFloat(x$0.real$mcF$sp()), x$0.infinitesimal$mcF$sp())));
   }

   private Jet$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
