package org.apache.hadoop.hive.serde2.objectinspector.primitive;

public interface SettableIntObjectInspector extends IntObjectInspector {
   Object set(Object var1, int var2);

   Object create(int var1);
}
