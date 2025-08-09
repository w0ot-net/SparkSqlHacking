package org.apache.hadoop.hive.common.type;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class FastHiveDecimal {
   protected int fastSignum;
   protected long fast2;
   protected long fast1;
   protected long fast0;
   protected int fastIntegerDigitCount;
   protected int fastScale;
   protected int fastSerializationScale;
   protected static final String STRING_ENFORCE_PRECISION_OUT_OF_RANGE = "Decimal precision out of allowed range [1,38]";
   protected static final String STRING_ENFORCE_SCALE_OUT_OF_RANGE = "Decimal scale out of allowed range [0,38]";
   protected static final String STRING_ENFORCE_SCALE_LESS_THAN_EQUAL_PRECISION = "Decimal scale must be less than or equal to precision";
   protected static final int FAST_SCRATCH_BUFFER_LEN_SERIALIZATION_UTILS_READ = 24;
   protected static final int SCRATCH_LONGS_LEN_FAST_SERIALIZATION_UTILS_WRITE = 6;
   protected static final int FAST_SCRATCH_BUFFER_LEN_BIG_INTEGER_BYTES = 49;
   protected static final int FAST_SCRATCH_LONGS_LEN = 6;
   protected static final int FAST_SCRATCH_BUFFER_LEN_TO_BYTES = 79;

   protected FastHiveDecimal() {
      this.fastReset();
   }

   protected FastHiveDecimal(FastHiveDecimal fastDec) {
      this();
      this.fastSignum = fastDec.fastSignum;
      this.fast0 = fastDec.fast0;
      this.fast1 = fastDec.fast1;
      this.fast2 = fastDec.fast2;
      this.fastIntegerDigitCount = fastDec.fastIntegerDigitCount;
      this.fastScale = fastDec.fastScale;
      this.fastSerializationScale = -1;
   }

   protected FastHiveDecimal(int fastSignum, FastHiveDecimal fastDec) {
      this();
      this.fastSignum = fastSignum;
      this.fast0 = fastDec.fast0;
      this.fast1 = fastDec.fast1;
      this.fast2 = fastDec.fast2;
      this.fastIntegerDigitCount = fastDec.fastIntegerDigitCount;
      this.fastScale = fastDec.fastScale;
      this.fastSerializationScale = -1;
   }

   protected FastHiveDecimal(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      this();
      this.fastSignum = fastSignum;
      this.fast0 = fast0;
      this.fast1 = fast1;
      this.fast2 = fast2;
      this.fastIntegerDigitCount = fastIntegerDigitCount;
      this.fastScale = fastScale;
      this.fastSerializationScale = -1;
   }

   protected FastHiveDecimal(long longValue) {
      this();
      FastHiveDecimalImpl.fastSetFromLong(longValue, this);
   }

   protected FastHiveDecimal(String string) {
      this();
      FastHiveDecimalImpl.fastSetFromString(string, false, this);
   }

   protected void fastReset() {
      this.fastSignum = 0;
      this.fast0 = 0L;
      this.fast1 = 0L;
      this.fast2 = 0L;
      this.fastIntegerDigitCount = 0;
      this.fastScale = 0;
      this.fastSerializationScale = -1;
   }

   protected void fastSet(FastHiveDecimal fastDec) {
      this.fastSignum = fastDec.fastSignum;
      this.fast0 = fastDec.fast0;
      this.fast1 = fastDec.fast1;
      this.fast2 = fastDec.fast2;
      this.fastIntegerDigitCount = fastDec.fastIntegerDigitCount;
      this.fastScale = fastDec.fastScale;
      this.fastSerializationScale = fastDec.fastSerializationScale;
   }

   protected void fastSet(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      this.fastSignum = fastSignum;
      this.fast0 = fast0;
      this.fast1 = fast1;
      this.fast2 = fast2;
      this.fastIntegerDigitCount = fastIntegerDigitCount;
      this.fastScale = fastScale;
      this.fastSerializationScale = -1;
   }

   protected void fastSetSerializationScale(int fastSerializationScale) {
      this.fastSerializationScale = fastSerializationScale;
   }

   protected int fastSerializationScale() {
      return this.fastSerializationScale;
   }

   protected boolean fastSetFromBigDecimal(BigDecimal bigDecimal, boolean allowRounding) {
      return FastHiveDecimalImpl.fastSetFromBigDecimal(bigDecimal, allowRounding, this);
   }

   protected boolean fastSetFromBigInteger(BigInteger bigInteger) {
      return FastHiveDecimalImpl.fastSetFromBigInteger(bigInteger, this);
   }

   protected boolean fastSetFromBigIntegerAndScale(BigInteger bigInteger, int scale) {
      return FastHiveDecimalImpl.fastSetFromBigInteger(bigInteger, scale, this);
   }

   protected boolean fastSetFromString(String string, boolean trimBlanks) {
      byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
      return this.fastSetFromBytes(bytes, 0, bytes.length, trimBlanks);
   }

   protected boolean fastSetFromBytes(byte[] bytes, int offset, int length, boolean trimBlanks) {
      return FastHiveDecimalImpl.fastSetFromBytes(bytes, offset, length, trimBlanks, this);
   }

   protected boolean fastSetFromDigitsOnlyBytesAndScale(boolean isNegative, byte[] bytes, int offset, int length, int scale) {
      return FastHiveDecimalImpl.fastSetFromDigitsOnlyBytesAndScale(isNegative, bytes, offset, length, scale, this);
   }

   protected void fastSetFromInt(int intValue) {
      FastHiveDecimalImpl.fastSetFromInt(intValue, this);
   }

   protected void fastSetFromLong(long longValue) {
      FastHiveDecimalImpl.fastSetFromLong(longValue, this);
   }

   protected boolean fastSetFromLongAndScale(long longValue, int scale) {
      return FastHiveDecimalImpl.fastSetFromLongAndScale(longValue, scale, this);
   }

   protected boolean fastSetFromFloat(float floatValue) {
      return FastHiveDecimalImpl.fastSetFromFloat(floatValue, this);
   }

   protected boolean fastSetFromDouble(double doubleValue) {
      return FastHiveDecimalImpl.fastSetFromDouble(doubleValue, this);
   }

   protected void fastFractionPortion() {
      FastHiveDecimalImpl.fastFractionPortion(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastScale, this);
   }

   protected void fastIntegerPortion() {
      FastHiveDecimalImpl.fastIntegerPortion(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, this);
   }

   protected boolean fastSerializationUtilsRead(InputStream inputStream, int scale, byte[] scratchBytes) throws IOException, EOFException {
      return FastHiveDecimalImpl.fastSerializationUtilsRead(inputStream, scale, scratchBytes, this);
   }

   protected boolean fastSetFromBigIntegerBytesAndScale(byte[] bytes, int offset, int length, int scale) {
      return FastHiveDecimalImpl.fastSetFromBigIntegerBytesAndScale(bytes, offset, length, scale, this);
   }

   protected boolean fastSerializationUtilsWrite(OutputStream outputStream, long[] scratchLongs) throws IOException {
      return FastHiveDecimalImpl.fastSerializationUtilsWrite(outputStream, this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, scratchLongs);
   }

   protected void fastDeserialize64(long decimalLong, int scale) {
      FastHiveDecimalImpl.fastDeserialize64(decimalLong, scale, this);
   }

   protected long fastSerialize64(int scale) {
      return FastHiveDecimalImpl.fastSerialize64(scale, this.fastSignum, this.fast1, this.fast0, this.fastScale);
   }

   protected int fastBigIntegerBytes(long[] scratchLongs, byte[] buffer) {
      return FastHiveDecimalImpl.fastBigIntegerBytes(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, this.fastSerializationScale, scratchLongs, buffer);
   }

   protected int fastBigIntegerBytesScaled(int serializationScale, long[] scratchLongs, byte[] buffer) {
      return FastHiveDecimalImpl.fastBigIntegerBytesScaled(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, serializationScale, scratchLongs, buffer);
   }

   protected boolean fastIsByte() {
      return FastHiveDecimalImpl.fastIsByte(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale);
   }

   protected byte fastByteValueClip() {
      return FastHiveDecimalImpl.fastByteValueClip(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale);
   }

   protected boolean fastIsShort() {
      return FastHiveDecimalImpl.fastIsShort(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale);
   }

   protected short fastShortValueClip() {
      return FastHiveDecimalImpl.fastShortValueClip(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale);
   }

   protected boolean fastIsInt() {
      return FastHiveDecimalImpl.fastIsInt(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale);
   }

   protected int fastIntValueClip() {
      return FastHiveDecimalImpl.fastIntValueClip(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale);
   }

   protected boolean fastIsLong() {
      return FastHiveDecimalImpl.fastIsLong(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale);
   }

   protected long fastLongValueClip() {
      return FastHiveDecimalImpl.fastLongValueClip(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale);
   }

   protected float fastFloatValue() {
      return FastHiveDecimalImpl.fastFloatValue(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale);
   }

   protected double fastDoubleValue() {
      return FastHiveDecimalImpl.fastDoubleValue(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale);
   }

   protected BigInteger fastBigIntegerValue() {
      return FastHiveDecimalImpl.fastBigIntegerValue(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, this.fastSerializationScale);
   }

   protected BigDecimal fastBigDecimalValue() {
      return FastHiveDecimalImpl.fastBigDecimalValue(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale);
   }

   protected int fastScale() {
      return this.fastScale;
   }

   protected int fastSignum() {
      return this.fastSignum;
   }

   protected int fastCompareTo(FastHiveDecimal right) {
      return FastHiveDecimalImpl.fastCompareTo(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastScale, right.fastSignum, right.fast0, right.fast1, right.fast2, right.fastScale);
   }

   protected static int fastCompareTo(FastHiveDecimal left, FastHiveDecimal right) {
      return FastHiveDecimalImpl.fastCompareTo(left.fastSignum, left.fast0, left.fast1, left.fast2, left.fastScale, right.fastSignum, right.fast0, right.fast1, right.fast2, right.fastScale);
   }

   protected boolean fastEquals(FastHiveDecimal that) {
      return FastHiveDecimalImpl.fastEquals(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastScale, that.fastSignum, that.fast0, that.fast1, that.fast2, that.fastScale);
   }

   protected void fastAbs() {
      if (this.fastSignum != 0) {
         this.fastSignum = 1;
      }
   }

   protected void fastNegate() {
      if (this.fastSignum != 0) {
         this.fastSignum = this.fastSignum == 1 ? -1 : 1;
      }
   }

   protected int fastNewFasterHashCode() {
      return FastHiveDecimalImpl.fastNewFasterHashCode(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale);
   }

   protected int fastHashCode() {
      return FastHiveDecimalImpl.fastHashCode(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale);
   }

   protected int fastIntegerDigitCount() {
      return this.fastIntegerDigitCount;
   }

   protected int fastSqlPrecision() {
      return FastHiveDecimalImpl.fastSqlPrecision(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale);
   }

   protected int fastRawPrecision() {
      return FastHiveDecimalImpl.fastRawPrecision(this.fastSignum, this.fast0, this.fast1, this.fast2);
   }

   protected boolean fastScaleByPowerOfTen(int n, FastHiveDecimal fastResult) {
      return FastHiveDecimalImpl.fastScaleByPowerOfTen(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, n, fastResult);
   }

   protected static String fastRoundingModeToString(int roundingMode) {
      String roundingModeString;
      switch (roundingMode) {
         case 0:
            roundingModeString = "ROUND_UP";
            break;
         case 1:
            roundingModeString = "ROUND_DOWN";
            break;
         case 2:
            roundingModeString = "ROUND_CEILING";
            break;
         case 3:
            roundingModeString = "ROUND_FLOOR";
            break;
         case 4:
            roundingModeString = "ROUND_HALF_UP";
            break;
         case 5:
         default:
            roundingModeString = "Unknown";
            break;
         case 6:
            roundingModeString = "ROUND_HALF_EVEN";
      }

      return roundingModeString + " (" + roundingMode + ")";
   }

   protected boolean fastRound(int newScale, int roundingMode, FastHiveDecimal fastResult) {
      return FastHiveDecimalImpl.fastRound(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, newScale, roundingMode, fastResult);
   }

   protected boolean isAllZeroesBelow(int power) {
      return FastHiveDecimalImpl.isAllZeroesBelow(this.fastSignum, this.fast0, this.fast1, this.fast2, power);
   }

   protected boolean fastEnforcePrecisionScale(int maxPrecision, int maxScale) {
      if (maxPrecision > 0 && maxPrecision <= 38) {
         if (maxScale >= 0 && maxScale <= 38) {
            FastCheckPrecisionScaleStatus status = FastHiveDecimalImpl.fastCheckPrecisionScale(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, maxPrecision, maxScale);
            switch (status) {
               case NO_CHANGE:
                  return true;
               case OVERFLOW:
                  return false;
               case UPDATE_SCALE_DOWN:
                  if (!FastHiveDecimalImpl.fastUpdatePrecisionScale(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, maxPrecision, maxScale, status, this)) {
                     return false;
                  }

                  return true;
               default:
                  throw new RuntimeException("Unknown fast decimal check precision and scale status " + status);
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected FastCheckPrecisionScaleStatus fastCheckPrecisionScale(int maxPrecision, int maxScale) {
      return FastHiveDecimalImpl.fastCheckPrecisionScale(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, maxPrecision, maxScale);
   }

   protected boolean fastUpdatePrecisionScale(int maxPrecision, int maxScale, FastCheckPrecisionScaleStatus status, FastHiveDecimal fastResult) {
      return FastHiveDecimalImpl.fastUpdatePrecisionScale(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, maxPrecision, maxScale, status, fastResult);
   }

   protected boolean fastAdd(FastHiveDecimal fastRight, FastHiveDecimal fastResult) {
      return FastHiveDecimalImpl.fastAdd(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, fastRight.fastSignum, fastRight.fast0, fastRight.fast1, fastRight.fast2, fastRight.fastIntegerDigitCount, fastRight.fastScale, fastResult);
   }

   protected boolean fastSubtract(FastHiveDecimal fastRight, FastHiveDecimal fastResult) {
      return FastHiveDecimalImpl.fastSubtract(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, fastRight.fastSignum, fastRight.fast0, fastRight.fast1, fastRight.fast2, fastRight.fastIntegerDigitCount, fastRight.fastScale, fastResult);
   }

   protected boolean fastMultiply(FastHiveDecimal fastRight, FastHiveDecimal fastResult) {
      return FastHiveDecimalImpl.fastMultiply(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, fastRight.fastSignum, fastRight.fast0, fastRight.fast1, fastRight.fast2, fastRight.fastIntegerDigitCount, fastRight.fastScale, fastResult);
   }

   protected boolean fastRemainder(FastHiveDecimal fastRight, FastHiveDecimal fastResult) {
      return FastHiveDecimalImpl.fastRemainder(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, fastRight.fastSignum, fastRight.fast0, fastRight.fast1, fastRight.fast2, fastRight.fastIntegerDigitCount, fastRight.fastScale, fastResult);
   }

   protected boolean fastDivide(FastHiveDecimal fastRight, FastHiveDecimal fastResult) {
      return FastHiveDecimalImpl.fastDivide(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, fastRight.fastSignum, fastRight.fast0, fastRight.fast1, fastRight.fast2, fastRight.fastIntegerDigitCount, fastRight.fastScale, fastResult);
   }

   protected boolean fastPow(int exponent, FastHiveDecimal fastResult) {
      return FastHiveDecimalImpl.fastPow(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, exponent, fastResult);
   }

   protected String fastToString(byte[] scratchBuffer) {
      return FastHiveDecimalImpl.fastToString(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, -1, scratchBuffer);
   }

   protected String fastToString() {
      return FastHiveDecimalImpl.fastToString(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, -1);
   }

   protected String fastToFormatString(int formatScale) {
      return FastHiveDecimalImpl.fastToFormatString(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, formatScale);
   }

   protected String fastToFormatString(int formatScale, byte[] scratchBuffer) {
      return FastHiveDecimalImpl.fastToFormatString(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, formatScale, scratchBuffer);
   }

   protected String fastToDigitsOnlyString() {
      return FastHiveDecimalImpl.fastToDigitsOnlyString(this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount);
   }

   protected int fastToBytes(byte[] scratchBuffer) {
      return FastHiveDecimalImpl.fastToBytes(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, -1, scratchBuffer);
   }

   protected int fastToFormatBytes(int formatScale, byte[] scratchBuffer) {
      return FastHiveDecimalImpl.fastToFormatBytes(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, formatScale, scratchBuffer);
   }

   protected int fastToDigitsOnlyBytes(byte[] scratchBuffer) {
      return FastHiveDecimalImpl.fastToDigitsOnlyBytes(this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, scratchBuffer);
   }

   public String toString() {
      return FastHiveDecimalImpl.fastToString(this.fastSignum, this.fast0, this.fast1, this.fast2, this.fastIntegerDigitCount, this.fastScale, -1);
   }

   protected boolean fastIsValid() {
      return FastHiveDecimalImpl.fastIsValid(this);
   }

   protected void fastRaiseInvalidException() {
      FastHiveDecimalImpl.fastRaiseInvalidException(this);
   }

   protected void fastRaiseInvalidException(String parameters) {
      FastHiveDecimalImpl.fastRaiseInvalidException(this, parameters);
   }

   protected static enum FastCheckPrecisionScaleStatus {
      NO_CHANGE,
      OVERFLOW,
      UPDATE_SCALE_DOWN;
   }
}
