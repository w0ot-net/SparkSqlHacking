package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.ByteWritable;

public class WritableByteObjectInspector extends AbstractPrimitiveWritableObjectInspector implements SettableByteObjectInspector {
   public WritableByteObjectInspector() {
      super(TypeInfoFactory.byteTypeInfo);
   }

   public byte get(Object o) {
      return ((ByteWritable)o).get();
   }

   public Object copyObject(Object o) {
      return o == null ? null : new org.apache.hadoop.hive.serde2.io.ByteWritable(this.get(o));
   }

   public Object getPrimitiveJavaObject(Object o) {
      return o == null ? null : this.get(o);
   }

   public Object create(byte value) {
      return new org.apache.hadoop.hive.serde2.io.ByteWritable(value);
   }

   public Object set(Object o, byte value) {
      ((ByteWritable)o).set(value);
      return o;
   }
}
