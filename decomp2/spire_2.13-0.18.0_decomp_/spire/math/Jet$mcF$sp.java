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

public final class Jet$mcF$sp extends Jet {
   private static final long serialVersionUID = 0L;
   public final float real$mcF$sp;
   public final float[] infinitesimal$mcF$sp;

   public float real$mcF$sp() {
      return this.real$mcF$sp;
   }

   public float real() {
      return this.real$mcF$sp();
   }

   public float[] infinitesimal$mcF$sp() {
      return this.infinitesimal$mcF$sp;
   }

   public float[] infinitesimal() {
      return this.infinitesimal$mcF$sp();
   }

   public int signum(final Signed r) {
      return this.signum$mcF$sp(r);
   }

   public int signum$mcF$sp(final Signed r) {
      return r.signum$mcF$sp(this.real());
   }

   public Tuple2 asTuple() {
      return this.asTuple$mcF$sp();
   }

   public Tuple2 asTuple$mcF$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.real()), this.infinitesimal());
   }

   public boolean eqv(final Jet b, final Eq o) {
      return this.eqv$mcF$sp(b, o);
   }

   public boolean eqv$mcF$sp(final Jet b, final Eq o) {
      return o.eqv$mcF$sp(this.real(), b.real$mcF$sp()) && ArraySupport$.MODULE$.eqv$mFc$sp(this.infinitesimal(), b.infinitesimal$mcF$sp(), o);
   }

   public boolean neqv(final Jet b, final Eq o) {
      return this.neqv$mcF$sp(b, o);
   }

   public boolean neqv$mcF$sp(final Jet b, final Eq o) {
      return !this.eqv$mcF$sp(b, o);
   }

   public Jet unary_$minus(final Field f, final VectorSpace v) {
      return this.unary_$minus$mcF$sp(f, v);
   }

   public Jet unary_$minus$mcF$sp(final Field f, final VectorSpace v) {
      return new Jet$mcF$sp(f.negate$mcF$sp(this.real()), (float[])v.negate(this.infinitesimal()));
   }

   public Jet $plus(final float b, final Field f) {
      return this.$plus$mcF$sp(b, f);
   }

   public Jet $plus$mcF$sp(final float b, final Field f) {
      return new Jet$mcF$sp(f.plus$mcF$sp(this.real(), b), this.infinitesimal());
   }

   public Jet $minus(final float b, final Field f) {
      return this.$minus$mcF$sp(b, f);
   }

   public Jet $minus$mcF$sp(final float b, final Field f) {
      return new Jet$mcF$sp(f.minus$mcF$sp(this.real(), b), this.infinitesimal());
   }

   public Jet $times(final float b, final Field f, final VectorSpace v) {
      return this.$times$mcF$sp(b, f, v);
   }

   public Jet $times$mcF$sp(final float b, final Field f, final VectorSpace v) {
      return new Jet$mcF$sp(f.times$mcF$sp(this.real(), b), (float[])v.timesr$mcF$sp(this.infinitesimal(), b));
   }

   public Jet $div(final float b, final Field f, final VectorSpace v) {
      return this.$div$mcF$sp(b, f, v);
   }

   public Jet $div$mcF$sp(final float b, final Field f, final VectorSpace v) {
      return new Jet$mcF$sp(f.div$mcF$sp(this.real(), b), (float[])v.divr$mcF$sp(this.infinitesimal(), b));
   }

   public Jet $plus(final Jet b, final Field f, final VectorSpace v) {
      return this.$plus$mcF$sp(b, f, v);
   }

   public Jet $plus$mcF$sp(final Jet b, final Field f, final VectorSpace v) {
      return new Jet$mcF$sp(f.plus$mcF$sp(this.real(), b.real$mcF$sp()), (float[])v.plus(this.infinitesimal(), b.infinitesimal$mcF$sp()));
   }

   public Jet $minus(final Jet b, final Field f, final VectorSpace v) {
      return this.$minus$mcF$sp(b, f, v);
   }

   public Jet $minus$mcF$sp(final Jet b, final Field f, final VectorSpace v) {
      return new Jet$mcF$sp(f.minus$mcF$sp(this.real(), b.real$mcF$sp()), (float[])v.minus(this.infinitesimal(), b.infinitesimal$mcF$sp()));
   }

   public Jet $times(final Jet b, final Field f, final VectorSpace v) {
      return this.$times$mcF$sp(b, f, v);
   }

   public Jet $times$mcF$sp(final Jet b, final Field f, final VectorSpace v) {
      float var10002 = f.times$mcF$sp(this.real(), b.real$mcF$sp());
      float var4 = b.real$mcF$sp();
      float[] var10004 = (float[])v.timesl$mcF$sp(var4, this.infinitesimal());
      float var5 = this.real();
      return new Jet$mcF$sp(var10002, (float[])v.plus(var10004, (float[])v.timesl$mcF$sp(var5, b.infinitesimal$mcF$sp())));
   }

   public Jet $div(final Jet b, final Field f, final VectorSpace v) {
      return this.$div$mcF$sp(b, f, v);
   }

   public Jet $div$mcF$sp(final Jet b, final Field f, final VectorSpace v) {
      float br_inv = f.div$mcF$sp(f.one$mcF$sp(), b.real$mcF$sp());
      float ar_div_br = f.times$mcF$sp(this.real(), br_inv);
      return new Jet$mcF$sp(ar_div_br, (float[])v.timesl$mcF$sp(br_inv, (float[])v.minus(this.infinitesimal(), (float[])v.timesl$mcF$sp(ar_div_br, b.infinitesimal$mcF$sp()))));
   }

   public Jet $div$tilde(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      return this.$div$tilde$mcF$sp(b, c, f, r, v);
   }

   public Jet $div$tilde$mcF$sp(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      Jet q = this.$div$mcF$sp(b, f, v);
      return new Jet$mcF$sp(r.floor$mcF$sp(q.real$mcF$sp()), (float[]).MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(q.infinitesimal$mcF$sp()), (JFunction1.mcFF.sp)(a) -> r.floor$mcF$sp(a), c));
   }

   public Jet $percent(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      return this.$percent$mcF$sp(b, c, f, r, v);
   }

   public Jet $percent$mcF$sp(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      return this.$minus$mcF$sp(this.$div$tilde$mcF$sp(b, c, f, r, v).$times$mcF$sp(b, f, v), f, v);
   }

   public Tuple2 $div$percent(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      return this.$div$percent$mcF$sp(b, c, f, r, v);
   }

   public Tuple2 $div$percent$mcF$sp(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      Jet q = this.$div$tilde$mcF$sp(b, c, f, r, v);
      return new Tuple2(q, this.$minus$mcF$sp(q.$times$mcF$sp(b, f, v), f, v));
   }

   public Jet $times$times(final int b, final Field f, final VectorSpace v) {
      return this.$times$times$mcF$sp(b, f, v);
   }

   public Jet $times$times$mcF$sp(final int b, final Field f, final VectorSpace v) {
      return this.pow$mcF$sp(b, f, v);
   }

   public Jet nroot(final int k, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.nroot$mcF$sp(k, f, o, s, t, v);
   }

   public Jet nroot$mcF$sp(final int k, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.pow$mcF$sp(f.reciprocal$mcF$sp(f.fromInt$mcF$sp(k)), f, o, s, t, v);
   }

   public Jet $times$times(final Jet b, final ClassTag c, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.$times$times$mcF$sp(b, c, f, o, s, t, v);
   }

   public Jet $times$times$mcF$sp(final Jet b, final ClassTag c, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.pow$mcF$sp(b, c, f, v, o, s, t);
   }

   public Jet floor(final ClassTag c, final IsReal r) {
      return this.floor$mcF$sp(c, r);
   }

   public Jet floor$mcF$sp(final ClassTag c, final IsReal r) {
      return new Jet$mcF$sp(r.floor$mcF$sp(this.real()), (float[]).MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(this.infinitesimal()), (JFunction1.mcFF.sp)(a) -> r.floor$mcF$sp(a), c));
   }

   public Jet ceil(final ClassTag c, final IsReal r) {
      return this.ceil$mcF$sp(c, r);
   }

   public Jet ceil$mcF$sp(final ClassTag c, final IsReal r) {
      return new Jet$mcF$sp(r.ceil$mcF$sp(this.real()), (float[]).MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(this.infinitesimal()), (JFunction1.mcFF.sp)(a) -> r.ceil$mcF$sp(a), c));
   }

   public Jet round(final ClassTag c, final IsReal r) {
      return this.round$mcF$sp(c, r);
   }

   public Jet round$mcF$sp(final ClassTag c, final IsReal r) {
      return new Jet$mcF$sp(r.round$mcF$sp(this.real()), (float[]).MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(this.infinitesimal()), (JFunction1.mcFF.sp)(a) -> r.round$mcF$sp(a), c));
   }

   public Jet abs(final Field f, final Order o, final Signed s, final VectorSpace v) {
      return this.abs$mcF$sp(f, o, s, v);
   }

   public Jet abs$mcF$sp(final Field f, final Order o, final Signed s, final VectorSpace v) {
      return o.lt$mcF$sp(this.real(), f.zero$mcF$sp()) ? new Jet$mcF$sp(f.negate$mcF$sp(this.real()), (float[])v.negate(this.infinitesimal())) : this;
   }

   public float powScalarToScalar(final float b, final float e, final Field f, final Order o, final Signed s, final Trig t) {
      return this.powScalarToScalar$mcF$sp(b, e, f, o, s, t);
   }

   public float powScalarToScalar$mcF$sp(final float b, final float e, final Field f, final Order o, final Signed s, final Trig t) {
      float var10000;
      if (o.eqv$mcF$sp(e, f.zero$mcF$sp())) {
         var10000 = f.one$mcF$sp();
      } else if (o.eqv$mcF$sp(b, f.zero$mcF$sp())) {
         if (o.lt$mcF$sp(e, f.zero$mcF$sp())) {
            throw new Exception("raising 0 to a negative power");
         }

         var10000 = f.zero$mcF$sp();
      } else {
         var10000 = BoxesRunTime.unboxToFloat(package$.MODULE$.exp(BoxesRunTime.boxToFloat(f.times$mcF$sp(e, BoxesRunTime.unboxToFloat(package$.MODULE$.log(BoxesRunTime.boxToFloat(b), t)))), t));
      }

      return var10000;
   }

   public Jet powScalarToJet(final float a, final ClassTag c, final Field f, final CModule m, final Order o, final Signed s, final Trig t) {
      return this.powScalarToJet$mcF$sp(a, c, f, m, o, s, t);
   }

   public Jet powScalarToJet$mcF$sp(final float a, final ClassTag c, final Field f, final CModule m, final Order o, final Signed s, final Trig t) {
      Object var10000;
      if (this.isZero()) {
         var10000 = Jet$.MODULE$.one$mFc$sp(c, this.jetDimension(), f);
      } else {
         float tmp = this.powScalarToScalar$mcF$sp(a, this.real(), f, o, s, t);
         float var9 = f.times$mcF$sp(BoxesRunTime.unboxToFloat(package$.MODULE$.log(BoxesRunTime.boxToFloat(a), t)), tmp);
         var10000 = new Jet$mcF$sp(tmp, (float[])m.timesl$mcF$sp(var9, this.infinitesimal()));
      }

      return (Jet)var10000;
   }

   public Jet pow(final float p, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.pow$mcF$sp(p, f, o, s, t, v);
   }

   public Jet pow$mcF$sp(final float p, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      float tmp = f.times$mcF$sp(p, this.powScalarToScalar$mcF$sp(this.real(), f.minus$mcF$sp(p, f.one$mcF$sp()), f, o, s, t));
      return new Jet$mcF$sp(this.powScalarToScalar$mcF$sp(this.real(), p, f, o, s, t), (float[])v.timesl$mcF$sp(tmp, this.infinitesimal()));
   }

   public Jet pow(final int p, final Field f, final VectorSpace v) {
      return this.pow$mcF$sp(p, f, v);
   }

   public Jet pow$mcF$sp(final int p, final Field f, final VectorSpace v) {
      float tmp = BoxesRunTime.unboxToFloat(LiteralIntMultiplicativeSemigroupOps$.MODULE$.$times$extension(spire.syntax.package.vectorSpace$.MODULE$.literalIntMultiplicativeSemigroupOps(p), BoxesRunTime.boxToFloat(f.pow$mcF$sp(this.real(), p - 1)), f));
      return new Jet$mcF$sp(f.pow$mcF$sp(this.real(), p), (float[])v.timesl$mcF$sp(tmp, this.infinitesimal()));
   }

   public Jet pow(final Jet b, final ClassTag c, final Field f, final CModule m, final Order o, final Signed s, final Trig t) {
      return this.pow$mcF$sp(b, c, f, m, o, s, t);
   }

   public Jet pow$mcF$sp(final Jet b, final ClassTag c, final Field f, final CModule m, final Order o, final Signed s, final Trig t) {
      Object var10000;
      if (b.isZero()) {
         var10000 = Jet$.MODULE$.one$mFc$sp(c, this.jetDimension(), f);
      } else {
         float tmp1 = this.powScalarToScalar$mcF$sp(this.real(), b.real$mcF$sp(), f, o, s, t);
         float tmp2 = f.times$mcF$sp(b.real$mcF$sp(), this.powScalarToScalar$mcF$sp(this.real(), f.minus$mcF$sp(b.real$mcF$sp(), f.one$mcF$sp()), f, o, s, t));
         float tmp3 = f.times$mcF$sp(tmp1, BoxesRunTime.unboxToFloat(package$.MODULE$.log(BoxesRunTime.boxToFloat(this.real()), t)));
         var10000 = new Jet$mcF$sp(tmp1, (float[])m.plus((float[])m.timesl$mcF$sp(tmp2, this.infinitesimal()), (float[])m.timesl$mcF$sp(tmp3, b.infinitesimal$mcF$sp())));
      }

      return (Jet)var10000;
   }

   public Jet log(final Field f, final Trig t, final VectorSpace v) {
      return this.log$mcF$sp(f, t, v);
   }

   public Jet log$mcF$sp(final Field f, final Trig t, final VectorSpace v) {
      float var10002 = BoxesRunTime.unboxToFloat(package$.MODULE$.log(BoxesRunTime.boxToFloat(this.real()), t));
      float var4 = f.div$mcF$sp(f.one$mcF$sp(), this.real());
      return new Jet$mcF$sp(var10002, (float[])v.timesl$mcF$sp(var4, this.infinitesimal()));
   }

   public Jet sqrt(final Field f, final NRoot n, final VectorSpace v) {
      return this.sqrt$mcF$sp(f, n, v);
   }

   public Jet sqrt$mcF$sp(final Field f, final NRoot n, final VectorSpace v) {
      float sa = n.sqrt$mcF$sp(this.real());
      float oneHalf = f.div$mcF$sp(f.one$mcF$sp(), f.plus$mcF$sp(f.one$mcF$sp(), f.one$mcF$sp()));
      float var6 = f.div$mcF$sp(oneHalf, sa);
      return new Jet$mcF$sp(sa, (float[])v.timesl$mcF$sp(var6, this.infinitesimal()));
   }

   public Jet acos(final Field f, final NRoot n, final Trig t, final VectorSpace v) {
      return this.acos$mcF$sp(f, n, t, v);
   }

   public Jet acos$mcF$sp(final Field f, final NRoot n, final Trig t, final VectorSpace v) {
      float tmp = f.div$mcF$sp(f.negate$mcF$sp(f.one$mcF$sp()), BoxesRunTime.unboxToFloat(package$.MODULE$.sqrt(BoxesRunTime.boxToFloat(f.minus$mcF$sp(f.one$mcF$sp(), f.times$mcF$sp(this.real(), this.real()))), n)));
      return new Jet$mcF$sp(package$.MODULE$.acos$mFc$sp(this.real(), t), (float[])v.timesl$mcF$sp(tmp, this.infinitesimal()));
   }

   public Jet asin(final Field f, final NRoot n, final Trig t, final VectorSpace v) {
      return this.asin$mcF$sp(f, n, t, v);
   }

   public Jet asin$mcF$sp(final Field f, final NRoot n, final Trig t, final VectorSpace v) {
      float tmp = f.div$mcF$sp(f.one$mcF$sp(), BoxesRunTime.unboxToFloat(package$.MODULE$.sqrt(BoxesRunTime.boxToFloat(f.minus$mcF$sp(f.one$mcF$sp(), f.times$mcF$sp(this.real(), this.real()))), n)));
      return new Jet$mcF$sp(package$.MODULE$.asin$mFc$sp(this.real(), t), (float[])v.timesl$mcF$sp(tmp, this.infinitesimal()));
   }

   public Jet atan(final Field f, final Trig t, final VectorSpace v) {
      return this.atan$mcF$sp(f, t, v);
   }

   public Jet atan$mcF$sp(final Field f, final Trig t, final VectorSpace v) {
      float tmp = f.div$mcF$sp(f.one$mcF$sp(), f.plus$mcF$sp(f.one$mcF$sp(), f.times$mcF$sp(this.real(), this.real())));
      return new Jet$mcF$sp(package$.MODULE$.atan$mFc$sp(this.real(), t), (float[])v.timesl$mcF$sp(tmp, this.infinitesimal()));
   }

   public Jet atan2(final Jet a, final Field f, final Trig t, final VectorSpace v) {
      return this.atan2$mcF$sp(a, f, t, v);
   }

   public Jet atan2$mcF$sp(final Jet a, final Field f, final Trig t, final VectorSpace v) {
      float tmp = f.div$mcF$sp(f.one$mcF$sp(), f.plus$mcF$sp(f.times$mcF$sp(a.real$mcF$sp(), a.real$mcF$sp()), f.times$mcF$sp(this.real(), this.real())));
      float var10002 = package$.MODULE$.atan2$mFc$sp(this.real(), a.real$mcF$sp(), t);
      float var6 = f.times$mcF$sp(tmp, f.negate$mcF$sp(this.real()));
      float[] var10004 = (float[])v.timesl$mcF$sp(var6, a.infinitesimal$mcF$sp());
      float var7 = f.times$mcF$sp(tmp, a.real$mcF$sp());
      return new Jet$mcF$sp(var10002, (float[])v.plus(var10004, (float[])v.timesl$mcF$sp(var7, this.infinitesimal())));
   }

   public Jet exp(final Trig t, final VectorSpace v) {
      return this.exp$mcF$sp(t, v);
   }

   public Jet exp$mcF$sp(final Trig t, final VectorSpace v) {
      float ea = BoxesRunTime.unboxToFloat(package$.MODULE$.exp(BoxesRunTime.boxToFloat(this.real()), t));
      return new Jet$mcF$sp(ea, (float[])v.timesl$mcF$sp(ea, this.infinitesimal()));
   }

   public Jet sin(final Trig t, final VectorSpace v) {
      return this.sin$mcF$sp(t, v);
   }

   public Jet sin$mcF$sp(final Trig t, final VectorSpace v) {
      float var10002 = package$.MODULE$.sin$mFc$sp(this.real(), t);
      float var3 = package$.MODULE$.cos$mFc$sp(this.real(), t);
      return new Jet$mcF$sp(var10002, (float[])v.timesl$mcF$sp(var3, this.infinitesimal()));
   }

   public Jet sinh(final Trig t, final VectorSpace v) {
      return this.sinh$mcF$sp(t, v);
   }

   public Jet sinh$mcF$sp(final Trig t, final VectorSpace v) {
      float var10002 = package$.MODULE$.sinh$mFc$sp(this.real(), t);
      float var3 = package$.MODULE$.cosh$mFc$sp(this.real(), t);
      return new Jet$mcF$sp(var10002, (float[])v.timesl$mcF$sp(var3, this.infinitesimal()));
   }

   public Jet cos(final Field f, final Trig t, final VectorSpace v) {
      return this.cos$mcF$sp(f, t, v);
   }

   public Jet cos$mcF$sp(final Field f, final Trig t, final VectorSpace v) {
      float var10002 = package$.MODULE$.cos$mFc$sp(this.real(), t);
      float var4 = f.negate$mcF$sp(package$.MODULE$.sin$mFc$sp(this.real(), t));
      return new Jet$mcF$sp(var10002, (float[])v.timesl$mcF$sp(var4, this.infinitesimal()));
   }

   public Jet cosh(final Trig t, final VectorSpace v) {
      return this.cosh$mcF$sp(t, v);
   }

   public Jet cosh$mcF$sp(final Trig t, final VectorSpace v) {
      float var10002 = package$.MODULE$.cosh$mFc$sp(this.real(), t);
      float var3 = package$.MODULE$.sinh$mFc$sp(this.real(), t);
      return new Jet$mcF$sp(var10002, (float[])v.timesl$mcF$sp(var3, this.infinitesimal()));
   }

   public Jet tan(final Field f, final Trig t, final VectorSpace v) {
      return this.tan$mcF$sp(f, t, v);
   }

   public Jet tan$mcF$sp(final Field f, final Trig t, final VectorSpace v) {
      float tan_a = package$.MODULE$.tan$mFc$sp(this.real(), t);
      float tmp = f.plus$mcF$sp(f.one$mcF$sp(), f.times$mcF$sp(tan_a, tan_a));
      return new Jet$mcF$sp(tan_a, (float[])v.timesl$mcF$sp(tmp, this.infinitesimal()));
   }

   public Jet tanh(final Field f, final Trig t, final VectorSpace v) {
      return this.tanh$mcF$sp(f, t, v);
   }

   public Jet tanh$mcF$sp(final Field f, final Trig t, final VectorSpace v) {
      float tanh_a = package$.MODULE$.tanh$mFc$sp(this.real(), t);
      float tmp = f.minus$mcF$sp(f.one$mcF$sp(), f.times$mcF$sp(tanh_a, tanh_a));
      return new Jet$mcF$sp(tanh_a, (float[])v.timesl$mcF$sp(tmp, this.infinitesimal()));
   }

   public float copy$default$1() {
      return this.copy$default$1$mcF$sp();
   }

   public float copy$default$1$mcF$sp() {
      return this.real();
   }

   public float[] copy$default$2() {
      return this.copy$default$2$mcF$sp();
   }

   public float[] copy$default$2$mcF$sp() {
      return this.infinitesimal();
   }

   public boolean specInstance$() {
      return true;
   }

   public Jet$mcF$sp(final float real$mcF$sp, final float[] infinitesimal$mcF$sp) {
      super((Object)null, (Object)null);
      this.real$mcF$sp = real$mcF$sp;
      this.infinitesimal$mcF$sp = infinitesimal$mcF$sp;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
