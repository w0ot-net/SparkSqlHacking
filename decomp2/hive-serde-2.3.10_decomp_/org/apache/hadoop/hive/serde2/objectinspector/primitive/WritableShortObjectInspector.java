package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class WritableShortObjectInspector extends AbstractPrimitiveWritableObjectInspector implements SettableShortObjectInspector {
   WritableShortObjectInspector() {
      super(TypeInfoFactory.shortTypeInfo);
   }

   public short get(Object o) {
      return ((ShortWritable)o).get();
   }

   public Object copyObject(Object o) {
      return o == null ? null : new ShortWritable(((ShortWritable)o).get());
   }

   public Object getPrimitiveJavaObject(Object o) {
      return o == null ? null : ((ShortWritable)o).get();
   }

   public Object create(short value) {
      return new ShortWritable(value);
   }

   public Object set(Object o, short value) {
      ((ShortWritable)o).set(value);
      return o;
   }
}
