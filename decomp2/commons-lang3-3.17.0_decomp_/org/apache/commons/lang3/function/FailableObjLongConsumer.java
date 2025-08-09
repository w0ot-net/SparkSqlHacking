package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableObjLongConsumer {
   FailableObjLongConsumer NOP = (t, u) -> {
   };

   static FailableObjLongConsumer nop() {
      return NOP;
   }

   void accept(Object var1, long var2) throws Throwable;
}
