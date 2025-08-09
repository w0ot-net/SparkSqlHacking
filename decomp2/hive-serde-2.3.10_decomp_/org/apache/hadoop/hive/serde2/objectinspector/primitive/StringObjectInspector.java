package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.io.Text;

public interface StringObjectInspector extends PrimitiveObjectInspector {
   Text getPrimitiveWritableObject(Object var1);

   String getPrimitiveJavaObject(Object var1);
}
