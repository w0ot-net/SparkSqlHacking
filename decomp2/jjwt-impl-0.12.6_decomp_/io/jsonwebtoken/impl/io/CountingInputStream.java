package io.jsonwebtoken.impl.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;

public class CountingInputStream extends FilterInputStream {
   private final AtomicLong count = new AtomicLong(0L);

   public CountingInputStream(InputStream in) {
      super(in);
   }

   public long getCount() {
      return this.count.get();
   }

   private void add(long n) {
      if (n > 0L) {
         this.count.addAndGet(n);
      }

   }

   public int read() throws IOException {
      int next = super.read();
      this.add(next == -1 ? -1L : 1L);
      return next;
   }

   public int read(byte[] b) throws IOException {
      int n = super.read(b);
      this.add((long)n);
      return n;
   }

   public int read(byte[] b, int off, int len) throws IOException {
      int n = super.read(b, off, len);
      this.add((long)n);
      return n;
   }

   public long skip(long n) throws IOException {
      long skipped = super.skip(n);
      this.add(skipped);
      return skipped;
   }
}
