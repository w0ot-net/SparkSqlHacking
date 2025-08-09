package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.build.AbstractStreamBuilder;

public class UnsynchronizedFilterInputStream extends InputStream {
   protected volatile InputStream inputStream;

   public static Builder builder() {
      return new Builder();
   }

   UnsynchronizedFilterInputStream(InputStream inputStream) {
      this.inputStream = inputStream;
   }

   public int available() throws IOException {
      return this.inputStream.available();
   }

   public void close() throws IOException {
      this.inputStream.close();
   }

   public void mark(int readLimit) {
      this.inputStream.mark(readLimit);
   }

   public boolean markSupported() {
      return this.inputStream.markSupported();
   }

   public int read() throws IOException {
      return this.inputStream.read();
   }

   public int read(byte[] buffer) throws IOException {
      return this.read(buffer, 0, buffer.length);
   }

   public int read(byte[] buffer, int offset, int count) throws IOException {
      return this.inputStream.read(buffer, offset, count);
   }

   public void reset() throws IOException {
      this.inputStream.reset();
   }

   public long skip(long count) throws IOException {
      return this.inputStream.skip(count);
   }

   public static class Builder extends AbstractStreamBuilder {
      public UnsynchronizedFilterInputStream get() throws IOException {
         return new UnsynchronizedFilterInputStream(this.getInputStream());
      }
   }
}
