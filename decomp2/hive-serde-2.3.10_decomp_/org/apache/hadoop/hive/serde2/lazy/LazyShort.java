package org.apache.hadoop.hive.serde2.lazy;

import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyShortObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public class LazyShort extends LazyPrimitive {
   public LazyShort(LazyShortObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new ShortWritable();
   }

   public LazyShort(LazyShort copy) {
      super((LazyPrimitive)copy);
      this.data = new ShortWritable(((ShortWritable)copy.data).get());
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      if (!LazyUtils.isNumberMaybe(bytes.getData(), start, length)) {
         this.isNull = true;
      } else {
         try {
            ((ShortWritable)this.data).set(parseShort(bytes.getData(), start, length));
            this.isNull = false;
         } catch (NumberFormatException var5) {
            this.isNull = true;
            this.logExceptionMessage(bytes, start, length, "SMALLINT");
         }

      }
   }

   public static short parseShort(byte[] bytes, int start, int length) {
      return parseShort(bytes, start, length, 10);
   }

   public static short parseShort(byte[] bytes, int start, int length, int radix) {
      int intValue = LazyInteger.parseInt(bytes, start, length, radix);
      short result = (short)intValue;
      if (result == intValue) {
         return result;
      } else {
         throw new NumberFormatException();
      }
   }
}
