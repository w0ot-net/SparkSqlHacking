package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.sql.Date;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class JavaDateObjectInspector extends AbstractPrimitiveJavaObjectInspector implements SettableDateObjectInspector {
   protected JavaDateObjectInspector() {
      super(TypeInfoFactory.dateTypeInfo);
   }

   public DateWritable getPrimitiveWritableObject(Object o) {
      return o == null ? null : new DateWritable((Date)o);
   }

   public Date getPrimitiveJavaObject(Object o) {
      return o == null ? null : (Date)o;
   }

   public Date get(Object o) {
      return (Date)o;
   }

   public Object set(Object o, Date value) {
      if (value == null) {
         return null;
      } else {
         ((Date)o).setTime(value.getTime());
         return o;
      }
   }

   public Object set(Object o, DateWritable d) {
      if (d == null) {
         return null;
      } else {
         ((Date)o).setTime(d.get().getTime());
         return o;
      }
   }

   public Object create(Date value) {
      return new Date(value.getTime());
   }
}
