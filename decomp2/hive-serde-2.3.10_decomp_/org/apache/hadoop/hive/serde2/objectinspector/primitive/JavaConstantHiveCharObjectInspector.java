package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.serde2.io.HiveCharWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;

public class JavaConstantHiveCharObjectInspector extends JavaHiveCharObjectInspector implements ConstantObjectInspector {
   private HiveChar value;

   public JavaConstantHiveCharObjectInspector(HiveChar value) {
      this.value = value;
   }

   public Object getWritableConstantValue() {
      return this.value == null ? null : new HiveCharWritable(this.value);
   }
}
