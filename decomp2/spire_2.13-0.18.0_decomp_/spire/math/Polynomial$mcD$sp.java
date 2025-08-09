package spire.math;

import algebra.ring.Field;
import algebra.ring.Rig;
import algebra.ring.Ring;
import algebra.ring.Rng;
import algebra.ring.Semiring;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.Map.;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.Nothing;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction2;
import spire.math.poly.Term;
import spire.math.poly.Term$mcD$sp;

public interface Polynomial$mcD$sp extends Polynomial {
   // $FF: synthetic method
   static void foreachNonZero$(final Polynomial$mcD$sp $this, final Function2 f, final Semiring ring, final Eq eq) {
      $this.foreachNonZero(f, ring, eq);
   }

   default void foreachNonZero(final Function2 f, final Semiring ring, final Eq eq) {
      this.foreachNonZero$mcD$sp(f, ring, eq);
   }

   // $FF: synthetic method
   static void foreachNonZero$mcD$sp$(final Polynomial$mcD$sp $this, final Function2 f, final Semiring ring, final Eq eq) {
      $this.foreachNonZero$mcD$sp(f, ring, eq);
   }

   default void foreachNonZero$mcD$sp(final Function2 f, final Semiring ring, final Eq eq) {
      this.foreach$mcD$sp((e, c) -> $anonfun$foreachNonZero$2(eq, ring, f, BoxesRunTime.unboxToInt(e), BoxesRunTime.unboxToDouble(c)));
   }

   // $FF: synthetic method
   static List terms$(final Polynomial$mcD$sp $this, final Semiring ring, final Eq eq) {
      return $this.terms(ring, eq);
   }

   default List terms(final Semiring ring, final Eq eq) {
      return this.terms$mcD$sp(ring, eq);
   }

   // $FF: synthetic method
   static List terms$mcD$sp$(final Polynomial$mcD$sp $this, final Semiring ring, final Eq eq) {
      return $this.terms$mcD$sp(ring, eq);
   }

   default List terms$mcD$sp(final Semiring ring, final Eq eq) {
      ListBuffer lb = new ListBuffer();
      this.foreachNonZero$mcD$sp((e, c) -> $anonfun$terms$2(lb, BoxesRunTime.unboxToInt(e), BoxesRunTime.unboxToDouble(c)), ring, eq);
      return lb.result();
   }

   // $FF: synthetic method
   static Map data$(final Polynomial$mcD$sp $this, final Semiring ring, final Eq eq) {
      return $this.data(ring, eq);
   }

   default Map data(final Semiring ring, final Eq eq) {
      return this.data$mcD$sp(ring, eq);
   }

   // $FF: synthetic method
   static Map data$mcD$sp$(final Polynomial$mcD$sp $this, final Semiring ring, final Eq eq) {
      return $this.data$mcD$sp(ring, eq);
   }

   default Map data$mcD$sp(final Semiring ring, final Eq eq) {
      Builder bldr = .MODULE$.newBuilder();
      this.foreachNonZero$mcD$sp((e, c) -> $anonfun$data$2(bldr, BoxesRunTime.unboxToInt(e), BoxesRunTime.unboxToDouble(c)), ring, eq);
      return ((IterableOnceOps)bldr.result()).toMap(scala..less.colon.less..MODULE$.refl());
   }

   // $FF: synthetic method
   static Term maxTerm$(final Polynomial$mcD$sp $this, final Semiring ring) {
      return $this.maxTerm(ring);
   }

   default Term maxTerm(final Semiring ring) {
      return this.maxTerm$mcD$sp(ring);
   }

   // $FF: synthetic method
   static Term maxTerm$mcD$sp$(final Polynomial$mcD$sp $this, final Semiring ring) {
      return $this.maxTerm$mcD$sp(ring);
   }

   default Term maxTerm$mcD$sp(final Semiring ring) {
      return new Term$mcD$sp(this.maxOrderTermCoeff$mcD$sp(ring), this.degree());
   }

   // $FF: synthetic method
   static Term minTerm$(final Polynomial$mcD$sp $this, final Semiring ring, final Eq eq) {
      return $this.minTerm(ring, eq);
   }

   default Term minTerm(final Semiring ring, final Eq eq) {
      return this.minTerm$mcD$sp(ring, eq);
   }

   // $FF: synthetic method
   static Term minTerm$mcD$sp$(final Polynomial$mcD$sp $this, final Semiring ring, final Eq eq) {
      return $this.minTerm$mcD$sp(ring, eq);
   }

   default Term minTerm$mcD$sp(final Semiring ring, final Eq eq) {
      Object var3 = new Object();

      Object var10000;
      try {
         this.foreachNonZero$mcD$sp((n, c) -> $anonfun$minTerm$2(var3, BoxesRunTime.unboxToInt(n), BoxesRunTime.unboxToDouble(c)), ring, eq);
         var10000 = new Term$mcD$sp(ring.zero$mcD$sp(), 0);
      } catch (NonLocalReturnControl var5) {
         if (var5.key() != var3) {
            throw var5;
         }

         var10000 = (Term)var5.value();
      }

      return (Term)var10000;
   }

   // $FF: synthetic method
   static Object evalWith$(final Polynomial$mcD$sp $this, final Object x, final Function1 f, final Semiring evidence$56, final Eq evidence$57, final ClassTag evidence$58) {
      return $this.evalWith(x, f, evidence$56, evidence$57, evidence$58);
   }

   default Object evalWith(final Object x, final Function1 f, final Semiring evidence$56, final Eq evidence$57, final ClassTag evidence$58) {
      return this.evalWith$mcD$sp(x, f, evidence$56, evidence$57, evidence$58);
   }

   // $FF: synthetic method
   static Object evalWith$mcD$sp$(final Polynomial$mcD$sp $this, final Object x, final Function1 f, final Semiring evidence$56, final Eq evidence$57, final ClassTag evidence$58) {
      return $this.evalWith$mcD$sp(x, f, evidence$56, evidence$57, evidence$58);
   }

   default Object evalWith$mcD$sp(final Object x, final Function1 f, final Semiring evidence$56, final Eq evidence$57, final ClassTag evidence$58) {
      return this.map$mcD$sp(f, evidence$56, evidence$57, evidence$58).apply(x, evidence$56);
   }

   // $FF: synthetic method
   static Polynomial compose$(final Polynomial$mcD$sp $this, final Polynomial y, final Rig ring, final Eq eq) {
      return $this.compose(y, ring, eq);
   }

   default Polynomial compose(final Polynomial y, final Rig ring, final Eq eq) {
      return this.compose$mcD$sp(y, ring, eq);
   }

   // $FF: synthetic method
   static Polynomial compose$mcD$sp$(final Polynomial$mcD$sp $this, final Polynomial y, final Rig ring, final Eq eq) {
      return $this.compose$mcD$sp(y, ring, eq);
   }

   default Polynomial compose$mcD$sp(final Polynomial y, final Rig ring, final Eq eq) {
      ObjectRef polynomial = ObjectRef.create(Polynomial$.MODULE$.zero$mDc$sp(eq, ring, this.ct()));
      this.foreachNonZero$mcD$sp((JFunction2.mcVID.sp)(e, c) -> {
         Polynomial z = y.pow$mcD$sp(e, ring, eq).$colon$times$mcD$sp(c, ring, eq);
         polynomial.elem = ((Polynomial)polynomial.elem).$plus$mcD$sp(z, ring, eq);
      }, ring, eq);
      return (Polynomial)polynomial.elem;
   }

   // $FF: synthetic method
   static Polynomial shift$(final Polynomial$mcD$sp $this, final double h, final Ring ring, final Eq eq) {
      return $this.shift(h, ring, eq);
   }

   default Polynomial shift(final double h, final Ring ring, final Eq eq) {
      return this.shift$mcD$sp(h, ring, eq);
   }

   // $FF: synthetic method
   static Polynomial shift$mcD$sp$(final Polynomial$mcD$sp $this, final double h, final Ring ring, final Eq eq) {
      return $this.shift$mcD$sp(h, ring, eq);
   }

   default Polynomial shift$mcD$sp(final double h, final Ring ring, final Eq eq) {
      double[] coeffs = (double[])this.coeffsArray$mcD$sp(ring).clone();
      this.foreachNonZero$mcD$sp((JFunction2.mcVID.sp)(deg, c) -> {
         int i = 1;
         int d = deg - 1;
         SafeLong m = SafeLong$.MODULE$.apply(1L);

         for(double k = c; d >= 0; ++i) {
            m = m.$times((long)(d + 1)).$div((long)i);
            k = ring.times$mcD$sp(k, h);
            coeffs[d] = ring.plus$mcD$sp(coeffs[d], ring.times$mcD$sp(this.fromSafeLong$2(m, ring), k));
            --d;
         }

      }, ring, eq);
      return Polynomial$.MODULE$.dense$mDc$sp(coeffs, ring, eq, this.ct());
   }

   // $FF: synthetic method
   static Polynomial monic$(final Polynomial$mcD$sp $this, final Field f, final Eq eq) {
      return $this.monic(f, eq);
   }

   default Polynomial monic(final Field f, final Eq eq) {
      return this.monic$mcD$sp(f, eq);
   }

   // $FF: synthetic method
   static Polynomial monic$mcD$sp$(final Polynomial$mcD$sp $this, final Field f, final Eq eq) {
      return $this.monic$mcD$sp(f, eq);
   }

   default Polynomial monic$mcD$sp(final Field f, final Eq eq) {
      return this.$colon$div$mcD$sp(this.maxOrderTermCoeff$mcD$sp(f), f, eq);
   }

   // $FF: synthetic method
   static int signVariations$(final Polynomial$mcD$sp $this, final Semiring ring, final Order order, final Signed signed) {
      return $this.signVariations(ring, order, signed);
   }

   default int signVariations(final Semiring ring, final Order order, final Signed signed) {
      return this.signVariations$mcD$sp(ring, order, signed);
   }

   // $FF: synthetic method
   static int signVariations$mcD$sp$(final Polynomial$mcD$sp $this, final Semiring ring, final Order order, final Signed signed) {
      return $this.signVariations$mcD$sp(ring, order, signed);
   }

   default int signVariations$mcD$sp(final Semiring ring, final Order order, final Signed signed) {
      ObjectRef prevSign = ObjectRef.create(algebra.ring.Signed.Zero..MODULE$);
      IntRef variations = IntRef.create(0);
      this.foreachNonZero$mcD$sp((JFunction2.mcVID.sp)(x$5, c) -> {
         Signed.Sign sign = signed.sign$mcD$sp(c);
         if (!algebra.ring.Signed.Zero..MODULE$.equals((Signed.Sign)prevSign.elem)) {
            label15: {
               Signed.Sign var7 = (Signed.Sign)prevSign.elem;
               if (sign == null) {
                  if (var7 == null) {
                     break label15;
                  }
               } else if (sign.equals(var7)) {
                  break label15;
               }

               ++variations.elem;
            }
         }

         prevSign.elem = sign;
      }, ring, order);
      return variations.elem;
   }

   // $FF: synthetic method
   static Polynomial removeZeroRoots$(final Polynomial$mcD$sp $this, final Semiring ring, final Eq eq) {
      return $this.removeZeroRoots(ring, eq);
   }

   default Polynomial removeZeroRoots(final Semiring ring, final Eq eq) {
      return this.removeZeroRoots$mcD$sp(ring, eq);
   }

   // $FF: synthetic method
   static Polynomial removeZeroRoots$mcD$sp$(final Polynomial$mcD$sp $this, final Semiring ring, final Eq eq) {
      return $this.removeZeroRoots$mcD$sp(ring, eq);
   }

   default Polynomial removeZeroRoots$mcD$sp(final Semiring ring, final Eq eq) {
      Term var5 = this.minTerm$mcD$sp(ring, eq);
      if (var5 != null) {
         int k = var5.exp();
         return this.mapTerms$mcD$sp((x0$1) -> {
            if (x0$1 != null) {
               double c = x0$1.coeff$mcD$sp();
               int n = x0$1.exp();
               Term$mcD$sp var2 = new Term$mcD$sp(c, n - k);
               return var2;
            } else {
               throw new MatchError(x0$1);
            }
         }, ring, eq, this.ct());
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   static Polynomial map$(final Polynomial$mcD$sp $this, final Function1 f, final Semiring evidence$59, final Eq evidence$60, final ClassTag evidence$61) {
      return $this.map(f, evidence$59, evidence$60, evidence$61);
   }

   default Polynomial map(final Function1 f, final Semiring evidence$59, final Eq evidence$60, final ClassTag evidence$61) {
      return this.map$mcD$sp(f, evidence$59, evidence$60, evidence$61);
   }

   // $FF: synthetic method
   static Polynomial map$mcD$sp$(final Polynomial$mcD$sp $this, final Function1 f, final Semiring evidence$59, final Eq evidence$60, final ClassTag evidence$61) {
      return $this.map$mcD$sp(f, evidence$59, evidence$60, evidence$61);
   }

   default Polynomial map$mcD$sp(final Function1 f, final Semiring evidence$59, final Eq evidence$60, final ClassTag evidence$61) {
      return this.mapTerms$mcD$sp((x0$1) -> {
         if (x0$1 != null) {
            double c = x0$1.coeff$mcD$sp();
            int n = x0$1.exp();
            Term var2 = new Term(f.apply(BoxesRunTime.boxToDouble(c)), n);
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      }, evidence$59, evidence$60, evidence$61);
   }

   // $FF: synthetic method
   static Polynomial mapTerms$(final Polynomial$mcD$sp $this, final Function1 f, final Semiring evidence$62, final Eq evidence$63, final ClassTag evidence$64) {
      return $this.mapTerms(f, evidence$62, evidence$63, evidence$64);
   }

   default Polynomial mapTerms(final Function1 f, final Semiring evidence$62, final Eq evidence$63, final ClassTag evidence$64) {
      return this.mapTerms$mcD$sp(f, evidence$62, evidence$63, evidence$64);
   }

   // $FF: synthetic method
   static Polynomial mapTerms$mcD$sp$(final Polynomial$mcD$sp $this, final Function1 f, final Semiring evidence$62, final Eq evidence$63, final ClassTag evidence$64) {
      return $this.mapTerms$mcD$sp(f, evidence$62, evidence$63, evidence$64);
   }

   default Polynomial mapTerms$mcD$sp(final Function1 f, final Semiring evidence$62, final Eq evidence$63, final ClassTag evidence$64) {
      return Polynomial$.MODULE$.apply((IterableOnce)this.termsIterator().map(f), evidence$62, evidence$63, evidence$64);
   }

   // $FF: synthetic method
   static Polynomial flip$(final Polynomial$mcD$sp $this, final Rng ring, final Eq eq) {
      return $this.flip(ring, eq);
   }

   default Polynomial flip(final Rng ring, final Eq eq) {
      return this.flip$mcD$sp(ring, eq);
   }

   // $FF: synthetic method
   static Polynomial flip$mcD$sp$(final Polynomial$mcD$sp $this, final Rng ring, final Eq eq) {
      return $this.flip$mcD$sp(ring, eq);
   }

   default Polynomial flip$mcD$sp(final Rng ring, final Eq eq) {
      return this.mapTerms$mcD$sp((x0$1) -> {
         if (x0$1 != null) {
            double coeff = x0$1.coeff$mcD$sp();
            int exp = x0$1.exp();
            Object var2 = exp % 2 == 0 ? x0$1 : new Term$mcD$sp(ring.negate$mcD$sp(coeff), exp);
            return (Term)var2;
         } else {
            throw new MatchError(x0$1);
         }
      }, ring, eq, this.ct());
   }

   // $FF: synthetic method
   static Polynomial reciprocal$(final Polynomial$mcD$sp $this, final Semiring ring, final Eq eq) {
      return $this.reciprocal(ring, eq);
   }

   default Polynomial reciprocal(final Semiring ring, final Eq eq) {
      return this.reciprocal$mcD$sp(ring, eq);
   }

   // $FF: synthetic method
   static Polynomial reciprocal$mcD$sp$(final Polynomial$mcD$sp $this, final Semiring ring, final Eq eq) {
      return $this.reciprocal$mcD$sp(ring, eq);
   }

   default Polynomial reciprocal$mcD$sp(final Semiring ring, final Eq eq) {
      int d = this.degree();
      return this.mapTerms$mcD$sp((x0$1) -> {
         if (x0$1 != null) {
            double coeff = x0$1.coeff$mcD$sp();
            int exp = x0$1.exp();
            Term$mcD$sp var2 = new Term$mcD$sp(coeff, d - exp);
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      }, ring, eq, this.ct());
   }

   // $FF: synthetic method
   static Polynomial $minus$(final Polynomial$mcD$sp $this, final Polynomial rhs, final Rng ring, final Eq eq) {
      return $this.$minus(rhs, ring, eq);
   }

   default Polynomial $minus(final Polynomial rhs, final Rng ring, final Eq eq) {
      return this.$minus$mcD$sp(rhs, ring, eq);
   }

   // $FF: synthetic method
   static Polynomial $minus$mcD$sp$(final Polynomial$mcD$sp $this, final Polynomial rhs, final Rng ring, final Eq eq) {
      return $this.$minus$mcD$sp(rhs, ring, eq);
   }

   default Polynomial $minus$mcD$sp(final Polynomial rhs, final Rng ring, final Eq eq) {
      return this.$plus$mcD$sp(rhs.unary_$minus$mcD$sp(ring), ring, eq);
   }

   // $FF: synthetic method
   static Polynomial $times$times$(final Polynomial$mcD$sp $this, final int k, final Rig ring, final Eq eq) {
      return $this.$times$times(k, ring, eq);
   }

   default Polynomial $times$times(final int k, final Rig ring, final Eq eq) {
      return this.$times$times$mcD$sp(k, ring, eq);
   }

   // $FF: synthetic method
   static Polynomial $times$times$mcD$sp$(final Polynomial$mcD$sp $this, final int k, final Rig ring, final Eq eq) {
      return $this.$times$times$mcD$sp(k, ring, eq);
   }

   default Polynomial $times$times$mcD$sp(final int k, final Rig ring, final Eq eq) {
      return this.pow$mcD$sp(k, ring, eq);
   }

   // $FF: synthetic method
   static Polynomial pow$(final Polynomial$mcD$sp $this, final int k, final Rig ring, final Eq eq) {
      return $this.pow(k, ring, eq);
   }

   default Polynomial pow(final int k, final Rig ring, final Eq eq) {
      return this.pow$mcD$sp(k, ring, eq);
   }

   // $FF: synthetic method
   static Polynomial pow$mcD$sp$(final Polynomial$mcD$sp $this, final int k, final Rig ring, final Eq eq) {
      return $this.pow$mcD$sp(k, ring, eq);
   }

   default Polynomial pow$mcD$sp(final int k, final Rig ring, final Eq eq) {
      if (k < 0) {
         throw new IllegalArgumentException("negative exponent");
      } else {
         return (Polynomial)(k == 0 ? Polynomial$.MODULE$.one$mDc$sp(eq, ring, this.ct()) : (k == 1 ? this : this.loop$7(this, k - 1, this, ring, eq)));
      }
   }

   // $FF: synthetic method
   static Polynomial $colon$times$(final Polynomial$mcD$sp $this, final double k, final Semiring ring, final Eq eq) {
      return $this.$colon$times(k, ring, eq);
   }

   default Polynomial $colon$times(final double k, final Semiring ring, final Eq eq) {
      return this.$colon$times$mcD$sp(k, ring, eq);
   }

   // $FF: synthetic method
   static Polynomial $colon$times$mcD$sp$(final Polynomial$mcD$sp $this, final double k, final Semiring ring, final Eq eq) {
      return $this.$colon$times$mcD$sp(k, ring, eq);
   }

   default Polynomial $colon$times$mcD$sp(final double k, final Semiring ring, final Eq eq) {
      return this.$times$colon$mcD$sp(k, ring, eq);
   }

   // $FF: synthetic method
   static Polynomial $colon$div$(final Polynomial$mcD$sp $this, final double k, final Field field, final Eq eq) {
      return $this.$colon$div(k, field, eq);
   }

   default Polynomial $colon$div(final double k, final Field field, final Eq eq) {
      return this.$colon$div$mcD$sp(k, field, eq);
   }

   // $FF: synthetic method
   static Polynomial $colon$div$mcD$sp$(final Polynomial$mcD$sp $this, final double k, final Field field, final Eq eq) {
      return $this.$colon$div$mcD$sp(k, field, eq);
   }

   default Polynomial $colon$div$mcD$sp(final double k, final Field field, final Eq eq) {
      return this.$colon$times$mcD$sp(field.reciprocal$mcD$sp(k), field, eq);
   }

   // $FF: synthetic method
   static Object $anonfun$foreachNonZero$2(final Eq eq$4, final Semiring ring$6, final Function2 f$3, final int e, final double c) {
      return eq$4.neqv$mcD$sp(c, ring$6.zero$mcD$sp()) ? f$3.apply(BoxesRunTime.boxToInteger(e), BoxesRunTime.boxToDouble(c)) : BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static ListBuffer $anonfun$terms$2(final ListBuffer lb$2, final int e, final double c) {
      return (ListBuffer)lb$2.$plus$eq(new Term$mcD$sp(c, e));
   }

   // $FF: synthetic method
   static Builder $anonfun$data$2(final Builder bldr$3, final int e, final double c) {
      return (Builder)bldr$3.$plus$eq(new Tuple2.mcID.sp(e, c));
   }

   // $FF: synthetic method
   static Nothing $anonfun$minTerm$2(final Object nonLocalReturnKey1$2, final int n, final double c) {
      throw new NonLocalReturnControl(nonLocalReturnKey1$2, new Term$mcD$sp(c, n));
   }

   private double loop$6(final double k, final SafeLong y, final double acc, final Ring ring$8, final long mask$2, final double d$3) {
      while(!y.isValidInt()) {
         SafeLong z = y.$greater$greater(30);
         double r = ring$8.fromInt$mcD$sp(y.$amp(mask$2).toInt());
         double var10000 = ring$8.times$mcD$sp(d$3, k);
         acc = ring$8.plus$mcD$sp(ring$8.times$mcD$sp(k, r), acc);
         y = z;
         k = var10000;
      }

      return ring$8.plus$mcD$sp(ring$8.times$mcD$sp(k, ring$8.fromInt$mcD$sp(y.toInt())), acc);
   }

   private double fromSafeLong$2(final SafeLong x, final Ring ring$8) {
      double var10000;
      if (x.isValidInt()) {
         var10000 = ring$8.fromInt$mcD$sp(x.toInt());
      } else {
         double d = ring$8.fromInt$mcD$sp(1073741824);
         long mask = 1073741823L;
         var10000 = this.loop$6(ring$8.one$mcD$sp(), x, ring$8.zero$mcD$sp(), ring$8, mask, d);
      }

      return var10000;
   }

   private Polynomial loop$7(final Polynomial b, final int k, final Polynomial extra, final Rig ring$10, final Eq eq$6) {
      while(k != 1) {
         Polynomial var10000 = b.$times$mcD$sp(b, ring$10, eq$6);
         int var10001 = k >>> 1;
         extra = (k & 1) == 1 ? b.$times$mcD$sp(extra, ring$10, eq$6) : extra;
         k = var10001;
         b = var10000;
      }

      return b.$times$mcD$sp(extra, ring$10, eq$6);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
