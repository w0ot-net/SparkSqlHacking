package spire.math;

import algebra.ring.Field;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import scala.Function1;
import scala.MatchError;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.immutable.Vector;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import spire.math.poly.RootIsolator$;
import spire.math.poly.Roots$;

public final class Algebraic$ implements AlgebraicInstances, Serializable {
   public static final Algebraic$ MODULE$ = new Algebraic$();
   private static final Algebraic Zero;
   private static final Algebraic One;
   private static final double bits2dec;
   private static final BigInteger spire$math$Algebraic$$MaxIntValue;
   private static final BigInteger spire$math$Algebraic$$MinIntValue;
   private static final BigInteger spire$math$Algebraic$$MaxLongValue;
   private static final BigInteger spire$math$Algebraic$$MinLongValue;
   private static Field AlgebraicAlgebra;
   private static NumberTag AlgebraicTag;

   static {
      AlgebraicInstances.$init$(MODULE$);
      Zero = new Algebraic(new Algebraic$Expr$ConstantLong(0L));
      One = new Algebraic(new Algebraic$Expr$ConstantLong(1L));
      bits2dec = package$.MODULE$.log((double)2.0F, 10);
      spire$math$Algebraic$$MaxIntValue = BigInteger.valueOf((long)Integer.MAX_VALUE);
      spire$math$Algebraic$$MinIntValue = BigInteger.valueOf((long)Integer.MIN_VALUE);
      spire$math$Algebraic$$MaxLongValue = BigInteger.valueOf(Long.MAX_VALUE);
      spire$math$Algebraic$$MinLongValue = BigInteger.valueOf(Long.MIN_VALUE);
   }

   public final Field AlgebraicAlgebra() {
      return AlgebraicAlgebra;
   }

   public final NumberTag AlgebraicTag() {
      return AlgebraicTag;
   }

   public final void spire$math$AlgebraicInstances$_setter_$AlgebraicAlgebra_$eq(final Field x$1) {
      AlgebraicAlgebra = x$1;
   }

   public final void spire$math$AlgebraicInstances$_setter_$AlgebraicTag_$eq(final NumberTag x$1) {
      AlgebraicTag = x$1;
   }

   public Algebraic Zero() {
      return Zero;
   }

   public Algebraic One() {
      return One;
   }

   public Algebraic apply(final int n) {
      return new Algebraic(new Algebraic$Expr$ConstantLong((long)n));
   }

   public Algebraic apply(final long n) {
      return new Algebraic(new Algebraic$Expr$ConstantLong(n));
   }

   public Algebraic apply(final float n) {
      return this.apply((double)n);
   }

   public Algebraic apply(final double n) {
      if (Double.isInfinite(n)) {
         throw new IllegalArgumentException("cannot construct inifinite Algebraic");
      } else if (Double.isNaN(n)) {
         throw new IllegalArgumentException("cannot construct Algebraic from NaN");
      } else {
         return new Algebraic(new Algebraic$Expr$ConstantDouble(n));
      }
   }

   public Algebraic apply(final BigInt n) {
      return new Algebraic(new Algebraic$Expr$ConstantBigDecimal(.MODULE$.BigDecimal().apply(n)));
   }

   public Algebraic apply(final BigDecimal n) {
      return new Algebraic(new Algebraic$Expr$ConstantBigDecimal(n));
   }

   public Algebraic apply(final Rational n) {
      return new Algebraic(new Algebraic$Expr$ConstantRational(n));
   }

   public Algebraic root(final Polynomial poly, final int i) {
      if (i < 0) {
         throw new ArithmeticException((new StringBuilder(25)).append("invalid real root index: ").append(i).toString());
      } else {
         Polynomial zpoly = Roots$.MODULE$.removeFractions(poly);
         Vector intervals = Roots$.MODULE$.isolateRoots(zpoly, RootIsolator$.MODULE$.BigIntRootIsolator());
         if (i >= intervals.size()) {
            throw new ArithmeticException((new StringBuilder(43)).append("cannot extract root ").append(i).append(", there are only ").append(intervals.size()).append(" roots").toString());
         } else {
            Interval var6 = (Interval)intervals.apply(i);
            Algebraic var3;
            if (var6 instanceof Point) {
               Point var7 = (Point)var6;
               Rational value = (Rational)var7.value();
               var3 = new Algebraic(new Algebraic$Expr$ConstantRational(value));
            } else {
               if (!(var6 instanceof Bounded)) {
                  throw new RuntimeException("invalid isolated root interval");
               }

               Bounded var9 = (Bounded)var6;
               Rational lb = (Rational)var9.lower();
               Rational ub = (Rational)var9.upper();
               var3 = new Algebraic(new Algebraic$Expr$ConstantRoot(zpoly, i, lb, ub));
            }

            return var3;
         }
      }
   }

   public Vector roots(final Polynomial poly) {
      Polynomial zpoly = Roots$.MODULE$.removeFractions(poly);
      Vector intervals = Roots$.MODULE$.isolateRoots(zpoly, RootIsolator$.MODULE$.BigIntRootIsolator());
      return (Vector)((StrictOptimizedIterableOps)intervals.zipWithIndex()).map((x0$1) -> {
         Algebraic var2;
         if (x0$1 != null) {
            Interval var4 = (Interval)x0$1._1();
            if (var4 instanceof Point) {
               Point var5 = (Point)var4;
               Rational value = (Rational)var5.value();
               var2 = new Algebraic(new Algebraic$Expr$ConstantRational(value));
               return var2;
            }
         }

         if (x0$1 == null) {
            throw new RuntimeException((new StringBuilder(32)).append("invalid isolated root interval: ").append(x0$1).toString());
         } else {
            Interval var7 = (Interval)x0$1._1();
            int i = x0$1._2$mcI$sp();
            if (!(var7 instanceof Bounded)) {
               throw new RuntimeException((new StringBuilder(32)).append("invalid isolated root interval: ").append(x0$1).toString());
            } else {
               Bounded var9 = (Bounded)var7;
               Rational lb = (Rational)var9.lower();
               Rational ub = (Rational)var9.upper();
               var2 = new Algebraic(new Algebraic$Expr$ConstantRoot(zpoly, i, lb, ub));
               return var2;
            }
         }
      });
   }

   public Algebraic unsafeRoot(final Polynomial poly, final int i, final Rational lb, final Rational ub) {
      return new Algebraic(new Algebraic$Expr$ConstantRoot(poly, i, lb, ub));
   }

   public Algebraic apply(final String n) {
      return this.apply(.MODULE$.BigDecimal().apply(new java.math.BigDecimal(n)));
   }

   public final java.math.BigDecimal nrootApprox(final java.math.BigDecimal x, final int n) {
      int k = package$.MODULE$.min((int)n, (int)306);
      int width = (int)(package$.MODULE$.ceil((double)x.unscaledValue().bitLength() * package$.MODULE$.log((double)2.0F) / package$.MODULE$.log((double)10.0F)) - (double)1);
      int safeWidth = width + (x.scale() - width) % k;
      double approx = (new java.math.BigDecimal(x.unscaledValue().abs(), safeWidth)).doubleValue();
      return (new java.math.BigDecimal((double)x.signum() * package$.MODULE$.pow(approx, (double)1.0F / (double)k))).scaleByPowerOfTen(-(x.scale() - safeWidth) / k).round(MathContext.DECIMAL64);
   }

   private final java.math.BigDecimal nroot(final java.math.BigDecimal signedValue, final int k, final Function1 getEps) {
      if (signedValue.compareTo(java.math.BigDecimal.ZERO) == 0) {
         return java.math.BigDecimal.ZERO;
      } else {
         java.math.BigDecimal value = signedValue.abs();
         java.math.BigDecimal n = new java.math.BigDecimal(k);
         java.math.BigDecimal init = this.nrootApprox(value, k);
         java.math.BigDecimal unsignedResult = this.loop$2(init, Integer.MIN_VALUE, java.math.BigDecimal.ZERO, getEps, k, value, n);
         return signedValue.signum() < 0 ? unsignedResult.negate() : unsignedResult;
      }
   }

   private double bits2dec() {
      return bits2dec;
   }

   public final java.math.BigDecimal nroot(final java.math.BigDecimal value, final int n, final MathContext mc) {
      java.math.BigDecimal result = this.nroot(value, n, (Function1)((x) -> BoxesRunTime.boxToInteger($anonfun$nroot$1(mc, x))));
      return result.round(mc);
   }

   public final java.math.BigDecimal nroot(final java.math.BigDecimal value, final int n, final int scale, final RoundingMode roundingMode) {
      return this.nroot(value, n, (Function1)((x$1) -> BoxesRunTime.boxToInteger($anonfun$nroot$2(scale, x$1)))).setScale(scale, roundingMode);
   }

   public java.math.BigDecimal spire$math$Algebraic$$roundExact(final Algebraic exact, final java.math.BigDecimal approx, final int scale, final RoundingMode mode) {
      java.math.BigDecimal var10000;
      if (approx.signum() == 0) {
         boolean var8;
         if (RoundingMode.UP.equals(mode)) {
            var8 = true;
         } else if (RoundingMode.CEILING.equals(mode)) {
            var8 = true;
         } else {
            var8 = false;
         }

         java.math.BigDecimal var6;
         if (var8 && exact.signum() > 0) {
            var6 = new java.math.BigDecimal(BigInteger.ONE, scale);
         } else {
            boolean var7;
            if (RoundingMode.UP.equals(mode)) {
               var7 = true;
            } else if (RoundingMode.FLOOR.equals(mode)) {
               var7 = true;
            } else {
               var7 = false;
            }

            if (var7 && exact.signum() < 0) {
               var6 = new java.math.BigDecimal(BigInteger.ONE.negate(), scale);
            } else {
               var6 = approx.setScale(scale, RoundingMode.DOWN);
            }
         }

         var10000 = var6;
      } else if (approx.signum() > 0) {
         var10000 = this.roundPositive(exact, approx, scale, mode);
      } else {
         RoundingMode adjustedMode;
         if (RoundingMode.CEILING.equals(mode)) {
            adjustedMode = RoundingMode.FLOOR;
         } else if (RoundingMode.FLOOR.equals(mode)) {
            adjustedMode = RoundingMode.CEILING;
         } else {
            adjustedMode = mode;
         }

         var10000 = this.roundPositive(exact.unary_$minus(), approx.abs(), scale, adjustedMode).negate();
      }

      return var10000;
   }

   private java.math.BigDecimal roundPositive(final Algebraic exact, final java.math.BigDecimal approx, final int scale, final RoundingMode mode) {
      while(true) {
         int cutoff = approx.scale() - scale;
         java.math.BigDecimal var10000;
         if (cutoff == 0) {
            var10000 = approx;
         } else if (cutoff < 0) {
            var10000 = approx.setScale(scale, RoundingMode.DOWN);
         } else {
            if (cutoff > 18) {
               java.math.BigDecimal var10001 = approx.setScale(scale + 18, RoundingMode.DOWN);
               mode = mode;
               scale = scale;
               approx = var10001;
               exact = exact;
               continue;
            }

            long unscale = package$.MODULE$.pow(10L, (long)cutoff);
            BigInteger[] arr = approx.unscaledValue().divideAndRemainder(BigInteger.valueOf(unscale));
            BigInteger truncatedUnscaledValue = arr[0];
            BigInteger bigRemainder = arr[1];
            java.math.BigDecimal truncated = new java.math.BigDecimal(truncatedUnscaledValue, scale);
            long remainder = bigRemainder.longValue();
            java.math.BigDecimal rounded;
            if (RoundingMode.UNNECESSARY.equals(mode)) {
               rounded = truncated;
            } else {
               boolean var10;
               if (RoundingMode.HALF_DOWN.equals(mode)) {
                  var10 = true;
               } else if (RoundingMode.HALF_UP.equals(mode)) {
                  var10 = true;
               } else if (RoundingMode.HALF_EVEN.equals(mode)) {
                  var10 = true;
               } else {
                  var10 = false;
               }

               if (var10) {
                  long dangerZoneStart = unscale / 2L - 1L;
                  long dangerZoneStop = dangerZoneStart + 2L;
                  if (remainder >= dangerZoneStart && remainder <= dangerZoneStop) {
                     BigDecimal splitter = .MODULE$.BigDecimal().apply(new java.math.BigDecimal(truncatedUnscaledValue.multiply(BigInteger.TEN).add(BigInteger.valueOf(5L)), scale + 1));
                     int cmp = exact.compare(this.apply(splitter));
                     boolean roundUp;
                     if (RoundingMode.HALF_DOWN.equals(mode)) {
                        roundUp = cmp > 0;
                     } else if (RoundingMode.HALF_UP.equals(mode)) {
                        roundUp = cmp >= 0;
                     } else {
                        if (!RoundingMode.HALF_EVEN.equals(mode)) {
                           throw new MatchError(mode);
                        }

                        roundUp = cmp > 0 || cmp == 0 && truncatedUnscaledValue.testBit(0);
                     }

                     var10000 = roundUp ? truncated.add(epsilon$1(scale)) : truncated;
                  } else {
                     var10000 = remainder < dangerZoneStart ? truncated : truncated.add(epsilon$1(scale));
                  }

                  rounded = var10000;
               } else {
                  boolean var8;
                  if (RoundingMode.CEILING.equals(mode)) {
                     var8 = true;
                  } else if (RoundingMode.UP.equals(mode)) {
                     var8 = true;
                  } else {
                     var8 = false;
                  }

                  if (!var8) {
                     boolean var7;
                     if (RoundingMode.FLOOR.equals(mode)) {
                        var7 = true;
                     } else if (RoundingMode.DOWN.equals(mode)) {
                        var7 = true;
                     } else {
                        var7 = false;
                     }

                     if (!var7) {
                        throw new MatchError(mode);
                     }

                     if (remainder <= 0L) {
                        var10000 = exact.$less(this.apply(.MODULE$.BigDecimal().apply(truncated))) ? truncated.subtract(epsilon$1(scale)) : truncated;
                     } else if (remainder >= unscale - 1L) {
                        java.math.BigDecimal roundedUp = truncated.add(epsilon$1(scale));
                        var10000 = exact.$greater$eq(this.apply(.MODULE$.BigDecimal().apply(roundedUp))) ? roundedUp : truncated;
                     } else {
                        var10000 = truncated;
                     }

                     rounded = var10000;
                  } else {
                     rounded = remainder <= 1L && exact.$less$eq(this.apply(.MODULE$.BigDecimal().apply(truncated))) ? truncated : truncated.add(epsilon$1(scale));
                  }
               }
            }

            var10000 = rounded;
         }

         return var10000;
      }
   }

   public BigInteger spire$math$Algebraic$$MaxIntValue() {
      return spire$math$Algebraic$$MaxIntValue;
   }

   public BigInteger spire$math$Algebraic$$MinIntValue() {
      return spire$math$Algebraic$$MinIntValue;
   }

   public BigInteger spire$math$Algebraic$$MaxLongValue() {
      return spire$math$Algebraic$$MaxLongValue;
   }

   public BigInteger spire$math$Algebraic$$MinLongValue() {
      return spire$math$Algebraic$$MinLongValue;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Algebraic$.class);
   }

   private final java.math.BigDecimal loop$2(final java.math.BigDecimal prev, final int prevDigits, final java.math.BigDecimal prevEps, final Function1 getEps$1, final int k$1, final java.math.BigDecimal value$1, final java.math.BigDecimal n$1) {
      while(true) {
         int digits = BoxesRunTime.unboxToInt(getEps$1.apply(prev));
         java.math.BigDecimal eps = digits == prevDigits ? prevEps : java.math.BigDecimal.ONE.movePointLeft(digits);
         java.math.BigDecimal prevExp = prev.pow(k$1 - 1);
         java.math.BigDecimal delta = value$1.divide(prevExp, digits, RoundingMode.HALF_UP).subtract(prev).divide(n$1, digits, RoundingMode.HALF_UP);
         if (delta.abs().compareTo(eps) <= 0) {
            return prev;
         }

         java.math.BigDecimal var10000 = prev.add(delta);
         prevEps = eps;
         prevDigits = digits;
         prev = var10000;
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$nroot$1(final MathContext mc$1, final java.math.BigDecimal x) {
      return x.scale() - (int)package$.MODULE$.ceil((double)x.unscaledValue().bitLength() * MODULE$.bits2dec()) + mc$1.getPrecision() + 1;
   }

   // $FF: synthetic method
   public static final int $anonfun$nroot$2(final int scale$1, final java.math.BigDecimal x$1) {
      return scale$1 + 1;
   }

   private static final java.math.BigDecimal epsilon$1(final int scale$2) {
      return new java.math.BigDecimal(BigInteger.ONE, scale$2);
   }

   private Algebraic$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
