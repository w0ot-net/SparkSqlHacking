package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.serde2.lazy.LazyHiveIntervalDayTime;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveIntervalDayTimeObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class LazyHiveIntervalDayTimeObjectInspector extends AbstractPrimitiveLazyObjectInspector implements HiveIntervalDayTimeObjectInspector {
   LazyHiveIntervalDayTimeObjectInspector() {
      super(TypeInfoFactory.intervalDayTimeTypeInfo);
   }

   public Object copyObject(Object o) {
      return o == null ? null : new LazyHiveIntervalDayTime((LazyHiveIntervalDayTime)o);
   }

   public HiveIntervalDayTime getPrimitiveJavaObject(Object o) {
      return o == null ? null : ((LazyHiveIntervalDayTime)o).getWritableObject().getHiveIntervalDayTime();
   }
}
