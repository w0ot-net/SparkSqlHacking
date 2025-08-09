package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.BooleanWritable;

public class WritableBooleanObjectInspector extends AbstractPrimitiveWritableObjectInspector implements SettableBooleanObjectInspector {
   WritableBooleanObjectInspector() {
      super(TypeInfoFactory.booleanTypeInfo);
   }

   public boolean get(Object o) {
      return ((BooleanWritable)o).get();
   }

   public Object copyObject(Object o) {
      return o == null ? null : new BooleanWritable(((BooleanWritable)o).get());
   }

   public Object getPrimitiveJavaObject(Object o) {
      return o == null ? null : ((BooleanWritable)o).get();
   }

   public Object create(boolean value) {
      return new BooleanWritable(value);
   }

   public Object set(Object o, boolean value) {
      ((BooleanWritable)o).set(value);
      return o;
   }
}
