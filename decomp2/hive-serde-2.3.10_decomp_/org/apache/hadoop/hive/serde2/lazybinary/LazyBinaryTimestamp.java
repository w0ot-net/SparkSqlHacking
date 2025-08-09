package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableTimestampObjectInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyBinaryTimestamp extends LazyBinaryPrimitive {
   static final Logger LOG = LoggerFactory.getLogger(LazyBinaryTimestamp.class);

   LazyBinaryTimestamp(WritableTimestampObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new TimestampWritable();
   }

   LazyBinaryTimestamp(LazyBinaryTimestamp copy) {
      super((LazyBinaryPrimitive)copy);
      this.data = new TimestampWritable((TimestampWritable)copy.data);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      ((TimestampWritable)this.data).set(bytes.getData(), start);
   }
}
