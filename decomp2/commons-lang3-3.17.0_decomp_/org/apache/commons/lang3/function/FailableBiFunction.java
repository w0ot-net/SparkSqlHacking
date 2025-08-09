package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableBiFunction {
   FailableBiFunction NOP = (t, u) -> null;

   static FailableBiFunction nop() {
      return NOP;
   }

   default FailableBiFunction andThen(FailableFunction after) {
      Objects.requireNonNull(after);
      return (t, u) -> after.apply(this.apply(t, u));
   }

   Object apply(Object var1, Object var2) throws Throwable;
}
