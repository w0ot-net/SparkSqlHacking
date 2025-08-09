package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.serde2.io.HiveIntervalYearMonthWritable;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;

public interface HiveIntervalYearMonthObjectInspector extends PrimitiveObjectInspector {
   HiveIntervalYearMonthWritable getPrimitiveWritableObject(Object var1);

   HiveIntervalYearMonth getPrimitiveJavaObject(Object var1);
}
