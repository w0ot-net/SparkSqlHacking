package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.util.Arrays;
import org.apache.hadoop.hive.serde2.lazy.LazyUtils;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.BytesWritable;

public class JavaBinaryObjectInspector extends AbstractPrimitiveJavaObjectInspector implements SettableBinaryObjectInspector {
   JavaBinaryObjectInspector() {
      super(TypeInfoFactory.binaryTypeInfo);
   }

   public BytesWritable getPrimitiveWritableObject(Object o) {
      return o == null ? null : new BytesWritable((byte[])o);
   }

   public byte[] getPrimitiveJavaObject(Object o) {
      return (byte[])o;
   }

   public byte[] set(Object o, byte[] bb) {
      return bb == null ? null : Arrays.copyOf(bb, bb.length);
   }

   public byte[] set(Object o, BytesWritable bw) {
      return bw == null ? null : LazyUtils.createByteArray(bw);
   }

   public byte[] create(byte[] bb) {
      return bb == null ? null : Arrays.copyOf(bb, bb.length);
   }

   public byte[] create(BytesWritable bw) {
      return bw == null ? null : LazyUtils.createByteArray(bw);
   }
}
