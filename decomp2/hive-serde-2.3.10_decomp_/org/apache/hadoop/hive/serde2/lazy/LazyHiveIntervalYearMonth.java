package org.apache.hadoop.hive.serde2.lazy;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.serde2.io.HiveIntervalYearMonthWritable;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyHiveIntervalYearMonthObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Text;

public class LazyHiveIntervalYearMonth extends LazyPrimitive {
   public LazyHiveIntervalYearMonth(LazyHiveIntervalYearMonthObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new HiveIntervalYearMonthWritable();
   }

   public LazyHiveIntervalYearMonth(LazyHiveIntervalYearMonth copy) {
      super((LazyPrimitive)copy);
      this.data = new HiveIntervalYearMonthWritable((HiveIntervalYearMonthWritable)copy.data);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      String s = null;

      try {
         s = Text.decode(bytes.getData(), start, length);
         ((HiveIntervalYearMonthWritable)this.data).set(HiveIntervalYearMonth.valueOf(s));
         this.isNull = false;
      } catch (Exception var6) {
         this.isNull = true;
         this.logExceptionMessage(bytes, start, length, "INTERVAL_YEAR_MONTH");
      }

   }

   public static void writeUTF8(OutputStream out, HiveIntervalYearMonthWritable i) throws IOException {
      ByteBuffer b = Text.encode(i.toString());
      out.write(b.array(), 0, b.limit());
   }

   public HiveIntervalYearMonthWritable getWritableObject() {
      return (HiveIntervalYearMonthWritable)this.data;
   }
}
