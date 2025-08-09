package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableFloatObjectInspector;
import org.apache.hadoop.io.FloatWritable;

public class LazyBinaryFloat extends LazyBinaryPrimitive {
   LazyBinaryFloat(WritableFloatObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new FloatWritable();
   }

   LazyBinaryFloat(LazyBinaryFloat copy) {
      super((LazyBinaryPrimitive)copy);
      this.data = new FloatWritable(((FloatWritable)copy.data).get());
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      assert 4 == length;

      ((FloatWritable)this.data).set(Float.intBitsToFloat(LazyBinaryUtils.byteArrayToInt(bytes.getData(), start)));
   }
}
