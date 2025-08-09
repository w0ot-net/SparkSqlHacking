package org.apache.hadoop.hive.serde2.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryUtils;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveIntervalDayTimeWritable implements WritableComparable {
   private static final Logger LOG = LoggerFactory.getLogger(HiveIntervalDayTimeWritable.class);
   protected HiveIntervalDayTime intervalValue = new HiveIntervalDayTime();

   public HiveIntervalDayTimeWritable() {
   }

   public HiveIntervalDayTimeWritable(HiveIntervalDayTime value) {
      this.intervalValue.set(value);
   }

   public HiveIntervalDayTimeWritable(HiveIntervalDayTimeWritable writable) {
      this.intervalValue.set(writable.intervalValue);
   }

   public void set(int days, int hours, int minutes, int seconds, int nanos) {
      this.intervalValue.set(days, hours, minutes, seconds, nanos);
   }

   public void set(HiveIntervalDayTime value) {
      this.intervalValue.set(value);
   }

   public void set(HiveIntervalDayTimeWritable writable) {
      this.intervalValue.set(writable.intervalValue);
   }

   public void set(long totalSeconds, int nanos) {
      this.intervalValue.set(totalSeconds, nanos);
   }

   public HiveIntervalDayTime getHiveIntervalDayTime() {
      return new HiveIntervalDayTime(this.intervalValue);
   }

   public void readFields(DataInput in) throws IOException {
      this.set(WritableUtils.readVLong(in), WritableUtils.readVInt(in));
   }

   public void write(DataOutput out) throws IOException {
      WritableUtils.writeVLong(out, this.intervalValue.getTotalSeconds());
      WritableUtils.writeVInt(out, this.intervalValue.getNanos());
   }

   public void writeToByteStream(ByteStream.RandomAccessOutput byteStream) {
      LazyBinaryUtils.writeVLong(byteStream, this.intervalValue.getTotalSeconds());
      LazyBinaryUtils.writeVInt(byteStream, this.intervalValue.getNanos());
   }

   public void setFromBytes(byte[] bytes, int offset, int length, LazyBinaryUtils.VInt vInt, LazyBinaryUtils.VLong vLong) {
      LazyBinaryUtils.readVLong(bytes, offset, vLong);
      LazyBinaryUtils.readVInt(bytes, offset + vLong.length, vInt);

      assert length == vInt.length + vLong.length;

      this.set(vLong.value, vInt.value);
   }

   public int compareTo(HiveIntervalDayTimeWritable other) {
      return this.intervalValue.compareTo(other.intervalValue);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof HiveIntervalDayTimeWritable)) {
         return false;
      } else {
         return 0 == this.compareTo((HiveIntervalDayTimeWritable)obj);
      }
   }

   public int hashCode() {
      return this.intervalValue.hashCode();
   }

   public String toString() {
      return this.intervalValue.toString();
   }
}
