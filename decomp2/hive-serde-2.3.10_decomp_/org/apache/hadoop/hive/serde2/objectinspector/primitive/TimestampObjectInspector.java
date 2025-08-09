package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.sql.Timestamp;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;

public interface TimestampObjectInspector extends PrimitiveObjectInspector {
   TimestampWritable getPrimitiveWritableObject(Object var1);

   Timestamp getPrimitiveJavaObject(Object var1);
}
