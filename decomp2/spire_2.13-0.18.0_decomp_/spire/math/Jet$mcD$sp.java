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
import scala.runtime.java8.JFunction1;
import spire.algebra.CModule;
import spire.algebra.IsReal;
import spire.algebra.NRoot;
import spire.algebra.Trig;
import spire.algebra.VectorSpace;
import spire.std.ArraySupport$;
import spire.syntax.LiteralIntMultiplicativeSemigroupOps$;

public final class Jet$mcD$sp extends Jet {
   private static final long serialVersionUID = 0L;
   public final double real$mcD$sp;
   public final double[] infinitesimal$mcD$sp;

   public double real$mcD$sp() {
      return this.real$mcD$sp;
   }

   public double real() {
      return this.real$mcD$sp();
   }

   public double[] infinitesimal$mcD$sp() {
      return this.infinitesimal$mcD$sp;
   }

   public double[] infinitesimal() {
      return this.infinitesimal$mcD$sp();
   }

   public int signum(final Signed r) {
      return this.signum$mcD$sp(r);
   }

   public int signum$mcD$sp(final Signed r) {
      return r.signum$mcD$sp(this.real());
   }

   public Tuple2 asTuple() {
      return this.asTuple$mcD$sp();
   }

   public Tuple2 asTuple$mcD$sp() {
      return new Tuple2(BoxesRunTime.boxToDouble(this.real()), this.infinitesimal());
   }

   public boolean eqv(final Jet b, final Eq o) {
      return this.eqv$mcD$sp(b, o);
   }

   public boolean eqv$mcD$sp(final Jet b, final Eq o) {
      return o.eqv$mcD$sp(this.real(), b.real$mcD$sp()) && ArraySupport$.MODULE$.eqv$mDc$sp(this.infinitesimal(), b.infinitesimal$mcD$sp(), o);
   }

   public boolean neqv(final Jet b, final Eq o) {
      return this.neqv$mcD$sp(b, o);
   }

   public boolean neqv$mcD$sp(final Jet b, final Eq o) {
      return !this.eqv$mcD$sp(b, o);
   }

   public Jet unary_$minus(final Field f, final VectorSpace v) {
      return this.unary_$minus$mcD$sp(f, v);
   }

   public Jet unary_$minus$mcD$sp(final Field f, final VectorSpace v) {
      return new Jet$mcD$sp(f.negate$mcD$sp(this.real()), (double[])v.negate(this.infinitesimal()));
   }

   public Jet $plus(final double b, final Field f) {
      return this.$plus$mcD$sp(b, f);
   }

   public Jet $plus$mcD$sp(final double b, final Field f) {
      return new Jet$mcD$sp(f.plus$mcD$sp(this.real(), b), this.infinitesimal());
   }

   public Jet $minus(final double b, final Field f) {
      return this.$minus$mcD$sp(b, f);
   }

   public Jet $minus$mcD$sp(final double b, final Field f) {
      return new Jet$mcD$sp(f.minus$mcD$sp(this.real(), b), this.infinitesimal());
   }

   public Jet $times(final double b, final Field f, final VectorSpace v) {
      return this.$times$mcD$sp(b, f, v);
   }

   public Jet $times$mcD$sp(final double b, final Field f, final VectorSpace v) {
      return new Jet$mcD$sp(f.times$mcD$sp(this.real(), b), (double[])v.timesr$mcD$sp(this.infinitesimal(), b));
   }

   public Jet $div(final double b, final Field f, final VectorSpace v) {
      return this.$div$mcD$sp(b, f, v);
   }

   public Jet $div$mcD$sp(final double b, final Field f, final VectorSpace v) {
      return new Jet$mcD$sp(f.div$mcD$sp(this.real(), b), (double[])v.divr$mcD$sp(this.infinitesimal(), b));
   }

   public Jet $plus(final Jet b, final Field f, final VectorSpace v) {
      return this.$plus$mcD$sp(b, f, v);
   }

   public Jet $plus$mcD$sp(final Jet b, final Field f, final VectorSpace v) {
      return new Jet$mcD$sp(f.plus$mcD$sp(this.real(), b.real$mcD$sp()), (double[])v.plus(this.infinitesimal(), b.infinitesimal$mcD$sp()));
   }

   public Jet $minus(final Jet b, final Field f, final VectorSpace v) {
      return this.$minus$mcD$sp(b, f, v);
   }

   public Jet $minus$mcD$sp(final Jet b, final Field f, final VectorSpace v) {
      return new Jet$mcD$sp(f.minus$mcD$sp(this.real(), b.real$mcD$sp()), (double[])v.minus(this.infinitesimal(), b.infinitesimal$mcD$sp()));
   }

   public Jet $times(final Jet b, final Field f, final VectorSpace v) {
      return this.$times$mcD$sp(b, f, v);
   }

   public Jet $times$mcD$sp(final Jet b, final Field f, final VectorSpace v) {
      double var10002 = f.times$mcD$sp(this.real(), b.real$mcD$sp());
      double var4 = b.real$mcD$sp();
      double[] var10004 = (double[])v.timesl$mcD$sp(var4, this.infinitesimal());
      double var6 = this.real();
      return new Jet$mcD$sp(var10002, (double[])v.plus(var10004, (double[])v.timesl$mcD$sp(var6, b.infinitesimal$mcD$sp())));
   }

   public Jet $div(final Jet b, final Field f, final VectorSpace v) {
      return this.$div$mcD$sp(b, f, v);
   }

   public Jet $div$mcD$sp(final Jet b, final Field f, final VectorSpace v) {
      double br_inv = f.div$mcD$sp(f.one$mcD$sp(), b.real$mcD$sp());
      double ar_div_br = f.times$mcD$sp(this.real(), br_inv);
      return new Jet$mcD$sp(ar_div_br, (double[])v.timesl$mcD$sp(br_inv, (double[])v.minus(this.infinitesimal(), (double[])v.timesl$mcD$sp(ar_div_br, b.infinitesimal$mcD$sp()))));
   }

   public Jet $div$tilde(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      return this.$div$tilde$mcD$sp(b, c, f, r, v);
   }

   public Jet $div$tilde$mcD$sp(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      Jet q = this.$div$mcD$sp(b, f, v);
      return new Jet$mcD$sp(r.floor$mcD$sp(q.real$mcD$sp()), (double[]).MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(q.infinitesimal$mcD$sp()), (JFunction1.mcDD.sp)(a) -> r.floor$mcD$sp(a), c));
   }

   public Jet $percent(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      return this.$percent$mcD$sp(b, c, f, r, v);
   }

   public Jet $percent$mcD$sp(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      return this.$minus$mcD$sp(this.$div$tilde$mcD$sp(b, c, f, r, v).$times$mcD$sp(b, f, v), f, v);
   }

   public Tuple2 $div$percent(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      return this.$div$percent$mcD$sp(b, c, f, r, v);
   }

   public Tuple2 $div$percent$mcD$sp(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      Jet q = this.$div$tilde$mcD$sp(b, c, f, r, v);
      return new Tuple2(q, this.$minus$mcD$sp(q.$times$mcD$sp(b, f, v), f, v));
   }

   public Jet $times$times(final int b, final Field f, final VectorSpace v) {
      return this.$times$times$mcD$sp(b, f, v);
   }

   public Jet $times$times$mcD$sp(final int b, final Field f, final VectorSpace v) {
      return this.pow$mcD$sp(b, f, v);
   }

   public Jet nroot(final int k, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.nroot$mcD$sp(k, f, o, s, t, v);
   }

   public Jet nroot$mcD$sp(final int k, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.pow$mcD$sp(f.reciprocal$mcD$sp(f.fromInt$mcD$sp(k)), f, o, s, t, v);
   }

   public Jet $times$times(final Jet b, final ClassTag c, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.$times$times$mcD$sp(b, c, f, o, s, t, v);
   }

   public Jet $times$times$mcD$sp(final Jet b, final ClassTag c, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.pow$mcD$sp(b, c, f, v, o, s, t);
   }

   public Jet floor(final ClassTag c, final IsReal r) {
      return this.floor$mcD$sp(c, r);
   }

   public Jet floor$mcD$sp(final ClassTag c, final IsReal r) {
      return new Jet$mcD$sp(r.floor$mcD$sp(this.real()), (double[]).MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(this.infinitesimal()), (JFunction1.mcDD.sp)(a) -> r.floor$mcD$sp(a), c));
   }

   public Jet ceil(final ClassTag c, final IsReal r) {
      return this.ceil$mcD$sp(c, r);
   }

   public Jet ceil$mcD$sp(final ClassTag c, final IsReal r) {
      return new Jet$mcD$sp(r.ceil$mcD$sp(this.real()), (double[]).MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(this.infinitesimal()), (JFunction1.mcDD.sp)(a) -> r.ceil$mcD$sp(a), c));
   }

   public Jet round(final ClassTag c, final IsReal r) {
      return this.round$mcD$sp(c, r);
   }

   public Jet round$mcD$sp(final ClassTag c, final IsReal r) {
      return new Jet$mcD$sp(r.round$mcD$sp(this.real()), (double[]).MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(this.infinitesimal()), (JFunction1.mcDD.sp)(a) -> r.round$mcD$sp(a), c));
   }

   public Jet abs(final Field f, final Order o, final Signed s, final VectorSpace v) {
      return this.abs$mcD$sp(f, o, s, v);
   }

   public Jet abs$mcD$sp(final Field f, final Order o, final Signed s, final VectorSpace v) {
      return o.lt$mcD$sp(this.real(), f.zero$mcD$sp()) ? new Jet$mcD$sp(f.negate$mcD$sp(this.real()), (double[])v.negate(this.infinitesimal())) : this;
   }

   public double powScalarToScalar(final double b, final double e, final Field f, final Order o, final Signed s, final Trig t) {
      return this.powScalarToScalar$mcD$sp(b, e, f, o, s, t);
   }

   public double powScalarToScalar$mcD$sp(final double b, final double e, final Field f, final Order o, final Signed s, final Trig t) {
      double var10000;
      if (o.eqv$mcD$sp(e, f.zero$mcD$sp())) {
         var10000 = f.one$mcD$sp();
      } else if (o.eqv$mcD$sp(b, f.zero$mcD$sp())) {
         if (o.lt$mcD$sp(e, f.zero$mcD$sp())) {
            throw new Exception("raising 0 to a negative power");
         }

         var10000 = f.zero$mcD$sp();
      } else {
         var10000 = BoxesRunTime.unboxToDouble(package$.MODULE$.exp(BoxesRunTime.boxToDouble(f.times$mcD$sp(e, BoxesRunTime.unboxToDouble(package$.MODULE$.log(BoxesRunTime.boxToDouble(b), t)))), t));
      }

      return var10000;
   }

   public Jet powScalarToJet(final double a, final ClassTag c, final Field f, final CModule m, final Order o, final Signed s, final Trig t) {
      return this.powScalarToJet$mcD$sp(a, c, f, m, o, s, t);
   }

   public Jet powScalarToJet$mcD$sp(final double a, final ClassTag c, final Field f, final CModule m, final Order o, final Signed s, final Trig t) {
      Object var10000;
      if (this.isZero()) {
         var10000 = Jet$.MODULE$.one$mDc$sp(c, this.jetDimension(), f);
      } else {
         double tmp = this.powScalarToScalar$mcD$sp(a, this.real(), f, o, s, t);
         double var11 = f.times$mcD$sp(BoxesRunTime.unboxToDouble(package$.MODULE$.log(BoxesRunTime.boxToDouble(a), t)), tmp);
         var10000 = new Jet$mcD$sp(tmp, (double[])m.timesl$mcD$sp(var11, this.infinitesimal()));
      }

      return (Jet)var10000;
   }

   public Jet pow(final double p, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.pow$mcD$sp(p, f, o, s, t, v);
   }

   public Jet pow$mcD$sp(final double p, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      double tmp = f.times$mcD$sp(p, this.powScalarToScalar$mcD$sp(this.real(), f.minus$mcD$sp(p, f.one$mcD$sp()), f, o, s, t));
      return new Jet$mcD$sp(this.powScalarToScalar$mcD$sp(this.real(), p, f, o, s, t), (double[])v.timesl$mcD$sp(tmp, this.infinitesimal()));
   }

   public Jet pow(final int p, final Field f, final VectorSpace v) {
      return this.pow$mcD$sp(p, f, v);
   }

   public Jet pow$mcD$sp(final int p, final Field f, final VectorSpace v) {
      double tmp = BoxesRunTime.unboxToDouble(LiteralIntMultiplicativeSemigroupOps$.MODULE$.$times$extension(spire.syntax.package.vectorSpace$.MODULE$.literalIntMultiplicativeSemigroupOps(p), BoxesRunTime.boxToDouble(f.pow$mcD$sp(this.real(), p - 1)), f));
      return new Jet$mcD$sp(f.pow$mcD$sp(this.real(), p), (double[])v.timesl$mcD$sp(tmp, this.infinitesimal()));
   }

   public Jet pow(final Jet b, final ClassTag c, final Field f, final CModule m, final Order o, final Signed s, final Trig t) {
      return this.pow$mcD$sp(b, c, f, m, o, s, t);
   }

   public Jet pow$mcD$sp(final Jet b, final ClassTag c, final Field f, final CModule m, final Order o, final Signed s, final Trig t) {
      Object var10000;
      if (b.isZero()) {
         var10000 = Jet$.MODULE$.one$mDc$sp(c, this.jetDimension(), f);
      } else {
         double tmp1 = this.powScalarToScalar$mcD$sp(this.real(), b.real$mcD$sp(), f, o, s, t);
         double tmp2 = f.times$mcD$sp(b.real$mcD$sp(), this.powScalarToScalar$mcD$sp(this.real(), f.minus$mcD$sp(b.real$mcD$sp(), f.one$mcD$sp()), f, o, s, t));
         double tmp3 = f.times$mcD$sp(tmp1, BoxesRunTime.unboxToDouble(package$.MODULE$.log(BoxesRunTime.boxToDouble(this.real()), t)));
         var10000 = new Jet$mcD$sp(tmp1, (double[])m.plus((double[])m.timesl$mcD$sp(tmp2, this.infinitesimal()), (double[])m.timesl$mcD$sp(tmp3, b.infinitesimal$mcD$sp())));
      }

      return (Jet)var10000;
   }

   public Jet log(final Field f, final Trig t, final VectorSpace v) {
      return this.log$mcD$sp(f, t, v);
   }

   public Jet log$mcD$sp(final Field f, final Trig t, final VectorSpace v) {
      double var10002 = BoxesRunTime.unboxToDouble(package$.MODULE$.log(BoxesRunTime.boxToDouble(this.real()), t));
      double var4 = f.div$mcD$sp(f.one$mcD$sp(), this.real());
      return new Jet$mcD$sp(var10002, (double[])v.timesl$mcD$sp(var4, this.infinitesimal()));
   }

   public Jet sqrt(final Field f, final NRoot n, final VectorSpace v) {
      return this.sqrt$mcD$sp(f, n, v);
   }

   public Jet sqrt$mcD$sp(final Field f, final NRoot n, final VectorSpace v) {
      double sa = n.sqrt$mcD$sp(this.real());
      double oneHalf = f.div$mcD$sp(f.one$mcD$sp(), f.plus$mcD$sp(f.one$mcD$sp(), f.one$mcD$sp()));
      double var8 = f.div$mcD$sp(oneHalf, sa);
      return new Jet$mcD$sp(sa, (double[])v.timesl$mcD$sp(var8, this.infinitesimal()));
   }

   public Jet acos(final Field f, final NRoot n, final Trig t, final VectorSpace v) {
      return this.acos$mcD$sp(f, n, t, v);
   }

   public Jet acos$mcD$sp(final Field f, final NRoot n, final Trig t, final VectorSpace v) {
      double tmp = f.div$mcD$sp(f.negate$mcD$sp(f.one$mcD$sp()), BoxesRunTime.unboxToDouble(package$.MODULE$.sqrt(BoxesRunTime.boxToDouble(f.minus$mcD$sp(f.one$mcD$sp(), f.times$mcD$sp(this.real(), this.real()))), n)));
      return new Jet$mcD$sp(package$.MODULE$.acos$mDc$sp(this.real(), t), (double[])v.timesl$mcD$sp(tmp, this.infinitesimal()));
   }

   public Jet asin(final Field f, final NRoot n, final Trig t, final VectorSpace v) {
      return this.asin$mcD$sp(f, n, t, v);
   }

   public Jet asin$mcD$sp(final Field f, final NRoot n, final Trig t, final VectorSpace v) {
      double tmp = f.div$mcD$sp(f.one$mcD$sp(), BoxesRunTime.unboxToDouble(package$.MODULE$.sqrt(BoxesRunTime.boxToDouble(f.minus$mcD$sp(f.one$mcD$sp(), f.times$mcD$sp(this.real(), this.real()))), n)));
      return new Jet$mcD$sp(package$.MODULE$.asin$mDc$sp(this.real(), t), (double[])v.timesl$mcD$sp(tmp, this.infinitesimal()));
   }

   public Jet atan(final Field f, final Trig t, final VectorSpace v) {
      return this.atan$mcD$sp(f, t, v);
   }

   public Jet atan$mcD$sp(final Field f, final Trig t, final VectorSpace v) {
      double tmp = f.div$mcD$sp(f.one$mcD$sp(), f.plus$mcD$sp(f.one$mcD$sp(), f.times$mcD$sp(this.real(), this.real())));
      return new Jet$mcD$sp(package$.MODULE$.atan$mDc$sp(this.real(), t), (double[])v.timesl$mcD$sp(tmp, this.infinitesimal()));
   }

   public Jet atan2(final Jet a, final Field f, final Trig t, final VectorSpace v) {
      return this.atan2$mcD$sp(a, f, t, v);
   }

   public Jet atan2$mcD$sp(final Jet a, final Field f, final Trig t, final VectorSpace v) {
      double tmp = f.div$mcD$sp(f.one$mcD$sp(), f.plus$mcD$sp(f.times$mcD$sp(a.real$mcD$sp(), a.real$mcD$sp()), f.times$mcD$sp(this.real(), this.real())));
      double var10002 = package$.MODULE$.atan2$mDc$sp(this.real(), a.real$mcD$sp(), t);
      double var7 = f.times$mcD$sp(tmp, f.negate$mcD$sp(this.real()));
      double[] var10004 = (double[])v.timesl$mcD$sp(var7, a.infinitesimal$mcD$sp());
      double var9 = f.times$mcD$sp(tmp, a.real$mcD$sp());
      return new Jet$mcD$sp(var10002, (double[])v.plus(var10004, (double[])v.timesl$mcD$sp(var9, this.infinitesimal())));
   }

   public Jet exp(final Trig t, final VectorSpace v) {
      return this.exp$mcD$sp(t, v);
   }

   public Jet exp$mcD$sp(final Trig t, final VectorSpace v) {
      double ea = BoxesRunTime.unboxToDouble(package$.MODULE$.exp(BoxesRunTime.boxToDouble(this.real()), t));
      return new Jet$mcD$sp(ea, (double[])v.timesl$mcD$sp(ea, this.infinitesimal()));
   }

   public Jet sin(final Trig t, final VectorSpace v) {
      return this.sin$mcD$sp(t, v);
   }

   public Jet sin$mcD$sp(final Trig t, final VectorSpace v) {
      double var10002 = package$.MODULE$.sin$mDc$sp(this.real(), t);
      double var3 = package$.MODULE$.cos$mDc$sp(this.real(), t);
      return new Jet$mcD$sp(var10002, (double[])v.timesl$mcD$sp(var3, this.infinitesimal()));
   }

   public Jet sinh(final Trig t, final VectorSpace v) {
      return this.sinh$mcD$sp(t, v);
   }

   public Jet sinh$mcD$sp(final Trig t, final VectorSpace v) {
      double var10002 = package$.MODULE$.sinh$mDc$sp(this.real(), t);
      double var3 = package$.MODULE$.cosh$mDc$sp(this.real(), t);
      return new Jet$mcD$sp(var10002, (double[])v.timesl$mcD$sp(var3, this.infinitesimal()));
   }

   public Jet cos(final Field f, final Trig t, final VectorSpace v) {
      return this.cos$mcD$sp(f, t, v);
   }

   public Jet cos$mcD$sp(final Field f, final Trig t, final VectorSpace v) {
      double var10002 = package$.MODULE$.cos$mDc$sp(this.real(), t);
      double var4 = f.negate$mcD$sp(package$.MODULE$.sin$mDc$sp(this.real(), t));
      return new Jet$mcD$sp(var10002, (double[])v.timesl$mcD$sp(var4, this.infinitesimal()));
   }

   public Jet cosh(final Trig t, final VectorSpace v) {
      return this.cosh$mcD$sp(t, v);
   }

   public Jet cosh$mcD$sp(final Trig t, final VectorSpace v) {
      double var10002 = package$.MODULE$.cosh$mDc$sp(this.real(), t);
      double var3 = package$.MODULE$.sinh$mDc$sp(this.real(), t);
      return new Jet$mcD$sp(var10002, (double[])v.timesl$mcD$sp(var3, this.infinitesimal()));
   }

   public Jet tan(final Field f, final Trig t, final VectorSpace v) {
      return this.tan$mcD$sp(f, t, v);
   }

   public Jet tan$mcD$sp(final Field f, final Trig t, final VectorSpace v) {
      double tan_a = package$.MODULE$.tan$mDc$sp(this.real(), t);
      double tmp = f.plus$mcD$sp(f.one$mcD$sp(), f.times$mcD$sp(tan_a, tan_a));
      return new Jet$mcD$sp(tan_a, (double[])v.timesl$mcD$sp(tmp, this.infinitesimal()));
   }

   public Jet tanh(final Field f, final Trig t, final VectorSpace v) {
      return this.tanh$mcD$sp(f, t, v);
   }

   public Jet tanh$mcD$sp(final Field f, final Trig t, final VectorSpace v) {
      double tanh_a = package$.MODULE$.tanh$mDc$sp(this.real(), t);
      double tmp = f.minus$mcD$sp(f.one$mcD$sp(), f.times$mcD$sp(tanh_a, tanh_a));
      return new Jet$mcD$sp(tanh_a, (double[])v.timesl$mcD$sp(tmp, this.infinitesimal()));
   }

   public double copy$default$1() {
      return this.copy$default$1$mcD$sp();
   }

   public double copy$default$1$mcD$sp() {
      return this.real();
   }

   public double[] copy$default$2() {
      return this.copy$default$2$mcD$sp();
   }

   public double[] copy$default$2$mcD$sp() {
      return this.infinitesimal();
   }

   public boolean specInstance$() {
      return true;
   }

   public Jet$mcD$sp(final double real$mcD$sp, final double[] infinitesimal$mcD$sp) {
      super((Object)null, (Object)null);
      this.real$mcD$sp = real$mcD$sp;
      this.infinitesimal$mcD$sp = infinitesimal$mcD$sp;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
