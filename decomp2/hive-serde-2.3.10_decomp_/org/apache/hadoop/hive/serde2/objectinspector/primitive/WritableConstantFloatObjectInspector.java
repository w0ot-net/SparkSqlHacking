package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.io.FloatWritable;

public class WritableConstantFloatObjectInspector extends WritableFloatObjectInspector implements ConstantObjectInspector {
   private FloatWritable value;

   protected WritableConstantFloatObjectInspector() {
   }

   WritableConstantFloatObjectInspector(FloatWritable value) {
      this.value = value;
   }

   public FloatWritable getWritableConstantValue() {
      return this.value;
   }
}
