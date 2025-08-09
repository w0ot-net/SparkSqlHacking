package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.math.BigDecimal;
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;

public class WritableConstantByteObjectInspector extends WritableByteObjectInspector implements ConstantObjectInspector {
   private ByteWritable value;

   protected WritableConstantByteObjectInspector() {
   }

   WritableConstantByteObjectInspector(ByteWritable value) {
      this.value = value;
   }

   public ByteWritable getWritableConstantValue() {
      return this.value;
   }

   public int precision() {
      return this.value == null ? super.precision() : BigDecimal.valueOf((long)this.value.get()).precision();
   }
}
