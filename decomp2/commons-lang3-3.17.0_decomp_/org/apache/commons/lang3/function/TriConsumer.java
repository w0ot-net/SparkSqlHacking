package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface TriConsumer {
   void accept(Object var1, Object var2, Object var3);

   default TriConsumer andThen(TriConsumer after) {
      Objects.requireNonNull(after);
      return (t, u, v) -> {
         this.accept(t, u, v);
         after.accept(t, u, v);
      };
   }
}
