package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleIterator;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleIterators;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleSpliterator;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleSpliterators;

public interface FloatIterable extends Iterable {
   FloatIterator iterator();

   default DoubleIterator doubleIterator() {
      return DoubleIterators.wrap(this.iterator());
   }

   default FloatSpliterator spliterator() {
      return FloatSpliterators.asSpliteratorUnknownSize(this.iterator(), 0);
   }

   default DoubleSpliterator doubleSpliterator() {
      return DoubleSpliterators.wrap(this.spliterator());
   }

   default void forEach(FloatConsumer action) {
      Objects.requireNonNull(action);
      this.iterator().forEachRemaining(action);
   }

   default void forEach(DoubleConsumer action) {
      Objects.requireNonNull(action);
      FloatConsumer var10001;
      if (action instanceof FloatConsumer) {
         var10001 = (FloatConsumer)action;
      } else {
         Objects.requireNonNull(action);
         var10001 = action::accept;
      }

      this.forEach(var10001);
   }

   /** @deprecated */
   @Deprecated
   default void forEach(Consumer action) {
      Objects.requireNonNull(action);
      FloatConsumer var10001;
      if (action instanceof FloatConsumer) {
         var10001 = (FloatConsumer)action;
      } else {
         Objects.requireNonNull(action);
         var10001 = action::accept;
      }

      this.forEach(var10001);
   }
}
