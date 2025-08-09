package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.serde2.io.HiveIntervalDayTimeWritable;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class WritableHiveIntervalDayTimeObjectInspector extends AbstractPrimitiveWritableObjectInspector implements SettableHiveIntervalDayTimeObjectInspector {
   public WritableHiveIntervalDayTimeObjectInspector() {
      super(TypeInfoFactory.intervalDayTimeTypeInfo);
   }

   public HiveIntervalDayTime getPrimitiveJavaObject(Object o) {
      return o == null ? null : ((HiveIntervalDayTimeWritable)o).getHiveIntervalDayTime();
   }

   public HiveIntervalDayTimeWritable getPrimitiveWritableObject(Object o) {
      return o == null ? null : (HiveIntervalDayTimeWritable)o;
   }

   public Object copyObject(Object o) {
      return o == null ? null : new HiveIntervalDayTimeWritable((HiveIntervalDayTimeWritable)o);
   }

   public Object set(Object o, HiveIntervalDayTime i) {
      if (i == null) {
         return null;
      } else {
         ((HiveIntervalDayTimeWritable)o).set(i);
         return o;
      }
   }

   public Object set(Object o, HiveIntervalDayTimeWritable i) {
      if (i == null) {
         return null;
      } else {
         ((HiveIntervalDayTimeWritable)o).set(i);
         return o;
      }
   }

   public Object create(HiveIntervalDayTime i) {
      return i == null ? null : new HiveIntervalDayTimeWritable(i);
   }
}
