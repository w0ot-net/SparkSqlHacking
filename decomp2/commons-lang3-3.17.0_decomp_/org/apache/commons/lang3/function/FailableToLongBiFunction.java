package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableToLongBiFunction {
   FailableToLongBiFunction NOP = (t, u) -> 0L;

   static FailableToLongBiFunction nop() {
      return NOP;
   }

   long applyAsLong(Object var1, Object var2) throws Throwable;
}
