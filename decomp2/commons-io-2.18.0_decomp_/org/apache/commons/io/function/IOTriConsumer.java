package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;

@FunctionalInterface
public interface IOTriConsumer {
   static IOTriConsumer noop() {
      return Constants.IO_TRI_CONSUMER;
   }

   void accept(Object var1, Object var2, Object var3) throws IOException;

   default IOTriConsumer andThen(IOTriConsumer after) {
      Objects.requireNonNull(after);
      return (t, u, v) -> {
         this.accept(t, u, v);
         after.accept(t, u, v);
      };
   }
}
