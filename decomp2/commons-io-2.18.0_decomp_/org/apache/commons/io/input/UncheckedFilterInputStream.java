package org.apache.commons.io.input;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.UncheckedIOException;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.Uncheck;

public final class UncheckedFilterInputStream extends FilterInputStream {
   public static Builder builder() {
      return new Builder();
   }

   private UncheckedFilterInputStream(InputStream inputStream) {
      super(inputStream);
   }

   public int available() throws UncheckedIOException {
      return (Integer)Uncheck.get(() -> super.available());
   }

   public void close() throws UncheckedIOException {
      Uncheck.run(() -> super.close());
   }

   public int read() throws UncheckedIOException {
      return (Integer)Uncheck.get(() -> super.read());
   }

   public int read(byte[] b) throws UncheckedIOException {
      return (Integer)Uncheck.apply((x$0) -> super.read(x$0), b);
   }

   public int read(byte[] b, int off, int len) throws UncheckedIOException {
      return (Integer)Uncheck.apply((x$0, x$1, x$2) -> super.read(x$0, x$1, x$2), b, off, len);
   }

   public synchronized void reset() throws UncheckedIOException {
      Uncheck.run(() -> super.reset());
   }

   public long skip(long n) throws UncheckedIOException {
      return (Long)Uncheck.apply((x$0) -> super.skip(x$0), n);
   }

   public static class Builder extends AbstractStreamBuilder {
      public UncheckedFilterInputStream get() {
         return (UncheckedFilterInputStream)Uncheck.get(() -> new UncheckedFilterInputStream(this.getInputStream()));
      }
   }
}
