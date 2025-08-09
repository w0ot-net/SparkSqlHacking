package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.util.Objects;
import java.util.function.Consumer;

public interface LongIterable extends Iterable {
   LongIterator iterator();

   default LongIterator longIterator() {
      return this.iterator();
   }

   default LongSpliterator spliterator() {
      return LongSpliterators.asSpliteratorUnknownSize(this.iterator(), 0);
   }

   default LongSpliterator longSpliterator() {
      return this.spliterator();
   }

   default void forEach(java.util.function.LongConsumer action) {
      Objects.requireNonNull(action);
      this.iterator().forEachRemaining((java.util.function.LongConsumer)action);
   }

   default void forEach(LongConsumer action) {
      this.forEach((java.util.function.LongConsumer)action);
   }

   /** @deprecated */
   @Deprecated
   default void forEach(Consumer action) {
      Objects.requireNonNull(action);
      java.util.function.LongConsumer var10001;
      if (action instanceof java.util.function.LongConsumer) {
         var10001 = (java.util.function.LongConsumer)action;
      } else {
         Objects.requireNonNull(action);
         var10001 = action::accept;
      }

      this.forEach(var10001);
   }
}
