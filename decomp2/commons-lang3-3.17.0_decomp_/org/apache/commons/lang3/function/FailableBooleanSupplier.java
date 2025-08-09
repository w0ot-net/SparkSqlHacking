package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableBooleanSupplier {
   boolean getAsBoolean() throws Throwable;
}
