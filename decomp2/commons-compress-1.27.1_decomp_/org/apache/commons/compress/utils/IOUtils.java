package org.apache.commons.compress.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import org.apache.commons.io.FileUtils;

public final class IOUtils {
   public static final LinkOption[] EMPTY_LINK_OPTIONS = new LinkOption[0];

   /** @deprecated */
   @Deprecated
   public static void closeQuietly(Closeable c) {
      org.apache.commons.io.IOUtils.closeQuietly(c);
   }

   /** @deprecated */
   @Deprecated
   public static void copy(File sourceFile, OutputStream outputStream) throws IOException {
      FileUtils.copyFile(sourceFile, outputStream);
   }

   /** @deprecated */
   @Deprecated
   public static long copy(InputStream input, OutputStream output) throws IOException {
      return (long)org.apache.commons.io.IOUtils.copy(input, output);
   }

   /** @deprecated */
   @Deprecated
   public static long copy(InputStream input, OutputStream output, int bufferSize) throws IOException {
      return org.apache.commons.io.IOUtils.copy(input, output, bufferSize);
   }

   /** @deprecated */
   @Deprecated
   public static long copyRange(InputStream input, long len, OutputStream output) throws IOException {
      return org.apache.commons.io.IOUtils.copyLarge(input, output, 0L, len);
   }

   /** @deprecated */
   @Deprecated
   public static long copyRange(InputStream input, long length, OutputStream output, int bufferSize) throws IOException {
      if (bufferSize < 1) {
         throw new IllegalArgumentException("bufferSize must be bigger than 0");
      } else {
         byte[] buffer = new byte[(int)Math.min((long)bufferSize, Math.max(0L, length))];
         int n = 0;

         long count;
         for(count = 0L; count < length && -1 != (n = input.read(buffer, 0, (int)Math.min(length - count, (long)buffer.length))); count += (long)n) {
            if (output != null) {
               output.write(buffer, 0, n);
            }
         }

         return count;
      }
   }

   /** @deprecated */
   @Deprecated
   public static int read(File file, byte[] array) throws IOException {
      InputStream inputStream = Files.newInputStream(file.toPath());

      int var3;
      try {
         var3 = readFully(inputStream, array, 0, array.length);
      } catch (Throwable var6) {
         if (inputStream != null) {
            try {
               inputStream.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }
         }

         throw var6;
      }

      if (inputStream != null) {
         inputStream.close();
      }

      return var3;
   }

   public static int readFully(InputStream input, byte[] array) throws IOException {
      return readFully(input, array, 0, array.length);
   }

   public static int readFully(InputStream input, byte[] array, int offset, int length) throws IOException {
      if (length >= 0 && offset >= 0 && length + offset <= array.length && length + offset >= 0) {
         return org.apache.commons.io.IOUtils.read(input, array, offset, length);
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public static void readFully(ReadableByteChannel channel, ByteBuffer byteBuffer) throws IOException {
      int expectedLength = byteBuffer.remaining();
      int read = org.apache.commons.io.IOUtils.read(channel, byteBuffer);
      if (read < expectedLength) {
         throw new EOFException();
      }
   }

   public static byte[] readRange(InputStream input, int length) throws IOException {
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      org.apache.commons.io.IOUtils.copyLarge(input, output, 0L, (long)length);
      return output.toByteArray();
   }

   public static byte[] readRange(ReadableByteChannel input, int length) throws IOException {
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      ByteBuffer b = ByteBuffer.allocate(Math.min(length, 8192));

      int readCount;
      for(int read = 0; read < length; read += readCount) {
         b.limit(Math.min(length - read, b.capacity()));
         readCount = input.read(b);
         if (readCount <= 0) {
            break;
         }

         output.write(b.array(), 0, readCount);
         b.rewind();
      }

      return output.toByteArray();
   }

   public static long skip(InputStream input, long toSkip) throws IOException {
      return org.apache.commons.io.IOUtils.skip(input, toSkip, org.apache.commons.io.IOUtils::byteArray);
   }

   /** @deprecated */
   @Deprecated
   public static byte[] toByteArray(InputStream input) throws IOException {
      return org.apache.commons.io.IOUtils.toByteArray(input);
   }

   private IOUtils() {
   }
}
