package org.apache.derby.impl.jdbc;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

class BinaryToRawStream extends FilterInputStream {
   private int length;
   private Object parent;

   BinaryToRawStream(InputStream var1, Object var2) throws IOException {
      super(var1);
      this.parent = var2;
      int var3 = var1.read();
      if (var3 == -1) {
         throw new EOFException();
      } else {
         if ((var3 & 128) != 0) {
            if (var3 == 192) {
               int var4 = var1.read();
               int var5 = var1.read();
               int var6 = var1.read();
               int var7 = var1.read();
               if (var4 == -1 || var5 == -1 || var6 == -1 || var7 == -1) {
                  throw new EOFException();
               }

               this.length = (var4 & 255) << 24 | (var5 & 255) << 16 | (var6 & 255) << 8 | var7 & 255;
            } else if (var3 == 160) {
               int var8 = var1.read();
               int var10 = var1.read();
               if (var8 == -1 || var10 == -1) {
                  throw new EOFException();
               }

               this.length = ((var8 & 255) << 8) + (var10 & 255);
            } else {
               this.length = var3 & 31;
            }
         } else {
            int var9 = var1.read();
            int var11 = var1.read();
            int var12 = var1.read();
            if (var9 == -1 || var11 == -1 || var12 == -1) {
               throw new EOFException();
            }

            int var13 = (var3 & 255) << 24 | (var9 & 255) << 16 | (var11 & 255) << 8 | var12 & 255;
            this.length = var13 / 8;
            if (var13 % 8 != 0) {
               ++this.length;
            }

            if (this.length == 0) {
               this.length = -1;
            }
         }

      }
   }

   int getLength() {
      return this.length;
   }
}
