package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.BooleanWritable;

public class JavaBooleanObjectInspector extends AbstractPrimitiveJavaObjectInspector implements SettableBooleanObjectInspector {
   JavaBooleanObjectInspector() {
      super(TypeInfoFactory.booleanTypeInfo);
   }

   public Object getPrimitiveWritableObject(Object o) {
      return o == null ? null : new BooleanWritable((Boolean)o);
   }

   public boolean get(Object o) {
      return (Boolean)o;
   }

   public Object create(boolean value) {
      return value ? Boolean.TRUE : Boolean.FALSE;
   }

   public Object set(Object o, boolean value) {
      return value ? Boolean.TRUE : Boolean.FALSE;
   }
}
