package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableBooleanObjectInspector;
import org.apache.hadoop.io.BooleanWritable;

public class LazyBinaryBoolean extends LazyBinaryPrimitive {
   public LazyBinaryBoolean(WritableBooleanObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new BooleanWritable();
   }

   public LazyBinaryBoolean(LazyBinaryBoolean copy) {
      super((LazyBinaryPrimitive)copy);
      this.data = new BooleanWritable(((BooleanWritable)copy.data).get());
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      assert 1 == length;

      byte val = bytes.getData()[start];
      if (val == 0) {
         ((BooleanWritable)this.data).set(false);
      } else if (val == 1) {
         ((BooleanWritable)this.data).set(true);
      }

   }
}
