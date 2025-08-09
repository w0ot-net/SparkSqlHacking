package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class JavaShortObjectInspector extends AbstractPrimitiveJavaObjectInspector implements SettableShortObjectInspector {
   JavaShortObjectInspector() {
      super(TypeInfoFactory.shortTypeInfo);
   }

   public Object getPrimitiveWritableObject(Object o) {
      return o == null ? null : new ShortWritable((Short)o);
   }

   public short get(Object o) {
      return (Short)o;
   }

   public Object create(short value) {
      return value;
   }

   public Object set(Object o, short value) {
      return value;
   }
}
