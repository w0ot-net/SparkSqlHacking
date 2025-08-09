package com.fasterxml.jackson.databind.util;

import java.util.function.BiConsumer;

public interface LookupCache {
   default void contents(BiConsumer consumer) {
      throw new UnsupportedOperationException();
   }

   default LookupCache emptyCopy() {
      throw new UnsupportedOperationException("LookupCache implementation " + this.getClass().getName() + " does not implement `emptyCopy()`");
   }

   int size();

   Object get(Object var1);

   Object put(Object var1, Object var2);

   Object putIfAbsent(Object var1, Object var2);

   void clear();
}
