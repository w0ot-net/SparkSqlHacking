package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableShortObjectInspector;

public class LazyBinaryShort extends LazyBinaryPrimitive {
   LazyBinaryShort(WritableShortObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new ShortWritable();
   }

   LazyBinaryShort(LazyBinaryShort copy) {
      super((LazyBinaryPrimitive)copy);
      this.data = new ShortWritable(((ShortWritable)copy.data).get());
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      assert 2 == length;

      ((ShortWritable)this.data).set(LazyBinaryUtils.byteArrayToShort(bytes.getData(), start));
   }
}
