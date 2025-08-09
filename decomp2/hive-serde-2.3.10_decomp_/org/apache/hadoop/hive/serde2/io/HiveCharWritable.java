package org.apache.hadoop.hive.serde2.io;

import org.apache.hadoop.hive.common.type.HiveBaseChar;
import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hive.common.util.HiveStringUtils;

public class HiveCharWritable extends HiveBaseCharWritable implements WritableComparable {
   public HiveCharWritable() {
   }

   public HiveCharWritable(HiveChar hc) {
      this.set(hc);
   }

   public HiveCharWritable(HiveCharWritable hcw) {
      this.set(hcw);
   }

   public void set(HiveChar val) {
      this.set((String)val.getValue(), -1);
   }

   public void set(String val) {
      this.set((String)val, -1);
   }

   public void set(HiveCharWritable val) {
      this.value.set(val.value);
   }

   public void set(HiveCharWritable val, int maxLength) {
      this.set(val.getHiveChar(), maxLength);
   }

   public void set(HiveChar val, int len) {
      this.set(val.getValue(), len);
   }

   public void set(String val, int maxLength) {
      this.value.set(HiveBaseChar.getPaddedValue(val, maxLength));
   }

   public HiveChar getHiveChar() {
      return new HiveChar(this.value.toString(), -1);
   }

   public void enforceMaxLength(int maxLength) {
      if (this.getCharacterLength() != maxLength) {
         this.set(this.getHiveChar(), maxLength);
      }

   }

   public Text getStrippedValue() {
      return new Text(this.getHiveChar().getStrippedValue());
   }

   public Text getPaddedValue() {
      return this.getTextValue();
   }

   public int getCharacterLength() {
      return HiveStringUtils.getTextUtfLength(this.getStrippedValue());
   }

   public int compareTo(HiveCharWritable rhs) {
      return this.getStrippedValue().compareTo(rhs.getStrippedValue());
   }

   public boolean equals(Object rhs) {
      if (rhs == this) {
         return true;
      } else {
         return rhs != null && rhs.getClass() == this.getClass() ? this.getStrippedValue().equals(((HiveCharWritable)rhs).getStrippedValue()) : false;
      }
   }

   public int hashCode() {
      return this.getStrippedValue().hashCode();
   }

   public String toString() {
      return this.getPaddedValue().toString();
   }
}
