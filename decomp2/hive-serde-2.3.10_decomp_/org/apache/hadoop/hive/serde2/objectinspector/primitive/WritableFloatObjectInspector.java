package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.FloatWritable;

public class WritableFloatObjectInspector extends AbstractPrimitiveWritableObjectInspector implements SettableFloatObjectInspector {
   WritableFloatObjectInspector() {
      super(TypeInfoFactory.floatTypeInfo);
   }

   public float get(Object o) {
      return ((FloatWritable)o).get();
   }

   public Object copyObject(Object o) {
      return o == null ? null : new FloatWritable(((FloatWritable)o).get());
   }

   public Object getPrimitiveJavaObject(Object o) {
      return o == null ? null : ((FloatWritable)o).get();
   }

   public Object create(float value) {
      return new FloatWritable(value);
   }

   public Object set(Object o, float value) {
      ((FloatWritable)o).set(value);
      return o;
   }
}
