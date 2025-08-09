package org.apache.commons.lang3.function;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface TriFunction {
   default TriFunction andThen(Function after) {
      Objects.requireNonNull(after);
      return (t, u, v) -> after.apply(this.apply(t, u, v));
   }

   Object apply(Object var1, Object var2, Object var3);
}
