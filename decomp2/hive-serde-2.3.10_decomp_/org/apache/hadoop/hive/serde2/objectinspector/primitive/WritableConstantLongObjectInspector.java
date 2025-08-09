package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.math.BigDecimal;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.io.LongWritable;

public class WritableConstantLongObjectInspector extends WritableLongObjectInspector implements ConstantObjectInspector {
   private LongWritable value;

   protected WritableConstantLongObjectInspector() {
   }

   WritableConstantLongObjectInspector(LongWritable value) {
      this.value = value;
   }

   public LongWritable getWritableConstantValue() {
      return this.value;
   }

   public int precision() {
      return this.value == null ? super.precision() : BigDecimal.valueOf(this.value.get()).precision();
   }
}
