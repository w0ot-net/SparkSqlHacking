package org.apache.hadoop.hive.serde2.objectinspector;

public interface SettableMapObjectInspector extends MapObjectInspector {
   Object create();

   Object put(Object var1, Object var2, Object var3);

   Object remove(Object var1, Object var2);

   Object clear(Object var1);
}
