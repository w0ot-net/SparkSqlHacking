package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableDoubleFunction {
   FailableDoubleFunction NOP = (t) -> null;

   static FailableDoubleFunction nop() {
      return NOP;
   }

   Object apply(double var1) throws Throwable;
}
