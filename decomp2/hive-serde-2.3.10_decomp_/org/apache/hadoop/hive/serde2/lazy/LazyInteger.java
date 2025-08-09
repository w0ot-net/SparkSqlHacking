package org.apache.hadoop.hive.serde2.lazy;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyIntObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.IntWritable;

public class LazyInteger extends LazyPrimitive {
   public LazyInteger(LazyIntObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new IntWritable();
   }

   public LazyInteger(LazyInteger copy) {
      super((LazyPrimitive)copy);
      this.data = new IntWritable(((IntWritable)copy.data).get());
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      if (!LazyUtils.isNumberMaybe(bytes.getData(), start, length)) {
         this.isNull = true;
      } else {
         try {
            ((IntWritable)this.data).set(parseInt(bytes.getData(), start, length, 10));
            this.isNull = false;
         } catch (NumberFormatException var5) {
            this.isNull = true;
            this.logExceptionMessage(bytes, start, length, "INT");
         }

      }
   }

   public static int parseInt(byte[] bytes, int start, int length) {
      return parseInt(bytes, start, length, 10);
   }

   public static int parseInt(byte[] bytes, int start, int length, int radix) {
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

   private static int parse(byte[] bytes, int start, int length, int offset, int radix, boolean negative) {
      byte separator = 46;
      int max = Integer.MIN_VALUE / radix;
      int result = 0;

      int end;
      int next;
      for(end = start + length; offset < end; result = next) {
         int digit = LazyUtils.digit(bytes[offset++], radix);
         if (digit == -1) {
            if (bytes[offset - 1] != separator) {
               throw new NumberFormatException(LazyUtils.convertToString(bytes, start, length));
            }
            break;
         }

         if (max > result) {
            throw new NumberFormatException(LazyUtils.convertToString(bytes, start, length));
         }

         next = result * radix - digit;
         if (next > result) {
            throw new NumberFormatException(LazyUtils.convertToString(bytes, start, length));
         }
      }

      while(offset < end) {
         int digit = LazyUtils.digit(bytes[offset++], radix);
         if (digit == -1) {
            throw new NumberFormatException(LazyUtils.convertToString(bytes, start, length));
         }
      }

      if (!negative) {
         result = -result;
         if (result < 0) {
            throw new NumberFormatException(LazyUtils.convertToString(bytes, start, length));
         }
      }

      return result;
   }

   public static void writeUTF8(OutputStream out, int i) throws IOException {
      if (i == 0) {
         out.write(48);
      } else {
         boolean negative = i < 0;
         if (negative) {
            out.write(45);
         } else {
            i = -i;
         }

         int start;
         for(start = 1000000000; i / start == 0; start /= 10) {
         }

         while(start > 0) {
            out.write(48 - i / start % 10);
            start /= 10;
         }

      }
   }

   public static void writeUTF8NoException(OutputStream out, int i) {
      try {
         writeUTF8(out, i);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
}
