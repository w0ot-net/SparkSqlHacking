package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.primitive.VoidObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class LazyVoidObjectInspector extends AbstractPrimitiveLazyObjectInspector implements VoidObjectInspector {
   LazyVoidObjectInspector() {
      super(TypeInfoFactory.voidTypeInfo);
   }

   public Object copyObject(Object o) {
      return o;
   }

   public Object getPrimitiveJavaObject(Object o) {
      return null;
   }
}
