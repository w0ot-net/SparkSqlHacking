package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.io.FloatWritable;

public class JavaConstantFloatObjectInspector extends JavaFloatObjectInspector implements ConstantObjectInspector {
   private Float value;

   public JavaConstantFloatObjectInspector(Float value) {
      this.value = value;
   }

   public Object getWritableConstantValue() {
      return this.value == null ? null : new FloatWritable(this.value);
   }
}
