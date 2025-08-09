package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.math.BigDecimal;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.io.IntWritable;

public class WritableConstantIntObjectInspector extends WritableIntObjectInspector implements ConstantObjectInspector {
   private IntWritable value;

   protected WritableConstantIntObjectInspector() {
   }

   WritableConstantIntObjectInspector(IntWritable value) {
      this.value = value;
   }

   public IntWritable getWritableConstantValue() {
      return this.value;
   }

   public int precision() {
      return this.value == null ? super.precision() : BigDecimal.valueOf((long)this.value.get()).precision();
   }
}
