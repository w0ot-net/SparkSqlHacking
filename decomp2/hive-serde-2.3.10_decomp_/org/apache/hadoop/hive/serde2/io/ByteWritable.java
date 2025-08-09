package org.apache.hadoop.hive.serde2.io;

import org.apache.hadoop.io.WritableComparator;

public class ByteWritable extends org.apache.hadoop.io.ByteWritable {
   public ByteWritable(byte b) {
      super(b);
   }

   public ByteWritable() {
   }

   static {
      WritableComparator.define(ByteWritable.class, new org.apache.hadoop.io.ByteWritable.Comparator());
   }
}
