package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;

public class JavaConstantShortObjectInspector extends JavaDateObjectInspector implements ConstantObjectInspector {
   private Short value;

   public JavaConstantShortObjectInspector(Short value) {
      this.value = value;
   }

   public Object getWritableConstantValue() {
      return this.value == null ? null : new ShortWritable(this.value);
   }
}
