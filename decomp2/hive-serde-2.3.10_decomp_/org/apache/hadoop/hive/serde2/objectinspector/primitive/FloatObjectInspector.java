package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;

public interface FloatObjectInspector extends PrimitiveObjectInspector {
   float get(Object var1);
}
