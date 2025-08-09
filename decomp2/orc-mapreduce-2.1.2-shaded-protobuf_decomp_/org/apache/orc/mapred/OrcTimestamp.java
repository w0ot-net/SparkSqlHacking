package org.apache.orc.mapred;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.Timestamp;
import org.apache.hadoop.io.WritableComparable;

public class OrcTimestamp extends Timestamp implements WritableComparable {
   public OrcTimestamp() {
      super(0L);
   }

   public OrcTimestamp(long time) {
      super(time);
   }

   public OrcTimestamp(String timeStr) {
      super(0L);
      Timestamp t = Timestamp.valueOf(timeStr);
      this.setTime(t.getTime());
      this.setNanos(t.getNanos());
   }

   public void write(DataOutput output) throws IOException {
      output.writeLong(this.getTime());
      output.writeInt(this.getNanos());
   }

   public void readFields(DataInput input) throws IOException {
      this.setTime(input.readLong());
      this.setNanos(input.readInt());
   }

   public void set(String timeStr) {
      Timestamp t = Timestamp.valueOf(timeStr);
      this.setTime(t.getTime());
      this.setNanos(t.getNanos());
   }
}
