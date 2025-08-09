package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.HiveIntervalYearMonthWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;

public class WritableConstantHiveIntervalYearMonthObjectInspector extends WritableHiveIntervalYearMonthObjectInspector implements ConstantObjectInspector {
   private HiveIntervalYearMonthWritable value;

   protected WritableConstantHiveIntervalYearMonthObjectInspector() {
   }

   WritableConstantHiveIntervalYearMonthObjectInspector(HiveIntervalYearMonthWritable value) {
      this.value = value;
   }

   public Object getWritableConstantValue() {
      return this.value;
   }
}
