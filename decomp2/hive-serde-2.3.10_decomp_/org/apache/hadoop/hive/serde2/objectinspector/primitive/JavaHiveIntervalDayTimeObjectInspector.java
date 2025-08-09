package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.serde2.io.HiveIntervalDayTimeWritable;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class JavaHiveIntervalDayTimeObjectInspector extends AbstractPrimitiveJavaObjectInspector implements SettableHiveIntervalDayTimeObjectInspector {
   public JavaHiveIntervalDayTimeObjectInspector() {
      super(TypeInfoFactory.intervalDayTimeTypeInfo);
   }

   public HiveIntervalDayTime getPrimitiveJavaObject(Object o) {
      return o == null ? null : (HiveIntervalDayTime)o;
   }

   public HiveIntervalDayTimeWritable getPrimitiveWritableObject(Object o) {
      return o == null ? null : new HiveIntervalDayTimeWritable((HiveIntervalDayTime)o);
   }

   public Object set(Object o, HiveIntervalDayTime i) {
      return i == null ? null : new HiveIntervalDayTime(i);
   }

   public Object set(Object o, HiveIntervalDayTimeWritable i) {
      return i == null ? null : i.getHiveIntervalDayTime();
   }

   public Object create(HiveIntervalDayTime i) {
      return i == null ? null : new HiveIntervalDayTime(i);
   }
}
