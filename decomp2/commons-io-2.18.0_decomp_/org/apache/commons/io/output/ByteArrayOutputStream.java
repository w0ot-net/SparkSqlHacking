package org.apache.commons.io.output;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteArrayOutputStream extends AbstractByteArrayOutputStream {
   public static InputStream toBufferedInputStream(InputStream input) throws IOException {
      return toBufferedInputStream(input, 1024);
   }

   public static InputStream toBufferedInputStream(InputStream input, int size) throws IOException {
      ByteArrayOutputStream output = new ByteArrayOutputStream(size);

      InputStream var3;
      try {
         output.write(input);
         var3 = output.toInputStream();
      } catch (Throwable var6) {
         try {
            output.close();
         } catch (Throwable var5) {
            var6.addSuppressed(var5);
         }

         throw var6;
      }

      output.close();
      return var3;
   }

   public ByteArrayOutputStream() {
      this(1024);
   }

   public ByteArrayOutputStream(int size) {
      if (size < 0) {
         throw new IllegalArgumentException("Negative initial size: " + size);
      } else {
         synchronized(this) {
            this.needNewBuffer(size);
         }
      }
   }

   public synchronized void reset() {
      this.resetImpl();
   }

   public synchronized int size() {
      return this.count;
   }

   public synchronized byte[] toByteArray() {
      return this.toByteArrayImpl();
   }

   public synchronized InputStream toInputStream() {
      return this.toInputStream(ByteArrayInputStream::new);
   }

   public void write(byte[] b, int off, int len) {
      if (off >= 0 && off <= b.length && len >= 0 && off + len <= b.length && off + len >= 0) {
         if (len != 0) {
            synchronized(this) {
               this.writeImpl(b, off, len);
            }
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public synchronized int write(InputStream in) throws IOException {
      return this.writeImpl(in);
   }

   public synchronized void write(int b) {
      this.writeImpl(b);
   }

   public synchronized void writeTo(OutputStream out) throws IOException {
      this.writeToImpl(out);
   }
}
