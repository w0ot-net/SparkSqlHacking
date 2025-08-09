package org.apache.hadoop.hive.serde2.lazy;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.sql.Date;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyDateObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyDate extends LazyPrimitive {
   private static final Logger LOG = LoggerFactory.getLogger(LazyDate.class);

   public LazyDate(LazyDateObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new DateWritable();
   }

   public LazyDate(LazyDate copy) {
      super((LazyPrimitive)copy);
      this.data = new DateWritable((DateWritable)copy.data);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      String s = null;
      if (!LazyUtils.isDateMaybe(bytes.getData(), start, length)) {
         this.isNull = true;
      } else {
         try {
            s = Text.decode(bytes.getData(), start, length);
            ((DateWritable)this.data).set(Date.valueOf(s));
            this.isNull = false;
         } catch (Exception var6) {
            this.isNull = true;
            this.logExceptionMessage(bytes, start, length, "DATE");
         }

      }
   }

   public static void writeUTF8(OutputStream out, DateWritable d) throws IOException {
      ByteBuffer b = Text.encode(d.toString());
      out.write(b.array(), 0, b.limit());
   }
}
