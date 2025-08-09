package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.lazy.LazyDouble;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DoubleObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class LazyDoubleObjectInspector extends AbstractPrimitiveLazyObjectInspector implements DoubleObjectInspector {
   LazyDoubleObjectInspector() {
      super(TypeInfoFactory.doubleTypeInfo);
   }

   public double get(Object o) {
      return ((DoubleWritable)this.getPrimitiveWritableObject(o)).get();
   }

   public Object copyObject(Object o) {
      return o == null ? null : new LazyDouble((LazyDouble)o);
   }

   public Object getPrimitiveJavaObject(Object o) {
      return o == null ? null : this.get(o);
   }
}
