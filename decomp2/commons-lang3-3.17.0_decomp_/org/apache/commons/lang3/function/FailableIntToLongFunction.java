package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableIntToLongFunction {
   FailableIntToLongFunction NOP = (t) -> 0L;

   static FailableIntToLongFunction nop() {
      return NOP;
   }

   long applyAsLong(int var1) throws Throwable;
}
