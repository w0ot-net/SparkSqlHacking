package org.apache.derby.iapi.services.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public final class InputStreamUtil {
   private static final int SKIP_FRAGMENT_SIZE = Integer.MAX_VALUE;

   public static int readUnsignedByte(InputStream var0) throws IOException {
      int var1 = var0.read();
      if (var1 < 0) {
         throw new EOFException();
      } else {
         return var1;
      }
   }

   public static void readFully(InputStream var0, byte[] var1, int var2, int var3) throws IOException {
      do {
         int var4 = var0.read(var1, var2, var3);
         if (var4 < 0) {
            throw new EOFException();
         }

         var3 -= var4;
         var2 += var4;
      } while(var3 != 0);

   }

   public static int readLoop(InputStream var0, byte[] var1, int var2, int var3) throws IOException {
      int var4 = var2;

      do {
         int var5 = var0.read(var1, var2, var3);
         if (var5 <= 0) {
            break;
         }

         var3 -= var5;
         var2 += var5;
      } while(var3 != 0);

      return var2 - var4;
   }

   public static long skipUntilEOF(InputStream var0) throws IOException {
      if (var0 == null) {
         throw new NullPointerException();
      } else {
         long var1 = 0L;

         long var3;
         do {
            var3 = skipPersistent(var0, 2147483647L);
            var1 += var3;
         } while(var3 >= 2147483647L);

         return var1;
      }
   }

   public static void skipFully(InputStream var0, long var1) throws IOException {
      if (var0 == null) {
         throw new NullPointerException();
      } else if (var1 > 0L) {
         long var3 = skipPersistent(var0, var1);
         if (var3 < var1) {
            throw new EOFException();
         }
      }
   }

   public static final long skipPersistent(InputStream var0, long var1) throws IOException {
      long var3;
      long var5;
      for(var3 = 0L; var3 < var1; var3 += var5) {
         var5 = var0.skip(var1 - var3);
         if (var5 == 0L) {
            if (var0.read() == -1) {
               break;
            }

            var5 = 1L;
         }
      }

      return var3;
   }
}
