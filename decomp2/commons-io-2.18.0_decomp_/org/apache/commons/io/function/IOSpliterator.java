package org.apache.commons.io.function;

import java.util.Objects;
import java.util.Spliterator;

public interface IOSpliterator {
   static IOSpliterator adapt(Spliterator iterator) {
      return IOSpliteratorAdapter.adapt(iterator);
   }

   default Spliterator asSpliterator() {
      return new UncheckedIOSpliterator(this);
   }

   default int characteristics() {
      return this.unwrap().characteristics();
   }

   default long estimateSize() {
      return this.unwrap().estimateSize();
   }

   default void forEachRemaining(IOConsumer action) {
      while(this.tryAdvance(action)) {
      }

   }

   default IOComparator getComparator() {
      return (IOComparator)this.unwrap().getComparator();
   }

   default long getExactSizeIfKnown() {
      return this.unwrap().getExactSizeIfKnown();
   }

   default boolean hasCharacteristics(int characteristics) {
      return this.unwrap().hasCharacteristics(characteristics);
   }

   default boolean tryAdvance(IOConsumer action) {
      return this.unwrap().tryAdvance(((IOConsumer)Objects.requireNonNull(action, "action")).asConsumer());
   }

   default IOSpliterator trySplit() {
      return adapt(this.unwrap().trySplit());
   }

   Spliterator unwrap();
}
