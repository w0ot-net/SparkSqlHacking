package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import java.util.Objects;
import java.util.function.Consumer;

@FunctionalInterface
public interface BooleanConsumer extends Consumer {
   void accept(boolean var1);

   /** @deprecated */
   @Deprecated
   default void accept(Boolean t) {
      this.accept(t);
   }

   default BooleanConsumer andThen(BooleanConsumer after) {
      Objects.requireNonNull(after);
      return (t) -> {
         this.accept(t);
         after.accept(t);
      };
   }

   /** @deprecated */
   @Deprecated
   default Consumer andThen(Consumer after) {
      return super.andThen(after);
   }
}
