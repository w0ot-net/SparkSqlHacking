package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;

public interface HiveDecimalObjectInspector extends PrimitiveObjectInspector {
   HiveDecimalWritable getPrimitiveWritableObject(Object var1);

   HiveDecimal getPrimitiveJavaObject(Object var1);
}
