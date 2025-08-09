package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.io.HiveVarcharWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableHiveVarcharObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.VarcharTypeInfo;
import org.apache.hadoop.io.Text;

public class LazyBinaryHiveVarchar extends LazyBinaryPrimitive {
   protected int maxLength = -1;

   LazyBinaryHiveVarchar(WritableHiveVarcharObjectInspector oi) {
      super((ObjectInspector)oi);
      this.maxLength = ((VarcharTypeInfo)oi.getTypeInfo()).getLength();
      this.data = new HiveVarcharWritable();
   }

   LazyBinaryHiveVarchar(LazyBinaryHiveVarchar copy) {
      super((LazyBinaryPrimitive)copy);
      this.maxLength = copy.maxLength;
      this.data = new HiveVarcharWritable((HiveVarcharWritable)copy.data);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      Text textValue = ((HiveVarcharWritable)this.data).getTextValue();
      textValue.set(bytes.getData(), start, length);
      ((HiveVarcharWritable)this.data).enforceMaxLength(this.maxLength);
   }
}
