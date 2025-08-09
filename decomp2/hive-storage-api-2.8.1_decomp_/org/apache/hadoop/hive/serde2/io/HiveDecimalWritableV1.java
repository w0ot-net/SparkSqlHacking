package org.apache.hadoop.hive.serde2.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.math.BigInteger;
import org.apache.hadoop.hive.common.type.HiveDecimalV1;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;
import org.apache.hive.common.util.SuppressFBWarnings;

public class HiveDecimalWritableV1 implements WritableComparable {
   private byte[] internalStorage = new byte[0];
   private int scale;

   @HiveDecimalWritableVersionV1
   public HiveDecimalWritableV1() {
   }

   @HiveDecimalWritableVersionV1
   public HiveDecimalWritableV1(String value) {
      this.set(HiveDecimalV1.create(value));
   }

   @HiveDecimalWritableVersionV1
   public HiveDecimalWritableV1(byte[] bytes, int scale) {
      this.set(bytes, scale);
   }

   @HiveDecimalWritableVersionV1
   public HiveDecimalWritableV1(HiveDecimalWritableV1 writable) {
      this.set(writable.getHiveDecimal());
   }

   @HiveDecimalWritableVersionV1
   public HiveDecimalWritableV1(HiveDecimalV1 value) {
      this.set(value);
   }

   @HiveDecimalWritableVersionV1
   public HiveDecimalWritableV1(long value) {
      this.set(HiveDecimalV1.create(value));
   }

   @HiveDecimalWritableVersionV1
   public void set(HiveDecimalV1 value) {
      this.set(value.unscaledValue().toByteArray(), value.scale());
   }

   @HiveDecimalWritableVersionV1
   public void set(HiveDecimalV1 value, int maxPrecision, int maxScale) {
      this.set(HiveDecimalV1.enforcePrecisionScale(value, maxPrecision, maxScale));
   }

   @HiveDecimalWritableVersionV1
   public void set(HiveDecimalWritableV1 writable) {
      this.set(writable.getHiveDecimal());
   }

   @HiveDecimalWritableVersionV1
   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP2"},
      justification = "Ref external obj for efficiency"
   )
   public void set(byte[] bytes, int scale) {
      this.internalStorage = bytes;
      this.scale = scale;
   }

   @HiveDecimalWritableVersionV1
   public HiveDecimalV1 getHiveDecimal() {
      return HiveDecimalV1.create(new BigInteger(this.internalStorage), this.scale);
   }

   @HiveDecimalWritableVersionV1
   public HiveDecimalV1 getHiveDecimal(int maxPrecision, int maxScale) {
      return HiveDecimalV1.enforcePrecisionScale(HiveDecimalV1.create(new BigInteger(this.internalStorage), this.scale), maxPrecision, maxScale);
   }

   @HiveDecimalWritableVersionV1
   public void readFields(DataInput in) throws IOException {
      this.scale = WritableUtils.readVInt(in);
      int byteArrayLen = WritableUtils.readVInt(in);
      if (this.internalStorage.length != byteArrayLen) {
         this.internalStorage = new byte[byteArrayLen];
      }

      in.readFully(this.internalStorage);
   }

   @HiveDecimalWritableVersionV1
   public void write(DataOutput out) throws IOException {
      WritableUtils.writeVInt(out, this.scale);
      WritableUtils.writeVInt(out, this.internalStorage.length);
      out.write(this.internalStorage);
   }

   @HiveDecimalWritableVersionV1
   public int compareTo(HiveDecimalWritableV1 that) {
      return this.getHiveDecimal().compareTo(that.getHiveDecimal());
   }

   @HiveDecimalWritableVersionV1
   public String toString() {
      return this.getHiveDecimal().toString();
   }

   @HiveDecimalWritableVersionV1
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         HiveDecimalWritableV1 bdw = (HiveDecimalWritableV1)other;
         return this.getHiveDecimal().compareTo(bdw.getHiveDecimal()) == 0;
      } else {
         return false;
      }
   }

   @HiveDecimalWritableVersionV1
   public int hashCode() {
      return this.getHiveDecimal().hashCode();
   }

   @HiveDecimalWritableVersionV1
   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP"},
      justification = "Expose internal rep for efficiency"
   )
   public byte[] getInternalStorage() {
      return this.internalStorage;
   }

   @HiveDecimalWritableVersionV1
   public int getScale() {
      return this.scale;
   }

   @HiveDecimalWritableVersionV1
   public static HiveDecimalWritableV1 enforcePrecisionScale(HiveDecimalWritableV1 writable, int precision, int scale) {
      if (writable == null) {
         return null;
      } else {
         HiveDecimalV1 dec = HiveDecimalV1.enforcePrecisionScale(writable.getHiveDecimal(), precision, scale);
         return dec == null ? null : new HiveDecimalWritableV1(dec);
      }
   }
}
