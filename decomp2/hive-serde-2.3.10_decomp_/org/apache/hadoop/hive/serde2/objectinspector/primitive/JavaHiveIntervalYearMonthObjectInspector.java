package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.serde2.io.HiveIntervalYearMonthWritable;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class JavaHiveIntervalYearMonthObjectInspector extends AbstractPrimitiveJavaObjectInspector implements SettableHiveIntervalYearMonthObjectInspector {
   public JavaHiveIntervalYearMonthObjectInspector() {
      super(TypeInfoFactory.intervalYearMonthTypeInfo);
   }

   public HiveIntervalYearMonth getPrimitiveJavaObject(Object o) {
      return o == null ? null : (HiveIntervalYearMonth)o;
   }

   public HiveIntervalYearMonthWritable getPrimitiveWritableObject(Object o) {
      return o == null ? null : new HiveIntervalYearMonthWritable((HiveIntervalYearMonth)o);
   }

   public Object set(Object o, HiveIntervalYearMonth i) {
      return i == null ? null : new HiveIntervalYearMonth(i);
   }

   public Object set(Object o, HiveIntervalYearMonthWritable i) {
      return i == null ? null : i.getHiveIntervalYearMonth();
   }

   public Object create(HiveIntervalYearMonth i) {
      return i == null ? null : new HiveIntervalYearMonth(i);
   }
}
