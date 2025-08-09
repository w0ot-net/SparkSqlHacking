package org.apache.commons.io.output;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.Uncheck;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;

public final class UnsynchronizedByteArrayOutputStream extends AbstractByteArrayOutputStream {
   public static Builder builder() {
      return new Builder();
   }

   public static InputStream toBufferedInputStream(InputStream input) throws IOException {
      return toBufferedInputStream(input, 1024);
   }

   public static InputStream toBufferedInputStream(InputStream input, int size) throws IOException {
      UnsynchronizedByteArrayOutputStream output = ((Builder)builder().setBufferSize(size)).get();

      InputStream var3;
      try {
         output.write(input);
         var3 = output.toInputStream();
      } catch (Throwable var6) {
         if (output != null) {
            try {
               output.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }
         }

         throw var6;
      }

      if (output != null) {
         output.close();
      }

      return var3;
   }

   /** @deprecated */
   @Deprecated
   public UnsynchronizedByteArrayOutputStream() {
      this(1024);
   }

   /** @deprecated */
   @Deprecated
   public UnsynchronizedByteArrayOutputStream(int size) {
      if (size < 0) {
         throw new IllegalArgumentException("Negative initial size: " + size);
      } else {
         this.needNewBuffer(size);
      }
   }

   public void reset() {
      this.resetImpl();
   }

   public int size() {
      return this.count;
   }

   public byte[] toByteArray() {
      return this.toByteArrayImpl();
   }

   public InputStream toInputStream() {
      return this.toInputStream((buffer, offset, length) -> (UnsynchronizedByteArrayInputStream)Uncheck.get(() -> UnsynchronizedByteArrayInputStream.builder().setByteArray(buffer).setOffset(offset).setLength(length).get()));
   }

   public void write(byte[] b, int off, int len) {
      if (off >= 0 && off <= b.length && len >= 0 && off + len <= b.length && off + len >= 0) {
         if (len != 0) {
            this.writeImpl(b, off, len);
         }
      } else {
         throw new IndexOutOfBoundsException(String.format("offset=%,d, length=%,d", off, len));
      }
   }

   public int write(InputStream in) throws IOException {
      return this.writeImpl(in);
   }

   public void write(int b) {
      this.writeImpl(b);
   }

   public void writeTo(OutputStream out) throws IOException {
      this.writeToImpl(out);
   }

   public static class Builder extends AbstractStreamBuilder {
      public UnsynchronizedByteArrayOutputStream get() {
         return new UnsynchronizedByteArrayOutputStream(this.getBufferSize());
      }
   }
}
