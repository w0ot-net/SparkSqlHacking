package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableLongObjectInspector;
import org.apache.hadoop.io.LongWritable;

public class LazyBinaryLong extends LazyBinaryPrimitive {
   LazyBinaryUtils.VLong vLong = new LazyBinaryUtils.VLong();

   LazyBinaryLong(WritableLongObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new LongWritable();
   }

   LazyBinaryLong(LazyBinaryLong copy) {
      super((LazyBinaryPrimitive)copy);
      this.data = new LongWritable(((LongWritable)copy.data).get());
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      LazyBinaryUtils.readVLong(bytes.getData(), start, this.vLong);

      assert length == this.vLong.length;

      ((LongWritable)this.data).set(this.vLong.value);
   }
}
