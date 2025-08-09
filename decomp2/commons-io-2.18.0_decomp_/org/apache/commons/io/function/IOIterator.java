package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

public interface IOIterator {
   static IOIterator adapt(Iterable iterable) {
      return IOIteratorAdapter.adapt(iterable.iterator());
   }

   static IOIterator adapt(Iterator iterator) {
      return IOIteratorAdapter.adapt(iterator);
   }

   default Iterator asIterator() {
      return new UncheckedIOIterator(this);
   }

   default void forEachRemaining(IOConsumer action) throws IOException {
      Objects.requireNonNull(action);

      while(this.hasNext()) {
         action.accept(this.next());
      }

   }

   boolean hasNext() throws IOException;

   Object next() throws IOException;

   default void remove() throws IOException {
      this.unwrap().remove();
   }

   Iterator unwrap();
}
