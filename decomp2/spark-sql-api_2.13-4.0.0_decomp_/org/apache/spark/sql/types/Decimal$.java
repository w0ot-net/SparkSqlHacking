package org.apache.spark.sql.types;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import org.apache.spark.QueryContext;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.sql.errors.DataTypeErrors$;
import org.apache.spark.sql.internal.SqlApiConf$;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Enumeration;
import scala.MatchError;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.BigDecimal.RoundingMode.;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction1;

@Unstable
public final class Decimal$ implements Serializable {
   public static final Decimal$ MODULE$ = new Decimal$();
   private static int[] minBytesForPrecision;
   private static final Enumeration.Value ROUND_HALF_UP;
   private static final Enumeration.Value ROUND_HALF_EVEN;
   private static final Enumeration.Value ROUND_CEILING;
   private static final Enumeration.Value ROUND_FLOOR;
   private static final int MAX_INT_DIGITS;
   private static final int MAX_LONG_DIGITS;
   private static final long[] POW_10;
   private static final MathContext org$apache$spark$sql$types$Decimal$$MATH_CONTEXT;
   private static final Decimal ZERO;
   private static final Decimal ONE;
   private static volatile boolean bitmap$0;

   static {
      ROUND_HALF_UP = .MODULE$.HALF_UP();
      ROUND_HALF_EVEN = .MODULE$.HALF_EVEN();
      ROUND_CEILING = .MODULE$.CEILING();
      ROUND_FLOOR = .MODULE$.FLOOR();
      MAX_INT_DIGITS = 9;
      MAX_LONG_DIGITS = 18;
      POW_10 = (long[])scala.Array..MODULE$.tabulate(MODULE$.MAX_LONG_DIGITS() + 1, (JFunction1.mcJI.sp)(i) -> (long)scala.math.package..MODULE$.pow((double)10.0F, (double)i), scala.reflect.ClassTag..MODULE$.Long());
      org$apache$spark$sql$types$Decimal$$MATH_CONTEXT = new MathContext(DecimalType$.MODULE$.MAX_PRECISION() + 1, RoundingMode.DOWN);
      ZERO = MODULE$.apply(0);
      ONE = MODULE$.apply(1);
   }

   public Enumeration.Value ROUND_HALF_UP() {
      return ROUND_HALF_UP;
   }

   public Enumeration.Value ROUND_HALF_EVEN() {
      return ROUND_HALF_EVEN;
   }

   public Enumeration.Value ROUND_CEILING() {
      return ROUND_CEILING;
   }

   public Enumeration.Value ROUND_FLOOR() {
      return ROUND_FLOOR;
   }

   public int MAX_INT_DIGITS() {
      return MAX_INT_DIGITS;
   }

   public int MAX_LONG_DIGITS() {
      return MAX_LONG_DIGITS;
   }

   public long[] POW_10() {
      return POW_10;
   }

   public MathContext org$apache$spark$sql$types$Decimal$$MATH_CONTEXT() {
      return org$apache$spark$sql$types$Decimal$$MATH_CONTEXT;
   }

   public Decimal ZERO() {
      return ZERO;
   }

   public Decimal ONE() {
      return ONE;
   }

   public Decimal apply(final double value) {
      return (new Decimal()).set(scala.math.BigDecimal..MODULE$.double2bigDecimal(value));
   }

   public Decimal apply(final long value) {
      return (new Decimal()).set(value);
   }

   public Decimal apply(final int value) {
      return (new Decimal()).set(value);
   }

   public Decimal apply(final BigDecimal value) {
      return (new Decimal()).set(value);
   }

   public Decimal apply(final java.math.BigDecimal value) {
      return (new Decimal()).set(scala.math.BigDecimal..MODULE$.javaBigDecimal2bigDecimal(value));
   }

   public Decimal apply(final BigInteger value) {
      return (new Decimal()).set(value);
   }

   public Decimal apply(final BigInt value) {
      return (new Decimal()).set(value.bigInteger());
   }

   public Decimal apply(final BigDecimal value, final int precision, final int scale) {
      return (new Decimal()).set(value, precision, scale);
   }

   public Decimal apply(final java.math.BigDecimal value, final int precision, final int scale) {
      return (new Decimal()).set(scala.math.BigDecimal..MODULE$.javaBigDecimal2bigDecimal(value), precision, scale);
   }

   public Decimal apply(final long unscaled, final int precision, final int scale) {
      return (new Decimal()).set(unscaled, precision, scale);
   }

   public Decimal apply(final String value) {
      return (new Decimal()).set(scala.package..MODULE$.BigDecimal().apply(value));
   }

   public Decimal fromDecimal(final Object value) {
      if (value instanceof java.math.BigDecimal var4) {
         return this.apply(var4);
      } else if (value instanceof BigDecimal var5) {
         return this.apply(var5);
      } else if (value instanceof BigInt var6) {
         return this.apply(var6);
      } else if (value instanceof BigInteger var7) {
         return this.apply(var7);
      } else if (value instanceof Decimal var8) {
         return var8;
      } else {
         throw new MatchError(value);
      }
   }

   private int numDigitsInIntegralPart(final java.math.BigDecimal bigDecimal) {
      return bigDecimal.precision() - bigDecimal.scale();
   }

   private java.math.BigDecimal stringToJavaBigDecimal(final UTF8String str) {
      return new java.math.BigDecimal(str.toString().trim());
   }

   public Decimal fromString(final UTF8String str) {
      Decimal var10000;
      try {
         java.math.BigDecimal bigDecimal = this.stringToJavaBigDecimal(str);
         var10000 = this.numDigitsInIntegralPart(bigDecimal) > DecimalType$.MODULE$.MAX_PRECISION() && !SqlApiConf$.MODULE$.get().allowNegativeScaleOfDecimalEnabled() ? null : this.apply(bigDecimal);
      } catch (NumberFormatException var3) {
         var10000 = null;
      }

      return var10000;
   }

   public Decimal fromStringANSI(final UTF8String str, final DecimalType to, final QueryContext context) {
      try {
         java.math.BigDecimal bigDecimal = this.stringToJavaBigDecimal(str);
         if (this.numDigitsInIntegralPart(bigDecimal) > DecimalType$.MODULE$.MAX_PRECISION() && !SqlApiConf$.MODULE$.get().allowNegativeScaleOfDecimalEnabled()) {
            throw DataTypeErrors$.MODULE$.outOfDecimalTypeRangeError(str);
         } else {
            return this.apply(bigDecimal);
         }
      } catch (NumberFormatException var5) {
         throw DataTypeErrors$.MODULE$.invalidInputInCastToNumberError(to, str, context);
      }
   }

   public DecimalType fromStringANSI$default$2() {
      return DecimalType$.MODULE$.USER_DEFAULT();
   }

   public QueryContext fromStringANSI$default$3() {
      return null;
   }

   public Decimal createUnsafe(final long unscaled, final int precision, final int scale) {
      DecimalType$.MODULE$.checkNegativeScale(scale);
      Decimal dec = new Decimal();
      dec.org$apache$spark$sql$types$Decimal$$longVal_$eq(unscaled);
      dec.org$apache$spark$sql$types$Decimal$$_precision_$eq(precision);
      dec.org$apache$spark$sql$types$Decimal$$_scale_$eq(scale);
      return dec;
   }

   public int maxPrecisionForBytes(final int numBytes) {
      return (int)Math.round(Math.floor(Math.log10(Math.pow((double)2.0F, (double)(8 * numBytes - 1)) - (double)1)));
   }

   private int[] minBytesForPrecision$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            minBytesForPrecision = (int[])scala.Array..MODULE$.tabulate(39, (JFunction1.mcII.sp)(precision) -> MODULE$.computeMinBytesForPrecision(precision), scala.reflect.ClassTag..MODULE$.Int());
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return minBytesForPrecision;
   }

   public int[] minBytesForPrecision() {
      return !bitmap$0 ? this.minBytesForPrecision$lzycompute() : minBytesForPrecision;
   }

   private int computeMinBytesForPrecision(final int precision) {
      int numBytes;
      for(numBytes = 1; scala.math.package..MODULE$.pow((double)2.0F, (double)(8 * numBytes - 1)) < scala.math.package..MODULE$.pow((double)10.0F, (double)precision); ++numBytes) {
      }

      return numBytes;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Decimal$.class);
   }

   private Decimal$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
