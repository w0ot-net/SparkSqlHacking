package org.apache.curator.shaded.com.google.common.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class CountingOutputStream extends FilterOutputStream {
   private long count;

   public CountingOutputStream(OutputStream out) {
      super((OutputStream)Preconditions.checkNotNull(out));
   }

   public long getCount() {
      return this.count;
   }

   public void write(byte[] b, int off, int len) throws IOException {
      this.out.write(b, off, len);
      this.count += (long)len;
   }

   public void write(int b) throws IOException {
      this.out.write(b);
      ++this.count;
   }

   public void close() throws IOException {
      this.out.close();
   }
}
