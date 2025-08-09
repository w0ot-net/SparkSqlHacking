package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveChar;

public interface SettableHiveCharObjectInspector extends HiveCharObjectInspector {
   Object set(Object var1, HiveChar var2);

   Object set(Object var1, String var2);

   Object create(HiveChar var1);
}
