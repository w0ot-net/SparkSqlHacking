package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

final class IOIteratorAdapter implements IOIterator {
   private final Iterator delegate;

   static IOIteratorAdapter adapt(Iterator delegate) {
      return new IOIteratorAdapter(delegate);
   }

   IOIteratorAdapter(Iterator delegate) {
      this.delegate = (Iterator)Objects.requireNonNull(delegate, "delegate");
   }

   public boolean hasNext() throws IOException {
      return this.delegate.hasNext();
   }

   public Object next() throws IOException {
      return this.delegate.next();
   }

   public Iterator unwrap() {
      return this.delegate;
   }
}
