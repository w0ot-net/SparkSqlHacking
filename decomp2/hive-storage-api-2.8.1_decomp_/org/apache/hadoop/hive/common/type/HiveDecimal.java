package org.apache.hadoop.hive.common.type;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public final class HiveDecimal extends FastHiveDecimal implements Comparable {
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
   public static final HiveDecimal ZERO = create(0);
   @HiveDecimalVersionV1
   public static final HiveDecimal ONE = create(1);
   @HiveDecimalVersionV1
   public static final int ROUND_FLOOR = 3;
   @HiveDecimalVersionV1
   public static final int ROUND_CEILING = 2;
   @HiveDecimalVersionV1
   public static final int ROUND_HALF_UP = 4;
   @HiveDecimalVersionV1
   public static final int ROUND_HALF_EVEN = 6;
   @HiveDecimalVersionV2
   public static final int SCRATCH_BUFFER_LEN_SERIALIZATION_UTILS_READ = 24;
   @HiveDecimalVersionV2
   public static final int SCRATCH_LONGS_LEN = 6;
   @HiveDecimalVersionV2
   public static final int SCRATCH_BUFFER_LEN_BIG_INTEGER_BYTES = 49;
   @HiveDecimalVersionV2
   public static final int SCRATCH_BUFFER_LEN_TO_BYTES = 79;

   private HiveDecimal() {
   }

   private HiveDecimal(HiveDecimal dec) {
      super((FastHiveDecimal)dec);
   }

   private HiveDecimal(FastHiveDecimal fastDec) {
      super(fastDec);
   }

   private HiveDecimal(int fastSignum, FastHiveDecimal fastDec) {
      super(fastSignum, fastDec);
   }

   private HiveDecimal(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      super(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale);
   }

   @HiveDecimalVersionV2
   public static HiveDecimal createFromFast(FastHiveDecimal fastDec) {
      return new HiveDecimal(fastDec);
   }

   @HiveDecimalVersionV1
   public static HiveDecimal create(BigDecimal bigDecimal) {
      return create(bigDecimal, true);
   }

   @HiveDecimalVersionV1
   public static HiveDecimal create(BigDecimal bigDecimal, boolean allowRounding) {
      HiveDecimal result = new HiveDecimal();
      return !result.fastSetFromBigDecimal(bigDecimal, allowRounding) ? null : result;
   }

   @HiveDecimalVersionV1
   public static HiveDecimal create(BigInteger bigInteger) {
      HiveDecimal result = new HiveDecimal();
      return !result.fastSetFromBigInteger(bigInteger) ? null : result;
   }

   @HiveDecimalVersionV1
   public static HiveDecimal create(BigInteger bigInteger, int scale) {
      HiveDecimal result = new HiveDecimal();
      return !result.fastSetFromBigIntegerAndScale(bigInteger, scale) ? null : result;
   }

   @HiveDecimalVersionV1
   public static HiveDecimal create(String string) {
      HiveDecimal result = new HiveDecimal();
      return !result.fastSetFromString(string, true) ? null : result;
   }

   @HiveDecimalVersionV2
   public static HiveDecimal create(String string, boolean trimBlanks) {
      HiveDecimal result = new HiveDecimal();
      return !result.fastSetFromString(string, trimBlanks) ? null : result;
   }

   @HiveDecimalVersionV2
   public static HiveDecimal create(byte[] bytes) {
      HiveDecimal result = new HiveDecimal();
      return !result.fastSetFromBytes(bytes, 0, bytes.length, false) ? null : result;
   }

   @HiveDecimalVersionV2
   public static HiveDecimal create(byte[] bytes, boolean trimBlanks) {
      HiveDecimal result = new HiveDecimal();
      return !result.fastSetFromBytes(bytes, 0, bytes.length, trimBlanks) ? null : result;
   }

   @HiveDecimalVersionV2
   public static HiveDecimal create(boolean isNegative, byte[] bytes, int scale) {
      HiveDecimal result = new HiveDecimal();
      if (!result.fastSetFromDigitsOnlyBytesAndScale(isNegative, bytes, 0, bytes.length, scale)) {
         return null;
      } else {
         if (isNegative) {
            result.fastNegate();
         }

         return result;
      }
   }

   @HiveDecimalVersionV2
   public static HiveDecimal create(boolean isNegative, byte[] bytes, int offset, int length, int scale) {
      HiveDecimal result = new HiveDecimal();
      return !result.fastSetFromDigitsOnlyBytesAndScale(isNegative, bytes, offset, length, scale) ? null : result;
   }

   @HiveDecimalVersionV2
   public static HiveDecimal create(byte[] bytes, int offset, int length) {
      HiveDecimal result = new HiveDecimal();
      return !result.fastSetFromBytes(bytes, offset, length, false) ? null : result;
   }

   @HiveDecimalVersionV2
   public static HiveDecimal create(byte[] bytes, int offset, int length, boolean trimBlanks) {
      HiveDecimal result = new HiveDecimal();
      return !result.fastSetFromBytes(bytes, offset, length, trimBlanks) ? null : result;
   }

   @HiveDecimalVersionV1
   public static HiveDecimal create(int intValue) {
      HiveDecimal result = new HiveDecimal();
      result.fastSetFromInt(intValue);
      return result;
   }

   @HiveDecimalVersionV1
   public static HiveDecimal create(long longValue) {
      HiveDecimal result = new HiveDecimal();
      result.fastSetFromLong(longValue);
      return result;
   }

   @HiveDecimalVersionV2
   public static HiveDecimal create(long longValue, int scale) {
      HiveDecimal result = new HiveDecimal();
      return !result.fastSetFromLongAndScale(longValue, scale) ? null : result;
   }

   @HiveDecimalVersionV2
   public static HiveDecimal create(float floatValue) {
      HiveDecimal result = new HiveDecimal();
      return !result.fastSetFromFloat(floatValue) ? null : result;
   }

   @HiveDecimalVersionV2
   public static HiveDecimal create(double doubleValue) {
      HiveDecimal result = new HiveDecimal();
      return !result.fastSetFromDouble(doubleValue) ? null : result;
   }

   @HiveDecimalVersionV2
   public static HiveDecimal serializationUtilsRead(InputStream inputStream, int scale, byte[] scratchBytes) throws IOException {
      HiveDecimal result = new HiveDecimal();
      return !result.fastSerializationUtilsRead(inputStream, scale, scratchBytes) ? null : result;
   }

   @HiveDecimalVersionV2
   public static HiveDecimal createFromBigIntegerBytesAndScale(byte[] bytes, int scale) {
      HiveDecimal result = new HiveDecimal();
      return !result.fastSetFromBigIntegerBytesAndScale(bytes, 0, bytes.length, scale) ? null : result;
   }

   @HiveDecimalVersionV2
   public static HiveDecimal createFromBigIntegerBytesAndScale(byte[] bytes, int offset, int length, int scale) {
      HiveDecimal result = new HiveDecimal();
      return !result.fastSetFromBigIntegerBytesAndScale(bytes, offset, length, scale) ? null : result;
   }

   @HiveDecimalVersionV2
   public boolean serializationUtilsWrite(OutputStream outputStream, long[] scratchLongs) throws IOException {
      return this.fastSerializationUtilsWrite(outputStream, scratchLongs);
   }

   @HiveDecimalVersionV2
   public int bigIntegerBytes(long[] scratchLongs, byte[] buffer) {
      return this.fastBigIntegerBytes(scratchLongs, buffer);
   }

   @HiveDecimalVersionV2
   public byte[] bigIntegerBytes() {
      long[] scratchLongs = new long[6];
      byte[] buffer = new byte[49];
      int byteLength = this.fastBigIntegerBytes(scratchLongs, buffer);
      return Arrays.copyOfRange(buffer, 0, byteLength);
   }

   @HiveDecimalVersionV2
   public int bigIntegerBytesScaled(int serializeScale, long[] scratchLongs, byte[] buffer) {
      return this.fastBigIntegerBytesScaled(serializeScale, scratchLongs, buffer);
   }

   @HiveDecimalVersionV2
   public byte[] bigIntegerBytesScaled(int serializeScale) {
      long[] scratchLongs = new long[6];
      byte[] buffer = new byte[49];
      int byteLength = this.fastBigIntegerBytesScaled(serializeScale, scratchLongs, buffer);
      return Arrays.copyOfRange(buffer, 0, byteLength);
   }

   @HiveDecimalVersionV1
   public String toString() {
      return this.fastSerializationScale() != -1 ? this.fastToFormatString(this.fastSerializationScale()) : this.fastToString();
   }

   @HiveDecimalVersionV2
   public String toString(byte[] scratchBuffer) {
      return this.fastSerializationScale() != -1 ? this.fastToFormatString(this.fastSerializationScale(), scratchBuffer) : this.fastToString(scratchBuffer);
   }

   @HiveDecimalVersionV1
   public String toFormatString(int formatScale) {
      return this.fastToFormatString(formatScale);
   }

   @HiveDecimalVersionV2
   public String toFormatString(int formatScale, byte[] scratchBuffer) {
      return this.fastToFormatString(formatScale, scratchBuffer);
   }

   @HiveDecimalVersionV2
   public String toDigitsOnlyString() {
      return this.fastToDigitsOnlyString();
   }

   @HiveDecimalVersionV2
   public int toBytes(byte[] scratchBuffer) {
      return this.fastToBytes(scratchBuffer);
   }

   @HiveDecimalVersionV2
   public int toFormatBytes(int formatScale, byte[] scratchBuffer) {
      return this.fastToFormatBytes(formatScale, scratchBuffer);
   }

   @HiveDecimalVersionV2
   public int toDigitsOnlyBytes(byte[] scratchBuffer) {
      return this.fastToDigitsOnlyBytes(scratchBuffer);
   }

   @HiveDecimalVersionV1
   public int compareTo(HiveDecimal dec) {
      return this.fastCompareTo(dec);
   }

   @HiveDecimalVersionV2
   public int newFasterHashCode() {
      return this.fastNewFasterHashCode();
   }

   @HiveDecimalVersionV1
   public int hashCode() {
      return this.fastHashCode();
   }

   @HiveDecimalVersionV1
   public boolean equals(Object obj) {
      return obj != null && obj.getClass() == this.getClass() ? this.fastEquals((HiveDecimal)obj) : false;
   }

   @HiveDecimalVersionV1
   public int scale() {
      return this.fastScale();
   }

   @HiveDecimalVersionV2
   public int integerDigitCount() {
      return this.fastIntegerDigitCount();
   }

   @HiveDecimalVersionV1
   public int precision() {
      return this.fastSqlPrecision();
   }

   @HiveDecimalVersionV2
   public int rawPrecision() {
      return this.fastRawPrecision();
   }

   @HiveDecimalVersionV1
   public int signum() {
      return this.fastSignum();
   }

   @HiveDecimalVersionV2
   public boolean isByte() {
      return this.fastIsByte();
   }

   @HiveDecimalVersionV1
   public byte byteValue() {
      return this.fastByteValueClip();
   }

   @HiveDecimalVersionV2
   public boolean isShort() {
      return this.fastIsShort();
   }

   @HiveDecimalVersionV1
   public short shortValue() {
      return this.fastShortValueClip();
   }

   @HiveDecimalVersionV2
   public boolean isInt() {
      return this.fastIsInt();
   }

   @HiveDecimalVersionV1
   public int intValue() {
      return this.fastIntValueClip();
   }

   @HiveDecimalVersionV2
   public boolean isLong() {
      return this.fastIsLong();
   }

   @HiveDecimalVersionV1
   public long longValue() {
      return this.fastLongValueClip();
   }

   @HiveDecimalVersionV1
   public long longValueExact() {
      if (!this.isLong()) {
         throw new ArithmeticException();
      } else {
         return this.fastLongValueClip();
      }
   }

   @HiveDecimalVersionV1
   public float floatValue() {
      return this.fastFloatValue();
   }

   @HiveDecimalVersionV1
   public double doubleValue() {
      return this.fastDoubleValue();
   }

   @HiveDecimalVersionV1
   public BigDecimal bigDecimalValue() {
      return this.fastBigDecimalValue();
   }

   @HiveDecimalVersionV1
   public BigInteger unscaledValue() {
      return this.fastBigIntegerValue();
   }

   @HiveDecimalVersionV2
   public HiveDecimal fractionPortion() {
      HiveDecimal result = new HiveDecimal();
      result.fastFractionPortion();
      return result;
   }

   @HiveDecimalVersionV2
   public HiveDecimal integerPortion() {
      HiveDecimal result = new HiveDecimal();
      result.fastIntegerPortion();
      return result;
   }

   @HiveDecimalVersionV1
   public HiveDecimal add(HiveDecimal dec) {
      HiveDecimal result = new HiveDecimal();
      return !this.fastAdd(dec, result) ? null : result;
   }

   @HiveDecimalVersionV1
   public HiveDecimal subtract(HiveDecimal dec) {
      HiveDecimal result = new HiveDecimal();
      return !this.fastSubtract(dec, result) ? null : result;
   }

   @HiveDecimalVersionV1
   public HiveDecimal multiply(HiveDecimal dec) {
      HiveDecimal result = new HiveDecimal();
      return !this.fastMultiply(dec, result) ? null : result;
   }

   @HiveDecimalVersionV1
   public HiveDecimal scaleByPowerOfTen(int power) {
      if (power != 0 && this.fastSignum() != 0) {
         HiveDecimal result = new HiveDecimal();
         return !this.fastScaleByPowerOfTen(power, result) ? null : result;
      } else {
         return this;
      }
   }

   @HiveDecimalVersionV1
   public HiveDecimal abs() {
      if (this.fastSignum() != -1) {
         return this;
      } else {
         HiveDecimal result = new HiveDecimal(this);
         result.fastAbs();
         return result;
      }
   }

   @HiveDecimalVersionV1
   public HiveDecimal negate() {
      if (this.fastSignum() == 0) {
         return this;
      } else {
         HiveDecimal result = new HiveDecimal(this);
         result.fastNegate();
         return result;
      }
   }

   /** @deprecated */
   @Deprecated
   @HiveDecimalVersionV1
   public HiveDecimal setScale(int serializationScale) {
      HiveDecimal result = new HiveDecimal(this);
      result.fastSetSerializationScale(serializationScale);
      return result;
   }

   @HiveDecimalVersionV1
   public HiveDecimal setScale(int roundingPoint, int roundingMode) {
      if (this.fastScale() == roundingPoint) {
         return this;
      } else {
         HiveDecimal result = new HiveDecimal();
         return !this.fastRound(roundingPoint, roundingMode, result) ? null : result;
      }
   }

   @HiveDecimalVersionV1
   public HiveDecimal pow(int exponent) {
      HiveDecimal result = new HiveDecimal(this);
      return !this.fastPow(exponent, result) ? null : result;
   }

   @HiveDecimalVersionV1
   public HiveDecimal divide(HiveDecimal divisor) {
      HiveDecimal result = new HiveDecimal();
      return !this.fastDivide(divisor, result) ? null : result;
   }

   @HiveDecimalVersionV1
   public HiveDecimal remainder(HiveDecimal divisor) {
      HiveDecimal result = new HiveDecimal();
      return !this.fastRemainder(divisor, result) ? null : result;
   }

   @HiveDecimalVersionV1
   public static HiveDecimal enforcePrecisionScale(HiveDecimal dec, int maxPrecision, int maxScale) {
      if (maxPrecision >= 1 && maxPrecision <= 38) {
         if (maxScale >= 0 && maxScale <= 38) {
            if (maxPrecision < maxScale) {
               throw new IllegalArgumentException("Decimal scale must be less than or equal to precision");
            } else if (dec == null) {
               return null;
            } else {
               FastHiveDecimal.FastCheckPrecisionScaleStatus status = dec.fastCheckPrecisionScale(maxPrecision, maxScale);
               switch (status) {
                  case NO_CHANGE:
                     return dec;
                  case OVERFLOW:
                     return null;
                  case UPDATE_SCALE_DOWN:
                     HiveDecimal result = new HiveDecimal();
                     if (!dec.fastUpdatePrecisionScale(maxPrecision, maxScale, status, result)) {
                        return null;
                     }

                     return result;
                  default:
                     throw new RuntimeException("Unknown fast decimal check precision and scale status " + status);
               }
            }
         } else {
            throw new IllegalArgumentException("Decimal scale out of allowed range [0,38]");
         }
      } else {
         throw new IllegalArgumentException("Decimal precision out of allowed range [1,38]");
      }
   }

   @HiveDecimalVersionV2
   public void validate() {
      if (!this.fastIsValid()) {
         this.fastRaiseInvalidException();
      }

   }
}
