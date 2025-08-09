package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;

public interface DoubleObjectInspector extends PrimitiveObjectInspector {
   double get(Object var1);
}
