package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableObjDoubleConsumer {
   FailableObjDoubleConsumer NOP = (t, u) -> {
   };

   static FailableObjDoubleConsumer nop() {
      return NOP;
   }

   void accept(Object var1, double var2) throws Throwable;
}
