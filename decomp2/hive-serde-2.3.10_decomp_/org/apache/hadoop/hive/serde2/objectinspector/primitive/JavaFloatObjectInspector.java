package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.FloatWritable;

public class JavaFloatObjectInspector extends AbstractPrimitiveJavaObjectInspector implements SettableFloatObjectInspector {
   JavaFloatObjectInspector() {
      super(TypeInfoFactory.floatTypeInfo);
   }

   public Object getPrimitiveWritableObject(Object o) {
      return o == null ? null : new FloatWritable((Float)o);
   }

   public float get(Object o) {
      return (Float)o;
   }

   public Object create(float value) {
      return value;
   }

   public Object set(Object o, float value) {
      return value;
   }
}
