package org.apache.curator.shaded.com.google.common.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
final class MultiInputStream extends InputStream {
   private Iterator it;
   @CheckForNull
   private InputStream in;

   public MultiInputStream(Iterator it) throws IOException {
      this.it = (Iterator)Preconditions.checkNotNull(it);
      this.advance();
   }

   public void close() throws IOException {
      if (this.in != null) {
         try {
            this.in.close();
         } finally {
            this.in = null;
         }
      }

   }

   private void advance() throws IOException {
      this.close();
      if (this.it.hasNext()) {
         this.in = ((ByteSource)this.it.next()).openStream();
      }

   }

   public int available() throws IOException {
      return this.in == null ? 0 : this.in.available();
   }

   public boolean markSupported() {
      return false;
   }

   public int read() throws IOException {
      while(this.in != null) {
         int result = this.in.read();
         if (result != -1) {
            return result;
         }

         this.advance();
      }

      return -1;
   }

   public int read(byte[] b, int off, int len) throws IOException {
      Preconditions.checkNotNull(b);

      while(this.in != null) {
         int result = this.in.read(b, off, len);
         if (result != -1) {
            return result;
         }

         this.advance();
      }

      return -1;
   }

   public long skip(long n) throws IOException {
      if (this.in != null && n > 0L) {
         long result = this.in.skip(n);
         if (result != 0L) {
            return result;
         } else {
            return this.read() == -1 ? 0L : 1L + this.in.skip(n - 1L);
         }
      } else {
         return 0L;
      }
   }
}
