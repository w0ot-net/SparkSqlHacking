package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;

public interface IntObjectInspector extends PrimitiveObjectInspector {
   int get(Object var1);
}
