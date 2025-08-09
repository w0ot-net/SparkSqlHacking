package org.apache.hadoop.hive.serde2.lazydio;

import java.io.DataInputStream;
import java.io.IOException;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazy.LazyShort;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyShortObjectInspector;

public class LazyDioShort extends LazyShort {
   private ByteStream.Input in;
   private DataInputStream din;

   public LazyDioShort(LazyShortObjectInspector oi) {
      super(oi);
   }

   public LazyDioShort(LazyDioShort copy) {
      super((LazyShort)copy);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      short value = 0;

      try {
         this.in = new ByteStream.Input(bytes.getData(), start, length);
         this.din = new DataInputStream(this.in);
         value = this.din.readShort();
         ((ShortWritable)this.data).set(value);
         this.isNull = false;
      } catch (Exception var18) {
         this.isNull = true;
      } finally {
         try {
            this.din.close();
         } catch (IOException var17) {
         }

         try {
            this.in.close();
         } catch (IOException var16) {
         }

      }

   }
}
