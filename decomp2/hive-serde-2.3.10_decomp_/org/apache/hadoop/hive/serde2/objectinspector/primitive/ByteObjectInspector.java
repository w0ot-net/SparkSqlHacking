package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;

public interface ByteObjectInspector extends PrimitiveObjectInspector {
   byte get(Object var1);
}
