package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;

public interface LongObjectInspector extends PrimitiveObjectInspector {
   long get(Object var1);
}
