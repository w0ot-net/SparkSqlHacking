package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;

public class WritableConstantDateObjectInspector extends WritableDateObjectInspector implements ConstantObjectInspector {
   private DateWritable value;

   protected WritableConstantDateObjectInspector() {
   }

   WritableConstantDateObjectInspector(DateWritable value) {
      this.value = value;
   }

   public DateWritable getWritableConstantValue() {
      return this.value;
   }
}
