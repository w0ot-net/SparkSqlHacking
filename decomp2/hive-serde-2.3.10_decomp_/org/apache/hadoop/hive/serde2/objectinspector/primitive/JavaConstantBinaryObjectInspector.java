package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.io.BytesWritable;

public class JavaConstantBinaryObjectInspector extends JavaBinaryObjectInspector implements ConstantObjectInspector {
   private byte[] value;

   public JavaConstantBinaryObjectInspector(byte[] value) {
      this.value = value;
   }

   public Object getWritableConstantValue() {
      return this.value == null ? null : new BytesWritable(this.value);
   }
}
