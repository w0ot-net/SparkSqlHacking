package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.DoubleWritable;

public class WritableDoubleObjectInspector extends AbstractPrimitiveWritableObjectInspector implements SettableDoubleObjectInspector {
   WritableDoubleObjectInspector() {
      super(TypeInfoFactory.doubleTypeInfo);
   }

   public double get(Object o) {
      return ((DoubleWritable)o).get();
   }

   public Object copyObject(Object o) {
      return o == null ? null : new org.apache.hadoop.hive.serde2.io.DoubleWritable(this.get(o));
   }

   public Object getPrimitiveJavaObject(Object o) {
      return o == null ? null : this.get(o);
   }

   public Object create(double value) {
      return new org.apache.hadoop.hive.serde2.io.DoubleWritable(value);
   }

   public Object set(Object o, double value) {
      ((DoubleWritable)o).set(value);
      return o;
   }
}
