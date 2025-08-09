package org.sparkproject.jetty.http;

import java.util.Collections;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

class EmptyHttpFields extends HttpFields.Immutable {
   public EmptyHttpFields() {
      super(new HttpField[0]);
   }

   public Iterator iterator() {
      return Collections.emptyIterator();
   }

   public void forEach(Consumer action) {
   }

   public Stream stream() {
      return Stream.empty();
   }
}
