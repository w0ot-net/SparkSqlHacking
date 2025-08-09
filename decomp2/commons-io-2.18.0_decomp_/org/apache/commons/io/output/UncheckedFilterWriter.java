package org.apache.commons.io.output;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.Uncheck;

public final class UncheckedFilterWriter extends FilterWriter {
   public static Builder builder() {
      return new Builder();
   }

   private UncheckedFilterWriter(Writer writer) {
      super(writer);
   }

   public Writer append(char c) throws UncheckedIOException {
      return (Writer)Uncheck.apply((x$0) -> super.append(x$0), c);
   }

   public Writer append(CharSequence csq) throws UncheckedIOException {
      return (Writer)Uncheck.apply((x$0) -> super.append(x$0), csq);
   }

   public Writer append(CharSequence csq, int start, int end) throws UncheckedIOException {
      return (Writer)Uncheck.apply((x$0, x$1, x$2) -> super.append(x$0, x$1, x$2), csq, start, end);
   }

   public void close() throws UncheckedIOException {
      Uncheck.run(() -> super.close());
   }

   public void flush() throws UncheckedIOException {
      Uncheck.run(() -> super.flush());
   }

   public void write(char[] cbuf) throws UncheckedIOException {
      Uncheck.accept((x$0) -> super.write(x$0), cbuf);
   }

   public void write(char[] cbuf, int off, int len) throws UncheckedIOException {
      Uncheck.accept((x$0, x$1, x$2) -> super.write(x$0, x$1, x$2), cbuf, off, len);
   }

   public void write(int c) throws UncheckedIOException {
      Uncheck.accept((x$0) -> super.write(x$0), c);
   }

   public void write(String str) throws UncheckedIOException {
      Uncheck.accept((x$0) -> super.write(x$0), str);
   }

   public void write(String str, int off, int len) throws UncheckedIOException {
      Uncheck.accept((x$0, x$1, x$2) -> super.write(x$0, x$1, x$2), str, off, len);
   }

   public static class Builder extends AbstractStreamBuilder {
      public UncheckedFilterWriter get() throws IOException {
         return new UncheckedFilterWriter(this.getWriter());
      }
   }
}
