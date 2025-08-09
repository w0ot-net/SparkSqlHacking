package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.sql.Timestamp;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;

public class JavaConstantTimestampObjectInspector extends JavaTimestampObjectInspector implements ConstantObjectInspector {
   private Timestamp value;

   public JavaConstantTimestampObjectInspector(Timestamp value) {
      this.value = value;
   }

   public Object getWritableConstantValue() {
      return this.value == null ? null : new TimestampWritable(this.value);
   }
}
