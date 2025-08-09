package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class WritableVoidObjectInspector extends AbstractPrimitiveWritableObjectInspector implements VoidObjectInspector, ConstantObjectInspector {
   WritableVoidObjectInspector() {
      super(TypeInfoFactory.voidTypeInfo);
   }

   public Object copyObject(Object o) {
      return o;
   }

   public Object getWritableConstantValue() {
      return null;
   }

   public Object getPrimitiveJavaObject(Object o) {
      return null;
   }

   public boolean equals(Object obj) {
      return null != obj && obj instanceof WritableVoidObjectInspector;
   }
}
