package org.apache.hadoop.hive.serde2.lazydio;

import java.io.DataInputStream;
import java.io.IOException;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazy.LazyBoolean;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyBooleanObjectInspector;
import org.apache.hadoop.io.BooleanWritable;

public class LazyDioBoolean extends LazyBoolean {
   private ByteStream.Input in;
   private DataInputStream din;

   public LazyDioBoolean(LazyBooleanObjectInspector oi) {
      super(oi);
   }

   public LazyDioBoolean(LazyDioBoolean copy) {
      super((LazyBoolean)copy);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      boolean value = false;

      try {
         this.in = new ByteStream.Input(bytes.getData(), start, length);
         this.din = new DataInputStream(this.in);
         value = this.din.readBoolean();
         ((BooleanWritable)this.data).set(value);
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
