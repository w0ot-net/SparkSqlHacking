package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public interface BooleanSpliterator extends Spliterator.OfPrimitive {
   /** @deprecated */
   @Deprecated
   default boolean tryAdvance(Consumer action) {
      BooleanConsumer var10001;
      if (action instanceof BooleanConsumer) {
         var10001 = (BooleanConsumer)action;
      } else {
         Objects.requireNonNull(action);
         var10001 = action::accept;
      }

      return this.tryAdvance(var10001);
   }

   /** @deprecated */
   @Deprecated
   default void forEachRemaining(Consumer action) {
      BooleanConsumer var10001;
      if (action instanceof BooleanConsumer) {
         var10001 = (BooleanConsumer)action;
      } else {
         Objects.requireNonNull(action);
         var10001 = action::accept;
      }

      this.forEachRemaining(var10001);
   }

   default long skip(long n) {
      if (n < 0L) {
         throw new IllegalArgumentException("Argument must be nonnegative: " + n);
      } else {
         long i = n;

         while(i-- != 0L && this.tryAdvance((BooleanConsumer)(unused) -> {
         })) {
         }

         return n - i - 1L;
      }
   }

   BooleanSpliterator trySplit();

   default BooleanComparator getComparator() {
      throw new IllegalStateException();
   }
}
