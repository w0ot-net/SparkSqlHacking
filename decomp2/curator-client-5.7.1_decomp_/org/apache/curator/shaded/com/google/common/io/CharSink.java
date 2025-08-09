package org.apache.curator.shaded.com.google.common.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.stream.Stream;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public abstract class CharSink {
   protected CharSink() {
   }

   public abstract Writer openStream() throws IOException;

   public Writer openBufferedStream() throws IOException {
      Writer writer = this.openStream();
      return writer instanceof BufferedWriter ? (BufferedWriter)writer : new BufferedWriter(writer);
   }

   public void write(CharSequence charSequence) throws IOException {
      Preconditions.checkNotNull(charSequence);
      Closer closer = Closer.create();

      try {
         Writer out = (Writer)closer.register(this.openStream());
         out.append(charSequence);
         out.flush();
      } catch (Throwable e) {
         throw closer.rethrow(e);
      } finally {
         closer.close();
      }

   }

   public void writeLines(Iterable lines) throws IOException {
      this.writeLines(lines, System.getProperty("line.separator"));
   }

   public void writeLines(Iterable lines, String lineSeparator) throws IOException {
      this.writeLines(lines.iterator(), lineSeparator);
   }

   public void writeLines(Stream lines) throws IOException {
      this.writeLines(lines, System.getProperty("line.separator"));
   }

   public void writeLines(Stream lines, String lineSeparator) throws IOException {
      this.writeLines(lines.iterator(), lineSeparator);
   }

   private void writeLines(Iterator lines, String lineSeparator) throws IOException {
      Preconditions.checkNotNull(lineSeparator);
      Writer out = this.openBufferedStream();

      try {
         while(lines.hasNext()) {
            out.append((CharSequence)lines.next()).append(lineSeparator);
         }
      } catch (Throwable var7) {
         if (out != null) {
            try {
               out.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }
         }

         throw var7;
      }

      if (out != null) {
         out.close();
      }

   }

   @CanIgnoreReturnValue
   public long writeFrom(Readable readable) throws IOException {
      Preconditions.checkNotNull(readable);
      Closer closer = Closer.create();

      long var6;
      try {
         Writer out = (Writer)closer.register(this.openStream());
         long written = CharStreams.copy(readable, out);
         out.flush();
         var6 = written;
      } catch (Throwable e) {
         throw closer.rethrow(e);
      } finally {
         closer.close();
      }

      return var6;
   }
}
