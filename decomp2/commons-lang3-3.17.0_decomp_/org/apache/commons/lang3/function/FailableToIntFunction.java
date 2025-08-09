package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableToIntFunction {
   FailableToIntFunction NOP = (t) -> 0;

   static FailableToIntFunction nop() {
      return NOP;
   }

   int applyAsInt(Object var1) throws Throwable;
}
