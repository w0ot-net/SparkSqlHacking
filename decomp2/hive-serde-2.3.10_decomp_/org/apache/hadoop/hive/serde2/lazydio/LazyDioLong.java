package org.apache.hadoop.hive.serde2.lazydio;

import java.io.DataInputStream;
import java.io.IOException;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazy.LazyLong;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyLongObjectInspector;
import org.apache.hadoop.io.LongWritable;

public class LazyDioLong extends LazyLong {
   private ByteStream.Input in;
   private DataInputStream din;

   public LazyDioLong(LazyLongObjectInspector oi) {
      super(oi);
   }

   public LazyDioLong(LazyDioLong copy) {
      super((LazyLong)copy);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      long value = 0L;

      try {
         this.in = new ByteStream.Input(bytes.getData(), start, length);
         this.din = new DataInputStream(this.in);
         value = this.din.readLong();
         ((LongWritable)this.data).set(value);
         this.isNull = false;
      } catch (IOException var19) {
         this.isNull = true;
      } finally {
         try {
            this.din.close();
         } catch (IOException var18) {
         }

         try {
            this.in.close();
         } catch (IOException var17) {
         }

      }

   }
}
