package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Writer;
import java.util.function.Supplier;
import org.apache.commons.io.function.Erase;

public class BrokenWriter extends Writer {
   public static final BrokenWriter INSTANCE = new BrokenWriter();
   private final Supplier exceptionSupplier;

   public BrokenWriter() {
      this((Supplier)(() -> new IOException("Broken writer")));
   }

   /** @deprecated */
   @Deprecated
   public BrokenWriter(IOException exception) {
      this((Supplier)(() -> exception));
   }

   public BrokenWriter(Supplier exceptionSupplier) {
      this.exceptionSupplier = exceptionSupplier;
   }

   public BrokenWriter(Throwable exception) {
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

   public void write(char[] cbuf, int off, int len) throws IOException {
      throw this.rethrow();
   }
}
