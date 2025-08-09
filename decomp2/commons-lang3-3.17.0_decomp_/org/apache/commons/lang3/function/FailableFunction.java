package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableFunction {
   FailableFunction NOP = (t) -> null;

   static FailableFunction function(FailableFunction function) {
      return function;
   }

   static FailableFunction identity() {
      return (t) -> t;
   }

   static FailableFunction nop() {
      return NOP;
   }

   default FailableFunction andThen(FailableFunction after) {
      Objects.requireNonNull(after);
      return (t) -> after.apply(this.apply(t));
   }

   Object apply(Object var1) throws Throwable;

   default FailableFunction compose(FailableFunction before) {
      Objects.requireNonNull(before);
      return (v) -> this.apply(before.apply(v));
   }
}
