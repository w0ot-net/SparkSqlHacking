package spire.math;

import algebra.ring.CommutativeRing;
import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import spire.algebra.NRoot;
import spire.algebra.Trig;

public final class Quaternion$mcD$sp extends Quaternion {
   public final double r$mcD$sp;
   public final double i$mcD$sp;
   public final double j$mcD$sp;
   public final double k$mcD$sp;

   public double r$mcD$sp() {
      return this.r$mcD$sp;
   }

   public double r() {
      return this.r$mcD$sp();
   }

   public double i$mcD$sp() {
      return this.i$mcD$sp;
   }

   public double i() {
      return this.i$mcD$sp();
   }

   public double j$mcD$sp() {
      return this.j$mcD$sp;
   }

   public double j() {
      return this.j$mcD$sp();
   }

   public double k$mcD$sp() {
      return this.k$mcD$sp;
   }

   public double k() {
      return this.k$mcD$sp();
   }

   public boolean isZero(final Signed s) {
      return this.isZero$mcD$sp(s);
   }

   public boolean isZero$mcD$sp(final Signed s) {
      return s.isSignZero$mcD$sp(this.r()) && s.isSignZero$mcD$sp(this.i()) && s.isSignZero$mcD$sp(this.j()) && s.isSignZero$mcD$sp(this.k());
   }

   public boolean isReal(final Signed s) {
      return this.isReal$mcD$sp(s);
   }

   public boolean isReal$mcD$sp(final Signed s) {
      return s.isSignZero$mcD$sp(this.i()) && s.isSignZero$mcD$sp(this.j()) && s.isSignZero$mcD$sp(this.k());
   }

   public boolean isPure(final Signed s) {
      return this.isPure$mcD$sp(s);
   }

   public boolean isPure$mcD$sp(final Signed s) {
      return s.isSignZero$mcD$sp(this.r());
   }

   public Quaternion real(final CommutativeRing s) {
      return this.real$mcD$sp(s);
   }

   public Quaternion real$mcD$sp(final CommutativeRing s) {
      return Quaternion$.MODULE$.apply$mDc$sp(this.r(), s);
   }

   public Quaternion pure(final CommutativeRing s) {
      return this.pure$mcD$sp(s);
   }

   public Quaternion pure$mcD$sp(final CommutativeRing s) {
      return new Quaternion$mcD$sp(s.zero$mcD$sp(), this.i(), this.j(), this.k());
   }

   public double abs(final Field f, final NRoot n) {
      return this.abs$mcD$sp(f, n);
   }

   public double abs$mcD$sp(final Field f, final NRoot n) {
      return n.sqrt$mcD$sp(f.plus$mcD$sp(f.plus$mcD$sp(f.plus$mcD$sp(f.pow$mcD$sp(this.r(), 2), f.pow$mcD$sp(this.i(), 2)), f.pow$mcD$sp(this.j(), 2)), f.pow$mcD$sp(this.k(), 2)));
   }

   public double pureAbs(final Field f, final NRoot n) {
      return this.pureAbs$mcD$sp(f, n);
   }

   public double pureAbs$mcD$sp(final Field f, final NRoot n) {
      return n.sqrt$mcD$sp(f.plus$mcD$sp(f.plus$mcD$sp(f.pow$mcD$sp(this.i(), 2), f.pow$mcD$sp(this.j(), 2)), f.pow$mcD$sp(this.k(), 2)));
   }

   public boolean eqv(final Quaternion rhs, final Eq o) {
      return this.eqv$mcD$sp(rhs, o);
   }

   public boolean eqv$mcD$sp(final Quaternion rhs, final Eq o) {
      return o.eqv$mcD$sp(this.r(), rhs.r$mcD$sp()) && o.eqv$mcD$sp(this.i(), rhs.i$mcD$sp()) && o.eqv$mcD$sp(this.j(), rhs.j$mcD$sp()) && o.eqv$mcD$sp(this.k(), rhs.k$mcD$sp());
   }

   public boolean neqv(final Quaternion rhs, final Eq o) {
      return this.neqv$mcD$sp(rhs, o);
   }

   public boolean neqv$mcD$sp(final Quaternion rhs, final Eq o) {
      return o.neqv$mcD$sp(this.r(), rhs.r$mcD$sp()) && o.neqv$mcD$sp(this.i(), rhs.i$mcD$sp()) && o.neqv$mcD$sp(this.j(), rhs.j$mcD$sp()) && o.neqv$mcD$sp(this.k(), rhs.k$mcD$sp());
   }

   public Complex toComplex() {
      return this.toComplex$mcD$sp();
   }

   public Complex toComplex$mcD$sp() {
      return new Complex$mcD$sp(this.r(), this.i());
   }

   public int signum(final Signed s) {
      return this.signum$mcD$sp(s);
   }

   public int signum$mcD$sp(final Signed s) {
      int var2 = s.signum$mcD$sp(this.r());
      int var10000;
      switch (var2) {
         case 0:
            int var3 = s.signum$mcD$sp(this.i());
            switch (var3) {
               case 0:
                  int var4 = s.signum$mcD$sp(this.j());
                  switch (var4) {
                     case 0:
                        var10000 = s.signum$mcD$sp(this.k());
                        return var10000;
                     default:
                        var10000 = var4;
                        return var10000;
                  }
               default:
                  var10000 = var3;
                  return var10000;
            }
         default:
            var10000 = var2;
            return var10000;
      }
   }

   public Quaternion quaternionSignum(final Field f, final NRoot n, final Signed s) {
      return this.quaternionSignum$mcD$sp(f, n, s);
   }

   public Quaternion quaternionSignum$mcD$sp(final Field f, final NRoot n, final Signed s) {
      return (Quaternion)(this.isZero$mcD$sp(s) ? this : this.$div$mcD$sp(this.abs$mcD$sp(f, n), f));
   }

   public Quaternion pureSignum(final Field f, final NRoot n, final Signed s) {
      return this.pureSignum$mcD$sp(f, n, s);
   }

   public Quaternion pureSignum$mcD$sp(final Field f, final NRoot n, final Signed s) {
      return this.isReal$mcD$sp(s) ? Quaternion$.MODULE$.zero$mDc$sp(f) : this.pure$mcD$sp(f).$div$mcD$sp(this.pureAbs$mcD$sp(f, n), f);
   }

   public Quaternion unary_$minus(final CommutativeRing s) {
      return this.unary_$minus$mcD$sp(s);
   }

   public Quaternion unary_$minus$mcD$sp(final CommutativeRing s) {
      return new Quaternion$mcD$sp(s.negate$mcD$sp(this.r()), s.negate$mcD$sp(this.i()), s.negate$mcD$sp(this.j()), s.negate$mcD$sp(this.k()));
   }

   public Quaternion conjugate(final CommutativeRing s) {
      return this.conjugate$mcD$sp(s);
   }

   public Quaternion conjugate$mcD$sp(final CommutativeRing s) {
      return new Quaternion$mcD$sp(this.r(), s.negate$mcD$sp(this.i()), s.negate$mcD$sp(this.j()), s.negate$mcD$sp(this.k()));
   }

   public Quaternion reciprocal(final Field f) {
      return this.reciprocal$mcD$sp(f);
   }

   public Quaternion reciprocal$mcD$sp(final Field f) {
      return this.conjugate$mcD$sp(f).$div$mcD$sp(f.plus$mcD$sp(f.plus$mcD$sp(f.plus$mcD$sp(f.pow$mcD$sp(this.r(), 2), f.pow$mcD$sp(this.i(), 2)), f.pow$mcD$sp(this.j(), 2)), f.pow$mcD$sp(this.k(), 2)), f);
   }

   public Quaternion sqrt(final Field f, final NRoot nr, final Signed s) {
      return this.sqrt$mcD$sp(f, nr, s);
   }

   public Quaternion sqrt$mcD$sp(final Field f, final NRoot nr, final Signed s) {
      Object var10000;
      if (!this.isReal$mcD$sp(s)) {
         double n = nr.sqrt$mcD$sp(f.plus$mcD$sp(this.r(), this.abs$mcD$sp(f, nr)));
         var10000 = (new Quaternion$mcD$sp(n, f.div$mcD$sp(this.i(), n), f.div$mcD$sp(this.j(), n), f.div$mcD$sp(this.k(), n))).$div$mcD$sp(nr.sqrt$mcD$sp(f.fromInt$mcD$sp(2)), f);
      } else {
         var10000 = s.signum$mcD$sp(this.r()) >= 0 ? Quaternion$.MODULE$.apply$mDc$sp(nr.sqrt$mcD$sp(this.r()), f) : new Quaternion$mcD$sp(f.zero$mcD$sp(), nr.sqrt$mcD$sp(s.abs$mcD$sp(this.r())), f.zero$mcD$sp(), f.zero$mcD$sp());
      }

      return (Quaternion)var10000;
   }

   public Quaternion nroot(final int m, final Field f, final NRoot nr, final Order or, final Signed si, final Trig tr) {
      return this.nroot$mcD$sp(m, f, nr, or, si, tr);
   }

   public Quaternion nroot$mcD$sp(final int m, final Field f, final NRoot nr, final Order or, final Signed si, final Trig tr) {
      if (m <= 0) {
         throw new IllegalArgumentException((new StringBuilder(14)).append("illegal root: ").append(m).toString());
      } else {
         Object var10000;
         if (m == 1) {
            var10000 = this;
         } else if (!this.isReal$mcD$sp(si)) {
            double s = this.pureAbs$mcD$sp(f, nr);
            double n = this.abs$mcD$sp(f, nr);
            double t = package$.MODULE$.acos$mDc$sp(f.div$mcD$sp(this.r(), n), tr);
            Quaternion v = new Quaternion$mcD$sp(f.zero$mcD$sp(), f.div$mcD$sp(this.i(), s), f.div$mcD$sp(this.j(), s), f.div$mcD$sp(this.k(), s));
            Quaternion e = si.signum$mcD$sp(package$.MODULE$.sin$mDc$sp(t, tr)) >= 0 ? v : v.unary_$minus$mcD$sp(f);
            double tm = f.div$mcD$sp(t, f.fromInt$mcD$sp(m));
            var10000 = e.$times$mcD$sp(package$.MODULE$.sin$mDc$sp(tm, tr), f).$plus$mcD$sp(package$.MODULE$.cos$mDc$sp(tm, tr), f).$times$mcD$sp(nr.nroot$mcD$sp(n, m), f);
         } else {
            var10000 = si.signum$mcD$sp(this.r()) >= 0 ? Quaternion$.MODULE$.apply$mDc$sp(nr.nroot$mcD$sp(this.r(), m), f) : Quaternion$.MODULE$.apply$mDc$sp(Complex$.MODULE$.apply$mDc$sp(this.r(), f).nroot$mcD$sp(m, f, nr, or, si, tr), f);
         }

         return (Quaternion)var10000;
      }
   }

   public Quaternion unit(final Field f, final NRoot n) {
      return this.unit$mcD$sp(f, n);
   }

   public Quaternion unit$mcD$sp(final Field f, final NRoot n) {
      return (new Quaternion$mcD$sp(f.pow$mcD$sp(this.r(), 2), f.pow$mcD$sp(this.i(), 2), f.pow$mcD$sp(this.j(), 2), f.pow$mcD$sp(this.k(), 2))).$div$mcD$sp(this.abs$mcD$sp(f, n), f);
   }

   public Quaternion $plus(final double rhs, final CommutativeRing s) {
      return this.$plus$mcD$sp(rhs, s);
   }

   public Quaternion $plus$mcD$sp(final double rhs, final CommutativeRing s) {
      return new Quaternion$mcD$sp(s.plus$mcD$sp(this.r(), rhs), this.i(), this.j(), this.k());
   }

   public Quaternion $plus(final Complex rhs, final CommutativeRing s) {
      return this.$plus$mcD$sp(rhs, s);
   }

   public Quaternion $plus$mcD$sp(final Complex rhs, final CommutativeRing s) {
      return new Quaternion$mcD$sp(s.plus$mcD$sp(this.r(), rhs.real$mcD$sp()), s.plus$mcD$sp(this.i(), rhs.imag$mcD$sp()), this.j(), this.k());
   }

   public Quaternion $plus(final Quaternion rhs, final CommutativeRing s) {
      return this.$plus$mcD$sp(rhs, s);
   }

   public Quaternion $plus$mcD$sp(final Quaternion rhs, final CommutativeRing s) {
      return new Quaternion$mcD$sp(s.plus$mcD$sp(this.r(), rhs.r$mcD$sp()), s.plus$mcD$sp(this.i(), rhs.i$mcD$sp()), s.plus$mcD$sp(this.j(), rhs.j$mcD$sp()), s.plus$mcD$sp(this.k(), rhs.k$mcD$sp()));
   }

   public Quaternion $minus(final double rhs, final CommutativeRing s) {
      return this.$minus$mcD$sp(rhs, s);
   }

   public Quaternion $minus$mcD$sp(final double rhs, final CommutativeRing s) {
      return new Quaternion$mcD$sp(s.minus$mcD$sp(this.r(), rhs), this.i(), this.j(), this.k());
   }

   public Quaternion $minus(final Complex rhs, final CommutativeRing s) {
      return this.$minus$mcD$sp(rhs, s);
   }

   public Quaternion $minus$mcD$sp(final Complex rhs, final CommutativeRing s) {
      return new Quaternion$mcD$sp(s.minus$mcD$sp(this.r(), rhs.real$mcD$sp()), s.minus$mcD$sp(this.i(), rhs.imag$mcD$sp()), this.j(), this.k());
   }

   public Quaternion $minus(final Quaternion rhs, final CommutativeRing s) {
      return this.$minus$mcD$sp(rhs, s);
   }

   public Quaternion $minus$mcD$sp(final Quaternion rhs, final CommutativeRing s) {
      return new Quaternion$mcD$sp(s.minus$mcD$sp(this.r(), rhs.r$mcD$sp()), s.minus$mcD$sp(this.i(), rhs.i$mcD$sp()), s.minus$mcD$sp(this.j(), rhs.j$mcD$sp()), s.minus$mcD$sp(this.k(), rhs.k$mcD$sp()));
   }

   public Quaternion $times(final double rhs, final CommutativeRing s) {
      return this.$times$mcD$sp(rhs, s);
   }

   public Quaternion $times$mcD$sp(final double rhs, final CommutativeRing s) {
      return new Quaternion$mcD$sp(s.times$mcD$sp(this.r(), rhs), s.times$mcD$sp(this.i(), rhs), s.times$mcD$sp(this.j(), rhs), s.times$mcD$sp(this.k(), rhs));
   }

   public Quaternion $times(final Complex rhs, final CommutativeRing s) {
      return this.$times$mcD$sp(rhs, s);
   }

   public Quaternion $times$mcD$sp(final Complex rhs, final CommutativeRing s) {
      return new Quaternion$mcD$sp(s.minus$mcD$sp(s.times$mcD$sp(this.r(), rhs.real$mcD$sp()), s.times$mcD$sp(this.i(), rhs.imag$mcD$sp())), s.plus$mcD$sp(s.times$mcD$sp(this.r(), rhs.imag$mcD$sp()), s.times$mcD$sp(this.i(), rhs.real$mcD$sp())), s.plus$mcD$sp(s.times$mcD$sp(this.j(), rhs.real$mcD$sp()), s.times$mcD$sp(this.k(), rhs.imag$mcD$sp())), s.plus$mcD$sp(s.times$mcD$sp(this.j(), rhs.imag$mcD$sp()), s.times$mcD$sp(this.k(), rhs.real$mcD$sp())));
   }

   public Quaternion $times(final Quaternion rhs, final CommutativeRing s) {
      return this.$times$mcD$sp(rhs, s);
   }

   public Quaternion $times$mcD$sp(final Quaternion rhs, final CommutativeRing s) {
      return new Quaternion$mcD$sp(s.minus$mcD$sp(s.minus$mcD$sp(s.minus$mcD$sp(s.times$mcD$sp(this.r(), rhs.r$mcD$sp()), s.times$mcD$sp(this.i(), rhs.i$mcD$sp())), s.times$mcD$sp(this.j(), rhs.j$mcD$sp())), s.times$mcD$sp(this.k(), rhs.k$mcD$sp())), s.minus$mcD$sp(s.plus$mcD$sp(s.plus$mcD$sp(s.times$mcD$sp(this.r(), rhs.i$mcD$sp()), s.times$mcD$sp(this.i(), rhs.r$mcD$sp())), s.times$mcD$sp(this.j(), rhs.k$mcD$sp())), s.times$mcD$sp(this.k(), rhs.j$mcD$sp())), s.plus$mcD$sp(s.plus$mcD$sp(s.minus$mcD$sp(s.times$mcD$sp(this.r(), rhs.j$mcD$sp()), s.times$mcD$sp(this.i(), rhs.k$mcD$sp())), s.times$mcD$sp(this.j(), rhs.r$mcD$sp())), s.times$mcD$sp(this.k(), rhs.i$mcD$sp())), s.plus$mcD$sp(s.minus$mcD$sp(s.plus$mcD$sp(s.times$mcD$sp(this.r(), rhs.k$mcD$sp()), s.times$mcD$sp(this.i(), rhs.j$mcD$sp())), s.times$mcD$sp(this.j(), rhs.i$mcD$sp())), s.times$mcD$sp(this.k(), rhs.r$mcD$sp())));
   }

   public Quaternion $div(final double rhs, final Field f) {
      return this.$div$mcD$sp(rhs, f);
   }

   public Quaternion $div$mcD$sp(final double rhs, final Field f) {
      return new Quaternion$mcD$sp(f.div$mcD$sp(this.r(), rhs), f.div$mcD$sp(this.i(), rhs), f.div$mcD$sp(this.j(), rhs), f.div$mcD$sp(this.k(), rhs));
   }

   public Quaternion $div(final Complex rhs, final Field f) {
      return this.$div$mcD$sp(rhs, f);
   }

   public Quaternion $div$mcD$sp(final Complex rhs, final Field f) {
      return this.$times$mcD$sp((Quaternion)Quaternion$.MODULE$.apply$mDc$sp(rhs, f).reciprocal$mcD$sp(f), f);
   }

   public Quaternion $div(final Quaternion rhs, final Field f) {
      return this.$div$mcD$sp(rhs, f);
   }

   public Quaternion $div$mcD$sp(final Quaternion rhs, final Field f) {
      return this.$times$mcD$sp((Quaternion)rhs.reciprocal$mcD$sp(f), f);
   }

   public Quaternion pow(final int k, final CommutativeRing s) {
      return this.pow$mcD$sp(k, s);
   }

   public Quaternion pow$mcD$sp(final int k, final CommutativeRing s) {
      if (k >= 0) {
         return this.loop$2(Quaternion$.MODULE$.one$mDc$sp(s), this, k, s);
      } else {
         throw new IllegalArgumentException((new StringBuilder(18)).append("illegal exponent: ").append(k).toString());
      }
   }

   public Quaternion $times$times(final int k, final CommutativeRing s) {
      return this.$times$times$mcD$sp(k, s);
   }

   public Quaternion $times$times$mcD$sp(final int k, final CommutativeRing s) {
      return this.pow$mcD$sp(k, s);
   }

   public Quaternion fpow(final double k0, final Field f, final NRoot nr, final Order or, final Signed si, final Trig tr) {
      return this.fpow$mcD$sp(k0, f, nr, or, si, tr);
   }

   public Quaternion fpow$mcD$sp(final double k0, final Field f, final NRoot nr, final Order or, final Signed si, final Trig tr) {
      Object var10000;
      if (si.signum$mcD$sp(k0) < 0) {
         var10000 = Quaternion$.MODULE$.zero$mDc$sp(f);
      } else if (k0 == f.zero$mcD$sp()) {
         var10000 = Quaternion$.MODULE$.one$mDc$sp(f);
      } else if (k0 == f.one$mcD$sp()) {
         var10000 = this;
      } else if (!this.isReal$mcD$sp(si)) {
         double s = nr.sqrt$mcD$sp(f.plus$mcD$sp(f.plus$mcD$sp(f.pow$mcD$sp(this.i(), 2), f.pow$mcD$sp(this.j(), 2)), f.pow$mcD$sp(this.k(), 2)));
         Quaternion v = new Quaternion$mcD$sp(f.zero$mcD$sp(), f.div$mcD$sp(this.i(), s), f.div$mcD$sp(this.j(), s), f.div$mcD$sp(this.k(), s));
         double n = this.abs$mcD$sp(f, nr);
         double t = package$.MODULE$.acos$mDc$sp(f.div$mcD$sp(this.r(), n), tr);
         var10000 = Quaternion$.MODULE$.apply$mDc$sp(package$.MODULE$.cos$mDc$sp(f.times$mcD$sp(t, k0), tr), f).$plus$mcD$sp((Quaternion)v.$times$mcD$sp(package$.MODULE$.sin$mDc$sp(f.times$mcD$sp(t, k0), tr), f), f).$times$mcD$sp(nr.fpow$mcD$sp(n, k0), f);
      } else {
         var10000 = si.signum$mcD$sp(this.r()) >= 0 ? Quaternion$.MODULE$.apply$mDc$sp(nr.fpow$mcD$sp(this.r(), k0), f) : Quaternion$.MODULE$.apply$mDc$sp(Complex$.MODULE$.apply$mDc$sp(this.r(), f).pow$mcD$sp(Complex$.MODULE$.apply$mDc$sp(k0, f), f, nr, or, si, tr), f);
      }

      return (Quaternion)var10000;
   }

   public double dot(final Quaternion rhs, final Field f) {
      return this.dot$mcD$sp(rhs, f);
   }

   public double dot$mcD$sp(final Quaternion rhs, final Field f) {
      return f.div$mcD$sp(this.conjugate$mcD$sp(f).$times$mcD$sp((Quaternion)rhs, f).$plus$mcD$sp((Quaternion)rhs.conjugate$mcD$sp(f).$times$mcD$sp((Quaternion)this, f), f).r$mcD$sp(), f.fromInt$mcD$sp(2));
   }

   public double copy$default$1() {
      return this.copy$default$1$mcD$sp();
   }

   public double copy$default$1$mcD$sp() {
      return this.r();
   }

   public double copy$default$2() {
      return this.copy$default$2$mcD$sp();
   }

   public double copy$default$2$mcD$sp() {
      return this.i();
   }

   public double copy$default$3() {
      return this.copy$default$3$mcD$sp();
   }

   public double copy$default$3$mcD$sp() {
      return this.j();
   }

   public double copy$default$4() {
      return this.copy$default$4$mcD$sp();
   }

   public double copy$default$4$mcD$sp() {
      return this.k();
   }

   public boolean specInstance$() {
      return true;
   }

   private final Quaternion loop$2(final Quaternion p, final Quaternion b, final int e, final CommutativeRing s$2) {
      while(e != 0) {
         if ((e & 1) == 1) {
            Quaternion var10000 = p.$times$mcD$sp(b, s$2);
            Quaternion var5 = b.$times$mcD$sp(b, s$2);
            e >>>= 1;
            b = var5;
            p = var10000;
         } else {
            Quaternion var10001 = b.$times$mcD$sp(b, s$2);
            e >>>= 1;
            b = var10001;
            p = p;
         }
      }

      return p;
   }

   public Quaternion$mcD$sp(final double r$mcD$sp, final double i$mcD$sp, final double j$mcD$sp, final double k$mcD$sp) {
      super((Object)null, (Object)null, (Object)null, (Object)null);
      this.r$mcD$sp = r$mcD$sp;
      this.i$mcD$sp = i$mcD$sp;
      this.j$mcD$sp = j$mcD$sp;
      this.k$mcD$sp = k$mcD$sp;
   }
}
