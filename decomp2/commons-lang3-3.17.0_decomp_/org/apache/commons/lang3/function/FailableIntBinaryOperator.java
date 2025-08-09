package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableIntBinaryOperator {
   int applyAsInt(int var1, int var2) throws Throwable;
}
