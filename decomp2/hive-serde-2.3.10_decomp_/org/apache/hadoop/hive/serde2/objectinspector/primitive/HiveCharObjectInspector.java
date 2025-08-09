package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.serde2.io.HiveCharWritable;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;

public interface HiveCharObjectInspector extends PrimitiveObjectInspector {
   HiveCharWritable getPrimitiveWritableObject(Object var1);

   HiveChar getPrimitiveJavaObject(Object var1);
}
