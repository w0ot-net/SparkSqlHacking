package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableToDoubleFunction {
   FailableToDoubleFunction NOP = (t) -> (double)0.0F;

   static FailableToDoubleFunction nop() {
      return NOP;
   }

   double applyAsDouble(Object var1) throws Throwable;
}
