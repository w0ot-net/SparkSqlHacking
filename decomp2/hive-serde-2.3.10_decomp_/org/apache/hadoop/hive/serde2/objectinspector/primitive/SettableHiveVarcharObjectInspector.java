package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveVarchar;

public interface SettableHiveVarcharObjectInspector extends HiveVarcharObjectInspector {
   Object set(Object var1, HiveVarchar var2);

   Object set(Object var1, String var2);

   Object create(HiveVarchar var1);
}
