package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableByteObjectInspector;

public class LazyBinaryByte extends LazyBinaryPrimitive {
   LazyBinaryByte(WritableByteObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new ByteWritable();
   }

   LazyBinaryByte(LazyBinaryByte copy) {
      super((LazyBinaryPrimitive)copy);
      this.data = new ByteWritable(((ByteWritable)copy.data).get());
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      assert 1 == length;

      ((ByteWritable)this.data).set(bytes.getData()[start]);
   }
}
