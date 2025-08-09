package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableDoubleToIntFunction {
   FailableDoubleToIntFunction NOP = (t) -> 0;

   static FailableDoubleToIntFunction nop() {
      return NOP;
   }

   int applyAsInt(double var1) throws Throwable;
}
