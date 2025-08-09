package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableLongSupplier {
   long getAsLong() throws Throwable;
}
