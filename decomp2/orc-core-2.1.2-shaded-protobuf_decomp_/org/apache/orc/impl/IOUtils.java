package org.apache.orc.impl;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public final class IOUtils {
   public static final int DEFAULT_BUFFER_SIZE = 8192;
   public static final int MAX_ARRAY_SIZE = 2147483639;
   private static final ThreadLocal SKIP_BYTE_BUFFER = ThreadLocal.withInitial(IOUtils::byteArray);

   public static byte[] byteArray() {
      return byteArray(8192);
   }

   public static byte[] byteArray(int size) {
      return new byte[size];
   }

   static byte[] getByteArray() {
      return (byte[])SKIP_BYTE_BUFFER.get();
   }

   public static void skipFully(InputStream input, long toSkip) throws IOException {
      if (toSkip < 0L) {
         throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip);
      } else {
         long skipped = skip(input, toSkip);
         if (skipped != toSkip) {
            throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
         }
      }
   }

   public static long skip(InputStream input, long toSkip) throws IOException {
      if (toSkip < 0L) {
         throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
      } else {
         long remain;
         long n;
         for(remain = toSkip; remain > 0L; remain -= n) {
            byte[] byteArray = getByteArray();
            n = (long)input.read(byteArray, 0, (int)Math.min(remain, (long)byteArray.length));
            if (n < 0L) {
               break;
            }
         }

         return toSkip - remain;
      }
   }
}
