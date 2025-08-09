package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Supplier;
import org.apache.commons.io.function.Erase;

public class BrokenOutputStream extends OutputStream {
   public static final BrokenOutputStream INSTANCE = new BrokenOutputStream();
   private final Supplier exceptionSupplier;

   public BrokenOutputStream() {
      this((Supplier)(() -> new IOException("Broken output stream")));
   }

   /** @deprecated */
   @Deprecated
   public BrokenOutputStream(IOException exception) {
      this((Supplier)(() -> exception));
   }

   public BrokenOutputStream(Supplier exceptionSupplier) {
      this.exceptionSupplier = exceptionSupplier;
   }

   public BrokenOutputStream(Throwable exception) {
      this((Supplier)(() -> exception));
   }

   public void close() throws IOException {
      throw this.rethrow();
   }

   public void flush() throws IOException {
      throw this.rethrow();
   }

   private RuntimeException rethrow() {
      return Erase.rethrow((Throwable)this.exceptionSupplier.get());
   }

   public void write(int b) throws IOException {
      throw this.rethrow();
   }
}
