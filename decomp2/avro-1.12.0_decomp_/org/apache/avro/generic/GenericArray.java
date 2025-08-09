package org.apache.avro.generic;

import java.util.List;

public interface GenericArray extends List, GenericContainer {
   Object peek();

   default void reset() {
      this.clear();
   }

   default void prune() {
   }

   void reverse();
}
