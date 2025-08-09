package spire.math.poly;

import algebra.ring.Field;
import algebra.ring.Rig;
import algebra.ring.Ring;
import algebra.ring.Rng;
import algebra.ring.Semiring;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import scala.Function1;
import scala.Function2;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import spire.math.Polynomial;
import spire.math.Polynomial$;
import spire.math.Polynomial$mcD$sp;

public class PolyDense$mcD$sp extends PolyDense implements Polynomial$mcD$sp {
   public final double[] coeffs$mcD$sp;

   public List terms(final Semiring ring, final Eq eq) {
      return Polynomial$mcD$sp.terms$(this, ring, eq);
   }

   public List terms$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial$mcD$sp.terms$mcD$sp$(this, ring, eq);
   }

   public Map data(final Semiring ring, final Eq eq) {
      return Polynomial$mcD$sp.data$(this, ring, eq);
   }

   public Map data$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial$mcD$sp.data$mcD$sp$(this, ring, eq);
   }

   public Term maxTerm(final Semiring ring) {
      return Polynomial$mcD$sp.maxTerm$(this, ring);
   }

   public Term maxTerm$mcD$sp(final Semiring ring) {
      return Polynomial$mcD$sp.maxTerm$mcD$sp$(this, ring);
   }

   public Term minTerm(final Semiring ring, final Eq eq) {
      return Polynomial$mcD$sp.minTerm$(this, ring, eq);
   }

   public Term minTerm$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial$mcD$sp.minTerm$mcD$sp$(this, ring, eq);
   }

   public Object evalWith(final Object x, final Function1 f, final Semiring evidence$56, final Eq evidence$57, final ClassTag evidence$58) {
      return Polynomial$mcD$sp.evalWith$(this, x, f, evidence$56, evidence$57, evidence$58);
   }

   public Object evalWith$mcD$sp(final Object x, final Function1 f, final Semiring evidence$56, final Eq evidence$57, final ClassTag evidence$58) {
      return Polynomial$mcD$sp.evalWith$mcD$sp$(this, x, f, evidence$56, evidence$57, evidence$58);
   }

   public Polynomial compose(final Polynomial y, final Rig ring, final Eq eq) {
      return Polynomial$mcD$sp.compose$(this, y, ring, eq);
   }

   public Polynomial compose$mcD$sp(final Polynomial y, final Rig ring, final Eq eq) {
      return Polynomial$mcD$sp.compose$mcD$sp$(this, y, ring, eq);
   }

   public Polynomial shift(final double h, final Ring ring, final Eq eq) {
      return Polynomial$mcD$sp.shift$(this, h, ring, eq);
   }

   public Polynomial shift$mcD$sp(final double h, final Ring ring, final Eq eq) {
      return Polynomial$mcD$sp.shift$mcD$sp$(this, h, ring, eq);
   }

   public Polynomial monic(final Field f, final Eq eq) {
      return Polynomial$mcD$sp.monic$(this, f, eq);
   }

   public Polynomial monic$mcD$sp(final Field f, final Eq eq) {
      return Polynomial$mcD$sp.monic$mcD$sp$(this, f, eq);
   }

   public int signVariations(final Semiring ring, final Order order, final Signed signed) {
      return Polynomial$mcD$sp.signVariations$(this, ring, order, signed);
   }

   public int signVariations$mcD$sp(final Semiring ring, final Order order, final Signed signed) {
      return Polynomial$mcD$sp.signVariations$mcD$sp$(this, ring, order, signed);
   }

   public Polynomial removeZeroRoots(final Semiring ring, final Eq eq) {
      return Polynomial$mcD$sp.removeZeroRoots$(this, ring, eq);
   }

   public Polynomial removeZeroRoots$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial$mcD$sp.removeZeroRoots$mcD$sp$(this, ring, eq);
   }

   public Polynomial map(final Function1 f, final Semiring evidence$59, final Eq evidence$60, final ClassTag evidence$61) {
      return Polynomial$mcD$sp.map$(this, f, evidence$59, evidence$60, evidence$61);
   }

   public Polynomial map$mcD$sp(final Function1 f, final Semiring evidence$59, final Eq evidence$60, final ClassTag evidence$61) {
      return Polynomial$mcD$sp.map$mcD$sp$(this, f, evidence$59, evidence$60, evidence$61);
   }

   public Polynomial mapTerms(final Function1 f, final Semiring evidence$62, final Eq evidence$63, final ClassTag evidence$64) {
      return Polynomial$mcD$sp.mapTerms$(this, f, evidence$62, evidence$63, evidence$64);
   }

   public Polynomial mapTerms$mcD$sp(final Function1 f, final Semiring evidence$62, final Eq evidence$63, final ClassTag evidence$64) {
      return Polynomial$mcD$sp.mapTerms$mcD$sp$(this, f, evidence$62, evidence$63, evidence$64);
   }

   public Polynomial flip(final Rng ring, final Eq eq) {
      return Polynomial$mcD$sp.flip$(this, ring, eq);
   }

   public Polynomial flip$mcD$sp(final Rng ring, final Eq eq) {
      return Polynomial$mcD$sp.flip$mcD$sp$(this, ring, eq);
   }

   public Polynomial reciprocal(final Semiring ring, final Eq eq) {
      return Polynomial$mcD$sp.reciprocal$(this, ring, eq);
   }

   public Polynomial reciprocal$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial$mcD$sp.reciprocal$mcD$sp$(this, ring, eq);
   }

   public Polynomial $minus(final Polynomial rhs, final Rng ring, final Eq eq) {
      return Polynomial$mcD$sp.$minus$(this, rhs, ring, eq);
   }

   public Polynomial $minus$mcD$sp(final Polynomial rhs, final Rng ring, final Eq eq) {
      return Polynomial$mcD$sp.$minus$mcD$sp$(this, rhs, ring, eq);
   }

   public Polynomial $times$times(final int k, final Rig ring, final Eq eq) {
      return Polynomial$mcD$sp.$times$times$(this, k, ring, eq);
   }

   public Polynomial $times$times$mcD$sp(final int k, final Rig ring, final Eq eq) {
      return Polynomial$mcD$sp.$times$times$mcD$sp$(this, k, ring, eq);
   }

   public Polynomial pow(final int k, final Rig ring, final Eq eq) {
      return Polynomial$mcD$sp.pow$(this, k, ring, eq);
   }

   public Polynomial pow$mcD$sp(final int k, final Rig ring, final Eq eq) {
      return Polynomial$mcD$sp.pow$mcD$sp$(this, k, ring, eq);
   }

   public Polynomial $colon$times(final double k, final Semiring ring, final Eq eq) {
      return Polynomial$mcD$sp.$colon$times$(this, k, ring, eq);
   }

   public Polynomial $colon$times$mcD$sp(final double k, final Semiring ring, final Eq eq) {
      return Polynomial$mcD$sp.$colon$times$mcD$sp$(this, k, ring, eq);
   }

   public Polynomial $colon$div(final double k, final Field field, final Eq eq) {
      return Polynomial$mcD$sp.$colon$div$(this, k, field, eq);
   }

   public Polynomial $colon$div$mcD$sp(final double k, final Field field, final Eq eq) {
      return Polynomial$mcD$sp.$colon$div$mcD$sp$(this, k, field, eq);
   }

   public double[] coeffs$mcD$sp() {
      return this.coeffs$mcD$sp;
   }

   public double[] coeffs() {
      return this.coeffs$mcD$sp();
   }

   public PolySparse toSparse(final Semiring ring, final Eq eq) {
      return this.toSparse$mcD$sp(ring, eq);
   }

   public PolySparse toSparse$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial$.MODULE$.sparse$mDc$sp(this.data$mcD$sp(ring, eq), ring, eq, this.ct());
   }

   public PolyDense toDense(final Semiring ring, final Eq eq) {
      return this.toDense$mcD$sp(ring, eq);
   }

   public PolyDense toDense$mcD$sp(final Semiring ring, final Eq eq) {
      return this;
   }

   public void foreach(final Function2 f) {
      this.foreach$mcD$sp(f);
   }

   public void foreach$mcD$sp(final Function2 f) {
      for(int index$macro$1 = 0; index$macro$1 < this.coeffs().length; ++index$macro$1) {
         f.apply(BoxesRunTime.boxToInteger(index$macro$1), BoxesRunTime.boxToDouble(this.coeffs()[index$macro$1]));
      }

   }

   public void foreachNonZero(final Function2 f, final Semiring ring, final Eq eq) {
      this.foreachNonZero$mcD$sp(f, ring, eq);
   }

   public void foreachNonZero$mcD$sp(final Function2 f, final Semiring ring, final Eq eq) {
      for(int index$macro$1 = 0; index$macro$1 < this.coeffs().length; ++index$macro$1) {
         double c = this.coeffs()[index$macro$1];
         if (eq.neqv$mcD$sp(c, ring.zero$mcD$sp())) {
            f.apply(BoxesRunTime.boxToInteger(index$macro$1), BoxesRunTime.boxToDouble(c));
         }
      }

   }

   public double[] coeffsArray(final Semiring ring) {
      return this.coeffsArray$mcD$sp(ring);
   }

   public double[] coeffsArray$mcD$sp(final Semiring ring) {
      return this.coeffs();
   }

   public double nth(final int n, final Semiring ring) {
      return this.nth$mcD$sp(n, ring);
   }

   public double nth$mcD$sp(final int n, final Semiring ring) {
      return n < this.coeffs().length ? this.coeffs()[n] : ring.zero$mcD$sp();
   }

   public double maxOrderTermCoeff(final Semiring ring) {
      return this.maxOrderTermCoeff$mcD$sp(ring);
   }

   public double maxOrderTermCoeff$mcD$sp(final Semiring ring) {
      return this.isZero() ? ring.zero$mcD$sp() : this.coeffs()[this.degree()];
   }

   public Polynomial reductum(final Eq e, final Semiring ring, final ClassTag ct) {
      return this.reductum$mcD$sp(e, ring, ct);
   }

   public Polynomial reductum$mcD$sp(final Eq e, final Semiring ring, final ClassTag ct) {
      int i;
      for(i = this.coeffs().length - 2; i >= 0 && e.eqv$mcD$sp(this.coeffs()[i], ring.zero$mcD$sp()); --i) {
      }

      PolyDense$mcD$sp var10000;
      if (i < 0) {
         var10000 = new PolyDense$mcD$sp((double[])ct.newArray(0), ct);
      } else {
         double[] arr = (double[])ct.newArray(i + 1);
         System.arraycopy(this.coeffs(), 0, arr, 0, i + 1);
         var10000 = new PolyDense$mcD$sp(arr, ct);
      }

      return var10000;
   }

   public double apply(final double x, final Semiring ring) {
      return this.apply$mcD$sp(x, ring);
   }

   public double apply$mcD$sp(final double x, final Semiring ring) {
      if (this.isZero()) {
         return ring.zero$mcD$sp();
      } else {
         int even = this.coeffs().length - 1;
         int odd = this.coeffs().length - 2;
         if ((even & 1) == 1) {
            even = odd;
            odd = this.coeffs().length - 1;
         }

         double c0 = this.coeffs()[even];
         double x2 = ring.pow$mcD$sp(x, 2);

         for(int index$macro$1 = even - 2; index$macro$1 >= 0; index$macro$1 -= 2) {
            c0 = ring.plus$mcD$sp(this.coeffs()[index$macro$1], ring.times$mcD$sp(c0, x2));
         }

         double var10000;
         if (odd >= 1) {
            double c1 = this.coeffs()[odd];

            for(int index$macro$2 = odd - 2; index$macro$2 >= 1; index$macro$2 -= 2) {
               c1 = ring.plus$mcD$sp(this.coeffs()[index$macro$2], ring.times$mcD$sp(c1, x2));
            }

            var10000 = ring.plus$mcD$sp(c0, ring.times$mcD$sp(c1, x));
         } else {
            var10000 = c0;
         }

         return var10000;
      }
   }

   public Polynomial derivative(final Ring ring, final Eq eq) {
      return this.derivative$mcD$sp(ring, eq);
   }

   public Polynomial derivative$mcD$sp(final Ring ring, final Eq eq) {
      if (this.isZero()) {
         return this;
      } else {
         double[] cs = (double[])this.ct().newArray(this.degree());
         int j = this.coeffs().length - 1;

         for(int index$macro$1 = cs.length - 1; index$macro$1 >= 0; --index$macro$1) {
            cs[index$macro$1] = ring.times$mcD$sp(ring.fromInt$mcD$sp(j), this.coeffs()[j]);
            --j;
         }

         return Polynomial$.MODULE$.dense$mDc$sp(cs, ring, eq, this.ct());
      }
   }

   public Polynomial integral(final Field field, final Eq eq) {
      return this.integral$mcD$sp(field, eq);
   }

   public Polynomial integral$mcD$sp(final Field field, final Eq eq) {
      double[] cs = (double[])this.ct().newArray(this.coeffs().length + 1);
      cs[0] = field.zero$mcD$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.coeffs().length; ++index$macro$1) {
         cs[index$macro$1 + 1] = field.div$mcD$sp(this.coeffs()[index$macro$1], field.fromInt$mcD$sp(index$macro$1 + 1));
      }

      return Polynomial$.MODULE$.dense$mDc$sp(cs, field, eq, this.ct());
   }

   public Polynomial unary_$minus(final Rng ring) {
      return this.unary_$minus$mcD$sp(ring);
   }

   public Polynomial unary_$minus$mcD$sp(final Rng ring) {
      double[] negArray = (double[])this.ct().newArray(this.coeffs().length);

      for(int index$macro$1 = 0; index$macro$1 < this.coeffs().length; ++index$macro$1) {
         negArray[index$macro$1] = ring.negate$mcD$sp(this.coeffs()[index$macro$1]);
      }

      return new PolyDense$mcD$sp(negArray, this.ct());
   }

   public Polynomial $plus(final Polynomial rhs, final Semiring ring, final Eq eq) {
      return this.$plus$mcD$sp(rhs, ring, eq);
   }

   public Polynomial $plus$mcD$sp(final Polynomial rhs, final Semiring ring, final Eq eq) {
      return PolyDense$.MODULE$.spire$math$poly$PolyDense$$plusDense(this, rhs, ring, eq, this.ct());
   }

   public Polynomial $times(final Polynomial rhs, final Semiring ring, final Eq eq) {
      return this.$times$mcD$sp(rhs, ring, eq);
   }

   public Polynomial $times$mcD$sp(final Polynomial rhs, final Semiring ring, final Eq eq) {
      if (rhs.isZero()) {
         return rhs;
      } else if (this.isZero()) {
         return this;
      } else {
         double[] lcs = this.coeffsArray$mcD$sp(ring);
         double[] rcs = rhs.coeffsArray$mcD$sp(ring);
         double[] cs = (double[])this.ct().newArray(lcs.length + rcs.length - 1);

         for(int index$macro$1 = 0; index$macro$1 < cs.length; ++index$macro$1) {
            cs[index$macro$1] = ring.zero$mcD$sp();
         }

         for(int index$macro$3 = 0; index$macro$3 < lcs.length; ++index$macro$3) {
            double c = lcs[index$macro$3];
            int k = index$macro$3;

            for(int index$macro$2 = 0; index$macro$2 < rcs.length; ++index$macro$2) {
               cs[k] = ring.plus$mcD$sp(cs[k], ring.times$mcD$sp(c, rcs[index$macro$2]));
               ++k;
            }
         }

         return Polynomial$.MODULE$.dense$mDc$sp(cs, ring, eq, this.ct());
      }
   }

   public Polynomial $times$colon(final double k, final Semiring ring, final Eq eq) {
      return this.$times$colon$mcD$sp(k, ring, eq);
   }

   public Polynomial $times$colon$mcD$sp(final double k, final Semiring ring, final Eq eq) {
      PolyDense var10000;
      if (eq.eqv$mcD$sp(k, ring.zero$mcD$sp())) {
         var10000 = Polynomial$.MODULE$.dense$mDc$sp((double[])this.ct().newArray(0), ring, eq, this.ct());
      } else {
         double[] cs = (double[])this.ct().newArray(this.coeffs().length);

         for(int index$macro$1 = 0; index$macro$1 < cs.length; ++index$macro$1) {
            cs[index$macro$1] = ring.times$mcD$sp(k, this.coeffs()[index$macro$1]);
         }

         var10000 = Polynomial$.MODULE$.dense$mDc$sp(cs, ring, eq, this.ct());
      }

      return var10000;
   }

   public boolean specInstance$() {
      return true;
   }

   public PolyDense$mcD$sp(final double[] coeffs$mcD$sp, final ClassTag ct) {
      super((Object)null, ct);
      this.coeffs$mcD$sp = coeffs$mcD$sp;
   }
}
