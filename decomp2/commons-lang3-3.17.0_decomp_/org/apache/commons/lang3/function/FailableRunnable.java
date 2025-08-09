package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableRunnable {
   void run() throws Throwable;
}
