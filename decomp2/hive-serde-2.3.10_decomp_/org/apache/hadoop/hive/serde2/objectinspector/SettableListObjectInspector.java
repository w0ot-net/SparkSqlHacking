package org.apache.hadoop.hive.serde2.objectinspector;

public interface SettableListObjectInspector extends ListObjectInspector {
   Object create(int var1);

   Object set(Object var1, int var2, Object var3);

   Object resize(Object var1, int var2);
}
