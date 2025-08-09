package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableDoubleObjectInspector;

public class LazyBinaryDouble extends LazyBinaryPrimitive {
   LazyBinaryDouble(WritableDoubleObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new DoubleWritable();
   }

   LazyBinaryDouble(LazyBinaryDouble copy) {
      super((LazyBinaryPrimitive)copy);
      this.data = new DoubleWritable(((DoubleWritable)copy.data).get());
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      assert 8 == length;

      ((DoubleWritable)this.data).set(Double.longBitsToDouble(LazyBinaryUtils.byteArrayToLong(bytes.getData(), start)));
   }
}
