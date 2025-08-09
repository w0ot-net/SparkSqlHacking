package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.LongWritable;

public class JavaLongObjectInspector extends AbstractPrimitiveJavaObjectInspector implements SettableLongObjectInspector {
   JavaLongObjectInspector() {
      super(TypeInfoFactory.longTypeInfo);
   }

   public Object getPrimitiveWritableObject(Object o) {
      return o == null ? null : new LongWritable((Long)o);
   }

   public long get(Object o) {
      return (Long)o;
   }

   public Object create(long value) {
      return value;
   }

   public Object set(Object o, long value) {
      return value;
   }
}
