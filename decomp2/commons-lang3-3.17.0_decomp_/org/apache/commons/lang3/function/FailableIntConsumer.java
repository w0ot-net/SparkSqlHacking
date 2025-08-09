package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableIntConsumer {
   FailableIntConsumer NOP = (t) -> {
   };

   static FailableIntConsumer nop() {
      return NOP;
   }

   void accept(int var1) throws Throwable;

   default FailableIntConsumer andThen(FailableIntConsumer after) {
      Objects.requireNonNull(after);
      return (t) -> {
         this.accept(t);
         after.accept(t);
      };
   }
}
