package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.io.IntWritable;

public class JavaConstantIntObjectInspector extends JavaIntObjectInspector implements ConstantObjectInspector {
   private Integer value;

   public JavaConstantIntObjectInspector(Integer value) {
      this.value = value;
   }

   public Object getWritableConstantValue() {
      return this.value == null ? null : new IntWritable(this.value);
   }
}
