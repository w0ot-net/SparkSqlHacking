package org.glassfish.jersey.innate.io;

import java.io.IOException;
import java.io.InputStream;

public abstract class InputStreamWrapper extends InputStream {
   protected abstract InputStream getWrapped();

   protected InputStream getWrappedIOE() throws IOException {
      return this.getWrapped();
   }

   public int read() throws IOException {
      return this.getWrappedIOE().read();
   }

   public int read(byte[] b) throws IOException {
      return this.getWrappedIOE().read(b);
   }

   public int read(byte[] b, int off, int len) throws IOException {
      return this.getWrappedIOE().read(b, off, len);
   }

   public long skip(long n) throws IOException {
      return this.getWrappedIOE().skip(n);
   }

   public int available() throws IOException {
      return this.getWrappedIOE().available();
   }

   public void close() throws IOException {
      this.getWrappedIOE().close();
   }

   public void mark(int readlimit) {
      this.getWrapped().mark(readlimit);
   }

   public void reset() throws IOException {
      this.getWrappedIOE().reset();
   }

   public boolean markSupported() {
      return this.getWrapped().markSupported();
   }
}
