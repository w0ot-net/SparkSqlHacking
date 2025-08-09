package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableDateObjectInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyBinaryDate extends LazyBinaryPrimitive {
   static final Logger LOG = LoggerFactory.getLogger(LazyBinaryDate.class);
   LazyBinaryUtils.VInt vInt = new LazyBinaryUtils.VInt();

   LazyBinaryDate(WritableDateObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new DateWritable();
   }

   LazyBinaryDate(LazyBinaryDate copy) {
      super((LazyBinaryPrimitive)copy);
      this.data = new DateWritable((DateWritable)copy.data);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      LazyBinaryUtils.readVInt(bytes.getData(), start, this.vInt);

      assert length == this.vInt.length;

      ((DateWritable)this.data).set(this.vInt.value);
   }
}
