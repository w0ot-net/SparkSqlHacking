package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.lazy.LazyBinary;
import org.apache.hadoop.hive.serde2.lazy.LazyUtils;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.BinaryObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.BytesWritable;

public class LazyBinaryObjectInspector extends AbstractPrimitiveLazyObjectInspector implements BinaryObjectInspector {
   public LazyBinaryObjectInspector() {
      super(TypeInfoFactory.binaryTypeInfo);
   }

   public Object copyObject(Object o) {
      return null == o ? null : new LazyBinary((LazyBinary)o);
   }

   public byte[] getPrimitiveJavaObject(Object o) {
      return null == o ? null : LazyUtils.createByteArray((BytesWritable)((LazyBinary)o).getWritableObject());
   }

   public BytesWritable getPrimitiveWritableObject(Object o) {
      return null == o ? null : (BytesWritable)((LazyBinary)o).getWritableObject();
   }
}
