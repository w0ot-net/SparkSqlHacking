package spire.math.poly;

import algebra.ring.Field;
import algebra.ring.Rig;
import algebra.ring.Ring;
import algebra.ring.Rng;
import algebra.ring.Semiring;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import java.util.Arrays;
import scala.Function1;
import scala.Function2;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import spire.math.Polynomial;
import spire.math.Polynomial$;
import spire.math.Polynomial$mcD$sp;
import spire.math.package$;
import spire.syntax.LiteralIntMultiplicativeSemigroupOps$;
import spire.syntax.package;

public class PolySparse$mcD$sp extends PolySparse implements Polynomial$mcD$sp {
   public final double[] coeff$mcD$sp;

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

   public double[] coeff$mcD$sp() {
      return this.coeff$mcD$sp;
   }

   public double[] coeff() {
      return this.coeff$mcD$sp();
   }

   public PolyDense toDense(final Semiring ring, final Eq eq) {
      return this.toDense$mcD$sp(ring, eq);
   }

   public PolyDense toDense$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial$.MODULE$.dense$mDc$sp(this.coeffsArray$mcD$sp(ring), ring, eq, this.ct());
   }

   public PolySparse toSparse(final Semiring ring, final Eq eq) {
      return this.toSparse$mcD$sp(ring, eq);
   }

   public PolySparse toSparse$mcD$sp(final Semiring ring, final Eq eq) {
      return this;
   }

   public void foreach(final Function2 f) {
      this.foreach$mcD$sp(f);
   }

   public void foreach$mcD$sp(final Function2 f) {
      for(int index$macro$1 = 0; index$macro$1 < this.exp().length; ++index$macro$1) {
         f.apply(BoxesRunTime.boxToInteger(this.exp()[index$macro$1]), BoxesRunTime.boxToDouble(this.coeff()[index$macro$1]));
      }

   }

   public void foreachNonZero(final Function2 f, final Semiring ring, final Eq eq) {
      this.foreachNonZero$mcD$sp(f, ring, eq);
   }

   public void foreachNonZero$mcD$sp(final Function2 f, final Semiring ring, final Eq eq) {
      this.foreach$mcD$sp(f);
   }

   public double[] coeffsArray(final Semiring ring) {
      return this.coeffsArray$mcD$sp(ring);
   }

   public double[] coeffsArray$mcD$sp(final Semiring ring) {
      double[] var10000;
      if (this.isZero()) {
         var10000 = (double[])this.ct().newArray(0);
      } else {
         double[] cs = (double[])this.ct().newArray(this.degree() + 1);

         for(int index$macro$1 = 0; index$macro$1 < cs.length; ++index$macro$1) {
            cs[index$macro$1] = ring.zero$mcD$sp();
         }

         for(int index$macro$2 = 0; index$macro$2 < this.exp().length; ++index$macro$2) {
            cs[this.exp()[index$macro$2]] = this.coeff()[index$macro$2];
         }

         var10000 = cs;
      }

      return var10000;
   }

   public double nth(final int n, final Semiring ring) {
      return this.nth$mcD$sp(n, ring);
   }

   public double nth$mcD$sp(final int n, final Semiring ring) {
      int i = Arrays.binarySearch(this.exp(), n);
      return i >= 0 ? this.coeff()[i] : ring.zero$mcD$sp();
   }

   public double maxOrderTermCoeff(final Semiring ring) {
      return this.maxOrderTermCoeff$mcD$sp(ring);
   }

   public double maxOrderTermCoeff$mcD$sp(final Semiring ring) {
      return this.isZero() ? ring.zero$mcD$sp() : this.coeff()[this.coeff().length - 1];
   }

   public Polynomial reductum(final Eq e, final Semiring ring, final ClassTag ct) {
      return this.reductum$mcD$sp(e, ring, ct);
   }

   public Polynomial reductum$mcD$sp(final Eq e, final Semiring ring, final ClassTag ct) {
      int i;
      for(i = this.coeff().length - 2; i >= 0 && e.eqv$mcD$sp(this.coeff()[i], ring.zero$mcD$sp()); --i) {
      }

      PolySparse$mcD$sp var10000;
      if (i < 0) {
         var10000 = new PolySparse$mcD$sp(new int[0], (double[])ct.newArray(0), ct);
      } else {
         int len = i + 1;
         int[] es = new int[len];
         double[] cs = (double[])ct.newArray(len);
         System.arraycopy(this.coeff(), 0, cs, 0, len);
         System.arraycopy(this.exp(), 0, es, 0, len);
         var10000 = new PolySparse$mcD$sp(es, cs, ct);
      }

      return var10000;
   }

   public final double[] expBits(final double x, final Semiring ring) {
      return this.expBits$mcD$sp(x, ring);
   }

   public final double[] expBits$mcD$sp(final double x, final Semiring ring) {
      double[] bits = (double[])this.ct().newArray(package$.MODULE$.max((int)2, (int)(32 - Integer.numberOfLeadingZeros(this.degree()))));
      bits[0] = x;
      if (bits.length > 1) {
         bits[1] = ring.pow$mcD$sp(x, 2);
      }

      for(int index$macro$1 = 2; index$macro$1 < bits.length; ++index$macro$1) {
         double prev = bits[index$macro$1 - 1];
         bits[index$macro$1] = ring.times$mcD$sp(prev, prev);
      }

      return bits;
   }

   public final double fastExp(final double[] bits, final int e, final int i, final double acc, final Semiring ring) {
      return this.fastExp$mcD$sp(bits, e, i, acc, ring);
   }

   public final double fastExp$mcD$sp(final double[] bits, final int e, final int i, final double acc, final Semiring ring) {
      while(e != 0) {
         int lb = Integer.numberOfTrailingZeros(e) + 1;
         int j = i + lb;
         int var10001 = e >>> lb;
         double var10003 = ring.times$mcD$sp(acc, bits[j - 1]);
         ring = ring;
         acc = var10003;
         i = j;
         e = var10001;
         bits = bits;
      }

      return acc;
   }

   public final double fastExp(final double[] bits, final int e, final Semiring ring) {
      return this.fastExp$mcD$sp(bits, e, ring);
   }

   public final double fastExp$mcD$sp(final double[] bits, final int e, final Semiring ring) {
      int lb = Integer.numberOfTrailingZeros(e) + 1;
      return this.fastExp$mcD$sp(bits, e >>> lb, lb, bits[lb - 1], ring);
   }

   public double apply(final double x, final Semiring ring) {
      return this.apply$mcD$sp(x, ring);
   }

   public double apply$mcD$sp(final double x, final Semiring ring) {
      double var10000;
      if (this.isZero()) {
         var10000 = ring.zero$mcD$sp();
      } else if (this.exp().length == 1) {
         var10000 = this.exp()[0] != 0 ? ring.times$mcD$sp(this.coeff()[0], ring.pow$mcD$sp(x, this.exp()[0])) : this.coeff()[0];
      } else {
         double[] bits = this.expBits$mcD$sp(x, ring);
         int e0 = this.exp()[0];
         double c0 = this.coeff()[0];
         double sum = e0 == 0 ? c0 : ring.times$mcD$sp(c0, this.fastExp$mcD$sp(bits, e0, ring));

         for(int index$macro$1 = 1; index$macro$1 < this.exp().length; ++index$macro$1) {
            sum = ring.plus$mcD$sp(sum, ring.times$mcD$sp(this.coeff()[index$macro$1], this.fastExp$mcD$sp(bits, this.exp()[index$macro$1], ring)));
         }

         var10000 = sum;
      }

      return var10000;
   }

   public Polynomial derivative(final Ring ring, final Eq eq) {
      return this.derivative$mcD$sp(ring, eq);
   }

   public Polynomial derivative$mcD$sp(final Ring ring, final Eq eq) {
      Object var10000;
      if (this.exp().length == 0) {
         var10000 = this;
      } else {
         int i0 = this.exp()[0] == 0 ? 1 : 0;
         int[] es = new int[this.exp().length - i0];
         double[] cs = (double[])this.ct().newArray(es.length);
         this.loop$9(i0, 0, es, cs, ring);
         var10000 = PolySparse$.MODULE$.safe$mDc$sp(es, cs, ring, eq, this.ct());
      }

      return (Polynomial)var10000;
   }

   public Polynomial integral(final Field field, final Eq eq) {
      return this.integral$mcD$sp(field, eq);
   }

   public Polynomial integral$mcD$sp(final Field field, final Eq eq) {
      int[] es = new int[this.exp().length];
      double[] cs = (double[])this.ct().newArray(es.length);

      for(int index$macro$1 = 0; index$macro$1 < es.length; ++index$macro$1) {
         int e = this.exp()[index$macro$1] + 1;
         es[index$macro$1] = e;
         cs[index$macro$1] = field.div$mcD$sp(this.coeff()[index$macro$1], field.fromInt$mcD$sp(e));
      }

      return PolySparse$.MODULE$.safe$mDc$sp(es, cs, field, eq, this.ct());
   }

   public Polynomial unary_$minus(final Rng ring) {
      return this.unary_$minus$mcD$sp(ring);
   }

   public Polynomial unary_$minus$mcD$sp(final Rng ring) {
      double[] cs = (double[])this.ct().newArray(this.coeff().length);

      for(int index$macro$1 = 0; index$macro$1 < cs.length; ++index$macro$1) {
         cs[index$macro$1] = ring.negate$mcD$sp(this.coeff()[index$macro$1]);
      }

      return new PolySparse$mcD$sp(this.exp(), cs, this.ct());
   }

   public Polynomial $plus(final Polynomial rhs0, final Semiring ring, final Eq eq) {
      return this.$plus$mcD$sp(rhs0, ring, eq);
   }

   public Polynomial $plus$mcD$sp(final Polynomial rhs0, final Semiring ring, final Eq eq) {
      PolySparse rhs = PolySparse$.MODULE$.apply$mDc$sp(rhs0, ring, eq, this.ct());
      return PolySparse$.MODULE$.spire$math$poly$PolySparse$$addSparse(this, rhs, eq, ring, this.ct());
   }

   public Polynomial $times(final Polynomial rhs0, final Semiring ring, final Eq eq) {
      return this.$times$mcD$sp(rhs0, ring, eq);
   }

   public Polynomial $times$mcD$sp(final Polynomial rhs0, final Semiring ring, final Eq eq) {
      PolySparse rhs = PolySparse$.MODULE$.apply$mDc$sp(rhs0, ring, eq, this.ct());
      return PolySparse$.MODULE$.spire$math$poly$PolySparse$$multiplySparse(this, rhs, ring, eq, this.ct());
   }

   public Polynomial $times$colon(final double k, final Semiring ring, final Eq eq) {
      return this.$times$colon$mcD$sp(k, ring, eq);
   }

   public Polynomial $times$colon$mcD$sp(final double k, final Semiring ring, final Eq eq) {
      Object var10000;
      if (eq.eqv$mcD$sp(k, ring.zero$mcD$sp())) {
         var10000 = PolySparse$.MODULE$.zero$mDc$sp(ring, eq, this.ct());
      } else {
         double[] cs = (double[])this.ct().newArray(this.coeff().length);

         for(int index$macro$1 = 0; index$macro$1 < cs.length; ++index$macro$1) {
            cs[index$macro$1] = ring.times$mcD$sp(k, this.coeff()[index$macro$1]);
         }

         var10000 = new PolySparse$mcD$sp(this.exp(), cs, this.ct());
      }

      return (Polynomial)var10000;
   }

   public double[] copy$default$2() {
      return this.copy$default$2$mcD$sp();
   }

   public double[] copy$default$2$mcD$sp() {
      return this.coeff();
   }

   public boolean specInstance$() {
      return true;
   }

   private final void loop$9(final int i, final int j, final int[] es$8, final double[] cs$8, final Ring ring$2) {
      while(j < es$8.length) {
         int e = this.exp()[i];
         es$8[j] = e - 1;
         cs$8[j] = BoxesRunTime.unboxToDouble(LiteralIntMultiplicativeSemigroupOps$.MODULE$.$times$extension(package.field$.MODULE$.literalIntMultiplicativeSemigroupOps(e), BoxesRunTime.boxToDouble(this.coeff()[i]), ring$2));
         int var10000 = i + 1;
         ++j;
         i = var10000;
      }

      BoxedUnit var8 = BoxedUnit.UNIT;
   }

   public PolySparse$mcD$sp(final int[] exp, final double[] coeff$mcD$sp, final ClassTag ct) {
      super(exp, (Object)null, ct);
      this.coeff$mcD$sp = coeff$mcD$sp;
   }
}
