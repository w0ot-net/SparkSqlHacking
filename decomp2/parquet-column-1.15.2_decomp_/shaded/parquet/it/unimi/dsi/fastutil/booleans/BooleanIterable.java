package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import java.util.Objects;
import java.util.function.Consumer;

public interface BooleanIterable extends Iterable {
   BooleanIterator iterator();

   default BooleanSpliterator spliterator() {
      return BooleanSpliterators.asSpliteratorUnknownSize(this.iterator(), 0);
   }

   default void forEach(BooleanConsumer action) {
      Objects.requireNonNull(action);
      this.iterator().forEachRemaining(action);
   }

   /** @deprecated */
   @Deprecated
   default void forEach(Consumer action) {
      Objects.requireNonNull(action);
      BooleanConsumer var10001;
      if (action instanceof BooleanConsumer) {
         var10001 = (BooleanConsumer)action;
      } else {
         Objects.requireNonNull(action);
         var10001 = action::accept;
      }

      this.forEach(var10001);
   }
}
