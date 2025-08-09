package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableToDoubleBiFunction {
   FailableToDoubleBiFunction NOP = (t, u) -> (double)0.0F;

   static FailableToDoubleBiFunction nop() {
      return NOP;
   }

   double applyAsDouble(Object var1, Object var2) throws Throwable;
}
