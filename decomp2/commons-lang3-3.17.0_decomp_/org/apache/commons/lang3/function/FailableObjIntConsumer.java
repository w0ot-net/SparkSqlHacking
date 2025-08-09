package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableObjIntConsumer {
   FailableObjIntConsumer NOP = (t, u) -> {
   };

   static FailableObjIntConsumer nop() {
      return NOP;
   }

   void accept(Object var1, int var2) throws Throwable;
}
