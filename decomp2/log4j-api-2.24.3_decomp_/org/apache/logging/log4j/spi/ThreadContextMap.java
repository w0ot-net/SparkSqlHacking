package org.apache.logging.log4j.spi;

import java.util.Map;

public interface ThreadContextMap {
   void clear();

   boolean containsKey(final String key);

   String get(final String key);

   Map getCopy();

   Map getImmutableMapOrNull();

   boolean isEmpty();

   void put(final String key, final String value);

   void remove(final String key);
}
