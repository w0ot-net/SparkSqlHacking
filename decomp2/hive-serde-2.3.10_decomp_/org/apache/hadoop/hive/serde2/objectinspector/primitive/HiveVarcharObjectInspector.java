package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.serde2.io.HiveVarcharWritable;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;

public interface HiveVarcharObjectInspector extends PrimitiveObjectInspector {
   HiveVarcharWritable getPrimitiveWritableObject(Object var1);

   HiveVarchar getPrimitiveJavaObject(Object var1);
}
