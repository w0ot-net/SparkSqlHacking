package org.apache.hadoop.hive.serde2.lazy;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.serde2.io.HiveIntervalDayTimeWritable;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyHiveIntervalDayTimeObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Text;

public class LazyHiveIntervalDayTime extends LazyPrimitive {
   public LazyHiveIntervalDayTime(LazyHiveIntervalDayTimeObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new HiveIntervalDayTimeWritable();
   }

   public LazyHiveIntervalDayTime(LazyHiveIntervalDayTime copy) {
      super((LazyPrimitive)copy);
      this.data = new HiveIntervalDayTimeWritable((HiveIntervalDayTimeWritable)copy.data);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      String s = null;

      try {
         s = Text.decode(bytes.getData(), start, length);
         ((HiveIntervalDayTimeWritable)this.data).set(HiveIntervalDayTime.valueOf(s));
         this.isNull = false;
      } catch (Exception var6) {
         this.isNull = true;
         this.logExceptionMessage(bytes, start, length, "INTERVAL_DAY_TIME");
      }

   }

   public static void writeUTF8(OutputStream out, HiveIntervalDayTimeWritable i) throws IOException {
      ByteBuffer b = Text.encode(i.toString());
      out.write(b.array(), 0, b.limit());
   }

   public HiveIntervalDayTimeWritable getWritableObject() {
      return (HiveIntervalDayTimeWritable)this.data;
   }
}
