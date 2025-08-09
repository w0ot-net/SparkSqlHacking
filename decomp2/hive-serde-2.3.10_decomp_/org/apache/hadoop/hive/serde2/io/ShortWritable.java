package org.apache.hadoop.hive.serde2.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class ShortWritable implements WritableComparable {
   private short value;

   public void write(DataOutput out) throws IOException {
      out.writeShort(this.value);
   }

   public void readFields(DataInput in) throws IOException {
      this.value = in.readShort();
   }

   public ShortWritable(short s) {
      this.value = s;
   }

   public ShortWritable() {
      this.value = 0;
   }

   public void set(short value) {
      this.value = value;
   }

   public short get() {
      return this.value;
   }

   public boolean equals(Object o) {
      if (o != null && o.getClass() == ShortWritable.class) {
         return this.get() == ((ShortWritable)o).get();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.value;
   }

   public String toString() {
      return String.valueOf(this.get());
   }

   public int compareTo(Object o) {
      int thisValue = this.value;
      int thatValue = ((ShortWritable)o).value;
      return thisValue - thatValue;
   }

   static {
      WritableComparator.define(ShortWritable.class, new Comparator());
   }

   public static class Comparator extends WritableComparator {
      public Comparator() {
         super(ShortWritable.class);
      }

      public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
         int a1 = (short)readUnsignedShort(b1, s1);
         int a2 = (short)readUnsignedShort(b2, s2);
         return a1 - a2;
      }
   }
}
