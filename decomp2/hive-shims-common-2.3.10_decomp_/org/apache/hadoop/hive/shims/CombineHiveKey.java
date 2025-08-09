package org.apache.hadoop.hive.shims;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;

public class CombineHiveKey implements WritableComparable {
   Object key;

   public CombineHiveKey(Object key) {
      this.key = key;
   }

   public Object getKey() {
      return this.key;
   }

   public void setKey(Object key) {
      this.key = key;
   }

   public void write(DataOutput out) throws IOException {
      throw new IOException("Method not supported");
   }

   public void readFields(DataInput in) throws IOException {
      throw new IOException("Method not supported");
   }

   public int compareTo(Object w) {
      assert false;

      return 0;
   }
}
