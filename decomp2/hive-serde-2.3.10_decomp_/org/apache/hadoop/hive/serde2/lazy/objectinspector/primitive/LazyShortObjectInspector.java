package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.lazy.LazyShort;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ShortObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class LazyShortObjectInspector extends AbstractPrimitiveLazyObjectInspector implements ShortObjectInspector {
   LazyShortObjectInspector() {
      super(TypeInfoFactory.shortTypeInfo);
   }

   public short get(Object o) {
      return ((ShortWritable)this.getPrimitiveWritableObject(o)).get();
   }

   public Object copyObject(Object o) {
      return o == null ? null : new LazyShort((LazyShort)o);
   }

   public Object getPrimitiveJavaObject(Object o) {
      return o == null ? null : this.get(o);
   }
}
