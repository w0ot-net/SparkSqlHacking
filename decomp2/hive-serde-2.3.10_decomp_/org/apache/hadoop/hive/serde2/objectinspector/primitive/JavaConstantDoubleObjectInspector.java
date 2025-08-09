package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;

public class JavaConstantDoubleObjectInspector extends JavaDoubleObjectInspector implements ConstantObjectInspector {
   private Double value;

   public JavaConstantDoubleObjectInspector(Double value) {
      this.value = value;
   }

   public Object getWritableConstantValue() {
      return this.value == null ? null : new DoubleWritable(this.value);
   }
}
