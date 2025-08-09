package org.apache.derby.impl.store.raw.data;

import java.io.IOException;

public abstract class BufferedByteHolderInputStream extends ByteHolderInputStream {
   public BufferedByteHolderInputStream(ByteHolder var1) {
      super(var1);
   }

   public abstract void fillByteHolder() throws IOException;

   public int read() throws IOException {
      this.fillByteHolder();
      return super.read();
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      this.fillByteHolder();
      return super.read(var1, var2, var3);
   }

   public long skip(long var1) throws IOException {
      long var3;
      long var5;
      for(var3 = 0L; var3 < var1; var3 += var5) {
         this.fillByteHolder();
         var5 = super.skip(var1 - var3);
         if (var5 <= 0L) {
            break;
         }
      }

      return var3;
   }

   public int available() throws IOException {
      this.fillByteHolder();
      return super.available();
   }
}
