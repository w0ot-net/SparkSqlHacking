package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.lazy.LazyLong;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.LongObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.LongWritable;

public class LazyLongObjectInspector extends AbstractPrimitiveLazyObjectInspector implements LongObjectInspector {
   LazyLongObjectInspector() {
      super(TypeInfoFactory.longTypeInfo);
   }

   public long get(Object o) {
      return ((LongWritable)this.getPrimitiveWritableObject(o)).get();
   }

   public Object copyObject(Object o) {
      return o == null ? null : new LazyLong((LazyLong)o);
   }

   public Object getPrimitiveJavaObject(Object o) {
      return o == null ? null : this.get(o);
   }
}
