package shaded.parquet.it.unimi.dsi.fastutil.ints;

import java.util.Objects;
import java.util.function.Consumer;

@FunctionalInterface
public interface IntConsumer extends Consumer, java.util.function.IntConsumer {
   /** @deprecated */
   @Deprecated
   default void accept(Integer t) {
      this.accept(t);
   }

   default IntConsumer andThen(java.util.function.IntConsumer after) {
      Objects.requireNonNull(after);
      return (t) -> {
         this.accept(t);
         after.accept(t);
      };
   }

   default IntConsumer andThen(IntConsumer after) {
      return this.andThen((java.util.function.IntConsumer)after);
   }

   /** @deprecated */
   @Deprecated
   default Consumer andThen(Consumer after) {
      return super.andThen(after);
   }
}
