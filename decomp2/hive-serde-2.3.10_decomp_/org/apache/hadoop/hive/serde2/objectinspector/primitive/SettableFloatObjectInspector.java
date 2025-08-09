package org.apache.hadoop.hive.serde2.objectinspector.primitive;

public interface SettableFloatObjectInspector extends FloatObjectInspector {
   Object set(Object var1, float var2);

   Object create(float var1);
}
