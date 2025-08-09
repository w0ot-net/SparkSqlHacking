package org.apache.hadoop.hive.serde2.objectinspector.primitive;

public interface SettableShortObjectInspector extends ShortObjectInspector {
   Object set(Object var1, short var2);

   Object create(short var1);
}
