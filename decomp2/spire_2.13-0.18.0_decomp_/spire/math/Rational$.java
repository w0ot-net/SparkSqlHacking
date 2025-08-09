package spire.math;

import algebra.ring.Field;
import java.io.Serializable;
import scala.MatchError;
import scala.Option;
import scala.collection.LinearSeqOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.util.matching.Regex;
import spire.macros.ArithmeticOverflowException;

public final class Rational$ implements RationalInstances, Serializable {
   public static final Rational$ MODULE$ = new Rational$();
   private static final Regex RationalString;
   private static final Regex IntegerString;
   private static final Rational zero;
   private static final Rational one;
   private static final BigInt Two31m1;
   private static final BigInt Two31m0;
   private static final BigInt Two63m1;
   private static final BigInt Two63m0;
   private static Field RationalAlgebra;
   private static NumberTag RationalTag;

   static {
      RationalInstances.$init$(MODULE$);
      RationalString = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^(-?\\d+)/(-?\\d+)$"));
      IntegerString = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^(-?\\d+)$"));
      zero = MODULE$.spire$math$Rational$$longRational(0L, 1L);
      one = MODULE$.spire$math$Rational$$longRational(1L, 1L);
      Two31m1 = scala.package..MODULE$.BigInt().apply(Integer.MAX_VALUE);
      Two31m0 = scala.package..MODULE$.BigInt().apply(Integer.MIN_VALUE).unary_$minus();
      Two63m1 = scala.package..MODULE$.BigInt().apply(Long.MAX_VALUE);
      Two63m0 = scala.package..MODULE$.BigInt().apply(Long.MIN_VALUE).unary_$minus();
   }

   public final Field RationalAlgebra() {
      return RationalAlgebra;
   }

   public final NumberTag RationalTag() {
      return RationalTag;
   }

   public final void spire$math$RationalInstances$_setter_$RationalAlgebra_$eq(final Field x$1) {
      RationalAlgebra = x$1;
   }

   public final void spire$math$RationalInstances$_setter_$RationalTag_$eq(final NumberTag x$1) {
      RationalTag = x$1;
   }

   private Regex RationalString() {
      return RationalString;
   }

   private Regex IntegerString() {
      return IntegerString;
   }

   public Rational zero() {
      return zero;
   }

   public Rational one() {
      return one;
   }

   public BigInt Two31m1() {
      return Two31m1;
   }

   public BigInt Two31m0() {
      return Two31m0;
   }

   public BigInt Two63m1() {
      return Two63m1;
   }

   public BigInt Two63m0() {
      return Two63m0;
   }

   public double toDouble(final SafeLong n, final SafeLong d) {
      int var3 = n.signum();
      double var10000;
      switch (var3) {
         case -1:
            var10000 = -this.toDouble(n.unary_$minus(), d);
            break;
         case 0:
            var10000 = (double)0.0F;
            break;
         case 1:
            int sharedLength = Math.min(n.bitLength(), d.bitLength());
            int dLowerLength = d.bitLength() - sharedLength;
            SafeLong nShared = n.$greater$greater(n.bitLength() - sharedLength);
            SafeLong dShared = d.$greater$greater(dLowerLength);
            int addBit = !nShared.$less(dShared) && (!BoxesRunTime.equalsNumNum(nShared, dShared) || d.toBigInteger().getLowestSetBit() >= dLowerLength) ? 0 : 1;
            int e = d.bitLength() - n.bitLength() + addBit;
            SafeLong ln = n.$less$less(53 + e);
            long lm = ln.$div(d).toLong();
            long m = (lm >> 1) + (lm & 1L) & 4503599627370495L;
            long bits = m | 1023L - (long)e << 52;
            var10000 = Double.longBitsToDouble(bits);
            break;
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(var3));
      }

      return var10000;
   }

   public Rational apply(final BigInt n, final BigInt d) {
      return this.apply(SafeLong$.MODULE$.apply(n), SafeLong$.MODULE$.apply(d));
   }

   public Rational apply(final long n, final long d) {
      if (d == 0L) {
         throw new IllegalArgumentException("0 denominator");
      } else {
         return d > 0L ? this.build0$1(n, d) : (n != Long.MIN_VALUE && d != Long.MIN_VALUE ? this.build0$1(-n, -d) : this.apply(scala.package..MODULE$.BigInt().apply(n).unary_$minus(), scala.package..MODULE$.BigInt().apply(d).unary_$minus()));
      }
   }

   public Rational buildWithDiv(final long num, final long ngcd, final long rd, final long lden) {
      long n = num / ngcd;
      long d = rd / ngcd;

      Rational var10000;
      try {
         var10000 = this.checked$attempt$macro$1$2(n, lden, d);
      } catch (ArithmeticException var13) {
         var10000 = this.apply(SafeLong$.MODULE$.apply(n), SafeLong$.MODULE$.apply(lden).$times(d));
      }

      return var10000;
   }

   public Rational apply(final SafeLong n, final SafeLong d) {
      while(!d.isZero()) {
         Object var10000;
         if (n.isValidLong() && d.isValidLong()) {
            var10000 = this.apply(n.toLong(), d.toLong());
         } else {
            if (d.signum() < 0) {
               SafeLong var17 = n.unary_$minus();
               d = d.unary_$minus();
               n = var17;
               continue;
            }

            SafeLong g = n.gcd(d);
            SafeLong var7 = n.$div(g);
            Object var4;
            if (var7 instanceof SafeLongLong) {
               SafeLongLong var8 = (SafeLongLong)var7;
               long x = var8.x();
               SafeLong var11 = d.$div(g);
               Object var5;
               if (var11 instanceof SafeLongLong) {
                  SafeLongLong var12 = (SafeLongLong)var11;
                  long y = var12.x();
                  var5 = this.spire$math$Rational$$longRational(x, y);
               } else {
                  if (!(var11 instanceof SafeLongBigInteger)) {
                     throw new MatchError(var11);
                  }

                  SafeLongBigInteger var15 = (SafeLongBigInteger)var11;
                  var5 = this.spire$math$Rational$$bigRational(SafeLong$.MODULE$.apply(x), var15);
               }

               var4 = var5;
            } else {
               if (!(var7 instanceof SafeLongBigInteger)) {
                  throw new MatchError(var7);
               }

               SafeLongBigInteger var16 = (SafeLongBigInteger)var7;
               var4 = this.spire$math$Rational$$bigRational(var16, d.$div(g));
            }

            var10000 = var4;
         }

         return (Rational)var10000;
      }

      throw new IllegalArgumentException("0 denominator");
   }

   public Rational apply(final int x) {
      return (Rational)(x == 0 ? this.zero() : this.spire$math$Rational$$longRational((long)x, 1L));
   }

   public Rational apply(final long x) {
      return (Rational)(x == 0L ? this.zero() : this.spire$math$Rational$$longRational(x, 1L));
   }

   public Rational apply(final BigInt x) {
      return this.apply(SafeLong$.MODULE$.apply(x), SafeLong$.MODULE$.one());
   }

   public Rational apply(final float x) {
      return this.apply((double)x);
   }

   public Rational apply(final double x) {
      Rational var10000;
      if (x == (double)0.0F) {
         var10000 = this.zero();
      } else {
         long bits = Double.doubleToLongBits(x);
         long value = bits >> 63 < 0L ? -(bits & 4503599627370495L | 4503599627370496L) : bits & 4503599627370495L | 4503599627370496L;
         int exp = (int)(bits >> 52 & 2047L) - 1075;
         var10000 = exp > 10 ? this.apply(SafeLong$.MODULE$.apply(value).$less$less(exp), SafeLong$.MODULE$.one()) : (exp >= 0 ? this.apply(value << exp, 1L) : (exp >= -52 && (~(-1L << -exp) & value) == 0L ? this.apply(value >> -exp, 1L) : this.apply(SafeLong$.MODULE$.apply(value), SafeLong$.MODULE$.one().$less$less(-exp))));
      }

      return var10000;
   }

   public Rational apply(final BigDecimal x) {
      Rational var10000;
      if (x.ulp().$greater$eq(scala.math.BigDecimal..MODULE$.int2bigDecimal(1))) {
         var10000 = this.apply(x.toBigInt(), scala.math.BigInt..MODULE$.int2bigInt(1));
      } else {
         BigInt n = x.$div(x.ulp()).toBigInt();
         BigInt d = scala.package..MODULE$.BigDecimal().apply((double)1.0F).$div(x.ulp()).toBigInt();
         var10000 = this.apply(n, d);
      }

      return var10000;
   }

   public Rational apply(final String r) {
      String var3 = r;
      Rational var2;
      if (r != null) {
         Option var4 = this.RationalString().unapplySeq(r);
         if (!var4.isEmpty() && var4.get() != null && ((List)var4.get()).lengthCompare(2) == 0) {
            String n = (String)((LinearSeqOps)var4.get()).apply(0);
            String d = (String)((LinearSeqOps)var4.get()).apply(1);
            var2 = this.apply(SafeLong$.MODULE$.apply(n), SafeLong$.MODULE$.apply(d));
            return var2;
         }
      }

      if (r != null) {
         Option var7 = this.IntegerString().unapplySeq(r);
         if (!var7.isEmpty() && var7.get() != null && ((List)var7.get()).lengthCompare(1) == 0) {
            String n = (String)((LinearSeqOps)var7.get()).apply(0);
            var2 = this.apply(SafeLong$.MODULE$.apply(n));
            return var2;
         }
      }

      Rational var10000;
      try {
         var10000 = this.apply(scala.package..MODULE$.BigDecimal().apply(var3));
      } catch (NumberFormatException var10) {
         throw new NumberFormatException((new StringBuilder(18)).append("For input string: ").append(r).toString());
      }

      var2 = var10000;
      return var2;
   }

   public Rational apply(final SafeLong n) {
      Object var2;
      if (n instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)n;
         long x = var4.x();
         var2 = x == 0L ? this.zero() : this.spire$math$Rational$$longRational(x, 1L);
      } else {
         if (!(n instanceof SafeLongBigInteger)) {
            throw new MatchError(n);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)n;
         var2 = this.spire$math$Rational$$bigRational(var7, SafeLong$.MODULE$.one());
      }

      return (Rational)var2;
   }

   public Rational apply(final Number x) {
      Rational var2;
      if (x instanceof RationalNumber) {
         RationalNumber var4 = (RationalNumber)x;
         Rational n = var4.n();
         var2 = n;
      } else if (x instanceof IntNumber) {
         IntNumber var6 = (IntNumber)x;
         SafeLong n = var6.n();
         var2 = this.apply(n);
      } else if (x instanceof FloatNumber) {
         FloatNumber var8 = (FloatNumber)x;
         double n = var8.n();
         var2 = this.apply(n);
      } else {
         if (!(x instanceof DecimalNumber)) {
            throw new MatchError(x);
         }

         DecimalNumber var11 = (DecimalNumber)x;
         BigDecimal n = var11.n();
         var2 = this.apply(n);
      }

      return var2;
   }

   public Rational.LongRational spire$math$Rational$$longRational(final long n, final long d) {
      return new Rational.LongRational(n, d);
   }

   public Rational.BigRational spire$math$Rational$$bigRational(final SafeLong n, final SafeLong d) {
      return new Rational.BigRational(n, d.isOne() ? SafeLong$.MODULE$.one() : d);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Rational$.class);
   }

   private final Rational build0$1(final long n, final long d) {
      Object var10000;
      if (n == 0L) {
         var10000 = this.zero();
      } else {
         long divisor = package$.MODULE$.gcd(n, d);
         var10000 = divisor == 1L ? this.spire$math$Rational$$longRational(n, d) : this.spire$math$Rational$$longRational(n / divisor, d / divisor);
      }

      return (Rational)var10000;
   }

   private static final Rational checked$fallback$macro$2$2() {
      throw new ArithmeticOverflowException();
   }

   private final Rational checked$attempt$macro$1$2(final long n$1, final long lden$1, final long d$1) {
      long z$macro$3 = lden$1 * d$1;
      return lden$1 != 0L && (d$1 != z$macro$3 / lden$1 || lden$1 == -1L && d$1 == Long.MIN_VALUE) ? checked$fallback$macro$2$2() : this.apply(n$1, z$macro$3);
   }

   private Rational$() {
   }
}
