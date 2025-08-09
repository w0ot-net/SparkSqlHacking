package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableIntToDoubleFunction {
   FailableIntToDoubleFunction NOP = (t) -> (double)0.0F;

   static FailableIntToDoubleFunction nop() {
      return NOP;
   }

   double applyAsDouble(int var1) throws Throwable;
}
