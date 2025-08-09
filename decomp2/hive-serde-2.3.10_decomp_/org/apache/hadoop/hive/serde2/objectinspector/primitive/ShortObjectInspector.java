package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;

public interface ShortObjectInspector extends PrimitiveObjectInspector {
   short get(Object var1);
}
