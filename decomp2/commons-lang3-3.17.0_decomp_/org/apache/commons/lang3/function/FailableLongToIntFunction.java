package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableLongToIntFunction {
   FailableLongToIntFunction NOP = (t) -> 0;

   static FailableLongToIntFunction nop() {
      return NOP;
   }

   int applyAsInt(long var1) throws Throwable;
}
