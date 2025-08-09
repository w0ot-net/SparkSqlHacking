package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.io.HiveIntervalYearMonthWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableHiveIntervalYearMonthObjectInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyBinaryHiveIntervalYearMonth extends LazyBinaryPrimitive {
   static final Logger LOG = LoggerFactory.getLogger(LazyBinaryHiveIntervalYearMonth.class);
   LazyBinaryUtils.VInt vInt = new LazyBinaryUtils.VInt();

   LazyBinaryHiveIntervalYearMonth(WritableHiveIntervalYearMonthObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new HiveIntervalYearMonthWritable();
   }

   LazyBinaryHiveIntervalYearMonth(LazyBinaryHiveIntervalYearMonth copy) {
      super((LazyBinaryPrimitive)copy);
      this.data = new HiveIntervalYearMonthWritable((HiveIntervalYearMonthWritable)copy.data);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      ((HiveIntervalYearMonthWritable)this.data).setFromBytes(bytes.getData(), start, length, this.vInt);
   }
}
