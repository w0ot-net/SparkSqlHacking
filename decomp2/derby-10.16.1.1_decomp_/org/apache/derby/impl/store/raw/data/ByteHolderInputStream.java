package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import java.io.InputStream;

public class ByteHolderInputStream extends InputStream {
   protected ByteHolder bh;

   public ByteHolderInputStream(ByteHolder var1) {
      this.bh = var1;
   }

   public int read() throws IOException {
      return this.bh.read();
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      return this.bh.read(var1, var2, var3);
   }

   public long skip(long var1) throws IOException {
      return this.bh.skip(var1);
   }

   public int available() throws IOException {
      return this.bh.available();
   }

   public void setByteHolder(ByteHolder var1) {
      this.bh = var1;
   }

   public ByteHolder getByteHolder() {
      return this.bh;
   }
}
