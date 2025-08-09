package org.apache.commons.lang3.function;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface FailableConsumer {
   FailableConsumer NOP;

   static FailableConsumer nop() {
      return NOP;
   }

   void accept(Object var1) throws Throwable;

   default FailableConsumer andThen(FailableConsumer after) {
      Objects.requireNonNull(after);
      return (t) -> {
         this.accept(t);
         after.accept(t);
      };
   }

   static {
      Function var10000 = Function.identity();
      Objects.requireNonNull(var10000);
      NOP = var10000::apply;
   }
}
