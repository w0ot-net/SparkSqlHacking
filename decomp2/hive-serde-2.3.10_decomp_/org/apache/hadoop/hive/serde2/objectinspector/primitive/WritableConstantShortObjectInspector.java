package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.math.BigDecimal;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;

public class WritableConstantShortObjectInspector extends WritableShortObjectInspector implements ConstantObjectInspector {
   private ShortWritable value;

   protected WritableConstantShortObjectInspector() {
   }

   WritableConstantShortObjectInspector(ShortWritable value) {
      this.value = value;
   }

   public ShortWritable getWritableConstantValue() {
      return this.value;
   }

   public int precision() {
      return this.value == null ? super.precision() : BigDecimal.valueOf((long)this.value.get()).precision();
   }
}
