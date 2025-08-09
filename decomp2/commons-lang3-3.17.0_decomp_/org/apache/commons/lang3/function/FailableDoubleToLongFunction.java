package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableDoubleToLongFunction {
   FailableDoubleToLongFunction NOP = (t) -> 0;

   static FailableDoubleToLongFunction nop() {
      return NOP;
   }

   int applyAsLong(double var1) throws Throwable;
}
