package org.apache.commons.io.build;

import org.apache.commons.io.function.IOSupplier;

public abstract class AbstractSupplier implements IOSupplier {
   protected AbstractSupplier asThis() {
      return this;
   }
}
