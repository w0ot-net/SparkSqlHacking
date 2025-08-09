package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.io.LongWritable;

public class JavaConstantLongObjectInspector extends JavaLongObjectInspector implements ConstantObjectInspector {
   private Long value;

   public JavaConstantLongObjectInspector(Long value) {
      this.value = value;
   }

   public Object getWritableConstantValue() {
      return this.value == null ? null : new LongWritable(this.value);
   }
}
