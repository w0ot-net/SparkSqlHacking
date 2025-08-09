package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.function.BiConsumer;

@FunctionalInterface
public interface IOBiConsumer {
   static IOBiConsumer noop() {
      return Constants.IO_BI_CONSUMER;
   }

   void accept(Object var1, Object var2) throws IOException;

   default IOBiConsumer andThen(IOBiConsumer after) {
      Objects.requireNonNull(after);
      return (t, u) -> {
         this.accept(t, u);
         after.accept(t, u);
      };
   }

   default BiConsumer asBiConsumer() {
      return (t, u) -> Uncheck.accept(this, t, u);
   }
}
