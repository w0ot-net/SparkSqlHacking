package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableBinaryObjectInspector;
import org.apache.hadoop.io.BytesWritable;

public class LazyBinaryBinary extends LazyBinaryPrimitive {
   LazyBinaryBinary(LazyBinaryPrimitive copy) {
      super(copy);
      BytesWritable incoming = (BytesWritable)copy.getWritableObject();
      byte[] outgoing = new byte[incoming.getLength()];
      System.arraycopy(incoming.getBytes(), 0, outgoing, 0, incoming.getLength());
      this.data = new BytesWritable(outgoing);
   }

   public LazyBinaryBinary(WritableBinaryObjectInspector baoi) {
      super((ObjectInspector)baoi);
      this.data = new BytesWritable();
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      assert length > -1;

      ((BytesWritable)this.data).set(bytes.getData(), start, length);
   }
}
