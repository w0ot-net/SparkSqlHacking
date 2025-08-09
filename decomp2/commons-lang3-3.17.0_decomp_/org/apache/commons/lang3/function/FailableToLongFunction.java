package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableToLongFunction {
   FailableToLongFunction NOP = (t) -> 0L;

   static FailableToLongFunction nop() {
      return NOP;
   }

   long applyAsLong(Object var1) throws Throwable;
}
