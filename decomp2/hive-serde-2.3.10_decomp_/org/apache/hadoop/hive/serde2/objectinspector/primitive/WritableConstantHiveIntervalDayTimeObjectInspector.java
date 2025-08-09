package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.HiveIntervalDayTimeWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;

public class WritableConstantHiveIntervalDayTimeObjectInspector extends WritableHiveIntervalDayTimeObjectInspector implements ConstantObjectInspector {
   private HiveIntervalDayTimeWritable value;

   protected WritableConstantHiveIntervalDayTimeObjectInspector() {
   }

   WritableConstantHiveIntervalDayTimeObjectInspector(HiveIntervalDayTimeWritable value) {
      this.value = value;
   }

   public Object getWritableConstantValue() {
      return this.value;
   }
}
