package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;

public abstract class AbstractPrimitiveJavaObjectInspector extends AbstractPrimitiveObjectInspector {
   protected AbstractPrimitiveJavaObjectInspector() {
   }

   protected AbstractPrimitiveJavaObjectInspector(PrimitiveTypeInfo typeInfo) {
      super(typeInfo);
   }

   public Object getPrimitiveJavaObject(Object o) {
      return o;
   }

   public Object copyObject(Object o) {
      return o;
   }

   public boolean preferWritable() {
      return false;
   }
}
