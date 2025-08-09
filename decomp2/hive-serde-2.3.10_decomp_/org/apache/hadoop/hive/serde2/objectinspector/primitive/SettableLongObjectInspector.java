package org.apache.hadoop.hive.serde2.objectinspector.primitive;

public interface SettableLongObjectInspector extends LongObjectInspector {
   Object set(Object var1, long var2);

   Object create(long var1);
}
