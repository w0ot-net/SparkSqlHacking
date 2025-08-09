package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.util.Arrays;
import org.apache.hadoop.hive.serde2.lazy.LazyUtils;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.BytesWritable;

public class WritableBinaryObjectInspector extends AbstractPrimitiveWritableObjectInspector implements SettableBinaryObjectInspector {
   WritableBinaryObjectInspector() {
      super(TypeInfoFactory.binaryTypeInfo);
   }

   public BytesWritable copyObject(Object o) {
      if (null == o) {
         return null;
      } else {
         BytesWritable incoming = (BytesWritable)o;
         byte[] bytes = new byte[incoming.getLength()];
         System.arraycopy(incoming.getBytes(), 0, bytes, 0, incoming.getLength());
         return new BytesWritable(bytes);
      }
   }

   public byte[] getPrimitiveJavaObject(Object o) {
      return o == null ? null : LazyUtils.createByteArray((BytesWritable)o);
   }

   public BytesWritable getPrimitiveWritableObject(Object o) {
      return null == o ? null : (BytesWritable)o;
   }

   public BytesWritable set(Object o, byte[] bb) {
      BytesWritable incoming = (BytesWritable)o;
      if (bb != null) {
         incoming.set(bb, 0, bb.length);
      }

      return incoming;
   }

   public BytesWritable set(Object o, BytesWritable bw) {
      BytesWritable incoming = (BytesWritable)o;
      if (bw != null) {
         incoming.set(bw);
      }

      return incoming;
   }

   public BytesWritable create(byte[] bb) {
      return new BytesWritable(Arrays.copyOf(bb, bb.length));
   }

   public BytesWritable create(BytesWritable bw) {
      BytesWritable newCpy = new BytesWritable();
      if (null != bw) {
         newCpy.set(bw);
      }

      return newCpy;
   }
}
