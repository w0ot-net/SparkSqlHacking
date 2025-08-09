package org.apache.hadoop.hive.serde2.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import org.apache.hadoop.hive.common.type.FastHiveDecimal;
import org.apache.hadoop.hive.common.type.FastHiveDecimalImpl;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;
import org.apache.hive.common.util.SuppressFBWarnings;

public final class HiveDecimalWritable extends FastHiveDecimal implements WritableComparable {
   private boolean isSet;
   private long[] internalScratchLongs;
   private byte[] internalScratchBuffer;
   @HiveDecimalWritableVersionV2
   public static final int DECIMAL64_DECIMAL_DIGITS = 18;
   private static final byte[] EMPTY_ARRAY = new byte[0];

   @HiveDecimalWritableVersionV1
   public HiveDecimalWritable() {
      this.isSet = false;
      this.internalScratchLongs = null;
      this.internalScratchBuffer = null;
   }

   @HiveDecimalWritableVersionV1
   public HiveDecimalWritable(String string) {
      this.isSet = this.fastSetFromString(string, false);
      if (!this.isSet) {
         this.fastReset();
      }

   }

   @HiveDecimalWritableVersionV1
   public HiveDecimalWritable(byte[] bigIntegerBytes, int scale) {
      this.setFromBigIntegerBytesAndScale(bigIntegerBytes, scale);
   }

   @HiveDecimalWritableVersionV1
   public HiveDecimalWritable(HiveDecimalWritable writable) {
      this.set(writable);
   }

   @HiveDecimalWritableVersionV1
   public HiveDecimalWritable(HiveDecimal value) {
      this.set(value);
   }

   @HiveDecimalWritableVersionV1
   public HiveDecimalWritable(long longValue) {
      this.setFromLong(longValue);
   }

   @HiveDecimalWritableVersionV1
   public void set(HiveDecimal value) {
      if (value == null) {
         this.fastReset();
         this.isSet = false;
      } else {
         this.fastSet(value);
         this.isSet = true;
      }

   }

   @HiveDecimalWritableVersionV1
   public void set(HiveDecimal value, int maxPrecision, int maxScale) {
      this.set(value);
      if (this.isSet) {
         this.isSet = this.fastEnforcePrecisionScale(maxPrecision, maxScale);
         if (!this.isSet) {
            this.fastReset();
         }
      }

   }

   @HiveDecimalWritableVersionV1
   public void set(HiveDecimalWritable writable) {
      if (writable != null && writable.isSet()) {
         this.fastSet(writable);
         this.isSet = true;
      } else {
         this.fastReset();
         this.isSet = false;
      }

   }

   @HiveDecimalWritableVersionV1
   public void set(byte[] bigIntegerBytes, int scale) {
      this.setFromBigIntegerBytesAndScale(bigIntegerBytes, scale);
   }

   @HiveDecimalWritableVersionV2
   public void set(HiveDecimalWritable writable, int maxPrecision, int maxScale) {
      this.set(writable);
      if (this.isSet) {
         this.isSet = this.fastEnforcePrecisionScale(maxPrecision, maxScale);
         if (!this.isSet) {
            this.fastReset();
         }
      }

   }

   @HiveDecimalWritableVersionV2
   public void setFromLong(long longValue) {
      this.fastReset();
      this.fastSetFromLong(longValue);
      this.isSet = true;
   }

   @HiveDecimalWritableVersionV2
   public void setFromDouble(double doubleValue) {
      this.fastReset();
      this.isSet = this.fastSetFromDouble(doubleValue);
      if (!this.isSet) {
         this.fastReset();
      }

   }

   @HiveDecimalWritableVersionV2
   public void setFromBytes(byte[] bytes, int offset, int length) {
      this.fastReset();
      this.isSet = this.fastSetFromBytes(bytes, offset, length, false);
      if (!this.isSet) {
         this.fastReset();
      }

   }

   @HiveDecimalWritableVersionV2
   public void setFromBytes(byte[] bytes, int offset, int length, boolean trimBlanks) {
      this.fastReset();
      this.isSet = this.fastSetFromBytes(bytes, offset, length, trimBlanks);
      if (!this.isSet) {
         this.fastReset();
      }

   }

   @HiveDecimalWritableVersionV2
   public void setFromDigitsOnlyBytesWithScale(boolean isNegative, byte[] bytes, int offset, int length, int scale) {
      this.fastReset();
      this.isSet = this.fastSetFromDigitsOnlyBytesAndScale(isNegative, bytes, offset, length, scale);
      if (!this.isSet) {
         this.fastReset();
      }

   }

   @HiveDecimalWritableVersionV2
   public void setFromBigIntegerBytesAndScale(byte[] bigIntegerBytes, int scale) {
      this.fastReset();
      this.isSet = this.fastSetFromBigIntegerBytesAndScale(bigIntegerBytes, 0, bigIntegerBytes.length, scale);
      if (!this.isSet) {
         this.fastReset();
      }

   }

   @HiveDecimalWritableVersionV2
   public void setFromBigIntegerBytesAndScale(byte[] bigIntegerBytes, int offset, int length, int scale) {
      this.fastReset();
      this.isSet = this.fastSetFromBigIntegerBytesAndScale(bigIntegerBytes, offset, length, scale);
      if (!this.isSet) {
         this.fastReset();
      }

   }

   @HiveDecimalWritableVersionV2
   public void setFromLongAndScale(long longValue, int scale) {
      this.fastReset();
      this.isSet = this.fastSetFromLongAndScale(longValue, scale);
      if (!this.isSet) {
         this.fastReset();
      }

   }

   @HiveDecimalWritableVersionV2
   public boolean isSet() {
      return this.isSet;
   }

   @HiveDecimalWritableVersionV1
   public HiveDecimal getHiveDecimal() {
      if (!this.isSet) {
         return null;
      } else {
         HiveDecimal result = HiveDecimal.createFromFast(this);
         return result;
      }
   }

   @HiveDecimalWritableVersionV1
   public HiveDecimal getHiveDecimal(int maxPrecision, int maxScale) {
      if (!this.isSet) {
         return null;
      } else {
         HiveDecimal dec = HiveDecimal.createFromFast(this);
         HiveDecimal result = HiveDecimal.enforcePrecisionScale(dec, maxPrecision, maxScale);
         return result;
      }
   }

   @HiveDecimalWritableVersionV1
   public void readFields(DataInput in) throws IOException {
      int scale = WritableUtils.readVInt(in);
      int byteArrayLen = WritableUtils.readVInt(in);
      byte[] bytes = new byte[byteArrayLen];
      in.readFully(bytes);
      this.fastReset();
      if (!this.fastSetFromBigIntegerBytesAndScale(bytes, 0, bytes.length, scale)) {
         throw new IOException("Couldn't convert decimal");
      } else {
         this.isSet = true;
      }
   }

   @HiveDecimalWritableVersionV1
   public void write(DataOutput out) throws IOException {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         if (this.internalScratchLongs == null) {
            this.internalScratchLongs = new long[6];
            this.internalScratchBuffer = new byte[49];
         }

         this.write(out, this.internalScratchLongs, this.internalScratchBuffer);
      }
   }

   @HiveDecimalWritableVersionV2
   public void write(DataOutput out, long[] scratchLongs, byte[] scratchBuffer) throws IOException {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         WritableUtils.writeVInt(out, this.fastScale());
         int byteLength = this.fastBigIntegerBytes(scratchLongs, scratchBuffer);
         if (byteLength == 0) {
            throw new RuntimeException("Couldn't convert decimal to binary");
         } else {
            WritableUtils.writeVInt(out, byteLength);
            out.write(scratchBuffer, 0, byteLength);
         }
      }
   }

   @HiveDecimalWritableVersionV2
   public boolean serializationUtilsRead(InputStream inputStream, int scale, byte[] scratchBytes) throws IOException {
      this.fastReset();
      this.isSet = this.fastSerializationUtilsRead(inputStream, scale, scratchBytes);
      return this.isSet;
   }

   @HiveDecimalWritableVersionV2
   public boolean serializationUtilsWrite(OutputStream outputStream, long[] scratchLongs) throws IOException {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastSerializationUtilsWrite(outputStream, scratchLongs);
      }
   }

   @HiveDecimalWritableVersionV2
   public static boolean isPrecisionDecimal64(int precision) {
      return precision <= 18;
   }

   @HiveDecimalWritableVersionV2
   public static long getDecimal64AbsMax(int precision) {
      return FastHiveDecimalImpl.getDecimal64AbsMax(precision);
   }

   @HiveDecimalWritableVersionV2
   public void deserialize64(long decimal64Long, int scale) {
      this.fastDeserialize64(decimal64Long, scale);
      this.isSet = true;
   }

   @HiveDecimalWritableVersionV2
   public long serialize64(int scale) {
      return this.fastSerialize64(scale);
   }

   @HiveDecimalWritableVersionV2
   public boolean isValid() {
      return !this.isSet ? false : FastHiveDecimalImpl.fastIsValid(this);
   }

   @HiveDecimalWritableVersionV2
   public int bigIntegerBytesInternalScratch() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         if (this.internalScratchLongs == null) {
            this.internalScratchLongs = new long[6];
            this.internalScratchBuffer = new byte[49];
         }

         int byteLength = this.fastBigIntegerBytes(this.internalScratchLongs, this.internalScratchBuffer);
         if (byteLength == 0) {
            throw new RuntimeException("Couldn't convert decimal to binary");
         } else {
            return byteLength;
         }
      }
   }

   @HiveDecimalWritableVersionV2
   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP"},
      justification = "Expose internal rep for efficiency"
   )
   public byte[] bigIntegerBytesInternalScratchBuffer() {
      return this.internalScratchBuffer;
   }

   @HiveDecimalWritableVersionV2
   public byte[] bigIntegerBytesCopy(long[] scratchLongs, byte[] scratchBuffer) {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         int byteLength = this.fastBigIntegerBytes(scratchLongs, scratchBuffer);
         if (byteLength == 0) {
            throw new RuntimeException("Couldn't convert decimal to binary");
         } else {
            return Arrays.copyOf(scratchBuffer, byteLength);
         }
      }
   }

   @HiveDecimalWritableVersionV2
   public int bigIntegerBytes(long[] scratchLongs, byte[] scratchBuffer) {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         int byteLength = this.fastBigIntegerBytes(scratchLongs, scratchBuffer);
         return byteLength;
      }
   }

   @HiveDecimalWritableVersionV2
   public int signum() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastSignum();
      }
   }

   @HiveDecimalWritableVersionV2
   public int precision() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastSqlPrecision();
      }
   }

   @HiveDecimalWritableVersionV2
   public int rawPrecision() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastRawPrecision();
      }
   }

   @HiveDecimalWritableVersionV2
   public int scale() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastScale();
      }
   }

   @HiveDecimalWritableVersionV2
   public boolean isByte() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastIsByte();
      }
   }

   @HiveDecimalWritableVersionV2
   public byte byteValue() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastByteValueClip();
      }
   }

   @HiveDecimalWritableVersionV2
   public boolean isShort() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastIsShort();
      }
   }

   @HiveDecimalWritableVersionV2
   public short shortValue() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastShortValueClip();
      }
   }

   @HiveDecimalWritableVersionV2
   public boolean isInt() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastIsInt();
      }
   }

   @HiveDecimalWritableVersionV2
   public int intValue() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastIntValueClip();
      }
   }

   @HiveDecimalWritableVersionV2
   public boolean isLong() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastIsLong();
      }
   }

   @HiveDecimalWritableVersionV2
   public long longValue() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastLongValueClip();
      }
   }

   @HiveDecimalWritableVersionV2
   public float floatValue() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastFloatValue();
      }
   }

   @HiveDecimalWritableVersionV2
   public double doubleValue() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastDoubleValue();
      }
   }

   @HiveDecimalWritableVersionV2
   public void mutateAbs() {
      if (this.isSet) {
         this.fastAbs();
      }
   }

   @HiveDecimalWritableVersionV2
   public void mutateNegate() {
      if (this.isSet) {
         this.fastNegate();
      }
   }

   @HiveDecimalWritableVersionV2
   public void mutateAdd(HiveDecimalWritable decWritable) {
      if (this.isSet && decWritable.isSet) {
         this.isSet = this.fastAdd(decWritable, this);
      } else {
         this.isSet = false;
      }
   }

   @HiveDecimalWritableVersionV2
   public void mutateAdd(HiveDecimal dec) {
      if (dec == null) {
         this.isSet = false;
      } else if (this.isSet) {
         this.isSet = this.fastAdd(dec, this);
      }
   }

   @HiveDecimalWritableVersionV2
   public void mutateSubtract(HiveDecimalWritable decWritable) {
      if (this.isSet && decWritable.isSet) {
         this.isSet = this.fastSubtract(decWritable, this);
      } else {
         this.isSet = false;
      }
   }

   @HiveDecimalWritableVersionV2
   public void mutateSubtract(HiveDecimal dec) {
      if (dec == null) {
         this.isSet = false;
      } else if (this.isSet) {
         this.isSet = this.fastSubtract(dec, this);
      }
   }

   @HiveDecimalWritableVersionV2
   public void mutateMultiply(HiveDecimalWritable decWritable) {
      if (this.isSet && decWritable.isSet) {
         this.isSet = this.fastMultiply(decWritable, this);
      } else {
         this.isSet = false;
      }
   }

   @HiveDecimalWritableVersionV2
   public void mutateMultiply(HiveDecimal dec) {
      if (dec == null) {
         this.isSet = false;
      } else if (this.isSet) {
         this.isSet = this.fastMultiply(dec, this);
      }
   }

   @HiveDecimalWritableVersionV2
   public void mutateDivide(HiveDecimalWritable decWritable) {
      if (this.isSet && decWritable.isSet) {
         this.isSet = this.fastDivide(decWritable, this);
      } else {
         this.isSet = false;
      }
   }

   @HiveDecimalWritableVersionV2
   public void mutateDivide(HiveDecimal dec) {
      if (dec == null) {
         this.isSet = false;
      } else if (this.isSet) {
         this.isSet = this.fastDivide(dec, this);
      }
   }

   @HiveDecimalWritableVersionV2
   public void mutateRemainder(HiveDecimalWritable decWritable) {
      if (this.isSet && decWritable.isSet) {
         this.isSet = this.fastRemainder(decWritable, this);
      } else {
         this.isSet = false;
      }
   }

   @HiveDecimalWritableVersionV2
   public void mutateRemainder(HiveDecimal dec) {
      if (dec == null) {
         this.isSet = false;
      } else if (this.isSet) {
         this.isSet = this.fastRemainder(dec, this);
      }
   }

   @HiveDecimalWritableVersionV2
   public void mutateScaleByPowerOfTen(int power) {
      if (this.isSet) {
         this.isSet = this.fastScaleByPowerOfTen(power, this);
      }
   }

   @HiveDecimalWritableVersionV2
   public void mutateFractionPortion() {
      if (this.isSet) {
         this.fastFractionPortion();
      }
   }

   @HiveDecimalWritableVersionV2
   public void mutateIntegerPortion() {
      if (this.isSet) {
         this.fastIntegerPortion();
      }
   }

   @HiveDecimalWritableVersionV1
   public int compareTo(HiveDecimalWritable writable) {
      if (this.isSet() && writable != null && writable.isSet()) {
         return this.fastCompareTo(writable);
      } else {
         throw new RuntimeException("Invalid comparision operand(s)");
      }
   }

   @HiveDecimalWritableVersionV2
   public int compareTo(HiveDecimal dec) {
      if (this.isSet() && dec != null) {
         return this.fastCompareTo(dec);
      } else {
         throw new RuntimeException("Invalid comparision operand(s)");
      }
   }

   @HiveDecimalWritableVersionV2
   public static int compareTo(HiveDecimal dec, HiveDecimalWritable writable) {
      if (dec != null && writable.isSet()) {
         return FastHiveDecimal.fastCompareTo(dec, writable);
      } else {
         throw new RuntimeException("Invalid comparision operand(s)");
      }
   }

   @HiveDecimalWritableVersionV2
   public int toBytes(byte[] scratchBuffer) {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastToBytes(scratchBuffer);
      }
   }

   @HiveDecimalWritableVersionV1
   public String toString() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastToString();
      }
   }

   @HiveDecimalWritableVersionV2
   public String toString(byte[] scratchBuffer) {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastSerializationScale() != -1 ? this.fastToFormatString(this.fastSerializationScale(), scratchBuffer) : this.fastToString(scratchBuffer);
      }
   }

   @HiveDecimalWritableVersionV2
   public String toFormatString(int formatScale) {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastToFormatString(formatScale);
      }
   }

   @HiveDecimalWritableVersionV2
   public int toFormatBytes(int formatScale, byte[] scratchBuffer) {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastToFormatBytes(formatScale, scratchBuffer);
      }
   }

   @HiveDecimalWritableVersionV2
   public int toDigitsOnlyBytes(byte[] scratchBuffer) {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastToDigitsOnlyBytes(scratchBuffer);
      }
   }

   @HiveDecimalWritableVersionV1
   public boolean equals(Object other) {
      if (!this.isSet) {
         return false;
      } else if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         HiveDecimalWritable otherHiveDecWritable = (HiveDecimalWritable)other;
         return !otherHiveDecWritable.isSet() ? false : this.fastEquals(otherHiveDecWritable);
      } else {
         return false;
      }
   }

   @HiveDecimalWritableVersionV2
   public int newFasterHashCode() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastNewFasterHashCode();
      }
   }

   @HiveDecimalWritableVersionV1
   public int hashCode() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastHashCode();
      }
   }

   @HiveDecimalWritableVersionV1
   public byte[] getInternalStorage() {
      if (!this.isSet()) {
         return EMPTY_ARRAY;
      } else {
         if (this.internalScratchLongs == null) {
            this.internalScratchLongs = new long[6];
            this.internalScratchBuffer = new byte[49];
         }

         return this.bigIntegerBytesCopy(this.internalScratchLongs, this.internalScratchBuffer);
      }
   }

   @HiveDecimalWritableVersionV1
   public int getScale() {
      if (!this.isSet()) {
         throw new RuntimeException("no value set");
      } else {
         return this.fastScale();
      }
   }

   @HiveDecimalWritableVersionV2
   public void mutateSetScale(int roundingPoint, int roundingMode) {
      if (this.isSet) {
         this.isSet = this.fastRound(roundingPoint, roundingMode, this);
         if (!this.isSet) {
            this.fastReset();
         }

      }
   }

   @HiveDecimalWritableVersionV2
   public boolean mutateEnforcePrecisionScale(int precision, int scale) {
      if (!this.isSet) {
         return false;
      } else {
         this.isSet = this.fastEnforcePrecisionScale(precision, scale);
         if (!this.isSet) {
            this.fastReset();
         }

         return this.isSet;
      }
   }

   @HiveDecimalWritableVersionV1
   public static HiveDecimalWritable enforcePrecisionScale(HiveDecimalWritable writable, int precision, int scale) {
      if (!writable.isSet) {
         return null;
      } else {
         HiveDecimalWritable result = new HiveDecimalWritable(writable);
         result.mutateEnforcePrecisionScale(precision, scale);
         return !result.isSet() ? null : result;
      }
   }
}
