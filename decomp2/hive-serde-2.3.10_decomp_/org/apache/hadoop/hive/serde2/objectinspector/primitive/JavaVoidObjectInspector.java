package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.NullWritable;

public class JavaVoidObjectInspector extends AbstractPrimitiveJavaObjectInspector implements VoidObjectInspector {
   JavaVoidObjectInspector() {
      super(TypeInfoFactory.voidTypeInfo);
   }

   public Object getPrimitiveWritableObject(Object o) {
      return NullWritable.get();
   }
}
