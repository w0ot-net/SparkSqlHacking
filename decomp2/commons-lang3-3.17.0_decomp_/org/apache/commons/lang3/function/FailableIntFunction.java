package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableIntFunction {
   FailableIntFunction NOP = (t) -> null;

   static FailableIntFunction nop() {
      return NOP;
   }

   Object apply(int var1) throws Throwable;
}
