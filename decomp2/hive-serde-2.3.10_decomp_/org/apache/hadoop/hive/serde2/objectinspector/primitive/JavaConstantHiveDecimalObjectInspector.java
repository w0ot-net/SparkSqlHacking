package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;

public class JavaConstantHiveDecimalObjectInspector extends JavaHiveDecimalObjectInspector implements ConstantObjectInspector {
   private HiveDecimal value;

   public JavaConstantHiveDecimalObjectInspector(HiveDecimal value) {
      this.value = value;
   }

   public Object getWritableConstantValue() {
      return this.value == null ? null : new HiveDecimalWritable(this.value);
   }
}
