package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableToIntBiFunction {
   FailableToIntBiFunction NOP = (t, u) -> 0;

   static FailableToIntBiFunction nop() {
      return NOP;
   }

   int applyAsInt(Object var1, Object var2) throws Throwable;
}
