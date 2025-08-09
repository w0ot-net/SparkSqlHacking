package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import java.sql.Date;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.lazy.LazyDate;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DateObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class LazyDateObjectInspector extends AbstractPrimitiveLazyObjectInspector implements DateObjectInspector {
   protected LazyDateObjectInspector() {
      super(TypeInfoFactory.dateTypeInfo);
   }

   public Object copyObject(Object o) {
      return o == null ? null : new LazyDate((LazyDate)o);
   }

   public Date getPrimitiveJavaObject(Object o) {
      return o == null ? null : ((DateWritable)((LazyDate)o).getWritableObject()).get();
   }
}
