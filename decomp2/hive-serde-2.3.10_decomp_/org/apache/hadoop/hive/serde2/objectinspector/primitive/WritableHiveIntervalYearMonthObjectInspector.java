package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.serde2.io.HiveIntervalYearMonthWritable;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class WritableHiveIntervalYearMonthObjectInspector extends AbstractPrimitiveWritableObjectInspector implements SettableHiveIntervalYearMonthObjectInspector {
   public WritableHiveIntervalYearMonthObjectInspector() {
      super(TypeInfoFactory.intervalYearMonthTypeInfo);
   }

   public HiveIntervalYearMonth getPrimitiveJavaObject(Object o) {
      return o == null ? null : ((HiveIntervalYearMonthWritable)o).getHiveIntervalYearMonth();
   }

   public HiveIntervalYearMonthWritable getPrimitiveWritableObject(Object o) {
      return o == null ? null : (HiveIntervalYearMonthWritable)o;
   }

   public Object copyObject(Object o) {
      return o == null ? null : new HiveIntervalYearMonthWritable((HiveIntervalYearMonthWritable)o);
   }

   public Object set(Object o, HiveIntervalYearMonth i) {
      if (i == null) {
         return null;
      } else {
         ((HiveIntervalYearMonthWritable)o).set(i);
         return o;
      }
   }

   public Object set(Object o, HiveIntervalYearMonthWritable i) {
      if (i == null) {
         return null;
      } else {
         ((HiveIntervalYearMonthWritable)o).set(i);
         return o;
      }
   }

   public Object create(HiveIntervalYearMonth i) {
      return i == null ? null : new HiveIntervalYearMonthWritable(i);
   }
}
