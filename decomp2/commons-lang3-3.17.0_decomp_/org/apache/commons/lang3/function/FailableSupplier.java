package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableSupplier {
   FailableSupplier NUL = () -> null;

   static FailableSupplier nul() {
      return NUL;
   }

   Object get() throws Throwable;
}
