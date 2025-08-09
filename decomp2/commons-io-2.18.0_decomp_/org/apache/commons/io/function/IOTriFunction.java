package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;

@FunctionalInterface
public interface IOTriFunction {
   default IOTriFunction andThen(IOFunction after) {
      Objects.requireNonNull(after);
      return (t, u, v) -> after.apply(this.apply(t, u, v));
   }

   Object apply(Object var1, Object var2, Object var3) throws IOException;
}
