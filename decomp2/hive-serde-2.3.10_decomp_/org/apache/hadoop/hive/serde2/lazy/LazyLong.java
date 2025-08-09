package org.apache.hadoop.hive.serde2.lazy;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyLongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.LongWritable;

public class LazyLong extends LazyPrimitive {
   public LazyLong(LazyLongObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new LongWritable();
   }

   public LazyLong(LazyLong copy) {
      super((LazyPrimitive)copy);
      this.data = new LongWritable(((LongWritable)copy.data).get());
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      if (!LazyUtils.isNumberMaybe(bytes.getData(), start, length)) {
         this.isNull = true;
      } else {
         try {
            ((LongWritable)this.data).set(parseLong(bytes.getData(), start, length, 10));
            this.isNull = false;
         } catch (NumberFormatException var5) {
            this.isNull = true;
            this.logExceptionMessage(bytes, start, length, "BIGINT");
         }

      }
   }

   public static long parseLong(byte[] bytes, int start, int length) {
      return parseLong(bytes, start, length, 10);
   }

   public static long parseLong(byte[] bytes, int start, int length, int radix) {
      if (bytes == null) {
         throw new NumberFormatException("String is null");
      } else if (radix >= 2 && radix <= 36) {
         if (length == 0) {
            throw new NumberFormatException("Empty string!");
         } else {
            int offset = start;
            boolean negative = bytes[start] == 45;
            if (negative || bytes[start] == 43) {
               offset = start + 1;
               if (length == 1) {
                  throw new NumberFormatException(LazyUtils.convertToString(bytes, start, length));
               }
            }

            return parse(bytes, start, length, offset, radix, negative);
         }
      } else {
         throw new NumberFormatException("Invalid radix: " + radix);
      }
   }

   private static long parse(byte[] bytes, int start, int length, int offset, int radix, boolean negative) {
      byte separator = 46;
      long max = Long.MIN_VALUE / (long)radix;
      long result = 0L;

      long end;
      long next;
      for(end = (long)(start + length); (long)offset < end; result = next) {
         int digit = LazyUtils.digit(bytes[offset++], radix);
         if (digit == -1 || max > result) {
            if (bytes[offset - 1] != separator) {
               throw new NumberFormatException(LazyUtils.convertToString(bytes, start, length));
            }
            break;
         }

         next = result * (long)radix - (long)digit;
         if (next > result) {
            throw new NumberFormatException(LazyUtils.convertToString(bytes, start, length));
         }
      }

      while((long)offset < end) {
         int digit = LazyUtils.digit(bytes[offset++], radix);
         if (digit == -1) {
            throw new NumberFormatException(LazyUtils.convertToString(bytes, start, length));
         }
      }

      if (!negative) {
         result = -result;
         if (result < 0L) {
            throw new NumberFormatException(LazyUtils.convertToString(bytes, start, length));
         }
      }

      return result;
   }

   public static void writeUTF8(OutputStream out, long i) throws IOException {
      if (i == 0L) {
         out.write(48);
      } else {
         boolean negative = i < 0L;
         if (negative) {
            out.write(45);
         } else {
            i = -i;
         }

         long start;
         for(start = 1000000000000000000L; i / start == 0L; start /= 10L) {
         }

         while(start > 0L) {
            out.write(48 - (int)(i / start % 10L));
            start /= 10L;
         }

      }
   }

   public static void writeUTF8NoException(OutputStream out, long i) {
      try {
         writeUTF8(out, i);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
}
