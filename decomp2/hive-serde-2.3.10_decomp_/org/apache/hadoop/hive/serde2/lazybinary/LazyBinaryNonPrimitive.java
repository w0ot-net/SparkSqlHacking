package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazy.LazyUtils;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public abstract class LazyBinaryNonPrimitive extends LazyBinaryObject {
   protected ByteArrayRef bytes = null;
   protected int start = 0;
   protected int length = 0;

   protected LazyBinaryNonPrimitive(ObjectInspector oi) {
      super(oi);
   }

   public Object getObject() {
      return this;
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      if (null == bytes) {
         throw new RuntimeException("bytes cannot be null!");
      } else if (length <= 0) {
         throw new RuntimeException("length should be positive!");
      } else {
         this.bytes = bytes;
         this.start = start;
         this.length = length;
      }
   }

   public int hashCode() {
      return LazyUtils.hashBytes(this.bytes.getData(), this.start, this.length);
   }
}
