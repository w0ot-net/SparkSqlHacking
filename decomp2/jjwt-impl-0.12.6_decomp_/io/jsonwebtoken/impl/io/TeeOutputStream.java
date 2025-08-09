package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.lang.Assert;
import java.io.IOException;
import java.io.OutputStream;

public class TeeOutputStream extends FilteredOutputStream {
   private final OutputStream other;

   public TeeOutputStream(OutputStream one, OutputStream two) {
      super(one);
      this.other = (OutputStream)Assert.notNull(two, "Second OutputStream cannot be null.");
   }

   public void close() throws IOException {
      try {
         super.close();
      } finally {
         this.other.close();
      }

   }

   public void flush() throws IOException {
      super.flush();
      this.other.flush();
   }

   public void write(byte[] bts) throws IOException {
      super.write(bts);
      this.other.write(bts);
   }

   public void write(byte[] bts, int st, int end) throws IOException {
      super.write(bts, st, end);
      this.other.write(bts, st, end);
   }

   public void write(int idx) throws IOException {
      super.write(idx);
      this.other.write(idx);
   }
}
