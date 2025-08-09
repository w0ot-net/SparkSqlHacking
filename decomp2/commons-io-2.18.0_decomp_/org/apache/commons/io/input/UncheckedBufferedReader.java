package org.apache.commons.io.input;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.CharBuffer;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.Uncheck;

public final class UncheckedBufferedReader extends BufferedReader {
   public static Builder builder() {
      return new Builder();
   }

   private UncheckedBufferedReader(Reader reader, int bufferSize) {
      super(reader, bufferSize);
   }

   public void close() throws UncheckedIOException {
      Uncheck.run(() -> super.close());
   }

   public void mark(int readAheadLimit) throws UncheckedIOException {
      Uncheck.accept((x$0) -> super.mark(x$0), readAheadLimit);
   }

   public int read() throws UncheckedIOException {
      return (Integer)Uncheck.get(() -> super.read());
   }

   public int read(char[] cbuf) throws UncheckedIOException {
      return (Integer)Uncheck.apply((x$0) -> super.read(x$0), cbuf);
   }

   public int read(char[] cbuf, int off, int len) throws UncheckedIOException {
      return (Integer)Uncheck.apply((x$0, x$1, x$2) -> super.read(x$0, x$1, x$2), cbuf, off, len);
   }

   public int read(CharBuffer target) throws UncheckedIOException {
      return (Integer)Uncheck.apply((x$0) -> super.read(x$0), target);
   }

   public String readLine() throws UncheckedIOException {
      return (String)Uncheck.get(() -> super.readLine());
   }

   public boolean ready() throws UncheckedIOException {
      return (Boolean)Uncheck.get(() -> super.ready());
   }

   public void reset() throws UncheckedIOException {
      Uncheck.run(() -> super.reset());
   }

   public long skip(long n) throws UncheckedIOException {
      return (Long)Uncheck.apply((x$0) -> super.skip(x$0), n);
   }

   public static class Builder extends AbstractStreamBuilder {
      public UncheckedBufferedReader get() {
         return (UncheckedBufferedReader)Uncheck.get(() -> new UncheckedBufferedReader(this.getReader(), this.getBufferSize()));
      }
   }
}
