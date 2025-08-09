package org.apache.hadoop.hive.serde2.lazydio;

import java.io.DataInputStream;
import java.io.IOException;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazy.LazyFloat;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyFloatObjectInspector;
import org.apache.hadoop.io.FloatWritable;

public class LazyDioFloat extends LazyFloat {
   private ByteStream.Input in;
   private DataInputStream din;

   public LazyDioFloat(LazyFloatObjectInspector oi) {
      super(oi);
   }

   public LazyDioFloat(LazyDioFloat copy) {
      super((LazyFloat)copy);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      float value = 0.0F;

      try {
         this.in = new ByteStream.Input(bytes.getData(), start, length);
         this.din = new DataInputStream(this.in);
         value = this.din.readFloat();
         ((FloatWritable)this.data).set(value);
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
