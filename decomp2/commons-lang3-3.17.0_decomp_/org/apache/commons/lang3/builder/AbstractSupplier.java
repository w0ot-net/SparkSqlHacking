package org.apache.commons.lang3.builder;

import org.apache.commons.lang3.function.FailableSupplier;

public abstract class AbstractSupplier implements FailableSupplier {
   protected AbstractSupplier asThis() {
      return this;
   }
}
