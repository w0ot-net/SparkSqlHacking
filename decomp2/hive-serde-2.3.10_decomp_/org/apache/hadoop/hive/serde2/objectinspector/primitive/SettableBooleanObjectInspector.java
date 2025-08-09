package org.apache.hadoop.hive.serde2.objectinspector.primitive;

public interface SettableBooleanObjectInspector extends BooleanObjectInspector {
   Object set(Object var1, boolean var2);

   Object create(boolean var1);
}
