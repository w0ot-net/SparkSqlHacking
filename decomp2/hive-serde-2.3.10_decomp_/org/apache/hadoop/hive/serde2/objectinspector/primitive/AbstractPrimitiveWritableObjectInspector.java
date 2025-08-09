package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;

public abstract class AbstractPrimitiveWritableObjectInspector extends AbstractPrimitiveObjectInspector {
   protected AbstractPrimitiveWritableObjectInspector() {
   }

   protected AbstractPrimitiveWritableObjectInspector(PrimitiveTypeInfo typeInfo) {
      super(typeInfo);
   }

   public Object getPrimitiveWritableObject(Object o) {
      return o;
   }

   public boolean preferWritable() {
      return true;
   }
}
