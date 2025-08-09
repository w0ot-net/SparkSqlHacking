package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.io.HiveIntervalDayTimeWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableHiveIntervalDayTimeObjectInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyBinaryHiveIntervalDayTime extends LazyBinaryPrimitive {
   static final Logger LOG = LoggerFactory.getLogger(LazyBinaryHiveIntervalDayTime.class);
   LazyBinaryUtils.VInt vInt = new LazyBinaryUtils.VInt();
   LazyBinaryUtils.VLong vLong = new LazyBinaryUtils.VLong();

   LazyBinaryHiveIntervalDayTime(WritableHiveIntervalDayTimeObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new HiveIntervalDayTimeWritable();
   }

   LazyBinaryHiveIntervalDayTime(LazyBinaryHiveIntervalDayTime copy) {
      super((LazyBinaryPrimitive)copy);
      this.data = new HiveIntervalDayTimeWritable((HiveIntervalDayTimeWritable)copy.data);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      ((HiveIntervalDayTimeWritable)this.data).setFromBytes(bytes.getData(), start, length, this.vInt, this.vLong);
   }
}
