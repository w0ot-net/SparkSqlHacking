package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;

@FunctionalInterface
public interface IOQuadFunction {
   default IOQuadFunction andThen(IOFunction after) {
      Objects.requireNonNull(after);
      return (t, u, v, w) -> after.apply(this.apply(t, u, v, w));
   }

   Object apply(Object var1, Object var2, Object var3, Object var4) throws IOException;
}
