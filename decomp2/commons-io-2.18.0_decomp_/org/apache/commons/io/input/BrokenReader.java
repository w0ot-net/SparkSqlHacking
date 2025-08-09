package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Supplier;
import org.apache.commons.io.function.Erase;

public class BrokenReader extends Reader {
   public static final BrokenReader INSTANCE = new BrokenReader();
   private final Supplier exceptionSupplier;

   public BrokenReader() {
      this((Supplier)(() -> new IOException("Broken reader")));
   }

   /** @deprecated */
   @Deprecated
   public BrokenReader(IOException exception) {
      this((Supplier)(() -> exception));
   }

   public BrokenReader(Supplier exceptionSupplier) {
      this.exceptionSupplier = exceptionSupplier;
   }

   public BrokenReader(Throwable exception) {
      this((Supplier)(() -> exception));
   }

   public void close() throws IOException {
      throw this.rethrow();
   }

   public void mark(int readAheadLimit) throws IOException {
      throw this.rethrow();
   }

   public int read(char[] cbuf, int off, int len) throws IOException {
      throw this.rethrow();
   }

   public boolean ready() throws IOException {
      throw this.rethrow();
   }

   public void reset() throws IOException {
      throw this.rethrow();
   }

   private RuntimeException rethrow() {
      return Erase.rethrow((Throwable)this.exceptionSupplier.get());
   }

   public long skip(long n) throws IOException {
      throw this.rethrow();
   }
}
