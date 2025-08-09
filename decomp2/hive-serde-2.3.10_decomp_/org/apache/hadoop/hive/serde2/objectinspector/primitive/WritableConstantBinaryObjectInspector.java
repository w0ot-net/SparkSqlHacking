package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.io.BytesWritable;

public class WritableConstantBinaryObjectInspector extends WritableBinaryObjectInspector implements ConstantObjectInspector {
   private BytesWritable value;

   protected WritableConstantBinaryObjectInspector() {
   }

   public WritableConstantBinaryObjectInspector(BytesWritable value) {
      this.value = value;
   }

   public BytesWritable getWritableConstantValue() {
      return this.value;
   }
}
