package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.io.BytesWritable;

public interface SettableBinaryObjectInspector extends BinaryObjectInspector {
   Object set(Object var1, byte[] var2);

   Object set(Object var1, BytesWritable var2);

   Object create(byte[] var1);

   Object create(BytesWritable var1);
}
