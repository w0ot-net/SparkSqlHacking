package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableDoubleBinaryOperator {
   double applyAsDouble(double var1, double var3) throws Throwable;
}
