package org.apache.commons.io.function;

import java.util.Comparator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

final class UncheckedIOSpliterator implements Spliterator {
   private final IOSpliterator delegate;

   UncheckedIOSpliterator(IOSpliterator delegate) {
      this.delegate = (IOSpliterator)Objects.requireNonNull(delegate, "delegate");
   }

   public int characteristics() {
      return this.delegate.characteristics();
   }

   public long estimateSize() {
      return this.delegate.estimateSize();
   }

   public void forEachRemaining(Consumer action) {
      IOSpliterator var10000 = this.delegate;
      Objects.requireNonNull(var10000);
      IOConsumer var2 = var10000::forEachRemaining;
      Objects.requireNonNull(action);
      Uncheck.accept(var2, action::accept);
   }

   public Comparator getComparator() {
      return this.delegate.getComparator().asComparator();
   }

   public long getExactSizeIfKnown() {
      return this.delegate.getExactSizeIfKnown();
   }

   public boolean hasCharacteristics(int characteristics) {
      return this.delegate.hasCharacteristics(characteristics);
   }

   public boolean tryAdvance(Consumer action) {
      IOSpliterator var10000 = this.delegate;
      Objects.requireNonNull(var10000);
      IOFunction var2 = var10000::tryAdvance;
      Objects.requireNonNull(action);
      return (Boolean)Uncheck.apply(var2, action::accept);
   }

   public Spliterator trySplit() {
      IOSpliterator var10000 = this.delegate;
      Objects.requireNonNull(var10000);
      return ((IOSpliterator)Uncheck.get(var10000::trySplit)).unwrap();
   }
}
