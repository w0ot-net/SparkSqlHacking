package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.lazy.LazyPrimitive;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.AbstractPrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.io.Writable;

public abstract class AbstractPrimitiveLazyObjectInspector extends AbstractPrimitiveObjectInspector {
   protected AbstractPrimitiveLazyObjectInspector() {
   }

   protected AbstractPrimitiveLazyObjectInspector(PrimitiveTypeInfo typeInfo) {
      super(typeInfo);
   }

   public Writable getPrimitiveWritableObject(Object o) {
      return o == null ? null : ((LazyPrimitive)o).getWritableObject();
   }

   public boolean preferWritable() {
      return true;
   }
}
