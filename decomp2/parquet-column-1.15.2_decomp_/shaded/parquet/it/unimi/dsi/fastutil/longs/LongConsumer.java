package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.util.Objects;
import java.util.function.Consumer;

@FunctionalInterface
public interface LongConsumer extends Consumer, java.util.function.LongConsumer {
   /** @deprecated */
   @Deprecated
   default void accept(Long t) {
      this.accept(t);
   }

   default LongConsumer andThen(java.util.function.LongConsumer after) {
      Objects.requireNonNull(after);
      return (t) -> {
         this.accept(t);
         after.accept(t);
      };
   }

   default LongConsumer andThen(LongConsumer after) {
      return this.andThen((java.util.function.LongConsumer)after);
   }

   /** @deprecated */
   @Deprecated
   default Consumer andThen(Consumer after) {
      return super.andThen(after);
   }
}
