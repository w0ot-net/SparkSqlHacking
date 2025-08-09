package org.apache.hadoop.hive.serde2.io;

import org.apache.hadoop.hive.common.type.HiveBaseChar;
import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.io.WritableComparable;

public class HiveVarcharWritable extends HiveBaseCharWritable implements WritableComparable {
   public HiveVarcharWritable() {
   }

   public HiveVarcharWritable(HiveVarchar hc) {
      this.set(hc);
   }

   public HiveVarcharWritable(HiveVarcharWritable hcw) {
      this.set(hcw);
   }

   public void set(HiveVarchar val) {
      this.set(val.getValue());
   }

   public void set(String val) {
      this.set((String)val, -1);
   }

   public void set(HiveVarcharWritable val) {
      this.value.set(val.value);
   }

   public void set(HiveVarcharWritable val, int maxLength) {
      this.set(val.getHiveVarchar(), maxLength);
   }

   public void set(HiveVarchar val, int len) {
      this.set(val.getValue(), len);
   }

   public void set(String val, int maxLength) {
      this.value.set(HiveBaseChar.enforceMaxLength(val, maxLength));
   }

   public HiveVarchar getHiveVarchar() {
      return new HiveVarchar(this.value.toString(), -1);
   }

   public void enforceMaxLength(int maxLength) {
      if (this.value.getLength() > maxLength && this.getCharacterLength() > maxLength) {
         this.set(this.getHiveVarchar(), maxLength);
      }

   }

   public int compareTo(HiveVarcharWritable rhs) {
      return this.value.compareTo(rhs.value);
   }

   public String toString() {
      return this.value.toString();
   }
}
