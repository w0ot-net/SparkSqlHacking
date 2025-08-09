package org.apache.hadoop.hive.serde2.io;

import org.apache.hadoop.io.WritableComparator;

public class DoubleWritable extends org.apache.hadoop.io.DoubleWritable {
   public DoubleWritable() {
   }

   public DoubleWritable(double value) {
      super(value);
   }

   static {
      WritableComparator.define(DoubleWritable.class, new org.apache.hadoop.io.DoubleWritable.Comparator());
   }
}
