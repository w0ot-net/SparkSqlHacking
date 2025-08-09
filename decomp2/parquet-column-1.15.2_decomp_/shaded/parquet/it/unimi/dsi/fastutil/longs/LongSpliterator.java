package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public interface LongSpliterator extends Spliterator.OfLong {
   /** @deprecated */
   @Deprecated
   default boolean tryAdvance(Consumer action) {
      java.util.function.LongConsumer var10001;
      if (action instanceof java.util.function.LongConsumer) {
         var10001 = (java.util.function.LongConsumer)action;
      } else {
         Objects.requireNonNull(action);
         var10001 = action::accept;
      }

      return this.tryAdvance((java.util.function.LongConsumer)var10001);
   }

   default boolean tryAdvance(LongConsumer action) {
      return this.tryAdvance((java.util.function.LongConsumer)action);
   }

   /** @deprecated */
   @Deprecated
   default void forEachRemaining(Consumer action) {
      java.util.function.LongConsumer var10001;
      if (action instanceof java.util.function.LongConsumer) {
         var10001 = (java.util.function.LongConsumer)action;
      } else {
         Objects.requireNonNull(action);
         var10001 = action::accept;
      }

      this.forEachRemaining((java.util.function.LongConsumer)var10001);
   }

   default void forEachRemaining(LongConsumer action) {
      this.forEachRemaining((java.util.function.LongConsumer)action);
   }

   default long skip(long n) {
      if (n < 0L) {
         throw new IllegalArgumentException("Argument must be nonnegative: " + n);
      } else {
         long i = n;

         while(i-- != 0L && this.tryAdvance((LongConsumer)((unused) -> {
         }))) {
         }

         return n - i - 1L;
      }
   }

   LongSpliterator trySplit();

   default LongComparator getComparator() {
      throw new IllegalStateException();
   }
}
