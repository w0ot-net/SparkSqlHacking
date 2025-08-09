package org.apache.hadoop.hive.serde2.objectinspector.primitive;

public interface SettableByteObjectInspector extends ByteObjectInspector {
   Object set(Object var1, byte var2);

   Object create(byte var1);
}
