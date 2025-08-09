package org.apache.hadoop.hive.serde2.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryUtils;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveIntervalYearMonthWritable implements WritableComparable {
   private static final Logger LOG = LoggerFactory.getLogger(HiveIntervalYearMonthWritable.class);
   protected HiveIntervalYearMonth intervalValue = new HiveIntervalYearMonth();

   public HiveIntervalYearMonthWritable() {
   }

   public HiveIntervalYearMonthWritable(HiveIntervalYearMonth hiveInterval) {
      this.intervalValue.set(hiveInterval);
   }

   public HiveIntervalYearMonthWritable(HiveIntervalYearMonthWritable hiveIntervalWritable) {
      this.intervalValue.set(hiveIntervalWritable.intervalValue);
   }

   public void set(int years, int months) {
      this.intervalValue.set(years, months);
   }

   public void set(HiveIntervalYearMonth hiveInterval) {
      this.intervalValue.set(hiveInterval);
   }

   public void set(HiveIntervalYearMonthWritable hiveIntervalWritable) {
      this.intervalValue.set(hiveIntervalWritable.intervalValue);
   }

   public void set(int totalMonths) {
      this.intervalValue.set(totalMonths);
   }

   public HiveIntervalYearMonth getHiveIntervalYearMonth() {
      return new HiveIntervalYearMonth(this.intervalValue);
   }

   public void readFields(DataInput in) throws IOException {
      this.set(WritableUtils.readVInt(in));
   }

   public void write(DataOutput out) throws IOException {
      WritableUtils.writeVInt(out, this.intervalValue.getTotalMonths());
   }

   public void writeToByteStream(ByteStream.RandomAccessOutput byteStream) {
      LazyBinaryUtils.writeVInt(byteStream, this.intervalValue.getTotalMonths());
   }

   public void setFromBytes(byte[] bytes, int offset, int length, LazyBinaryUtils.VInt vInt) {
      LazyBinaryUtils.readVInt(bytes, offset, vInt);

      assert length == vInt.length;

      this.set(vInt.value);
   }

   public int compareTo(HiveIntervalYearMonthWritable other) {
      return this.intervalValue.compareTo(other.intervalValue);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof HiveIntervalYearMonthWritable)) {
         return false;
      } else {
         return 0 == this.compareTo((HiveIntervalYearMonthWritable)obj);
      }
   }

   public int hashCode() {
      return this.intervalValue.hashCode();
   }

   public String toString() {
      return this.intervalValue.toString();
   }
}
