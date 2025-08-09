package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.sql.Date;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;

public interface DateObjectInspector extends PrimitiveObjectInspector {
   DateWritable getPrimitiveWritableObject(Object var1);

   Date getPrimitiveJavaObject(Object var1);
}
