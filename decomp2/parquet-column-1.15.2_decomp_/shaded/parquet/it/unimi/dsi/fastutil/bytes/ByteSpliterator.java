package shaded.parquet.it.unimi.dsi.fastutil.bytes;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public interface ByteSpliterator extends Spliterator.OfPrimitive {
   /** @deprecated */
   @Deprecated
   default boolean tryAdvance(Consumer action) {
      ByteConsumer var10001;
      if (action instanceof ByteConsumer) {
         var10001 = (ByteConsumer)action;
      } else {
         Objects.requireNonNull(action);
         var10001 = action::accept;
      }

      return this.tryAdvance(var10001);
   }

   /** @deprecated */
   @Deprecated
   default void forEachRemaining(Consumer action) {
      ByteConsumer var10001;
      if (action instanceof ByteConsumer) {
         var10001 = (ByteConsumer)action;
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

         while(i-- != 0L && this.tryAdvance((ByteConsumer)(unused) -> {
         })) {
         }

         return n - i - 1L;
      }
   }

   ByteSpliterator trySplit();

   default ByteComparator getComparator() {
      throw new IllegalStateException();
   }
}
