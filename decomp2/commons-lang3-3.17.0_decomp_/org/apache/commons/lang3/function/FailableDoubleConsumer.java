package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableDoubleConsumer {
   FailableDoubleConsumer NOP = (t) -> {
   };

   static FailableDoubleConsumer nop() {
      return NOP;
   }

   void accept(double var1) throws Throwable;

   default FailableDoubleConsumer andThen(FailableDoubleConsumer after) {
      Objects.requireNonNull(after);
      return (t) -> {
         this.accept(t);
         after.accept(t);
      };
   }
}
