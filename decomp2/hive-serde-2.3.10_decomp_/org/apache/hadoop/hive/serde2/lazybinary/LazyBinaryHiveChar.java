package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.io.HiveCharWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableHiveCharObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.CharTypeInfo;
import org.apache.hadoop.io.Text;

public class LazyBinaryHiveChar extends LazyBinaryPrimitive {
   protected int maxLength = -1;

   LazyBinaryHiveChar(WritableHiveCharObjectInspector oi) {
      super((ObjectInspector)oi);
      this.maxLength = ((CharTypeInfo)oi.getTypeInfo()).getLength();
      this.data = new HiveCharWritable();
   }

   LazyBinaryHiveChar(LazyBinaryHiveChar copy) {
      super((LazyBinaryPrimitive)copy);
      this.maxLength = copy.maxLength;
      this.data = new HiveCharWritable((HiveCharWritable)copy.data);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      Text textValue = ((HiveCharWritable)this.data).getTextValue();
      textValue.set(bytes.getData(), start, length);
      ((HiveCharWritable)this.data).enforceMaxLength(this.maxLength);
   }
}
