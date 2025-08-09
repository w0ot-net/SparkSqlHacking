package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.serde2.io.HiveIntervalDayTimeWritable;

public interface SettableHiveIntervalDayTimeObjectInspector extends HiveIntervalDayTimeObjectInspector {
   Object set(Object var1, HiveIntervalDayTime var2);

   Object set(Object var1, HiveIntervalDayTimeWritable var2);

   Object create(HiveIntervalDayTime var1);
}
