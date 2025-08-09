package org.apache.derby.impl.jdbc;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

final class ClobAsciiStream extends OutputStream {
   private final Writer writer;
   private final char[] buffer = new char[1024];

   ClobAsciiStream(Writer var1) {
      this.writer = var1;
   }

   public void write(int var1) throws IOException {
      this.writer.write(var1 & 255);
   }

   public void write(byte[] var1, int var2, int var3) throws IOException {
      while(var3 > 0) {
         int var4 = Math.min(var3, this.buffer.length);

         for(int var5 = 0; var5 < var4; ++var5) {
            this.buffer[var5] = (char)(var1[var2 + var5] & 255);
         }

         this.writer.write(this.buffer, 0, var4);
         var2 += var4;
         var3 -= var4;
      }

   }
}
