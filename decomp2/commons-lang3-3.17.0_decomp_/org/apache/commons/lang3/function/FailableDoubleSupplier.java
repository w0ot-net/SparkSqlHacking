package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableDoubleSupplier {
   double getAsDouble() throws Throwable;
}
