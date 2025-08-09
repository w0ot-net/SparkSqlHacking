package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableLongConsumer {
   FailableLongConsumer NOP = (t) -> {
   };

   static FailableLongConsumer nop() {
      return NOP;
   }

   void accept(long var1) throws Throwable;

   default FailableLongConsumer andThen(FailableLongConsumer after) {
      Objects.requireNonNull(after);
      return (t) -> {
         this.accept(t);
         after.accept(t);
      };
   }
}
