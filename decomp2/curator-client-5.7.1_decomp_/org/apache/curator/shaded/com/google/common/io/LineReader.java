package org.apache.curator.shaded.com.google.common.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class LineReader {
   private final Readable readable;
   @CheckForNull
   private final Reader reader;
   private final CharBuffer cbuf = CharStreams.createBuffer();
   private final char[] buf;
   private final Queue lines;
   private final LineBuffer lineBuf;

   public LineReader(Readable readable) {
      this.buf = this.cbuf.array();
      this.lines = new ArrayDeque();
      this.lineBuf = new LineBuffer() {
         protected void handleLine(String line, String end) {
            LineReader.this.lines.add(line);
         }
      };
      this.readable = (Readable)Preconditions.checkNotNull(readable);
      this.reader = readable instanceof Reader ? (Reader)readable : null;
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public String readLine() throws IOException {
      while(true) {
         if (this.lines.peek() == null) {
            Java8Compatibility.clear(this.cbuf);
            int read = this.reader != null ? this.reader.read(this.buf, 0, this.buf.length) : this.readable.read(this.cbuf);
            if (read != -1) {
               this.lineBuf.add(this.buf, 0, read);
               continue;
            }

            this.lineBuf.finish();
         }

         return (String)this.lines.poll();
      }
   }
}
