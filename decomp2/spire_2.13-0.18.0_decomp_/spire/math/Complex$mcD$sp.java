package spire.math;

import algebra.ring.AdditiveMonoid;
import algebra.ring.CommutativeRing;
import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import scala.Tuple2;
import spire.algebra.IsReal;
import spire.algebra.NRoot;
import spire.algebra.Trig;

public final class Complex$mcD$sp extends Complex {
   private static final long serialVersionUID = 0L;
   public final double real$mcD$sp;
   public final double imag$mcD$sp;

   public double real$mcD$sp() {
      return this.real$mcD$sp;
   }

   public double real() {
      return this.real$mcD$sp();
   }

   public double imag$mcD$sp() {
      return this.imag$mcD$sp;
   }

   public double imag() {
      return this.imag$mcD$sp();
   }

   public Complex complexSignum(final Field f, final NRoot n, final Order o, final Signed s) {
      return this.complexSignum$mcD$sp(f, n, o, s);
   }

   public Complex complexSignum$mcD$sp(final Field f, final NRoot n, final Order o, final Signed s) {
      return (Complex)(this.isZero$mcD$sp(s) ? this : this.$div$mcD$sp(this.abs$mcD$sp(f, n, o, s), f));
   }

   public double abs(final Field f, final NRoot n, final Order o, final Signed s) {
      return this.abs$mcD$sp(f, n, o, s);
   }

   public double abs$mcD$sp(final Field f, final NRoot n, final Order o, final Signed s) {
      return package$.MODULE$.hypot$mDc$sp(this.real(), this.imag(), f, n, o, s);
   }

   public double absSquare(final CommutativeRing r) {
      return this.absSquare$mcD$sp(r);
   }

   public double absSquare$mcD$sp(final CommutativeRing r) {
      return r.plus$mcD$sp(r.times$mcD$sp(this.real(), this.real()), r.times$mcD$sp(this.imag(), this.imag()));
   }

   public double arg(final Field f, final Signed s, final Trig t) {
      return this.arg$mcD$sp(f, s, t);
   }

   public double arg$mcD$sp(final Field f, final Signed s, final Trig t) {
      return this.isZero$mcD$sp(s) ? f.zero$mcD$sp() : t.atan2$mcD$sp(this.imag(), this.real());
   }

   public double norm(final Field f, final NRoot n, final Order o, final Signed s) {
      return this.norm$mcD$sp(f, n, o, s);
   }

   public double norm$mcD$sp(final Field f, final NRoot n, final Order o, final Signed s) {
      return package$.MODULE$.hypot$mDc$sp(this.real(), this.imag(), f, n, o, s);
   }

   public Complex conjugate(final CommutativeRing f) {
      return this.conjugate$mcD$sp(f);
   }

   public Complex conjugate$mcD$sp(final CommutativeRing f) {
      return new Complex$mcD$sp(this.real(), f.negate$mcD$sp(this.imag()));
   }

   public Tuple2 asTuple() {
      return this.asTuple$mcD$sp();
   }

   public Tuple2 asTuple$mcD$sp() {
      return new Tuple2.mcDD.sp(this.real(), this.imag());
   }

   public Tuple2 asPolarTuple(final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.asPolarTuple$mcD$sp(f, n, o, s, t);
   }

   public Tuple2 asPolarTuple$mcD$sp(final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return new Tuple2.mcDD.sp(this.abs$mcD$sp(f, n, o, s), this.arg$mcD$sp(f, s, t));
   }

   public boolean isZero(final Signed s) {
      return this.isZero$mcD$sp(s);
   }

   public boolean isZero$mcD$sp(final Signed s) {
      return s.isSignZero$mcD$sp(this.real()) && s.isSignZero$mcD$sp(this.imag());
   }

   public boolean isImaginary(final Signed s) {
      return this.isImaginary$mcD$sp(s);
   }

   public boolean isImaginary$mcD$sp(final Signed s) {
      return s.isSignZero$mcD$sp(this.real());
   }

   public boolean isReal(final Signed s) {
      return this.isReal$mcD$sp(s);
   }

   public boolean isReal$mcD$sp(final Signed s) {
      return s.isSignZero$mcD$sp(this.imag());
   }

   public boolean eqv(final Complex b, final Eq o) {
      return this.eqv$mcD$sp(b, o);
   }

   public boolean eqv$mcD$sp(final Complex b, final Eq o) {
      return o.eqv$mcD$sp(this.real(), b.real$mcD$sp()) && o.eqv$mcD$sp(this.imag(), b.imag$mcD$sp());
   }

   public boolean neqv(final Complex b, final Eq o) {
      return this.neqv$mcD$sp(b, o);
   }

   public boolean neqv$mcD$sp(final Complex b, final Eq o) {
      return o.neqv$mcD$sp(this.real(), b.real$mcD$sp()) || o.neqv$mcD$sp(this.imag(), b.imag$mcD$sp());
   }

   public Complex unary_$minus(final CommutativeRing r) {
      return this.unary_$minus$mcD$sp(r);
   }

   public Complex unary_$minus$mcD$sp(final CommutativeRing r) {
      return new Complex$mcD$sp(r.negate$mcD$sp(this.real()), r.negate$mcD$sp(this.imag()));
   }

   public Complex $plus(final double rhs, final CommutativeRing r) {
      return this.$plus$mcD$sp(rhs, r);
   }

   public Complex $plus$mcD$sp(final double rhs, final CommutativeRing r) {
      return new Complex$mcD$sp(r.plus$mcD$sp(this.real(), rhs), this.imag());
   }

   public Complex $minus(final double rhs, final CommutativeRing r) {
      return this.$minus$mcD$sp(rhs, r);
   }

   public Complex $minus$mcD$sp(final double rhs, final CommutativeRing r) {
      return new Complex$mcD$sp(r.minus$mcD$sp(this.real(), rhs), this.imag());
   }

   public Complex $times(final double rhs, final CommutativeRing r) {
      return this.$times$mcD$sp(rhs, r);
   }

   public Complex $times$mcD$sp(final double rhs, final CommutativeRing r) {
      return new Complex$mcD$sp(r.times$mcD$sp(this.real(), rhs), r.times$mcD$sp(this.imag(), rhs));
   }

   public Complex $div(final double rhs, final Field r) {
      return this.$div$mcD$sp(rhs, r);
   }

   public Complex $div$mcD$sp(final double rhs, final Field r) {
      return new Complex$mcD$sp(r.div$mcD$sp(this.real(), rhs), r.div$mcD$sp(this.imag(), rhs));
   }

   public Complex $times$times(final double e, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.$times$times$mcD$sp(e, f, n, o, s, t);
   }

   public Complex $times$times$mcD$sp(final double e, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow$mcD$sp(e, f, n, o, s, t);
   }

   public Complex pow(final double e, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow$mcD$sp(e, f, n, o, s, t);
   }

   public Complex pow$mcD$sp(final double e, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      Complex var10000;
      if (s.isSignZero$mcD$sp(e)) {
         var10000 = Complex$.MODULE$.one$mDc$sp(f);
      } else if (this.isZero$mcD$sp(s)) {
         if (o.lt$mcD$sp(e, f.zero$mcD$sp())) {
            throw new Exception("raising 0 to negative/complex power");
         }

         var10000 = Complex$.MODULE$.zero$mDc$sp(f);
      } else {
         var10000 = Complex$.MODULE$.polar$mDc$sp(n.fpow$mcD$sp(this.abs$mcD$sp(f, n, o, s), e), f.times$mcD$sp(this.arg$mcD$sp(f, s, t), e), f, t);
      }

      return var10000;
   }

   public Complex $plus(final Complex b, final CommutativeRing r) {
      return this.$plus$mcD$sp(b, r);
   }

   public Complex $plus$mcD$sp(final Complex b, final CommutativeRing r) {
      return new Complex$mcD$sp(r.plus$mcD$sp(this.real(), b.real$mcD$sp()), r.plus$mcD$sp(this.imag(), b.imag$mcD$sp()));
   }

   public Complex $minus(final Complex b, final CommutativeRing r) {
      return this.$minus$mcD$sp(b, r);
   }

   public Complex $minus$mcD$sp(final Complex b, final CommutativeRing r) {
      return new Complex$mcD$sp(r.minus$mcD$sp(this.real(), b.real$mcD$sp()), r.minus$mcD$sp(this.imag(), b.imag$mcD$sp()));
   }

   public Complex $times(final Complex b, final CommutativeRing r) {
      return this.$times$mcD$sp(b, r);
   }

   public Complex $times$mcD$sp(final Complex b, final CommutativeRing r) {
      return new Complex$mcD$sp(r.minus$mcD$sp(r.times$mcD$sp(this.real(), b.real$mcD$sp()), r.times$mcD$sp(this.imag(), b.imag$mcD$sp())), r.plus$mcD$sp(r.times$mcD$sp(this.imag(), b.real$mcD$sp()), r.times$mcD$sp(this.real(), b.imag$mcD$sp())));
   }

   public Complex $div(final Complex b, final Field f, final Order o, final Signed s) {
      return this.$div$mcD$sp(b, f, o, s);
   }

   public Complex $div$mcD$sp(final Complex b, final Field f, final Order o, final Signed s) {
      double abs_breal = s.abs$mcD$sp(b.real$mcD$sp());
      double abs_bimag = s.abs$mcD$sp(b.imag$mcD$sp());
      Complex$mcD$sp var10000;
      if (o.gteqv$mcD$sp(abs_breal, abs_bimag)) {
         if (o.eqv$mcD$sp(abs_breal, f.zero$mcD$sp())) {
            throw new Exception("/ by zero");
         }

         double ratio = f.div$mcD$sp(b.imag$mcD$sp(), b.real$mcD$sp());
         double denom = f.plus$mcD$sp(b.real$mcD$sp(), f.times$mcD$sp(b.imag$mcD$sp(), ratio));
         var10000 = new Complex$mcD$sp(f.div$mcD$sp(f.plus$mcD$sp(this.real(), f.times$mcD$sp(this.imag(), ratio)), denom), f.div$mcD$sp(f.minus$mcD$sp(this.imag(), f.times$mcD$sp(this.real(), ratio)), denom));
      } else {
         if (o.eqv$mcD$sp(abs_bimag, f.zero$mcD$sp())) {
            throw new Exception("/ by zero");
         }

         double ratio = f.div$mcD$sp(b.real$mcD$sp(), b.imag$mcD$sp());
         double denom = f.plus$mcD$sp(f.times$mcD$sp(b.real$mcD$sp(), ratio), b.imag$mcD$sp());
         var10000 = new Complex$mcD$sp(f.div$mcD$sp(f.plus$mcD$sp(f.times$mcD$sp(this.real(), ratio), this.imag()), denom), f.div$mcD$sp(f.minus$mcD$sp(f.times$mcD$sp(this.imag(), ratio), this.real()), denom));
      }

      return var10000;
   }

   public Complex $times$times(final int b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.$times$times$mcD$sp(b, f, n, o, s, t);
   }

   public Complex $times$times$mcD$sp(final int b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow$mcD$sp(b, f, n, o, s, t);
   }

   public Complex nroot(final int k, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.nroot$mcD$sp(k, f, n, o, s, t);
   }

   public Complex nroot$mcD$sp(final int k, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.isZero$mcD$sp(s) ? Complex$.MODULE$.zero$mDc$sp(f) : this.pow$mcD$sp(new Complex$mcD$sp(f.reciprocal$mcD$sp(f.fromInt$mcD$sp(k)), f.zero$mcD$sp()), f, n, o, s, t);
   }

   public Complex pow(final int b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow$mcD$sp(b, f, n, o, s, t);
   }

   public Complex pow$mcD$sp(final int b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.isZero$mcD$sp(s) ? Complex$.MODULE$.zero$mDc$sp(f) : Complex$.MODULE$.polar$mDc$sp(f.pow$mcD$sp(this.abs$mcD$sp(f, n, o, s), b), f.times$mcD$sp(this.arg$mcD$sp(f, s, t), f.fromInt$mcD$sp(b)), f, t);
   }

   public Complex $times$times(final Complex b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.$times$times$mcD$sp(b, f, n, o, s, t);
   }

   public Complex $times$times$mcD$sp(final Complex b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow$mcD$sp(b, f, n, o, s, t);
   }

   public Complex pow(final Complex b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow$mcD$sp(b, f, n, o, s, t);
   }

   public Complex pow$mcD$sp(final Complex b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      Complex var10000;
      if (b.isZero$mcD$sp(s)) {
         var10000 = Complex$.MODULE$.one$mDc$sp(f);
      } else if (this.isZero$mcD$sp(s)) {
         if (o.neqv$mcD$sp(b.imag$mcD$sp(), f.zero$mcD$sp()) || o.lt$mcD$sp(b.real$mcD$sp(), f.zero$mcD$sp())) {
            throw new Exception("raising 0 to negative/complex power");
         }

         var10000 = Complex$.MODULE$.zero$mDc$sp(f);
      } else if (o.neqv$mcD$sp(b.imag$mcD$sp(), f.zero$mcD$sp())) {
         double len = f.div$mcD$sp(n.fpow$mcD$sp(this.abs$mcD$sp(f, n, o, s), b.real$mcD$sp()), t.exp$mcD$sp(f.times$mcD$sp(this.arg$mcD$sp(f, s, t), b.imag$mcD$sp())));
         double phase = f.plus$mcD$sp(f.times$mcD$sp(this.arg$mcD$sp(f, s, t), b.real$mcD$sp()), f.times$mcD$sp(t.log$mcD$sp(this.abs$mcD$sp(f, n, o, s)), b.imag$mcD$sp()));
         var10000 = Complex$.MODULE$.polar$mDc$sp(len, phase, f, t);
      } else {
         var10000 = Complex$.MODULE$.polar$mDc$sp(n.fpow$mcD$sp(this.abs$mcD$sp(f, n, o, s), b.real$mcD$sp()), f.times$mcD$sp(this.arg$mcD$sp(f, s, t), b.real$mcD$sp()), f, t);
      }

      return var10000;
   }

   public Complex log(final Field f, final NRoot n, final Order o, final Trig t, final Signed s) {
      return this.log$mcD$sp(f, n, o, t, s);
   }

   public Complex log$mcD$sp(final Field f, final NRoot n, final Order o, final Trig t, final Signed s) {
      if (this.isZero$mcD$sp(s)) {
         throw new IllegalArgumentException("log(0) undefined");
      } else {
         return new Complex$mcD$sp(t.log$mcD$sp(this.abs$mcD$sp(f, n, o, s)), this.arg$mcD$sp(f, s, t));
      }
   }

   public Complex sqrt(final Field f, final NRoot n0, final Order o, final Signed s) {
      return this.sqrt$mcD$sp(f, n0, o, s);
   }

   public Complex sqrt$mcD$sp(final Field f, final NRoot n0, final Order o, final Signed s) {
      Complex$mcD$sp var10000;
      if (this.isZero$mcD$sp(s)) {
         var10000 = this;
      } else if (s.isSignZero$mcD$sp(this.imag())) {
         var10000 = s.isSignNegative$mcD$sp(this.real()) ? new Complex$mcD$sp(f.zero$mcD$sp(), n0.sqrt$mcD$sp(s.abs$mcD$sp(this.real()))) : new Complex$mcD$sp(n0.sqrt$mcD$sp(s.abs$mcD$sp(this.real())), f.zero$mcD$sp());
      } else {
         double two = f.fromInt$mcD$sp(2);
         double abs = this.abs$mcD$sp(f, n0, o, s);
         double a = n0.sqrt$mcD$sp(f.div$mcD$sp(f.plus$mcD$sp(abs, this.real()), two));
         double b = n0.sqrt$mcD$sp(f.div$mcD$sp(f.minus$mcD$sp(abs, this.real()), two));
         var10000 = s.isSignNegative$mcD$sp(this.imag()) ? new Complex$mcD$sp(a, f.negate$mcD$sp(b)) : new Complex$mcD$sp(a, b);
      }

      return var10000;
   }

   public Complex floor(final IsReal o) {
      return this.floor$mcD$sp(o);
   }

   public Complex floor$mcD$sp(final IsReal o) {
      return new Complex$mcD$sp(o.floor$mcD$sp(this.real()), o.floor$mcD$sp(this.imag()));
   }

   public Complex ceil(final IsReal o) {
      return this.ceil$mcD$sp(o);
   }

   public Complex ceil$mcD$sp(final IsReal o) {
      return new Complex$mcD$sp(o.ceil$mcD$sp(this.real()), o.ceil$mcD$sp(this.imag()));
   }

   public Complex round(final IsReal o) {
      return this.round$mcD$sp(o);
   }

   public Complex round$mcD$sp(final IsReal o) {
      return new Complex$mcD$sp(o.round$mcD$sp(this.real()), o.round$mcD$sp(this.imag()));
   }

   public Complex acos(final Field f, final NRoot n, final Order o, final Trig t, final Signed s0) {
      return this.acos$mcD$sp(f, n, o, t, s0);
   }

   public Complex acos$mcD$sp(final Field f, final NRoot n, final Order o, final Trig t, final Signed s0) {
      Complex z2 = this.$times$mcD$sp(this, f);
      Complex s = (new Complex$mcD$sp(f.minus$mcD$sp(f.one$mcD$sp(), z2.real$mcD$sp()), f.negate$mcD$sp(z2.imag$mcD$sp()))).sqrt$mcD$sp(f, n, o, s0);
      Complex l = (new Complex$mcD$sp(f.plus$mcD$sp(this.real(), s.imag$mcD$sp()), f.plus$mcD$sp(this.imag(), s.real$mcD$sp()))).log$mcD$sp(f, n, o, t, s0);
      return new Complex$mcD$sp(l.imag$mcD$sp(), f.negate$mcD$sp(l.real$mcD$sp()));
   }

   public Complex asin(final Field f, final NRoot n, final Order o, final Trig t, final Signed s0) {
      return this.asin$mcD$sp(f, n, o, t, s0);
   }

   public Complex asin$mcD$sp(final Field f, final NRoot n, final Order o, final Trig t, final Signed s0) {
      Complex z2 = this.$times$mcD$sp(this, f);
      Complex s = (new Complex$mcD$sp(f.minus$mcD$sp(f.one$mcD$sp(), z2.real$mcD$sp()), f.negate$mcD$sp(z2.imag$mcD$sp()))).sqrt$mcD$sp(f, n, o, s0);
      Complex l = (new Complex$mcD$sp(f.plus$mcD$sp(s.real$mcD$sp(), f.negate$mcD$sp(this.imag())), f.plus$mcD$sp(s.imag$mcD$sp(), this.real()))).log$mcD$sp(f, n, o, t, s0);
      return new Complex$mcD$sp(l.imag$mcD$sp(), f.negate$mcD$sp(l.real$mcD$sp()));
   }

   public Complex atan(final Field f, final Order o, final NRoot r, final Signed s, final Trig t) {
      return this.atan$mcD$sp(f, o, r, s, t);
   }

   public Complex atan$mcD$sp(final Field f, final Order o, final NRoot r, final Signed s, final Trig t) {
      Complex n = new Complex$mcD$sp(this.real(), f.plus$mcD$sp(this.imag(), f.one$mcD$sp()));
      Complex d = new Complex$mcD$sp(f.negate$mcD$sp(this.real()), f.minus$mcD$sp(f.one$mcD$sp(), this.imag()));
      Complex l = n.$div$mcD$sp(d, f, o, s).log$mcD$sp(f, r, o, t, s);
      return new Complex$mcD$sp(f.div$mcD$sp(l.imag$mcD$sp(), f.fromInt$mcD$sp(-2)), f.div$mcD$sp(l.real$mcD$sp(), f.fromInt$mcD$sp(2)));
   }

   public Complex exp(final Field f, final Trig t) {
      return this.exp$mcD$sp(f, t);
   }

   public Complex exp$mcD$sp(final Field f, final Trig t) {
      return new Complex$mcD$sp(f.times$mcD$sp(t.exp$mcD$sp(this.real()), t.cos$mcD$sp(this.imag())), f.times$mcD$sp(t.exp$mcD$sp(this.real()), t.sin$mcD$sp(this.imag())));
   }

   public Complex sin(final Field f, final Trig t) {
      return this.sin$mcD$sp(f, t);
   }

   public Complex sin$mcD$sp(final Field f, final Trig t) {
      return new Complex$mcD$sp(f.times$mcD$sp(t.sin$mcD$sp(this.real()), t.cosh$mcD$sp(this.imag())), f.times$mcD$sp(t.cos$mcD$sp(this.real()), t.sinh$mcD$sp(this.imag())));
   }

   public Complex sinh(final Field f, final Trig t) {
      return this.sinh$mcD$sp(f, t);
   }

   public Complex sinh$mcD$sp(final Field f, final Trig t) {
      return new Complex$mcD$sp(f.times$mcD$sp(t.sinh$mcD$sp(this.real()), t.cos$mcD$sp(this.imag())), f.times$mcD$sp(t.cosh$mcD$sp(this.real()), t.sin$mcD$sp(this.imag())));
   }

   public Complex cos(final Field f, final Trig t) {
      return this.cos$mcD$sp(f, t);
   }

   public Complex cos$mcD$sp(final Field f, final Trig t) {
      return new Complex$mcD$sp(f.times$mcD$sp(t.cos$mcD$sp(this.real()), t.cosh$mcD$sp(this.imag())), f.times$mcD$sp(f.negate$mcD$sp(t.sin$mcD$sp(this.real())), t.sinh$mcD$sp(this.imag())));
   }

   public Complex cosh(final Field f, final Trig t) {
      return this.cosh$mcD$sp(f, t);
   }

   public Complex cosh$mcD$sp(final Field f, final Trig t) {
      return new Complex$mcD$sp(f.times$mcD$sp(t.cosh$mcD$sp(this.real()), t.cos$mcD$sp(this.imag())), f.times$mcD$sp(t.sinh$mcD$sp(this.real()), t.sin$mcD$sp(this.imag())));
   }

   public Complex tan(final Field f, final Trig t) {
      return this.tan$mcD$sp(f, t);
   }

   public Complex tan$mcD$sp(final Field f, final Trig t) {
      double r2 = f.plus$mcD$sp(this.real(), this.real());
      double i2 = f.plus$mcD$sp(this.imag(), this.imag());
      double d = f.plus$mcD$sp(t.cos$mcD$sp(r2), t.cosh$mcD$sp(i2));
      return new Complex$mcD$sp(f.div$mcD$sp(t.sin$mcD$sp(r2), d), f.div$mcD$sp(t.sinh$mcD$sp(i2), d));
   }

   public Complex tanh(final Field f, final Trig t) {
      return this.tanh$mcD$sp(f, t);
   }

   public Complex tanh$mcD$sp(final Field f, final Trig t) {
      double r2 = f.plus$mcD$sp(this.real(), this.real());
      double i2 = f.plus$mcD$sp(this.imag(), this.imag());
      double d = f.plus$mcD$sp(t.cos$mcD$sp(r2), t.cosh$mcD$sp(i2));
      return new Complex$mcD$sp(f.div$mcD$sp(t.sinh$mcD$sp(r2), d), f.div$mcD$sp(t.sin$mcD$sp(i2), d));
   }

   public Quaternion toQuaternion(final AdditiveMonoid ev) {
      return this.toQuaternion$mcD$sp(ev);
   }

   public Quaternion toQuaternion$mcD$sp(final AdditiveMonoid ev) {
      return new Quaternion$mcD$sp(this.real(), this.imag(), ev.zero$mcD$sp(), ev.zero$mcD$sp());
   }

   public double copy$default$1() {
      return this.copy$default$1$mcD$sp();
   }

   public double copy$default$1$mcD$sp() {
      return this.real();
   }

   public double copy$default$2() {
      return this.copy$default$2$mcD$sp();
   }

   public double copy$default$2$mcD$sp() {
      return this.imag();
   }

   public boolean specInstance$() {
      return true;
   }

   public Complex$mcD$sp(final double real$mcD$sp, final double imag$mcD$sp) {
      super((Object)null, (Object)null);
      this.real$mcD$sp = real$mcD$sp;
      this.imag$mcD$sp = imag$mcD$sp;
   }
}
