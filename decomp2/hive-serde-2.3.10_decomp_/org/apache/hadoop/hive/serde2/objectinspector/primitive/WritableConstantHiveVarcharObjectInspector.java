package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.HiveVarcharWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.VarcharTypeInfo;

public class WritableConstantHiveVarcharObjectInspector extends WritableHiveVarcharObjectInspector implements ConstantObjectInspector {
   protected HiveVarcharWritable value;

   WritableConstantHiveVarcharObjectInspector() {
   }

   WritableConstantHiveVarcharObjectInspector(VarcharTypeInfo typeInfo, HiveVarcharWritable value) {
      super(typeInfo);
      this.value = value;
   }

   public HiveVarcharWritable getWritableConstantValue() {
      return this.value;
   }
}
