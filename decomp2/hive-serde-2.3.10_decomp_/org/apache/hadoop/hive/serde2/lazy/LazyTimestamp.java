package org.apache.hadoop.hive.serde2.lazy;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyTimestampObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyTimestamp extends LazyPrimitive {
   private static final Logger LOG = LoggerFactory.getLogger(LazyTimestamp.class);

   public LazyTimestamp(LazyTimestampObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new TimestampWritable();
   }

   public LazyTimestamp(LazyTimestamp copy) {
      super((LazyPrimitive)copy);
      this.data = new TimestampWritable((TimestampWritable)copy.data);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      String s = null;
      if (!LazyUtils.isDateMaybe(bytes.getData(), start, length)) {
         this.isNull = true;
      } else {
         try {
            s = new String(bytes.getData(), start, length, "US-ASCII");
         } catch (UnsupportedEncodingException e) {
            LOG.error("Unsupported encoding found ", e);
            s = "";
         }

         Timestamp t = null;
         if (s.compareTo("NULL") == 0) {
            this.isNull = true;
            this.logExceptionMessage(bytes, start, length, "TIMESTAMP");
         } else {
            try {
               t = ((LazyTimestampObjectInspector)this.oi).getTimestampParser().parseTimestamp(s);
               this.isNull = false;
            } catch (IllegalArgumentException var7) {
               this.isNull = true;
               this.logExceptionMessage(bytes, start, length, "TIMESTAMP");
            }
         }

         ((TimestampWritable)this.data).set(t);
      }
   }

   public static void writeUTF8(OutputStream out, TimestampWritable i) throws IOException {
      if (i == null) {
         out.write(TimestampWritable.nullBytes);
      } else {
         out.write(i.toString().getBytes("US-ASCII"));
      }

   }

   public TimestampWritable getWritableObject() {
      return (TimestampWritable)this.data;
   }
}
