package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.HiveCharWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.CharTypeInfo;

public class WritableConstantHiveCharObjectInspector extends WritableHiveCharObjectInspector implements ConstantObjectInspector {
   protected HiveCharWritable value;

   WritableConstantHiveCharObjectInspector() {
   }

   WritableConstantHiveCharObjectInspector(CharTypeInfo typeInfo, HiveCharWritable value) {
      super(typeInfo);
      this.value = value;
   }

   public HiveCharWritable getWritableConstantValue() {
      return this.value;
   }
}
