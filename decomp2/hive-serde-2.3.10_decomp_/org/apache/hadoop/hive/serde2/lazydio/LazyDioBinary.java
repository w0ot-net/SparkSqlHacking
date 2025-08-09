package org.apache.hadoop.hive.serde2.lazydio;

import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazy.LazyBinary;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyBinaryObjectInspector;
import org.apache.hadoop.io.BytesWritable;

public class LazyDioBinary extends LazyBinary {
   public LazyDioBinary(LazyBinaryObjectInspector oi) {
      super(oi);
   }

   LazyDioBinary(LazyDioBinary copy) {
      super((LazyBinary)copy);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      if (bytes == null) {
         throw new RuntimeException("bytes cannot be null!");
      } else {
         this.isNull = false;
         byte[] recv = new byte[length];
         System.arraycopy(bytes.getData(), start, recv, 0, length);
         ((BytesWritable)this.data).set(recv, 0, length);
      }
   }
}
