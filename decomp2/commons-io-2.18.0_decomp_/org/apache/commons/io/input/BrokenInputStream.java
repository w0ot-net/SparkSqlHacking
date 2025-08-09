package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;
import org.apache.commons.io.function.Erase;

public class BrokenInputStream extends InputStream {
   public static final BrokenInputStream INSTANCE = new BrokenInputStream();
   private final Supplier exceptionSupplier;

   public BrokenInputStream() {
      this((Supplier)(() -> new IOException("Broken input stream")));
   }

   /** @deprecated */
   @Deprecated
   public BrokenInputStream(IOException exception) {
      this((Supplier)(() -> exception));
   }

   public BrokenInputStream(Supplier exceptionSupplier) {
      this.exceptionSupplier = exceptionSupplier;
   }

   public BrokenInputStream(Throwable exception) {
      this((Supplier)(() -> exception));
   }

   public int available() throws IOException {
      throw this.rethrow();
   }

   public void close() throws IOException {
      throw this.rethrow();
   }

   Throwable getThrowable() {
      return (Throwable)this.exceptionSupplier.get();
   }

   public int read() throws IOException {
      throw this.rethrow();
   }

   public synchronized void reset() throws IOException {
      throw this.rethrow();
   }

   private RuntimeException rethrow() {
      return Erase.rethrow(this.getThrowable());
   }

   public long skip(long n) throws IOException {
      throw this.rethrow();
   }
}
