package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.io.BooleanWritable;

public class WritableConstantBooleanObjectInspector extends WritableBooleanObjectInspector implements ConstantObjectInspector {
   private BooleanWritable value;

   protected WritableConstantBooleanObjectInspector() {
   }

   WritableConstantBooleanObjectInspector(BooleanWritable value) {
      this.value = value;
   }

   public BooleanWritable getWritableConstantValue() {
      return this.value;
   }
}
