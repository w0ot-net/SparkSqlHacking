package org.apache.commons.io.function;

import java.util.Iterator;
import java.util.Objects;

final class UncheckedIOIterator implements Iterator {
   private final IOIterator delegate;

   UncheckedIOIterator(IOIterator delegate) {
      this.delegate = (IOIterator)Objects.requireNonNull(delegate, "delegate");
   }

   public boolean hasNext() {
      IOIterator var10000 = this.delegate;
      Objects.requireNonNull(var10000);
      return (Boolean)Uncheck.get(var10000::hasNext);
   }

   public Object next() {
      IOIterator var10000 = this.delegate;
      Objects.requireNonNull(var10000);
      return Uncheck.get(var10000::next);
   }

   public void remove() {
      IOIterator var10000 = this.delegate;
      Objects.requireNonNull(var10000);
      Uncheck.run(var10000::remove);
   }
}
