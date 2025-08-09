package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.impl.lang.Bytes;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class FilteredInputStream extends FilterInputStream {
   public FilteredInputStream(InputStream in) {
      super(in);
   }

   protected void afterRead(int n) throws IOException {
   }

   public int available() throws IOException {
      try {
         return super.available();
      } catch (Throwable t) {
         this.onThrowable(t);
         return 0;
      }
   }

   protected void beforeRead(int n) throws IOException {
   }

   public void close() throws IOException {
      try {
         super.close();
      } catch (Throwable t) {
         this.onThrowable(t);
      }

   }

   protected void onThrowable(Throwable t) throws IOException {
      if (t instanceof IOException) {
         throw (IOException)t;
      } else {
         throw new IOException("IO Exception: " + t.getMessage(), t);
      }
   }

   public synchronized void mark(int readlimit) {
      this.in.mark(readlimit);
   }

   public boolean markSupported() {
      return this.in.markSupported();
   }

   public int read() throws IOException {
      try {
         this.beforeRead(1);
         int b = this.in.read();
         this.afterRead(b != -1 ? 1 : -1);
         return b;
      } catch (Throwable t) {
         this.onThrowable(t);
         return -1;
      }
   }

   public int read(byte[] bts) throws IOException {
      try {
         this.beforeRead(Bytes.length(bts));
         int n = this.in.read(bts);
         this.afterRead(n);
         return n;
      } catch (Throwable t) {
         this.onThrowable(t);
         return -1;
      }
   }

   public int read(byte[] bts, int off, int len) throws IOException {
      try {
         this.beforeRead(len);
         int n = this.in.read(bts, off, len);
         this.afterRead(n);
         return n;
      } catch (Throwable t) {
         this.onThrowable(t);
         return -1;
      }
   }

   public synchronized void reset() throws IOException {
      try {
         this.in.reset();
      } catch (Throwable t) {
         this.onThrowable(t);
      }

   }

   public long skip(long ln) throws IOException {
      try {
         return this.in.skip(ln);
      } catch (Throwable t) {
         this.onThrowable(t);
         return 0L;
      }
   }
}
