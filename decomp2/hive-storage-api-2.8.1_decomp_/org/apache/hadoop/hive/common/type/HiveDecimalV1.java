package org.apache.hadoop.hive.common.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public final class HiveDecimalV1 implements Comparable {
   @HiveDecimalVersionV1
   public static final int MAX_PRECISION = 38;
   @HiveDecimalVersionV1
   public static final int MAX_SCALE = 38;
   @HiveDecimalVersionV1
   public static final int USER_DEFAULT_PRECISION = 10;
   @HiveDecimalVersionV1
   public static final int USER_DEFAULT_SCALE = 0;
   @HiveDecimalVersionV1
   public static final int SYSTEM_DEFAULT_PRECISION = 38;
   @HiveDecimalVersionV1
   public static final int SYSTEM_DEFAULT_SCALE = 18;
   @HiveDecimalVersionV1
   public static final HiveDecimalV1 ZERO;
   @HiveDecimalVersionV1
   public static final HiveDecimalV1 ONE;
   @HiveDecimalVersionV1
   public static final int ROUND_FLOOR = 3;
   @HiveDecimalVersionV1
   public static final int ROUND_CEILING = 2;
   @HiveDecimalVersionV1
   public static final int ROUND_HALF_UP = 4;
   @HiveDecimalVersionV1
   public static final int ROUND_HALF_EVEN = 6;
   private BigDecimal bd;

   private HiveDecimalV1(BigDecimal bd) {
      this.bd = BigDecimal.ZERO;
      this.bd = bd;
   }

   @HiveDecimalVersionV1
   public static HiveDecimalV1 create(BigDecimal b) {
      return create(b, true);
   }

   @HiveDecimalVersionV1
   public static HiveDecimalV1 create(BigDecimal b, boolean allowRounding) {
      BigDecimal bd = normalize(b, allowRounding);
      return bd == null ? null : new HiveDecimalV1(bd);
   }

   @HiveDecimalVersionV1
   public static HiveDecimalV1 create(BigInteger unscaled, int scale) {
      BigDecimal bd = normalize(new BigDecimal(unscaled, scale), true);
      return bd == null ? null : new HiveDecimalV1(bd);
   }

   @HiveDecimalVersionV1
   public static HiveDecimalV1 create(String dec) {
      BigDecimal bd;
      try {
         bd = new BigDecimal(dec.trim());
      } catch (NumberFormatException var3) {
         return null;
      }

      bd = normalize(bd, true);
      return bd == null ? null : new HiveDecimalV1(bd);
   }

   @HiveDecimalVersionV1
   public static HiveDecimalV1 create(BigInteger bi) {
      BigDecimal bd = normalize(new BigDecimal(bi), true);
      return bd == null ? null : new HiveDecimalV1(bd);
   }

   @HiveDecimalVersionV1
   public static HiveDecimalV1 create(int i) {
      return new HiveDecimalV1(new BigDecimal(i));
   }

   @HiveDecimalVersionV1
   public static HiveDecimalV1 create(long l) {
      return new HiveDecimalV1(new BigDecimal(l));
   }

   @HiveDecimalVersionV1
   public String toString() {
      return this.bd.toPlainString();
   }

   @HiveDecimalVersionV1
   public String toFormatString(int scale) {
      return (this.bd.scale() == scale ? this.bd : this.bd.setScale(scale, RoundingMode.HALF_UP)).toPlainString();
   }

   @HiveDecimalVersionV1
   public HiveDecimalV1 setScale(int i) {
      return new HiveDecimalV1(this.bd.setScale(i, RoundingMode.HALF_UP));
   }

   @HiveDecimalVersionV1
   public int compareTo(HiveDecimalV1 dec) {
      return this.bd.compareTo(dec.bd);
   }

   @HiveDecimalVersionV1
   public int hashCode() {
      return this.bd.hashCode();
   }

   @HiveDecimalVersionV1
   public boolean equals(Object obj) {
      return obj != null && obj.getClass() == this.getClass() ? this.bd.equals(((HiveDecimalV1)obj).bd) : false;
   }

   @HiveDecimalVersionV1
   public int scale() {
      return this.bd.scale();
   }

   @HiveDecimalVersionV1
   public int precision() {
      int bdPrecision = this.bd.precision();
      int bdScale = this.bd.scale();
      return bdPrecision < bdScale ? bdScale : bdPrecision;
   }

   @HiveDecimalVersionV1
   public int intValue() {
      return this.bd.intValue();
   }

   @HiveDecimalVersionV1
   public double doubleValue() {
      return this.bd.doubleValue();
   }

   @HiveDecimalVersionV1
   public long longValue() {
      return this.bd.longValue();
   }

   @HiveDecimalVersionV1
   public short shortValue() {
      return this.bd.shortValue();
   }

   @HiveDecimalVersionV1
   public float floatValue() {
      return this.bd.floatValue();
   }

   @HiveDecimalVersionV1
   public BigDecimal bigDecimalValue() {
      return this.bd;
   }

   @HiveDecimalVersionV1
   public byte byteValue() {
      return this.bd.byteValue();
   }

   @HiveDecimalVersionV1
   public HiveDecimalV1 setScale(int adjustedScale, int rm) {
      return create(this.bd.setScale(adjustedScale, rm));
   }

   @HiveDecimalVersionV1
   public HiveDecimalV1 subtract(HiveDecimalV1 dec) {
      return create(this.bd.subtract(dec.bd));
   }

   @HiveDecimalVersionV1
   public HiveDecimalV1 multiply(HiveDecimalV1 dec) {
      return create(this.bd.multiply(dec.bd), false);
   }

   @HiveDecimalVersionV1
   public BigInteger unscaledValue() {
      return this.bd.unscaledValue();
   }

   @HiveDecimalVersionV1
   public HiveDecimalV1 scaleByPowerOfTen(int n) {
      return create(this.bd.scaleByPowerOfTen(n));
   }

   @HiveDecimalVersionV1
   public HiveDecimalV1 abs() {
      return create(this.bd.abs());
   }

   @HiveDecimalVersionV1
   public HiveDecimalV1 negate() {
      return create(this.bd.negate());
   }

   @HiveDecimalVersionV1
   public HiveDecimalV1 add(HiveDecimalV1 dec) {
      return create(this.bd.add(dec.bd));
   }

   @HiveDecimalVersionV1
   public HiveDecimalV1 pow(int n) {
      BigDecimal result = normalize(this.bd.pow(n), false);
      return result == null ? null : new HiveDecimalV1(result);
   }

   @HiveDecimalVersionV1
   public HiveDecimalV1 remainder(HiveDecimalV1 dec) {
      return create(this.bd.remainder(dec.bd));
   }

   @HiveDecimalVersionV1
   public HiveDecimalV1 divide(HiveDecimalV1 dec) {
      return create(this.bd.divide(dec.bd, 38, RoundingMode.HALF_UP), true);
   }

   @HiveDecimalVersionV1
   public int signum() {
      return this.bd.signum();
   }

   private static BigDecimal trim(BigDecimal d) {
      if (d.compareTo(BigDecimal.ZERO) == 0) {
         d = BigDecimal.ZERO;
      } else {
         d = d.stripTrailingZeros();
         if (d.scale() < 0) {
            d = d.setScale(0);
         }
      }

      return d;
   }

   private static BigDecimal normalize(BigDecimal bd, boolean allowRounding) {
      if (bd == null) {
         return null;
      } else {
         bd = trim(bd);
         int intDigits = bd.precision() - bd.scale();
         if (intDigits > 38) {
            return null;
         } else {
            int maxScale = Math.min(38, Math.min(38 - intDigits, bd.scale()));
            if (bd.scale() > maxScale) {
               if (allowRounding) {
                  bd = bd.setScale(maxScale, RoundingMode.HALF_UP);
                  bd = trim(bd);
               } else {
                  bd = null;
               }
            }

            return bd;
         }
      }
   }

   private static BigDecimal enforcePrecisionScale(BigDecimal bd, int maxPrecision, int maxScale) {
      if (bd == null) {
         return null;
      } else if (bd.compareTo(BigDecimal.ZERO) == 0 && bd.scale() == 0 && maxPrecision == maxScale) {
         return bd.setScale(maxScale);
      } else {
         bd = trim(bd);
         if (bd.scale() > maxScale) {
            bd = bd.setScale(maxScale, RoundingMode.HALF_UP);
         }

         int maxIntDigits = maxPrecision - maxScale;
         int intDigits = bd.precision() - bd.scale();
         return intDigits > maxIntDigits ? null : bd;
      }
   }

   @HiveDecimalVersionV1
   public static HiveDecimalV1 enforcePrecisionScale(HiveDecimalV1 dec, int maxPrecision, int maxScale) {
      if (dec == null) {
         return null;
      } else if (dec.precision() - dec.scale() <= maxPrecision - maxScale && dec.scale() <= maxScale) {
         return dec;
      } else {
         BigDecimal bd = enforcePrecisionScale(dec.bd, maxPrecision, maxScale);
         return bd == null ? null : create(bd);
      }
   }

   @HiveDecimalVersionV1
   public long longValueExact() {
      return this.bd.longValueExact();
   }

   static {
      ZERO = new HiveDecimalV1(BigDecimal.ZERO);
      ONE = new HiveDecimalV1(BigDecimal.ONE);
   }
}
