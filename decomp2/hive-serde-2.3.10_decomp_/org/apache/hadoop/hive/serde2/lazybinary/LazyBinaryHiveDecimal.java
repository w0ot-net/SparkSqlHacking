package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableHiveDecimalObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.DecimalTypeInfo;

public class LazyBinaryHiveDecimal extends LazyBinaryPrimitive {
   private int precision;
   private int scale;

   LazyBinaryHiveDecimal(WritableHiveDecimalObjectInspector oi) {
      super((ObjectInspector)oi);
      DecimalTypeInfo typeInfo = (DecimalTypeInfo)oi.getTypeInfo();
      this.precision = typeInfo.precision();
      this.scale = typeInfo.scale();
      this.data = new HiveDecimalWritable();
   }

   LazyBinaryHiveDecimal(LazyBinaryHiveDecimal copy) {
      super((LazyBinaryPrimitive)copy);
      this.data = new HiveDecimalWritable((HiveDecimalWritable)copy.data);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      LazyBinarySerDe.setFromBigIntegerBytesAndScale(bytes.getData(), start, length, (HiveDecimalWritable)this.data);
      ((HiveDecimalWritable)this.data).mutateEnforcePrecisionScale(this.precision, this.scale);
   }
}
