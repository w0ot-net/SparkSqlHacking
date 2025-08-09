package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.io.BooleanWritable;

public class JavaConstantBooleanObjectInspector extends JavaBooleanObjectInspector implements ConstantObjectInspector {
   private Boolean value;

   public JavaConstantBooleanObjectInspector(Boolean value) {
      this.value = value;
   }

   public Object getWritableConstantValue() {
      return this.value == null ? null : new BooleanWritable(this.value);
   }
}
