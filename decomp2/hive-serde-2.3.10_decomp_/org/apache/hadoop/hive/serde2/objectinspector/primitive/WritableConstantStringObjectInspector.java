package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.io.Text;

public class WritableConstantStringObjectInspector extends WritableStringObjectInspector implements ConstantObjectInspector {
   private Text value;

   protected WritableConstantStringObjectInspector() {
   }

   WritableConstantStringObjectInspector(Text value) {
      this.value = value;
   }

   public Text getWritableConstantValue() {
      return this.value;
   }
}
