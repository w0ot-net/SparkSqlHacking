package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.io.BytesWritable;

public interface BinaryObjectInspector extends PrimitiveObjectInspector {
   byte[] getPrimitiveJavaObject(Object var1);

   BytesWritable getPrimitiveWritableObject(Object var1);
}
