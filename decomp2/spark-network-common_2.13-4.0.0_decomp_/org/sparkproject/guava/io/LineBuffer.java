package org.sparkproject.guava.io;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
abstract class LineBuffer {
   private StringBuilder line = new StringBuilder();
   private boolean sawReturn;

   protected void add(char[] cbuf, int off, int len) throws IOException {
      int pos = off;
      if (this.sawReturn && len > 0 && this.finishLine(cbuf[off] == '\n')) {
         pos = off + 1;
      }

      int start = pos;

      for(int end = off + len; pos < end; ++pos) {
         switch (cbuf[pos]) {
            case '\n':
               this.line.append(cbuf, start, pos - start);
               this.finishLine(true);
               start = pos + 1;
               break;
            case '\r':
               this.line.append(cbuf, start, pos - start);
               this.sawReturn = true;
               if (pos + 1 < end && this.finishLine(cbuf[pos + 1] == '\n')) {
                  ++pos;
               }

               start = pos + 1;
         }
      }

      this.line.append(cbuf, start, off + len - start);
   }

   @CanIgnoreReturnValue
   private boolean finishLine(boolean sawNewline) throws IOException {
      String separator = this.sawReturn ? (sawNewline ? "\r\n" : "\r") : (sawNewline ? "\n" : "");
      this.handleLine(this.line.toString(), separator);
      this.line = new StringBuilder();
      this.sawReturn = false;
      return sawNewline;
   }

   protected void finish() throws IOException {
      if (this.sawReturn || this.line.length() > 0) {
         this.finishLine(false);
      }

   }

   protected abstract void handleLine(String line, String end) throws IOException;
}
