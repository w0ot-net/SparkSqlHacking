package org.apache.derby.impl.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public final class ReaderToAscii extends InputStream {
   private final Reader data;
   private char[] conv;
   private boolean closed;

   public ReaderToAscii(Reader var1) {
      this.data = var1;
      if (!(var1 instanceof UTF8Reader)) {
         this.conv = new char[256];
      }

   }

   public int read() throws IOException {
      if (this.closed) {
         throw new IOException();
      } else {
         int var1 = this.data.read();
         if (var1 == -1) {
            return -1;
         } else {
            return var1 <= 255 ? var1 & 255 : 63;
         }
      }
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      if (this.closed) {
         throw new IOException();
      } else if (this.data instanceof UTF8Reader) {
         return ((UTF8Reader)this.data).readAsciiInto(var1, var2, var3);
      } else {
         if (var3 > this.conv.length) {
            var3 = this.conv.length;
         }

         var3 = this.data.read(this.conv, 0, var3);
         if (var3 == -1) {
            return -1;
         } else {
            for(int var4 = 0; var4 < var3; ++var4) {
               char var5 = this.conv[var4];
               byte var6;
               if (var5 <= 255) {
                  var6 = (byte)var5;
               } else {
                  var6 = 63;
               }

               var1[var2++] = var6;
            }

            return var3;
         }
      }
   }

   public long skip(long var1) throws IOException {
      if (this.closed) {
         throw new IOException();
      } else {
         return this.data.skip(var1);
      }
   }

   public void close() throws IOException {
      if (!this.closed) {
         this.closed = true;
         this.data.close();
      }

   }
}
