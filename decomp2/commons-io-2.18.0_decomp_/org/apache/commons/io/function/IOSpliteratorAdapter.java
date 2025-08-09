package org.apache.commons.io.function;

import java.util.Objects;
import java.util.Spliterator;

final class IOSpliteratorAdapter implements IOSpliterator {
   private final Spliterator delegate;

   static IOSpliteratorAdapter adapt(Spliterator delegate) {
      return new IOSpliteratorAdapter(delegate);
   }

   IOSpliteratorAdapter(Spliterator delegate) {
      this.delegate = (Spliterator)Objects.requireNonNull(delegate, "delegate");
   }

   public Spliterator unwrap() {
      return this.delegate;
   }
}
