package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.serde2.io.HiveIntervalYearMonthWritable;

public interface SettableHiveIntervalYearMonthObjectInspector extends HiveIntervalYearMonthObjectInspector {
   Object set(Object var1, HiveIntervalYearMonth var2);

   Object set(Object var1, HiveIntervalYearMonthWritable var2);

   Object create(HiveIntervalYearMonth var1);
}
