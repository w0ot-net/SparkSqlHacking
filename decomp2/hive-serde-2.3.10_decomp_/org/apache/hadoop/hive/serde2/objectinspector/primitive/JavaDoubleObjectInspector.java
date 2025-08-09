package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class JavaDoubleObjectInspector extends AbstractPrimitiveJavaObjectInspector implements SettableDoubleObjectInspector {
   JavaDoubleObjectInspector() {
      super(TypeInfoFactory.doubleTypeInfo);
   }

   public Object getPrimitiveWritableObject(Object o) {
      return o == null ? null : new DoubleWritable((Double)o);
   }

   public double get(Object o) {
      return (Double)o;
   }

   public Object create(double value) {
      return value;
   }

   public Object set(Object o, double value) {
      return value;
   }
}
