package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableLongFunction {
   FailableLongFunction NOP = (t) -> null;

   static FailableLongFunction nop() {
      return NOP;
   }

   Object apply(long var1) throws Throwable;
}
