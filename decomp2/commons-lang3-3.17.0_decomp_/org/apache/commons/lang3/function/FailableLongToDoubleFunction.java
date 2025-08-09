package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableLongToDoubleFunction {
   FailableLongToDoubleFunction NOP = (t) -> (double)0.0F;

   static FailableLongToDoubleFunction nop() {
      return NOP;
   }

   double applyAsDouble(long var1) throws Throwable;
}
