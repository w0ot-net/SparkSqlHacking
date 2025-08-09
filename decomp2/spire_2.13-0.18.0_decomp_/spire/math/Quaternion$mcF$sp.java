package spire.math;

import algebra.ring.CommutativeRing;
import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import spire.algebra.NRoot;
import spire.algebra.Trig;

public final class Quaternion$mcF$sp extends Quaternion {
   public final float r$mcF$sp;
   public final float i$mcF$sp;
   public final float j$mcF$sp;
   public final float k$mcF$sp;

   public float r$mcF$sp() {
      return this.r$mcF$sp;
   }

   public float r() {
      return this.r$mcF$sp();
   }

   public float i$mcF$sp() {
      return this.i$mcF$sp;
   }

   public float i() {
      return this.i$mcF$sp();
   }

   public float j$mcF$sp() {
      return this.j$mcF$sp;
   }

   public float j() {
      return this.j$mcF$sp();
   }

   public float k$mcF$sp() {
      return this.k$mcF$sp;
   }

   public float k() {
      return this.k$mcF$sp();
   }

   public boolean isZero(final Signed s) {
      return this.isZero$mcF$sp(s);
   }

   public boolean isZero$mcF$sp(final Signed s) {
      return s.isSignZero$mcF$sp(this.r()) && s.isSignZero$mcF$sp(this.i()) && s.isSignZero$mcF$sp(this.j()) && s.isSignZero$mcF$sp(this.k());
   }

   public boolean isReal(final Signed s) {
      return this.isReal$mcF$sp(s);
   }

   public boolean isReal$mcF$sp(final Signed s) {
      return s.isSignZero$mcF$sp(this.i()) && s.isSignZero$mcF$sp(this.j()) && s.isSignZero$mcF$sp(this.k());
   }

   public boolean isPure(final Signed s) {
      return this.isPure$mcF$sp(s);
   }

   public boolean isPure$mcF$sp(final Signed s) {
      return s.isSignZero$mcF$sp(this.r());
   }

   public Quaternion real(final CommutativeRing s) {
      return this.real$mcF$sp(s);
   }

   public Quaternion real$mcF$sp(final CommutativeRing s) {
      return Quaternion$.MODULE$.apply$mFc$sp(this.r(), s);
   }

   public Quaternion pure(final CommutativeRing s) {
      return this.pure$mcF$sp(s);
   }

   public Quaternion pure$mcF$sp(final CommutativeRing s) {
      return new Quaternion$mcF$sp(s.zero$mcF$sp(), this.i(), this.j(), this.k());
   }

   public float abs(final Field f, final NRoot n) {
      return this.abs$mcF$sp(f, n);
   }

   public float abs$mcF$sp(final Field f, final NRoot n) {
      return n.sqrt$mcF$sp(f.plus$mcF$sp(f.plus$mcF$sp(f.plus$mcF$sp(f.pow$mcF$sp(this.r(), 2), f.pow$mcF$sp(this.i(), 2)), f.pow$mcF$sp(this.j(), 2)), f.pow$mcF$sp(this.k(), 2)));
   }

   public float pureAbs(final Field f, final NRoot n) {
      return this.pureAbs$mcF$sp(f, n);
   }

   public float pureAbs$mcF$sp(final Field f, final NRoot n) {
      return n.sqrt$mcF$sp(f.plus$mcF$sp(f.plus$mcF$sp(f.pow$mcF$sp(this.i(), 2), f.pow$mcF$sp(this.j(), 2)), f.pow$mcF$sp(this.k(), 2)));
   }

   public boolean eqv(final Quaternion rhs, final Eq o) {
      return this.eqv$mcF$sp(rhs, o);
   }

   public boolean eqv$mcF$sp(final Quaternion rhs, final Eq o) {
      return o.eqv$mcF$sp(this.r(), rhs.r$mcF$sp()) && o.eqv$mcF$sp(this.i(), rhs.i$mcF$sp()) && o.eqv$mcF$sp(this.j(), rhs.j$mcF$sp()) && o.eqv$mcF$sp(this.k(), rhs.k$mcF$sp());
   }

   public boolean neqv(final Quaternion rhs, final Eq o) {
      return this.neqv$mcF$sp(rhs, o);
   }

   public boolean neqv$mcF$sp(final Quaternion rhs, final Eq o) {
      return o.neqv$mcF$sp(this.r(), rhs.r$mcF$sp()) && o.neqv$mcF$sp(this.i(), rhs.i$mcF$sp()) && o.neqv$mcF$sp(this.j(), rhs.j$mcF$sp()) && o.neqv$mcF$sp(this.k(), rhs.k$mcF$sp());
   }

   public Complex toComplex() {
      return this.toComplex$mcF$sp();
   }

   public Complex toComplex$mcF$sp() {
      return new Complex$mcF$sp(this.r(), this.i());
   }

   public int signum(final Signed s) {
      return this.signum$mcF$sp(s);
   }

   public int signum$mcF$sp(final Signed s) {
      int var2 = s.signum$mcF$sp(this.r());
      int var10000;
      switch (var2) {
         case 0:
            int var3 = s.signum$mcF$sp(this.i());
            switch (var3) {
               case 0:
                  int var4 = s.signum$mcF$sp(this.j());
                  switch (var4) {
                     case 0:
                        var10000 = s.signum$mcF$sp(this.k());
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
      return this.quaternionSignum$mcF$sp(f, n, s);
   }

   public Quaternion quaternionSignum$mcF$sp(final Field f, final NRoot n, final Signed s) {
      return (Quaternion)(this.isZero$mcF$sp(s) ? this : this.$div$mcF$sp(this.abs$mcF$sp(f, n), f));
   }

   public Quaternion pureSignum(final Field f, final NRoot n, final Signed s) {
      return this.pureSignum$mcF$sp(f, n, s);
   }

   public Quaternion pureSignum$mcF$sp(final Field f, final NRoot n, final Signed s) {
      return this.isReal$mcF$sp(s) ? Quaternion$.MODULE$.zero$mFc$sp(f) : this.pure$mcF$sp(f).$div$mcF$sp(this.pureAbs$mcF$sp(f, n), f);
   }

   public Quaternion unary_$minus(final CommutativeRing s) {
      return this.unary_$minus$mcF$sp(s);
   }

   public Quaternion unary_$minus$mcF$sp(final CommutativeRing s) {
      return new Quaternion$mcF$sp(s.negate$mcF$sp(this.r()), s.negate$mcF$sp(this.i()), s.negate$mcF$sp(this.j()), s.negate$mcF$sp(this.k()));
   }

   public Quaternion conjugate(final CommutativeRing s) {
      return this.conjugate$mcF$sp(s);
   }

   public Quaternion conjugate$mcF$sp(final CommutativeRing s) {
      return new Quaternion$mcF$sp(this.r(), s.negate$mcF$sp(this.i()), s.negate$mcF$sp(this.j()), s.negate$mcF$sp(this.k()));
   }

   public Quaternion reciprocal(final Field f) {
      return this.reciprocal$mcF$sp(f);
   }

   public Quaternion reciprocal$mcF$sp(final Field f) {
      return this.conjugate$mcF$sp(f).$div$mcF$sp(f.plus$mcF$sp(f.plus$mcF$sp(f.plus$mcF$sp(f.pow$mcF$sp(this.r(), 2), f.pow$mcF$sp(this.i(), 2)), f.pow$mcF$sp(this.j(), 2)), f.pow$mcF$sp(this.k(), 2)), f);
   }

   public Quaternion sqrt(final Field f, final NRoot nr, final Signed s) {
      return this.sqrt$mcF$sp(f, nr, s);
   }

   public Quaternion sqrt$mcF$sp(final Field f, final NRoot nr, final Signed s) {
      Object var10000;
      if (!this.isReal$mcF$sp(s)) {
         float n = nr.sqrt$mcF$sp(f.plus$mcF$sp(this.r(), this.abs$mcF$sp(f, nr)));
         var10000 = (new Quaternion$mcF$sp(n, f.div$mcF$sp(this.i(), n), f.div$mcF$sp(this.j(), n), f.div$mcF$sp(this.k(), n))).$div$mcF$sp(nr.sqrt$mcF$sp(f.fromInt$mcF$sp(2)), f);
      } else {
         var10000 = s.signum$mcF$sp(this.r()) >= 0 ? Quaternion$.MODULE$.apply$mFc$sp(nr.sqrt$mcF$sp(this.r()), f) : new Quaternion$mcF$sp(f.zero$mcF$sp(), nr.sqrt$mcF$sp(s.abs$mcF$sp(this.r())), f.zero$mcF$sp(), f.zero$mcF$sp());
      }

      return (Quaternion)var10000;
   }

   public Quaternion nroot(final int m, final Field f, final NRoot nr, final Order or, final Signed si, final Trig tr) {
      return this.nroot$mcF$sp(m, f, nr, or, si, tr);
   }

   public Quaternion nroot$mcF$sp(final int m, final Field f, final NRoot nr, final Order or, final Signed si, final Trig tr) {
      if (m <= 0) {
         throw new IllegalArgumentException((new StringBuilder(14)).append("illegal root: ").append(m).toString());
      } else {
         Object var10000;
         if (m == 1) {
            var10000 = this;
         } else if (!this.isReal$mcF$sp(si)) {
            float s = this.pureAbs$mcF$sp(f, nr);
            float n = this.abs$mcF$sp(f, nr);
            float t = package$.MODULE$.acos$mFc$sp(f.div$mcF$sp(this.r(), n), tr);
            Quaternion v = new Quaternion$mcF$sp(f.zero$mcF$sp(), f.div$mcF$sp(this.i(), s), f.div$mcF$sp(this.j(), s), f.div$mcF$sp(this.k(), s));
            Quaternion e = si.signum$mcF$sp(package$.MODULE$.sin$mFc$sp(t, tr)) >= 0 ? v : v.unary_$minus$mcF$sp(f);
            float tm = f.div$mcF$sp(t, f.fromInt$mcF$sp(m));
            var10000 = e.$times$mcF$sp(package$.MODULE$.sin$mFc$sp(tm, tr), f).$plus$mcF$sp(package$.MODULE$.cos$mFc$sp(tm, tr), f).$times$mcF$sp(nr.nroot$mcF$sp(n, m), f);
         } else {
            var10000 = si.signum$mcF$sp(this.r()) >= 0 ? Quaternion$.MODULE$.apply$mFc$sp(nr.nroot$mcF$sp(this.r(), m), f) : Quaternion$.MODULE$.apply$mFc$sp(Complex$.MODULE$.apply$mFc$sp(this.r(), f).nroot$mcF$sp(m, f, nr, or, si, tr), f);
         }

         return (Quaternion)var10000;
      }
   }

   public Quaternion unit(final Field f, final NRoot n) {
      return this.unit$mcF$sp(f, n);
   }

   public Quaternion unit$mcF$sp(final Field f, final NRoot n) {
      return (new Quaternion$mcF$sp(f.pow$mcF$sp(this.r(), 2), f.pow$mcF$sp(this.i(), 2), f.pow$mcF$sp(this.j(), 2), f.pow$mcF$sp(this.k(), 2))).$div$mcF$sp(this.abs$mcF$sp(f, n), f);
   }

   public Quaternion $plus(final float rhs, final CommutativeRing s) {
      return this.$plus$mcF$sp(rhs, s);
   }

   public Quaternion $plus$mcF$sp(final float rhs, final CommutativeRing s) {
      return new Quaternion$mcF$sp(s.plus$mcF$sp(this.r(), rhs), this.i(), this.j(), this.k());
   }

   public Quaternion $plus(final Complex rhs, final CommutativeRing s) {
      return this.$plus$mcF$sp(rhs, s);
   }

   public Quaternion $plus$mcF$sp(final Complex rhs, final CommutativeRing s) {
      return new Quaternion$mcF$sp(s.plus$mcF$sp(this.r(), rhs.real$mcF$sp()), s.plus$mcF$sp(this.i(), rhs.imag$mcF$sp()), this.j(), this.k());
   }

   public Quaternion $plus(final Quaternion rhs, final CommutativeRing s) {
      return this.$plus$mcF$sp(rhs, s);
   }

   public Quaternion $plus$mcF$sp(final Quaternion rhs, final CommutativeRing s) {
      return new Quaternion$mcF$sp(s.plus$mcF$sp(this.r(), rhs.r$mcF$sp()), s.plus$mcF$sp(this.i(), rhs.i$mcF$sp()), s.plus$mcF$sp(this.j(), rhs.j$mcF$sp()), s.plus$mcF$sp(this.k(), rhs.k$mcF$sp()));
   }

   public Quaternion $minus(final float rhs, final CommutativeRing s) {
      return this.$minus$mcF$sp(rhs, s);
   }

   public Quaternion $minus$mcF$sp(final float rhs, final CommutativeRing s) {
      return new Quaternion$mcF$sp(s.minus$mcF$sp(this.r(), rhs), this.i(), this.j(), this.k());
   }

   public Quaternion $minus(final Complex rhs, final CommutativeRing s) {
      return this.$minus$mcF$sp(rhs, s);
   }

   public Quaternion $minus$mcF$sp(final Complex rhs, final CommutativeRing s) {
      return new Quaternion$mcF$sp(s.minus$mcF$sp(this.r(), rhs.real$mcF$sp()), s.minus$mcF$sp(this.i(), rhs.imag$mcF$sp()), this.j(), this.k());
   }

   public Quaternion $minus(final Quaternion rhs, final CommutativeRing s) {
      return this.$minus$mcF$sp(rhs, s);
   }

   public Quaternion $minus$mcF$sp(final Quaternion rhs, final CommutativeRing s) {
      return new Quaternion$mcF$sp(s.minus$mcF$sp(this.r(), rhs.r$mcF$sp()), s.minus$mcF$sp(this.i(), rhs.i$mcF$sp()), s.minus$mcF$sp(this.j(), rhs.j$mcF$sp()), s.minus$mcF$sp(this.k(), rhs.k$mcF$sp()));
   }

   public Quaternion $times(final float rhs, final CommutativeRing s) {
      return this.$times$mcF$sp(rhs, s);
   }

   public Quaternion $times$mcF$sp(final float rhs, final CommutativeRing s) {
      return new Quaternion$mcF$sp(s.times$mcF$sp(this.r(), rhs), s.times$mcF$sp(this.i(), rhs), s.times$mcF$sp(this.j(), rhs), s.times$mcF$sp(this.k(), rhs));
   }

   public Quaternion $times(final Complex rhs, final CommutativeRing s) {
      return this.$times$mcF$sp(rhs, s);
   }

   public Quaternion $times$mcF$sp(final Complex rhs, final CommutativeRing s) {
      return new Quaternion$mcF$sp(s.minus$mcF$sp(s.times$mcF$sp(this.r(), rhs.real$mcF$sp()), s.times$mcF$sp(this.i(), rhs.imag$mcF$sp())), s.plus$mcF$sp(s.times$mcF$sp(this.r(), rhs.imag$mcF$sp()), s.times$mcF$sp(this.i(), rhs.real$mcF$sp())), s.plus$mcF$sp(s.times$mcF$sp(this.j(), rhs.real$mcF$sp()), s.times$mcF$sp(this.k(), rhs.imag$mcF$sp())), s.plus$mcF$sp(s.times$mcF$sp(this.j(), rhs.imag$mcF$sp()), s.times$mcF$sp(this.k(), rhs.real$mcF$sp())));
   }

   public Quaternion $times(final Quaternion rhs, final CommutativeRing s) {
      return this.$times$mcF$sp(rhs, s);
   }

   public Quaternion $times$mcF$sp(final Quaternion rhs, final CommutativeRing s) {
      return new Quaternion$mcF$sp(s.minus$mcF$sp(s.minus$mcF$sp(s.minus$mcF$sp(s.times$mcF$sp(this.r(), rhs.r$mcF$sp()), s.times$mcF$sp(this.i(), rhs.i$mcF$sp())), s.times$mcF$sp(this.j(), rhs.j$mcF$sp())), s.times$mcF$sp(this.k(), rhs.k$mcF$sp())), s.minus$mcF$sp(s.plus$mcF$sp(s.plus$mcF$sp(s.times$mcF$sp(this.r(), rhs.i$mcF$sp()), s.times$mcF$sp(this.i(), rhs.r$mcF$sp())), s.times$mcF$sp(this.j(), rhs.k$mcF$sp())), s.times$mcF$sp(this.k(), rhs.j$mcF$sp())), s.plus$mcF$sp(s.plus$mcF$sp(s.minus$mcF$sp(s.times$mcF$sp(this.r(), rhs.j$mcF$sp()), s.times$mcF$sp(this.i(), rhs.k$mcF$sp())), s.times$mcF$sp(this.j(), rhs.r$mcF$sp())), s.times$mcF$sp(this.k(), rhs.i$mcF$sp())), s.plus$mcF$sp(s.minus$mcF$sp(s.plus$mcF$sp(s.times$mcF$sp(this.r(), rhs.k$mcF$sp()), s.times$mcF$sp(this.i(), rhs.j$mcF$sp())), s.times$mcF$sp(this.j(), rhs.i$mcF$sp())), s.times$mcF$sp(this.k(), rhs.r$mcF$sp())));
   }

   public Quaternion $div(final float rhs, final Field f) {
      return this.$div$mcF$sp(rhs, f);
   }

   public Quaternion $div$mcF$sp(final float rhs, final Field f) {
      return new Quaternion$mcF$sp(f.div$mcF$sp(this.r(), rhs), f.div$mcF$sp(this.i(), rhs), f.div$mcF$sp(this.j(), rhs), f.div$mcF$sp(this.k(), rhs));
   }

   public Quaternion $div(final Complex rhs, final Field f) {
      return this.$div$mcF$sp(rhs, f);
   }

   public Quaternion $div$mcF$sp(final Complex rhs, final Field f) {
      return this.$times$mcF$sp((Quaternion)Quaternion$.MODULE$.apply$mFc$sp(rhs, f).reciprocal$mcF$sp(f), f);
   }

   public Quaternion $div(final Quaternion rhs, final Field f) {
      return this.$div$mcF$sp(rhs, f);
   }

   public Quaternion $div$mcF$sp(final Quaternion rhs, final Field f) {
      return this.$times$mcF$sp((Quaternion)rhs.reciprocal$mcF$sp(f), f);
   }

   public Quaternion pow(final int k, final CommutativeRing s) {
      return this.pow$mcF$sp(k, s);
   }

   public Quaternion pow$mcF$sp(final int k, final CommutativeRing s) {
      if (k >= 0) {
         return this.loop$3(Quaternion$.MODULE$.one$mFc$sp(s), this, k, s);
      } else {
         throw new IllegalArgumentException((new StringBuilder(18)).append("illegal exponent: ").append(k).toString());
      }
   }

   public Quaternion $times$times(final int k, final CommutativeRing s) {
      return this.$times$times$mcF$sp(k, s);
   }

   public Quaternion $times$times$mcF$sp(final int k, final CommutativeRing s) {
      return this.pow$mcF$sp(k, s);
   }

   public Quaternion fpow(final float k0, final Field f, final NRoot nr, final Order or, final Signed si, final Trig tr) {
      return this.fpow$mcF$sp(k0, f, nr, or, si, tr);
   }

   public Quaternion fpow$mcF$sp(final float k0, final Field f, final NRoot nr, final Order or, final Signed si, final Trig tr) {
      Object var10000;
      if (si.signum$mcF$sp(k0) < 0) {
         var10000 = Quaternion$.MODULE$.zero$mFc$sp(f);
      } else if (k0 == f.zero$mcF$sp()) {
         var10000 = Quaternion$.MODULE$.one$mFc$sp(f);
      } else if (k0 == f.one$mcF$sp()) {
         var10000 = this;
      } else if (!this.isReal$mcF$sp(si)) {
         float s = nr.sqrt$mcF$sp(f.plus$mcF$sp(f.plus$mcF$sp(f.pow$mcF$sp(this.i(), 2), f.pow$mcF$sp(this.j(), 2)), f.pow$mcF$sp(this.k(), 2)));
         Quaternion v = new Quaternion$mcF$sp(f.zero$mcF$sp(), f.div$mcF$sp(this.i(), s), f.div$mcF$sp(this.j(), s), f.div$mcF$sp(this.k(), s));
         float n = this.abs$mcF$sp(f, nr);
         float t = package$.MODULE$.acos$mFc$sp(f.div$mcF$sp(this.r(), n), tr);
         var10000 = Quaternion$.MODULE$.apply$mFc$sp(package$.MODULE$.cos$mFc$sp(f.times$mcF$sp(t, k0), tr), f).$plus$mcF$sp((Quaternion)v.$times$mcF$sp(package$.MODULE$.sin$mFc$sp(f.times$mcF$sp(t, k0), tr), f), f).$times$mcF$sp(nr.fpow$mcF$sp(n, k0), f);
      } else {
         var10000 = si.signum$mcF$sp(this.r()) >= 0 ? Quaternion$.MODULE$.apply$mFc$sp(nr.fpow$mcF$sp(this.r(), k0), f) : Quaternion$.MODULE$.apply$mFc$sp(Complex$.MODULE$.apply$mFc$sp(this.r(), f).pow$mcF$sp(Complex$.MODULE$.apply$mFc$sp(k0, f), f, nr, or, si, tr), f);
      }

      return (Quaternion)var10000;
   }

   public float dot(final Quaternion rhs, final Field f) {
      return this.dot$mcF$sp(rhs, f);
   }

   public float dot$mcF$sp(final Quaternion rhs, final Field f) {
      return f.div$mcF$sp(this.conjugate$mcF$sp(f).$times$mcF$sp((Quaternion)rhs, f).$plus$mcF$sp((Quaternion)rhs.conjugate$mcF$sp(f).$times$mcF$sp((Quaternion)this, f), f).r$mcF$sp(), f.fromInt$mcF$sp(2));
   }

   public float copy$default$1() {
      return this.copy$default$1$mcF$sp();
   }

   public float copy$default$1$mcF$sp() {
      return this.r();
   }

   public float copy$default$2() {
      return this.copy$default$2$mcF$sp();
   }

   public float copy$default$2$mcF$sp() {
      return this.i();
   }

   public float copy$default$3() {
      return this.copy$default$3$mcF$sp();
   }

   public float copy$default$3$mcF$sp() {
      return this.j();
   }

   public float copy$default$4() {
      return this.copy$default$4$mcF$sp();
   }

   public float copy$default$4$mcF$sp() {
      return this.k();
   }

   public boolean specInstance$() {
      return true;
   }

   private final Quaternion loop$3(final Quaternion p, final Quaternion b, final int e, final CommutativeRing s$3) {
      while(e != 0) {
         if ((e & 1) == 1) {
            Quaternion var10000 = p.$times$mcF$sp(b, s$3);
            Quaternion var5 = b.$times$mcF$sp(b, s$3);
            e >>>= 1;
            b = var5;
            p = var10000;
         } else {
            Quaternion var10001 = b.$times$mcF$sp(b, s$3);
            e >>>= 1;
            b = var10001;
            p = p;
         }
      }

      return p;
   }

   public Quaternion$mcF$sp(final float r$mcF$sp, final float i$mcF$sp, final float j$mcF$sp, final float k$mcF$sp) {
      super((Object)null, (Object)null, (Object)null, (Object)null);
      this.r$mcF$sp = r$mcF$sp;
      this.i$mcF$sp = i$mcF$sp;
      this.j$mcF$sp = j$mcF$sp;
      this.k$mcF$sp = k$mcF$sp;
   }
}
