package org.apache.curator.shaded.com.google.common.io;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
class MultiReader extends Reader {
   private final Iterator it;
   @CheckForNull
   private Reader current;

   MultiReader(Iterator readers) throws IOException {
      this.it = readers;
      this.advance();
   }

   private void advance() throws IOException {
      this.close();
      if (this.it.hasNext()) {
         this.current = ((CharSource)this.it.next()).openStream();
      }

   }

   public int read(char[] cbuf, int off, int len) throws IOException {
      Preconditions.checkNotNull(cbuf);
      if (this.current == null) {
         return -1;
      } else {
         int result = this.current.read(cbuf, off, len);
         if (result == -1) {
            this.advance();
            return this.read(cbuf, off, len);
         } else {
            return result;
         }
      }
   }

   public long skip(long n) throws IOException {
      Preconditions.checkArgument(n >= 0L, "n is negative");
      if (n > 0L) {
         while(this.current != null) {
            long result = this.current.skip(n);
            if (result > 0L) {
               return result;
            }

            this.advance();
         }
      }

      return 0L;
   }

   public boolean ready() throws IOException {
      return this.current != null && this.current.ready();
   }

   public void close() throws IOException {
      if (this.current != null) {
         try {
            this.current.close();
         } finally {
            this.current = null;
         }
      }

   }
}
