package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.sql.Date;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;

public class JavaConstantDateObjectInspector extends JavaDateObjectInspector implements ConstantObjectInspector {
   private Date value;

   public JavaConstantDateObjectInspector(Date value) {
      this.value = value;
   }

   public Object getWritableConstantValue() {
      return this.value == null ? null : new DateWritable(this.value);
   }
}
