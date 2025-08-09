package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;

public class JavaConstantByteObjectInspector extends JavaByteObjectInspector implements ConstantObjectInspector {
   private Byte value;

   public JavaConstantByteObjectInspector(Byte value) {
      this.value = value;
   }

   public Object getWritableConstantValue() {
      return this.value == null ? null : new ByteWritable(this.value);
   }
}
