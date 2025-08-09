package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableShortSupplier {
   short getAsShort() throws Throwable;
}
