package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableIntSupplier {
   int getAsInt() throws Throwable;
}
