package spire.math;

import algebra.ring.AdditiveMonoid;
import algebra.ring.CommutativeRing;
import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;
import spire.algebra.IsReal;
import spire.algebra.NRoot;
import spire.algebra.Trig;

public final class Complex$mcF$sp extends Complex {
   private static final long serialVersionUID = 0L;
   public final float real$mcF$sp;
   public final float imag$mcF$sp;

   public float real$mcF$sp() {
      return this.real$mcF$sp;
   }

   public float real() {
      return this.real$mcF$sp();
   }

   public float imag$mcF$sp() {
      return this.imag$mcF$sp;
   }

   public float imag() {
      return this.imag$mcF$sp();
   }

   public Complex complexSignum(final Field f, final NRoot n, final Order o, final Signed s) {
      return this.complexSignum$mcF$sp(f, n, o, s);
   }

   public Complex complexSignum$mcF$sp(final Field f, final NRoot n, final Order o, final Signed s) {
      return (Complex)(this.isZero$mcF$sp(s) ? this : this.$div$mcF$sp(this.abs$mcF$sp(f, n, o, s), f));
   }

   public float abs(final Field f, final NRoot n, final Order o, final Signed s) {
      return this.abs$mcF$sp(f, n, o, s);
   }

   public float abs$mcF$sp(final Field f, final NRoot n, final Order o, final Signed s) {
      return package$.MODULE$.hypot$mFc$sp(this.real(), this.imag(), f, n, o, s);
   }

   public float absSquare(final CommutativeRing r) {
      return this.absSquare$mcF$sp(r);
   }

   public float absSquare$mcF$sp(final CommutativeRing r) {
      return r.plus$mcF$sp(r.times$mcF$sp(this.real(), this.real()), r.times$mcF$sp(this.imag(), this.imag()));
   }

   public float arg(final Field f, final Signed s, final Trig t) {
      return this.arg$mcF$sp(f, s, t);
   }

   public float arg$mcF$sp(final Field f, final Signed s, final Trig t) {
      return this.isZero$mcF$sp(s) ? f.zero$mcF$sp() : t.atan2$mcF$sp(this.imag(), this.real());
   }

   public float norm(final Field f, final NRoot n, final Order o, final Signed s) {
      return this.norm$mcF$sp(f, n, o, s);
   }

   public float norm$mcF$sp(final Field f, final NRoot n, final Order o, final Signed s) {
      return package$.MODULE$.hypot$mFc$sp(this.real(), this.imag(), f, n, o, s);
   }

   public Complex conjugate(final CommutativeRing f) {
      return this.conjugate$mcF$sp(f);
   }

   public Complex conjugate$mcF$sp(final CommutativeRing f) {
      return new Complex$mcF$sp(this.real(), f.negate$mcF$sp(this.imag()));
   }

   public Tuple2 asTuple() {
      return this.asTuple$mcF$sp();
   }

   public Tuple2 asTuple$mcF$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.real()), BoxesRunTime.boxToFloat(this.imag()));
   }

   public Tuple2 asPolarTuple(final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.asPolarTuple$mcF$sp(f, n, o, s, t);
   }

   public Tuple2 asPolarTuple$mcF$sp(final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.abs$mcF$sp(f, n, o, s)), BoxesRunTime.boxToFloat(this.arg$mcF$sp(f, s, t)));
   }

   public boolean isZero(final Signed s) {
      return this.isZero$mcF$sp(s);
   }

   public boolean isZero$mcF$sp(final Signed s) {
      return s.isSignZero$mcF$sp(this.real()) && s.isSignZero$mcF$sp(this.imag());
   }

   public boolean isImaginary(final Signed s) {
      return this.isImaginary$mcF$sp(s);
   }

   public boolean isImaginary$mcF$sp(final Signed s) {
      return s.isSignZero$mcF$sp(this.real());
   }

   public boolean isReal(final Signed s) {
      return this.isReal$mcF$sp(s);
   }

   public boolean isReal$mcF$sp(final Signed s) {
      return s.isSignZero$mcF$sp(this.imag());
   }

   public boolean eqv(final Complex b, final Eq o) {
      return this.eqv$mcF$sp(b, o);
   }

   public boolean eqv$mcF$sp(final Complex b, final Eq o) {
      return o.eqv$mcF$sp(this.real(), b.real$mcF$sp()) && o.eqv$mcF$sp(this.imag(), b.imag$mcF$sp());
   }

   public boolean neqv(final Complex b, final Eq o) {
      return this.neqv$mcF$sp(b, o);
   }

   public boolean neqv$mcF$sp(final Complex b, final Eq o) {
      return o.neqv$mcF$sp(this.real(), b.real$mcF$sp()) || o.neqv$mcF$sp(this.imag(), b.imag$mcF$sp());
   }

   public Complex unary_$minus(final CommutativeRing r) {
      return this.unary_$minus$mcF$sp(r);
   }

   public Complex unary_$minus$mcF$sp(final CommutativeRing r) {
      return new Complex$mcF$sp(r.negate$mcF$sp(this.real()), r.negate$mcF$sp(this.imag()));
   }

   public Complex $plus(final float rhs, final CommutativeRing r) {
      return this.$plus$mcF$sp(rhs, r);
   }

   public Complex $plus$mcF$sp(final float rhs, final CommutativeRing r) {
      return new Complex$mcF$sp(r.plus$mcF$sp(this.real(), rhs), this.imag());
   }

   public Complex $minus(final float rhs, final CommutativeRing r) {
      return this.$minus$mcF$sp(rhs, r);
   }

   public Complex $minus$mcF$sp(final float rhs, final CommutativeRing r) {
      return new Complex$mcF$sp(r.minus$mcF$sp(this.real(), rhs), this.imag());
   }

   public Complex $times(final float rhs, final CommutativeRing r) {
      return this.$times$mcF$sp(rhs, r);
   }

   public Complex $times$mcF$sp(final float rhs, final CommutativeRing r) {
      return new Complex$mcF$sp(r.times$mcF$sp(this.real(), rhs), r.times$mcF$sp(this.imag(), rhs));
   }

   public Complex $div(final float rhs, final Field r) {
      return this.$div$mcF$sp(rhs, r);
   }

   public Complex $div$mcF$sp(final float rhs, final Field r) {
      return new Complex$mcF$sp(r.div$mcF$sp(this.real(), rhs), r.div$mcF$sp(this.imag(), rhs));
   }

   public Complex $times$times(final float e, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.$times$times$mcF$sp(e, f, n, o, s, t);
   }

   public Complex $times$times$mcF$sp(final float e, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow$mcF$sp(e, f, n, o, s, t);
   }

   public Complex pow(final float e, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow$mcF$sp(e, f, n, o, s, t);
   }

   public Complex pow$mcF$sp(final float e, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      Complex var10000;
      if (s.isSignZero$mcF$sp(e)) {
         var10000 = Complex$.MODULE$.one$mFc$sp(f);
      } else if (this.isZero$mcF$sp(s)) {
         if (o.lt$mcF$sp(e, f.zero$mcF$sp())) {
            throw new Exception("raising 0 to negative/complex power");
         }

         var10000 = Complex$.MODULE$.zero$mFc$sp(f);
      } else {
         var10000 = Complex$.MODULE$.polar$mFc$sp(n.fpow$mcF$sp(this.abs$mcF$sp(f, n, o, s), e), f.times$mcF$sp(this.arg$mcF$sp(f, s, t), e), f, t);
      }

      return var10000;
   }

   public Complex $plus(final Complex b, final CommutativeRing r) {
      return this.$plus$mcF$sp(b, r);
   }

   public Complex $plus$mcF$sp(final Complex b, final CommutativeRing r) {
      return new Complex$mcF$sp(r.plus$mcF$sp(this.real(), b.real$mcF$sp()), r.plus$mcF$sp(this.imag(), b.imag$mcF$sp()));
   }

   public Complex $minus(final Complex b, final CommutativeRing r) {
      return this.$minus$mcF$sp(b, r);
   }

   public Complex $minus$mcF$sp(final Complex b, final CommutativeRing r) {
      return new Complex$mcF$sp(r.minus$mcF$sp(this.real(), b.real$mcF$sp()), r.minus$mcF$sp(this.imag(), b.imag$mcF$sp()));
   }

   public Complex $times(final Complex b, final CommutativeRing r) {
      return this.$times$mcF$sp(b, r);
   }

   public Complex $times$mcF$sp(final Complex b, final CommutativeRing r) {
      return new Complex$mcF$sp(r.minus$mcF$sp(r.times$mcF$sp(this.real(), b.real$mcF$sp()), r.times$mcF$sp(this.imag(), b.imag$mcF$sp())), r.plus$mcF$sp(r.times$mcF$sp(this.imag(), b.real$mcF$sp()), r.times$mcF$sp(this.real(), b.imag$mcF$sp())));
   }

   public Complex $div(final Complex b, final Field f, final Order o, final Signed s) {
      return this.$div$mcF$sp(b, f, o, s);
   }

   public Complex $div$mcF$sp(final Complex b, final Field f, final Order o, final Signed s) {
      float abs_breal = s.abs$mcF$sp(b.real$mcF$sp());
      float abs_bimag = s.abs$mcF$sp(b.imag$mcF$sp());
      Complex$mcF$sp var10000;
      if (o.gteqv$mcF$sp(abs_breal, abs_bimag)) {
         if (o.eqv$mcF$sp(abs_breal, f.zero$mcF$sp())) {
            throw new Exception("/ by zero");
         }

         float ratio = f.div$mcF$sp(b.imag$mcF$sp(), b.real$mcF$sp());
         float denom = f.plus$mcF$sp(b.real$mcF$sp(), f.times$mcF$sp(b.imag$mcF$sp(), ratio));
         var10000 = new Complex$mcF$sp(f.div$mcF$sp(f.plus$mcF$sp(this.real(), f.times$mcF$sp(this.imag(), ratio)), denom), f.div$mcF$sp(f.minus$mcF$sp(this.imag(), f.times$mcF$sp(this.real(), ratio)), denom));
      } else {
         if (o.eqv$mcF$sp(abs_bimag, f.zero$mcF$sp())) {
            throw new Exception("/ by zero");
         }

         float ratio = f.div$mcF$sp(b.real$mcF$sp(), b.imag$mcF$sp());
         float denom = f.plus$mcF$sp(f.times$mcF$sp(b.real$mcF$sp(), ratio), b.imag$mcF$sp());
         var10000 = new Complex$mcF$sp(f.div$mcF$sp(f.plus$mcF$sp(f.times$mcF$sp(this.real(), ratio), this.imag()), denom), f.div$mcF$sp(f.minus$mcF$sp(f.times$mcF$sp(this.imag(), ratio), this.real()), denom));
      }

      return var10000;
   }

   public Complex $times$times(final int b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.$times$times$mcF$sp(b, f, n, o, s, t);
   }

   public Complex $times$times$mcF$sp(final int b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow$mcF$sp(b, f, n, o, s, t);
   }

   public Complex nroot(final int k, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.nroot$mcF$sp(k, f, n, o, s, t);
   }

   public Complex nroot$mcF$sp(final int k, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.isZero$mcF$sp(s) ? Complex$.MODULE$.zero$mFc$sp(f) : this.pow$mcF$sp(new Complex$mcF$sp(f.reciprocal$mcF$sp(f.fromInt$mcF$sp(k)), f.zero$mcF$sp()), f, n, o, s, t);
   }

   public Complex pow(final int b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow$mcF$sp(b, f, n, o, s, t);
   }

   public Complex pow$mcF$sp(final int b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.isZero$mcF$sp(s) ? Complex$.MODULE$.zero$mFc$sp(f) : Complex$.MODULE$.polar$mFc$sp(f.pow$mcF$sp(this.abs$mcF$sp(f, n, o, s), b), f.times$mcF$sp(this.arg$mcF$sp(f, s, t), f.fromInt$mcF$sp(b)), f, t);
   }

   public Complex $times$times(final Complex b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.$times$times$mcF$sp(b, f, n, o, s, t);
   }

   public Complex $times$times$mcF$sp(final Complex b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow$mcF$sp(b, f, n, o, s, t);
   }

   public Complex pow(final Complex b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow$mcF$sp(b, f, n, o, s, t);
   }

   public Complex pow$mcF$sp(final Complex b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      Complex var10000;
      if (b.isZero$mcF$sp(s)) {
         var10000 = Complex$.MODULE$.one$mFc$sp(f);
      } else if (this.isZero$mcF$sp(s)) {
         if (o.neqv$mcF$sp(b.imag$mcF$sp(), f.zero$mcF$sp()) || o.lt$mcF$sp(b.real$mcF$sp(), f.zero$mcF$sp())) {
            throw new Exception("raising 0 to negative/complex power");
         }

         var10000 = Complex$.MODULE$.zero$mFc$sp(f);
      } else if (o.neqv$mcF$sp(b.imag$mcF$sp(), f.zero$mcF$sp())) {
         float len = f.div$mcF$sp(n.fpow$mcF$sp(this.abs$mcF$sp(f, n, o, s), b.real$mcF$sp()), t.exp$mcF$sp(f.times$mcF$sp(this.arg$mcF$sp(f, s, t), b.imag$mcF$sp())));
         float phase = f.plus$mcF$sp(f.times$mcF$sp(this.arg$mcF$sp(f, s, t), b.real$mcF$sp()), f.times$mcF$sp(t.log$mcF$sp(this.abs$mcF$sp(f, n, o, s)), b.imag$mcF$sp()));
         var10000 = Complex$.MODULE$.polar$mFc$sp(len, phase, f, t);
      } else {
         var10000 = Complex$.MODULE$.polar$mFc$sp(n.fpow$mcF$sp(this.abs$mcF$sp(f, n, o, s), b.real$mcF$sp()), f.times$mcF$sp(this.arg$mcF$sp(f, s, t), b.real$mcF$sp()), f, t);
      }

      return var10000;
   }

   public Complex log(final Field f, final NRoot n, final Order o, final Trig t, final Signed s) {
      return this.log$mcF$sp(f, n, o, t, s);
   }

   public Complex log$mcF$sp(final Field f, final NRoot n, final Order o, final Trig t, final Signed s) {
      if (this.isZero$mcF$sp(s)) {
         throw new IllegalArgumentException("log(0) undefined");
      } else {
         return new Complex$mcF$sp(t.log$mcF$sp(this.abs$mcF$sp(f, n, o, s)), this.arg$mcF$sp(f, s, t));
      }
   }

   public Complex sqrt(final Field f, final NRoot n0, final Order o, final Signed s) {
      return this.sqrt$mcF$sp(f, n0, o, s);
   }

   public Complex sqrt$mcF$sp(final Field f, final NRoot n0, final Order o, final Signed s) {
      Complex$mcF$sp var10000;
      if (this.isZero$mcF$sp(s)) {
         var10000 = this;
      } else if (s.isSignZero$mcF$sp(this.imag())) {
         var10000 = s.isSignNegative$mcF$sp(this.real()) ? new Complex$mcF$sp(f.zero$mcF$sp(), n0.sqrt$mcF$sp(s.abs$mcF$sp(this.real()))) : new Complex$mcF$sp(n0.sqrt$mcF$sp(s.abs$mcF$sp(this.real())), f.zero$mcF$sp());
      } else {
         float two = f.fromInt$mcF$sp(2);
         float abs = this.abs$mcF$sp(f, n0, o, s);
         float a = n0.sqrt$mcF$sp(f.div$mcF$sp(f.plus$mcF$sp(abs, this.real()), two));
         float b = n0.sqrt$mcF$sp(f.div$mcF$sp(f.minus$mcF$sp(abs, this.real()), two));
         var10000 = s.isSignNegative$mcF$sp(this.imag()) ? new Complex$mcF$sp(a, f.negate$mcF$sp(b)) : new Complex$mcF$sp(a, b);
      }

      return var10000;
   }

   public Complex floor(final IsReal o) {
      return this.floor$mcF$sp(o);
   }

   public Complex floor$mcF$sp(final IsReal o) {
      return new Complex$mcF$sp(o.floor$mcF$sp(this.real()), o.floor$mcF$sp(this.imag()));
   }

   public Complex ceil(final IsReal o) {
      return this.ceil$mcF$sp(o);
   }

   public Complex ceil$mcF$sp(final IsReal o) {
      return new Complex$mcF$sp(o.ceil$mcF$sp(this.real()), o.ceil$mcF$sp(this.imag()));
   }

   public Complex round(final IsReal o) {
      return this.round$mcF$sp(o);
   }

   public Complex round$mcF$sp(final IsReal o) {
      return new Complex$mcF$sp(o.round$mcF$sp(this.real()), o.round$mcF$sp(this.imag()));
   }

   public Complex acos(final Field f, final NRoot n, final Order o, final Trig t, final Signed s0) {
      return this.acos$mcF$sp(f, n, o, t, s0);
   }

   public Complex acos$mcF$sp(final Field f, final NRoot n, final Order o, final Trig t, final Signed s0) {
      Complex z2 = this.$times$mcF$sp(this, f);
      Complex s = (new Complex$mcF$sp(f.minus$mcF$sp(f.one$mcF$sp(), z2.real$mcF$sp()), f.negate$mcF$sp(z2.imag$mcF$sp()))).sqrt$mcF$sp(f, n, o, s0);
      Complex l = (new Complex$mcF$sp(f.plus$mcF$sp(this.real(), s.imag$mcF$sp()), f.plus$mcF$sp(this.imag(), s.real$mcF$sp()))).log$mcF$sp(f, n, o, t, s0);
      return new Complex$mcF$sp(l.imag$mcF$sp(), f.negate$mcF$sp(l.real$mcF$sp()));
   }

   public Complex asin(final Field f, final NRoot n, final Order o, final Trig t, final Signed s0) {
      return this.asin$mcF$sp(f, n, o, t, s0);
   }

   public Complex asin$mcF$sp(final Field f, final NRoot n, final Order o, final Trig t, final Signed s0) {
      Complex z2 = this.$times$mcF$sp(this, f);
      Complex s = (new Complex$mcF$sp(f.minus$mcF$sp(f.one$mcF$sp(), z2.real$mcF$sp()), f.negate$mcF$sp(z2.imag$mcF$sp()))).sqrt$mcF$sp(f, n, o, s0);
      Complex l = (new Complex$mcF$sp(f.plus$mcF$sp(s.real$mcF$sp(), f.negate$mcF$sp(this.imag())), f.plus$mcF$sp(s.imag$mcF$sp(), this.real()))).log$mcF$sp(f, n, o, t, s0);
      return new Complex$mcF$sp(l.imag$mcF$sp(), f.negate$mcF$sp(l.real$mcF$sp()));
   }

   public Complex atan(final Field f, final Order o, final NRoot r, final Signed s, final Trig t) {
      return this.atan$mcF$sp(f, o, r, s, t);
   }

   public Complex atan$mcF$sp(final Field f, final Order o, final NRoot r, final Signed s, final Trig t) {
      Complex n = new Complex$mcF$sp(this.real(), f.plus$mcF$sp(this.imag(), f.one$mcF$sp()));
      Complex d = new Complex$mcF$sp(f.negate$mcF$sp(this.real()), f.minus$mcF$sp(f.one$mcF$sp(), this.imag()));
      Complex l = n.$div$mcF$sp(d, f, o, s).log$mcF$sp(f, r, o, t, s);
      return new Complex$mcF$sp(f.div$mcF$sp(l.imag$mcF$sp(), f.fromInt$mcF$sp(-2)), f.div$mcF$sp(l.real$mcF$sp(), f.fromInt$mcF$sp(2)));
   }

   public Complex exp(final Field f, final Trig t) {
      return this.exp$mcF$sp(f, t);
   }

   public Complex exp$mcF$sp(final Field f, final Trig t) {
      return new Complex$mcF$sp(f.times$mcF$sp(t.exp$mcF$sp(this.real()), t.cos$mcF$sp(this.imag())), f.times$mcF$sp(t.exp$mcF$sp(this.real()), t.sin$mcF$sp(this.imag())));
   }

   public Complex sin(final Field f, final Trig t) {
      return this.sin$mcF$sp(f, t);
   }

   public Complex sin$mcF$sp(final Field f, final Trig t) {
      return new Complex$mcF$sp(f.times$mcF$sp(t.sin$mcF$sp(this.real()), t.cosh$mcF$sp(this.imag())), f.times$mcF$sp(t.cos$mcF$sp(this.real()), t.sinh$mcF$sp(this.imag())));
   }

   public Complex sinh(final Field f, final Trig t) {
      return this.sinh$mcF$sp(f, t);
   }

   public Complex sinh$mcF$sp(final Field f, final Trig t) {
      return new Complex$mcF$sp(f.times$mcF$sp(t.sinh$mcF$sp(this.real()), t.cos$mcF$sp(this.imag())), f.times$mcF$sp(t.cosh$mcF$sp(this.real()), t.sin$mcF$sp(this.imag())));
   }

   public Complex cos(final Field f, final Trig t) {
      return this.cos$mcF$sp(f, t);
   }

   public Complex cos$mcF$sp(final Field f, final Trig t) {
      return new Complex$mcF$sp(f.times$mcF$sp(t.cos$mcF$sp(this.real()), t.cosh$mcF$sp(this.imag())), f.times$mcF$sp(f.negate$mcF$sp(t.sin$mcF$sp(this.real())), t.sinh$mcF$sp(this.imag())));
   }

   public Complex cosh(final Field f, final Trig t) {
      return this.cosh$mcF$sp(f, t);
   }

   public Complex cosh$mcF$sp(final Field f, final Trig t) {
      return new Complex$mcF$sp(f.times$mcF$sp(t.cosh$mcF$sp(this.real()), t.cos$mcF$sp(this.imag())), f.times$mcF$sp(t.sinh$mcF$sp(this.real()), t.sin$mcF$sp(this.imag())));
   }

   public Complex tan(final Field f, final Trig t) {
      return this.tan$mcF$sp(f, t);
   }

   public Complex tan$mcF$sp(final Field f, final Trig t) {
      float r2 = f.plus$mcF$sp(this.real(), this.real());
      float i2 = f.plus$mcF$sp(this.imag(), this.imag());
      float d = f.plus$mcF$sp(t.cos$mcF$sp(r2), t.cosh$mcF$sp(i2));
      return new Complex$mcF$sp(f.div$mcF$sp(t.sin$mcF$sp(r2), d), f.div$mcF$sp(t.sinh$mcF$sp(i2), d));
   }

   public Complex tanh(final Field f, final Trig t) {
      return this.tanh$mcF$sp(f, t);
   }

   public Complex tanh$mcF$sp(final Field f, final Trig t) {
      float r2 = f.plus$mcF$sp(this.real(), this.real());
      float i2 = f.plus$mcF$sp(this.imag(), this.imag());
      float d = f.plus$mcF$sp(t.cos$mcF$sp(r2), t.cosh$mcF$sp(i2));
      return new Complex$mcF$sp(f.div$mcF$sp(t.sinh$mcF$sp(r2), d), f.div$mcF$sp(t.sin$mcF$sp(i2), d));
   }

   public Quaternion toQuaternion(final AdditiveMonoid ev) {
      return this.toQuaternion$mcF$sp(ev);
   }

   public Quaternion toQuaternion$mcF$sp(final AdditiveMonoid ev) {
      return new Quaternion$mcF$sp(this.real(), this.imag(), ev.zero$mcF$sp(), ev.zero$mcF$sp());
   }

   public float copy$default$1() {
      return this.copy$default$1$mcF$sp();
   }

   public float copy$default$1$mcF$sp() {
      return this.real();
   }

   public float copy$default$2() {
      return this.copy$default$2$mcF$sp();
   }

   public float copy$default$2$mcF$sp() {
      return this.imag();
   }

   public boolean specInstance$() {
      return true;
   }

   public Complex$mcF$sp(final float real$mcF$sp, final float imag$mcF$sp) {
      super((Object)null, (Object)null);
      this.real$mcF$sp = real$mcF$sp;
      this.imag$mcF$sp = imag$mcF$sp;
   }
}
