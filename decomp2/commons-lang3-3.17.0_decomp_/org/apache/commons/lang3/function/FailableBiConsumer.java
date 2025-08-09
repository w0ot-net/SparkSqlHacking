package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableBiConsumer {
   FailableBiConsumer NOP = (t, u) -> {
   };

   static FailableBiConsumer nop() {
      return NOP;
   }

   void accept(Object var1, Object var2) throws Throwable;

   default FailableBiConsumer andThen(FailableBiConsumer after) {
      Objects.requireNonNull(after);
      return (t, u) -> {
         this.accept(t, u);
         after.accept(t, u);
      };
   }
}
