package org.apache.commons.io.output;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.Uncheck;

public final class UncheckedFilterOutputStream extends FilterOutputStream {
   public static Builder builder() {
      return new Builder();
   }

   private UncheckedFilterOutputStream(OutputStream outputStream) {
      super(outputStream);
   }

   public void close() throws UncheckedIOException {
      Uncheck.run(() -> super.close());
   }

   public void flush() throws UncheckedIOException {
      Uncheck.run(() -> super.flush());
   }

   public void write(byte[] b) throws UncheckedIOException {
      Uncheck.accept((x$0) -> super.write(x$0), b);
   }

   public void write(byte[] b, int off, int len) throws UncheckedIOException {
      Uncheck.accept((x$0, x$1, x$2) -> super.write(x$0, x$1, x$2), b, off, len);
   }

   public void write(int b) throws UncheckedIOException {
      Uncheck.accept((x$0) -> super.write(x$0), b);
   }

   public static class Builder extends AbstractStreamBuilder {
      public UncheckedFilterOutputStream get() throws IOException {
         return new UncheckedFilterOutputStream(this.getOutputStream());
      }
   }
}
