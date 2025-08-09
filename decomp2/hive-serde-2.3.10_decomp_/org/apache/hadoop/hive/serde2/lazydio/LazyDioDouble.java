package org.apache.hadoop.hive.serde2.lazydio;

import java.io.DataInputStream;
import java.io.IOException;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazy.LazyDouble;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyDoubleObjectInspector;

public class LazyDioDouble extends LazyDouble {
   private ByteStream.Input in;
   private DataInputStream din;

   public LazyDioDouble(LazyDoubleObjectInspector oi) {
      super(oi);
   }

   LazyDioDouble(LazyDioDouble copy) {
      super((LazyDouble)copy);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      double value = (double)0.0F;

      try {
         this.in = new ByteStream.Input(bytes.getData(), start, length);
         this.din = new DataInputStream(this.in);
         value = this.din.readDouble();
         ((DoubleWritable)this.data).set(value);
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
