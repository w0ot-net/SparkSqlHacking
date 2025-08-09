package org.apache.hadoop.hive.serde2.lazy;

import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Text;

public abstract class LazyNonPrimitive extends LazyObject {
   protected ByteArrayRef bytes = null;
   protected int start = 0;
   protected int length = 0;

   protected LazyNonPrimitive(ObjectInspector oi) {
      super(oi);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      super.init(bytes, start, length);
      this.bytes = bytes;
      this.start = start;
      this.length = length;

      assert start >= 0;

      assert start + length <= bytes.getData().length;

   }

   protected final boolean isNull(Text nullSequence, ByteArrayRef ref, int fieldByteBegin, int fieldLength) {
      return ref == null || this.isNull(nullSequence, ref.getData(), fieldByteBegin, fieldLength);
   }

   protected final boolean isNull(Text nullSequence, byte[] bytes, int fieldByteBegin, int fieldLength) {
      return fieldLength < 0 || fieldLength == nullSequence.getLength() && LazyUtils.compare(bytes, fieldByteBegin, fieldLength, nullSequence.getBytes(), 0, nullSequence.getLength()) == 0;
   }

   public int hashCode() {
      return LazyUtils.hashBytes(this.bytes.getData(), this.start, this.length);
   }
}
