package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableIntObjectInspector;
import org.apache.hadoop.io.IntWritable;

public class LazyBinaryInteger extends LazyBinaryPrimitive {
   LazyBinaryUtils.VInt vInt = new LazyBinaryUtils.VInt();

   LazyBinaryInteger(WritableIntObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new IntWritable();
   }

   LazyBinaryInteger(LazyBinaryInteger copy) {
      super((LazyBinaryPrimitive)copy);
      this.data = new IntWritable(((IntWritable)copy.data).get());
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      LazyBinaryUtils.readVInt(bytes.getData(), start, this.vInt);

      assert length == this.vInt.length;

      ((IntWritable)this.data).set(this.vInt.value);
   }
}
