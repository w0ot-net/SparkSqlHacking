package org.apache.hadoop.hive.serde2.lazydio;

import java.io.DataInputStream;
import java.io.IOException;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazy.LazyByte;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyByteObjectInspector;

public class LazyDioByte extends LazyByte {
   private ByteStream.Input in;
   private DataInputStream din;

   public LazyDioByte(LazyByteObjectInspector oi) {
      super(oi);
   }

   public LazyDioByte(LazyDioByte copy) {
      super((LazyByte)copy);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      byte value = 0;

      try {
         this.in = new ByteStream.Input(bytes.getData(), start, length);
         this.din = new DataInputStream(this.in);
         value = this.din.readByte();
         ((ByteWritable)this.data).set(value);
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
