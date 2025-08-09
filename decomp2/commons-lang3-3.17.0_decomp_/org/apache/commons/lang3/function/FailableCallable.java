package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableCallable {
   Object call() throws Throwable;
}
