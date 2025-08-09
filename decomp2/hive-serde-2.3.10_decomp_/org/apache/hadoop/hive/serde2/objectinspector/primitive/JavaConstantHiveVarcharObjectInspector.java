package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.serde2.io.HiveVarcharWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;

public class JavaConstantHiveVarcharObjectInspector extends JavaHiveVarcharObjectInspector implements ConstantObjectInspector {
   private HiveVarchar value;

   public JavaConstantHiveVarcharObjectInspector(HiveVarchar value) {
      this.value = value;
   }

   public Object getWritableConstantValue() {
      return this.value == null ? null : new HiveVarcharWritable(this.value);
   }
}
