package org.apache.log4j.config;

import java.io.IOException;
import java.io.InputStream;

class InputStreamWrapper extends InputStream {
   private final String description;
   private final InputStream input;

   public InputStreamWrapper(final InputStream input, final String description) {
      this.input = input;
      this.description = description;
   }

   public int available() throws IOException {
      return this.input.available();
   }

   public void close() throws IOException {
      this.input.close();
   }

   public boolean equals(final Object obj) {
      return this.input.equals(obj);
   }

   public int hashCode() {
      return this.input.hashCode();
   }

   public synchronized void mark(final int readlimit) {
      this.input.mark(readlimit);
   }

   public boolean markSupported() {
      return this.input.markSupported();
   }

   public int read() throws IOException {
      return this.input.read();
   }

   public int read(final byte[] b) throws IOException {
      return this.input.read(b);
   }

   public int read(final byte[] b, final int off, final int len) throws IOException {
      return this.input.read(b, off, len);
   }

   public synchronized void reset() throws IOException {
      this.input.reset();
   }

   public long skip(final long n) throws IOException {
      return this.input.skip(n);
   }

   public String toString() {
      return this.getClass().getSimpleName() + " [description=" + this.description + ", input=" + this.input + "]";
   }
}
