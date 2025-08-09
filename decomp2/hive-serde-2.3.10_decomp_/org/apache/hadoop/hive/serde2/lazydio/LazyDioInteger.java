package org.apache.hadoop.hive.serde2.lazydio;

import java.io.DataInputStream;
import java.io.IOException;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazy.LazyInteger;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyIntObjectInspector;
import org.apache.hadoop.io.IntWritable;

public class LazyDioInteger extends LazyInteger {
   private ByteStream.Input in;
   private DataInputStream din;

   public LazyDioInteger(LazyIntObjectInspector oi) {
      super(oi);
   }

   public LazyDioInteger(LazyDioInteger copy) {
      super((LazyInteger)copy);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      int value = 0;

      try {
         this.in = new ByteStream.Input(bytes.getData(), start, length);
         this.din = new DataInputStream(this.in);
         value = this.din.readInt();
         ((IntWritable)this.data).set(value);
         this.isNull = false;
      } catch (IOException var18) {
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
