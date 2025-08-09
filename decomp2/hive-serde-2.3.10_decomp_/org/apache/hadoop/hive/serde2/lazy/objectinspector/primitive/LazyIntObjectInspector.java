package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.lazy.LazyInteger;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.IntObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.IntWritable;

public class LazyIntObjectInspector extends AbstractPrimitiveLazyObjectInspector implements IntObjectInspector {
   LazyIntObjectInspector() {
      super(TypeInfoFactory.intTypeInfo);
   }

   public int get(Object o) {
      return ((IntWritable)this.getPrimitiveWritableObject(o)).get();
   }

   public Object copyObject(Object o) {
      return o == null ? null : new LazyInteger((LazyInteger)o);
   }

   public Object getPrimitiveJavaObject(Object o) {
      return o == null ? null : this.get(o);
   }
}
