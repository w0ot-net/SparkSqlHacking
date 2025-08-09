package org.apache.hadoop.hive.serde2.objectinspector.primitive;

public interface SettableDoubleObjectInspector extends DoubleObjectInspector {
   Object set(Object var1, double var2);

   Object create(double var1);
}
