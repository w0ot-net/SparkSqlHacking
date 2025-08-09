package shaded.parquet.it.unimi.dsi.fastutil.ints;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public interface IntSpliterator extends Spliterator.OfInt {
   /** @deprecated */
   @Deprecated
   default boolean tryAdvance(Consumer action) {
      java.util.function.IntConsumer var10001;
      if (action instanceof java.util.function.IntConsumer) {
         var10001 = (java.util.function.IntConsumer)action;
      } else {
         Objects.requireNonNull(action);
         var10001 = action::accept;
      }

      return this.tryAdvance((java.util.function.IntConsumer)var10001);
   }

   default boolean tryAdvance(IntConsumer action) {
      return this.tryAdvance((java.util.function.IntConsumer)action);
   }

   /** @deprecated */
   @Deprecated
   default void forEachRemaining(Consumer action) {
      java.util.function.IntConsumer var10001;
      if (action instanceof java.util.function.IntConsumer) {
         var10001 = (java.util.function.IntConsumer)action;
      } else {
         Objects.requireNonNull(action);
         var10001 = action::accept;
      }

      this.forEachRemaining((java.util.function.IntConsumer)var10001);
   }

   default void forEachRemaining(IntConsumer action) {
      this.forEachRemaining((java.util.function.IntConsumer)action);
   }

   default long skip(long n) {
      if (n < 0L) {
         throw new IllegalArgumentException("Argument must be nonnegative: " + n);
      } else {
         long i = n;

         while(i-- != 0L && this.tryAdvance((IntConsumer)((unused) -> {
         }))) {
         }

         return n - i - 1L;
      }
   }

   IntSpliterator trySplit();

   default IntComparator getComparator() {
      throw new IllegalStateException();
   }
}
