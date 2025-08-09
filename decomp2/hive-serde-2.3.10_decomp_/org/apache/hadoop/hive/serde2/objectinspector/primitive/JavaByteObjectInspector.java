package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class JavaByteObjectInspector extends AbstractPrimitiveJavaObjectInspector implements SettableByteObjectInspector {
   JavaByteObjectInspector() {
      super(TypeInfoFactory.byteTypeInfo);
   }

   public Object getPrimitiveWritableObject(Object o) {
      return o == null ? null : new ByteWritable((Byte)o);
   }

   public byte get(Object o) {
      return (Byte)o;
   }

   public Object create(byte value) {
      return value;
   }

   public Object set(Object o, byte value) {
      return value;
   }
}
