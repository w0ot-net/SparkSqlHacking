package org.apache.commons.io;

import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

public final class StreamIterator implements Iterator, AutoCloseable {
   private final Iterator iterator;
   private final Stream stream;
   private boolean closed;

   public static StreamIterator iterator(Stream stream) {
      return new StreamIterator(stream);
   }

   private StreamIterator(Stream stream) {
      this.stream = (Stream)Objects.requireNonNull(stream, "stream");
      this.iterator = stream.iterator();
   }

   public void close() {
      this.closed = true;
      this.stream.close();
   }

   public boolean hasNext() {
      if (this.closed) {
         return false;
      } else {
         boolean hasNext = this.iterator.hasNext();
         if (!hasNext) {
            this.close();
         }

         return hasNext;
      }
   }

   public Object next() {
      E next = (E)this.iterator.next();
      if (next == null) {
         this.close();
      }

      return next;
   }
}
