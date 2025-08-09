package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.function.Consumer;

public interface BooleanIterator extends PrimitiveIterator {
   boolean nextBoolean();

   /** @deprecated */
   @Deprecated
   default Boolean next() {
      return this.nextBoolean();
   }

   default void forEachRemaining(BooleanConsumer action) {
      Objects.requireNonNull(action);

      while(this.hasNext()) {
         action.accept(this.nextBoolean());
      }

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

   default int skip(int n) {
      if (n < 0) {
         throw new IllegalArgumentException("Argument must be nonnegative: " + n);
      } else {
         int i = n;

         while(i-- != 0 && this.hasNext()) {
            this.nextBoolean();
         }

         return n - i - 1;
      }
   }
}
