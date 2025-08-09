package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.function.BiFunction;

@FunctionalInterface
public interface IOBiFunction {
   default IOBiFunction andThen(IOFunction after) {
      Objects.requireNonNull(after);
      return (t, u) -> after.apply(this.apply(t, u));
   }

   Object apply(Object var1, Object var2) throws IOException;

   default BiFunction asBiFunction() {
      return (t, u) -> Uncheck.apply(this, t, u);
   }
}
