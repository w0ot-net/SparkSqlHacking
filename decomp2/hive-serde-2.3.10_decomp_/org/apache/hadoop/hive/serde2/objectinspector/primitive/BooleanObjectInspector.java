package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;

public interface BooleanObjectInspector extends PrimitiveObjectInspector {
   boolean get(Object var1);
}
