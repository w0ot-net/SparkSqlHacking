package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;

public interface SettableHiveDecimalObjectInspector extends HiveDecimalObjectInspector {
   Object set(Object var1, byte[] var2, int var3);

   Object set(Object var1, HiveDecimal var2);

   Object set(Object var1, HiveDecimalWritable var2);

   Object create(byte[] var1, int var2);

   Object create(HiveDecimal var1);
}
