package org.apache.hadoop.hive.serde2.lazy;

import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyByteObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public class LazyByte extends LazyPrimitive {
   public LazyByte(LazyByteObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new ByteWritable();
   }

   public LazyByte(LazyByte copy) {
      super((LazyPrimitive)copy);
      this.data = new ByteWritable(((ByteWritable)copy.data).get());
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      if (!LazyUtils.isNumberMaybe(bytes.getData(), start, length)) {
         this.isNull = true;
      } else {
         try {
            ((ByteWritable)this.data).set(parseByte(bytes.getData(), start, length, 10));
            this.isNull = false;
         } catch (NumberFormatException var5) {
            this.isNull = true;
            this.logExceptionMessage(bytes, start, length, "TINYINT");
         }

      }
   }

   public static byte parseByte(byte[] bytes, int start, int length) {
      return parseByte(bytes, start, length, 10);
   }

   public static byte parseByte(byte[] bytes, int start, int length, int radix) {
      int intValue = LazyInteger.parseInt(bytes, start, length, radix);
      byte result = (byte)intValue;
      if (result == intValue) {
         return result;
      } else {
         throw new NumberFormatException();
      }
   }
}
