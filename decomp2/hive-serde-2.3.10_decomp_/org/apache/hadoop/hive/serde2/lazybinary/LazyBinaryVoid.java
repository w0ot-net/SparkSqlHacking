package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableVoidObjectInspector;

public class LazyBinaryVoid extends LazyBinaryPrimitive {
   LazyBinaryVoid(WritableVoidObjectInspector oi) {
      super((ObjectInspector)oi);
   }

   LazyBinaryVoid(LazyBinaryVoid copy) {
      super((LazyBinaryPrimitive)copy);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
   }
}
