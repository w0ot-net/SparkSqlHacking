package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.io.Text;

public class JavaConstantStringObjectInspector extends JavaStringObjectInspector implements ConstantObjectInspector {
   private String value;

   public JavaConstantStringObjectInspector(String value) {
      this.value = value;
   }

   public Object getWritableConstantValue() {
      return this.value == null ? null : new Text(this.value);
   }
}
