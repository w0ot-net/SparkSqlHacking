package org.apache.derby.iapi.util;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UTFDataFormatException;
import org.apache.derby.iapi.services.io.InputStreamUtil;

public final class UTF8Util {
   private UTF8Util() {
   }

   public static final long skipUntilEOF(InputStream var0) throws IOException {
      return internalSkip(var0, Long.MAX_VALUE).charsSkipped();
   }

   public static final long skipFully(InputStream var0, long var1) throws EOFException, IOException {
      SkipCount var3 = internalSkip(var0, var1);
      if (var3.charsSkipped() != var1) {
         long var10002 = var3.charsSkipped();
         throw new EOFException("Reached end-of-stream prematurely at character/byte position " + var10002 + "/" + var3.bytesSkipped() + ", trying to skip " + var1);
      } else {
         return var3.bytesSkipped();
      }
   }

   private static final SkipCount internalSkip(InputStream var0, long var1) throws IOException {
      long var3 = 0L;
      long var5 = 0L;

      while(var3 < var1) {
         int var7 = var0.read();
         if (var7 == -1) {
            break;
         }

         ++var3;
         if ((var7 & 128) == 0) {
            ++var5;
         } else if ((var7 & 96) == 64) {
            if (InputStreamUtil.skipPersistent(var0, 1L) != 1L) {
               throw new UTFDataFormatException("Second byte in two byte character missing; byte pos " + var5 + " ; char pos " + var3);
            }

            var5 += 2L;
         } else {
            if ((var7 & 112) != 96) {
               throw new UTFDataFormatException("Invalid UTF-8 encoding encountered: (decimal) " + var7);
            }

            int var8 = 0;
            if (var7 == 224) {
               int var9 = var0.read();
               int var10 = var0.read();
               if (var9 == 0 && var10 == 0) {
                  --var3;
                  break;
               }

               if (var9 != -1 && var10 != -1) {
                  var8 = 2;
               }
            } else {
               var8 = (int)InputStreamUtil.skipPersistent(var0, 2L);
            }

            if (var8 != 2) {
               throw new UTFDataFormatException("Second or third byte in three byte character missing; byte pos " + var5 + " ; char pos " + var3);
            }

            var5 += 3L;
         }
      }

      return new SkipCount(var3, var5);
   }

   private static final class SkipCount {
      private final long byteCount;
      private final long charCount;

      SkipCount(long var1, long var3) {
         if (var3 >= 0L && var1 >= 0L) {
            if (var3 < var1) {
               throw new IllegalArgumentException("Number of bytes cannot beless than number of chars: " + var3 + " < " + var1);
            } else {
               this.byteCount = var3;
               this.charCount = var1;
            }
         } else {
            throw new IllegalArgumentException("charCount/byteCount cannot be negative: " + var1 + "/" + var3);
         }
      }

      long charsSkipped() {
         return this.charCount;
      }

      long bytesSkipped() {
         return this.byteCount;
      }
   }
}
