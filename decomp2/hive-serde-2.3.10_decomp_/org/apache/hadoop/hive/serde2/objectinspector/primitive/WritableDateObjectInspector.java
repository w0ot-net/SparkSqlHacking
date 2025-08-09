package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.sql.Date;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class WritableDateObjectInspector extends AbstractPrimitiveWritableObjectInspector implements SettableDateObjectInspector {
   public WritableDateObjectInspector() {
      super(TypeInfoFactory.dateTypeInfo);
   }

   public DateWritable getPrimitiveWritableObject(Object o) {
      return o == null ? null : (DateWritable)o;
   }

   public Date getPrimitiveJavaObject(Object o) {
      return o == null ? null : ((DateWritable)o).get();
   }

   public Object copyObject(Object o) {
      return o == null ? null : new DateWritable((DateWritable)o);
   }

   public Object set(Object o, Date d) {
      if (d == null) {
         return null;
      } else {
         ((DateWritable)o).set(d);
         return o;
      }
   }

   public Object set(Object o, DateWritable d) {
      if (d == null) {
         return null;
      } else {
         ((DateWritable)o).set(d);
         return o;
      }
   }

   public Object create(Date d) {
      return new DateWritable(d);
   }
}
