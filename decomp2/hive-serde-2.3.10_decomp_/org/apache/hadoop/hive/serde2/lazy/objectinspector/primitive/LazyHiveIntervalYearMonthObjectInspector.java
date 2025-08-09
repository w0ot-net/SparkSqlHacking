package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.serde2.lazy.LazyHiveIntervalYearMonth;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveIntervalYearMonthObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class LazyHiveIntervalYearMonthObjectInspector extends AbstractPrimitiveLazyObjectInspector implements HiveIntervalYearMonthObjectInspector {
   LazyHiveIntervalYearMonthObjectInspector() {
      super(TypeInfoFactory.intervalYearMonthTypeInfo);
   }

   public Object copyObject(Object o) {
      return o == null ? null : new LazyHiveIntervalYearMonth((LazyHiveIntervalYearMonth)o);
   }

   public HiveIntervalYearMonth getPrimitiveJavaObject(Object o) {
      return o == null ? null : ((LazyHiveIntervalYearMonth)o).getWritableObject().getHiveIntervalYearMonth();
   }
}
