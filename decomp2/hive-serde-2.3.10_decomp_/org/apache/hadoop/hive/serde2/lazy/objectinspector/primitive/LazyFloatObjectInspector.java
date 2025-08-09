package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.lazy.LazyFloat;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.FloatObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.FloatWritable;

public class LazyFloatObjectInspector extends AbstractPrimitiveLazyObjectInspector implements FloatObjectInspector {
   LazyFloatObjectInspector() {
      super(TypeInfoFactory.floatTypeInfo);
   }

   public float get(Object o) {
      return ((FloatWritable)this.getPrimitiveWritableObject(o)).get();
   }

   public Object copyObject(Object o) {
      return o == null ? null : new LazyFloat((LazyFloat)o);
   }

   public Object getPrimitiveJavaObject(Object o) {
      return o == null ? null : this.get(o);
   }
}
