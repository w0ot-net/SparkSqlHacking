package org.apache.commons.io.function;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.function.Supplier;

@FunctionalInterface
public interface IOSupplier {
   default Supplier asSupplier() {
      return this::getUnchecked;
   }

   Object get() throws IOException;

   default Object getUnchecked() throws UncheckedIOException {
      return Uncheck.get(this);
   }
}
