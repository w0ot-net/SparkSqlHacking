package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.serde2.io.HiveIntervalDayTimeWritable;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;

public interface HiveIntervalDayTimeObjectInspector extends PrimitiveObjectInspector {
   HiveIntervalDayTimeWritable getPrimitiveWritableObject(Object var1);

   HiveIntervalDayTime getPrimitiveJavaObject(Object var1);
}
